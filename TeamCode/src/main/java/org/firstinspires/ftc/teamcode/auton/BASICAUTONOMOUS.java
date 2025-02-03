package org.firstinspires.ftc.teamcode.auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;


import org.firstinspires.ftc.teamcode.Projects.HWMap;

@Autonomous(name = "CurrentAutonomous")

public class BASICAUTONOMOUS extends LinearOpMode {
    enum Parking {
        rBlue,
        lBlue,//a
        rRed,
        lRed,
    }

    Gamepad currentGamepad1 = new Gamepad();
    Gamepad previousGamepad1 = new Gamepad();
    public HWMap robot = new HWMap();

    @Override
    public void runOpMode() throws InterruptedException {
        //initialize hardware map

        robot.init(hardwareMap);
        Parking Alliance = Parking.rBlue;


        int direction = 1;
        int otherDirection = -1;

        boolean isrBlue = false;
        boolean islBlue = false;
        boolean isrRed = false;
        boolean islRed = false;
        // hi
        // Autonomous code starts here

        while (!isStarted()) {
            // uygiufougoijpiuhitfjfstuhhdstersuyrukuy;oyg
            previousGamepad1.copy(currentGamepad1);
            currentGamepad1.copy(gamepad1);

            if (currentGamepad1.right_bumper && !previousGamepad1.right_bumper) {

                isrBlue = true;
                islBlue = false;
                isrRed = false;
                islRed = false;
            }
            if (currentGamepad1.left_bumper && !previousGamepad1.left_bumper) {

                isrBlue = false;
                islBlue = true;
                isrRed = false;
                islRed = false;
            }
            if (currentGamepad1.left_trigger != 0) {

                isrBlue = false;
                islBlue = false;
                isrRed = false;
                islRed = true;
            }
            if (currentGamepad1.right_trigger != 0) {

                isrBlue = false;
                islBlue = false;
                isrRed = true;
                islRed = false;
            }

            if (isrBlue) {

                Alliance = Parking.lBlue;
            }
            if (islBlue) {

                Alliance = Parking.rRed;
            }
            if (isrRed) {

                Alliance = Parking.lRed;
            }
            if (islRed) {

                Alliance = Parking.rBlue;
            }
            telemetry.addData("Parking", Alliance);
            telemetry.update();
        }
        waitForStart(); //wait for play button to be pressed

        if (Alliance == Parking.rBlue) {
            strafeLeft(0.1);
            tile(1.7);
//            tile(1.5);
//            specimen("high");

        }
        if (Alliance == Parking.rRed) {
            tile(1.2);
            slide("high", 1);
            sleep(5000);
            robot.RelbowMotor.setPower(-0.83);
            sleep(210);
            robot.RelbowMotor.setPower(0);
            sleep(5000);
            robot.clawServo.setPosition(0.4);
            tile(-0.3);
//            specimen();
//            strafeLeft(0.1);
//            tile(1.2);
//            tile(1.5);
//            specimen("high");

        }

        if (Alliance == Parking.lRed) {
            robot.RelbowMotor.setPower(1);
            sleep(1000);
            slide("high", 1);
            sleep(1000);
            robot.RslideMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.LslideMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            while (robot.DistSensor.getDistance(DistanceUnit.CM) > 16){
                robot.frontRightDrive.setPower(0.5);
                robot.frontLeftDrive.setPower(0.5);
                robot.backRightDrive.setPower(0.5);
                robot.backLeftDrive.setPower(0.5);
            }
            robot.frontRightDrive.setPower(0);
            robot.frontLeftDrive.setPower(0);
            robot.backRightDrive.setPower(0);
            robot.backLeftDrive.setPower(0);
            sleep(1000);
            robot.RslideMotor.setTargetPosition(robot.RslideMotor.getCurrentPosition() - 1000);
            robot.LslideMotor.setTargetPosition(robot.LslideMotor.getCurrentPosition() - 1000);
            robot.RslideMotor.setPower(0.8);
            robot.LslideMotor.setPower(0.8);
            robot.LslideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.RslideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            while (robot.LslideMotor.isBusy() && robot.RslideMotor.isBusy() ) {

            }
            robot.RslideMotor.setPower(0);
            robot.LslideMotor.setPower(0);
            sleep(1000);
            robot.clawServo.setPosition(0.4);
            sleep(1000);
            tile(-0.5);
        }

        if (Alliance == Parking.lBlue) {
//            strafeRight(0.1);
//            tile(1.6);
        }
    }
    public void strafeRight(double tileNum)
    {
        int fleft = robot.frontLeftDrive.getCurrentPosition();
        int bleft = robot.backLeftDrive.getCurrentPosition();
        int bright = robot.backRightDrive.getCurrentPosition();
        int fright = robot.frontRightDrive.getCurrentPosition();
        robot.frontLeftDrive.setTargetPosition((int) (fleft + 1300 * tileNum));
        robot.frontRightDrive.setTargetPosition((int) (fright - 1300 * tileNum));;
        robot.backLeftDrive.setTargetPosition((int) (bleft - 1300 * tileNum));
        robot.backRightDrive.setTargetPosition((int)(bright + 1300 * tileNum));
        robot.frontLeftDrive.setPower(-1);
        robot.frontRightDrive.setPower(1);
        robot.backRightDrive.setPower(-1);
        robot.backLeftDrive.setPower(1);
        robot.frontRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.frontLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.backRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.backLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        while (robot.backLeftDrive.isBusy() && robot.backRightDrive.isBusy() && robot.frontLeftDrive.isBusy() && robot.frontRightDrive.isBusy()) {

        }
        robot.backRightDrive.setPower(0);
        robot.backLeftDrive.setPower(0);
        robot.frontRightDrive.setPower(0);
        robot.frontLeftDrive.setPower(0);
        robot.frontRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.frontLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.backRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.backLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    public void strafeLeft(double tileNum)
    {
        int fleft = robot.frontLeftDrive.getCurrentPosition();
        int bleft = robot.backLeftDrive.getCurrentPosition();
        int bright = robot.backRightDrive.getCurrentPosition();
        int fright = robot.frontRightDrive.getCurrentPosition();
        robot.frontLeftDrive.setTargetPosition((int) (fleft - 1300 * tileNum));
        robot.frontRightDrive.setTargetPosition((int) (fright + 1300 * tileNum));;
        robot.backLeftDrive.setTargetPosition((int) (bleft + 1300 * tileNum));
        robot.backRightDrive.setTargetPosition((int)(bright - 1300 * tileNum));
        robot.frontLeftDrive.setPower(-0.3);
        robot.frontRightDrive.setPower(0.3);
        robot.backRightDrive.setPower(-0.3);
        robot.backLeftDrive.setPower(0.3);
        robot.frontRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.frontLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.backRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.backLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        while (robot.backLeftDrive.isBusy() && robot.backRightDrive.isBusy() && robot.frontLeftDrive.isBusy() && robot.frontRightDrive.isBusy()) {

        }
        robot.backRightDrive.setPower(0);
        robot.backLeftDrive.setPower(0);
        robot.frontRightDrive.setPower(0);
        robot.frontLeftDrive.setPower(0);
        robot.frontRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.frontLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.backRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.backLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    public void slowTile(double tileNum)
    {
        int fright = robot.frontRightDrive.getCurrentPosition();
        int fleft = robot.frontLeftDrive.getCurrentPosition();
        int bright = robot.backRightDrive.getCurrentPosition();
        int bleft = robot.backLeftDrive.getCurrentPosition();
        robot.frontRightDrive.setTargetPosition((int)(fright + 1000 * tileNum));
        robot.frontLeftDrive.setTargetPosition((int)(fleft + 1000 * tileNum));
        robot.backRightDrive.setTargetPosition((int)(bright + 1000 * tileNum));
        robot.backLeftDrive.setTargetPosition((int)(bleft + 1000 * tileNum));
        robot.frontRightDrive.setPower(0.2);
        robot.frontLeftDrive.setPower(0.2);
        robot.backRightDrive.setPower(0.2);
        robot.backLeftDrive.setPower(0.2);
        robot.frontRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.frontLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.backRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.backLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        while (robot.backLeftDrive.isBusy() && robot.backRightDrive.isBusy() && robot.frontLeftDrive.isBusy() && robot.frontRightDrive.isBusy()) {

        }
        robot.backRightDrive.setPower(0);
        robot.backLeftDrive.setPower(0);
        robot.frontRightDrive.setPower(0);
        robot.frontLeftDrive.setPower(0);
        robot.frontRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.frontLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.backRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.backLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    public void tile(double tileNum)
    {
        int fright = robot.frontRightDrive.getCurrentPosition();
        int fleft = robot.frontLeftDrive.getCurrentPosition();
        int bright = robot.backRightDrive.getCurrentPosition();
        int bleft = robot.backLeftDrive.getCurrentPosition();
        robot.frontRightDrive.setTargetPosition((int)(fright + 760 * tileNum));
        robot.frontLeftDrive.setTargetPosition((int)(fleft + 800 * tileNum));
        robot.backRightDrive.setTargetPosition((int)(bright + 760 * tileNum));
        robot.backLeftDrive.setTargetPosition((int)(bleft + 800 * tileNum));
        robot.frontRightDrive.setPower(0.7);
        robot.frontLeftDrive.setPower(0.9);
        robot.backRightDrive.setPower(0.7);
        robot.backLeftDrive.setPower(0.9);
        robot.frontRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.frontLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.backRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.backLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        while (robot.backLeftDrive.isBusy() && robot.backRightDrive.isBusy() && robot.frontLeftDrive.isBusy() && robot.frontRightDrive.isBusy()) {

        }
        robot.backRightDrive.setPower(0);
        robot.backLeftDrive.setPower(0);
        robot.frontRightDrive.setPower(0);
        robot.frontLeftDrive.setPower(0);
        robot.frontRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.frontLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.backRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.backLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    public void slide(String height, Integer pwr){
        if (height == "low") {
            robot.LslideMotor.setTargetPosition(pwr * 10);
            robot.RslideMotor.setTargetPosition(pwr * 10);
        }
        if (height == "down"){
            robot.LslideMotor.setTargetPosition(0);
            robot.RslideMotor.setTargetPosition(0);
        }
        else{
            robot.LslideMotor.setTargetPosition(pwr * 1500);
            robot.RslideMotor.setTargetPosition(pwr * 1500);
        }

        robot.LslideMotor.setPower(1 * pwr);
        robot.RslideMotor.setPower(1 * pwr);
        robot.LslideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.RslideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        while (robot.LslideMotor.isBusy() && robot.RslideMotor.isBusy() ) {

        }
        robot.RslideMotor.setPower(0);
        robot.LslideMotor.setPower(0);
        robot.RslideMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.LslideMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    public void specimen(){
        tile(0.1);
        robot.RelbowMotor.setPower(0.23);
        sleep(500);
//        robot.wristServo.setPosition(1);
        sleep(1000);
        tile(-0.3);
        sleep(1000);
    }


}

