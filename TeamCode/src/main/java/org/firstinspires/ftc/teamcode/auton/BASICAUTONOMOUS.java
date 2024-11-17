package org.firstinspires.ftc.teamcode.auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Projects.HWMap;

@Autonomous(name = "why dont you guys just use xbox controllers")

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
//            strafeLeft(0.1);
            tile(0.85);
            slide("high", 1);
            specimen();
//            tile(1.5);
//            specimen("high");

        }
        if (Alliance == Parking.rRed) {
            strafeLeft(0.1);
            tile(1.2);
//            tile(1.5);
//            specimen("high");
        }

        if (Alliance == Parking.lRed) {
//            strafeRight(0.1);
//            tile(1.6);
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
        robot.frontLeftDrive.setPower(-0.7);
        robot.frontRightDrive.setPower(0.7);
        robot.backRightDrive.setPower(-0.7);
        robot.backLeftDrive.setPower(0.7);
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
        robot.frontRightDrive.setTargetPosition((int)(fright + 1000 * tileNum));
        robot.frontLeftDrive.setTargetPosition((int)(fleft + 1000 * tileNum));
        robot.backRightDrive.setTargetPosition((int)(bright + 1000 * tileNum));
        robot.backLeftDrive.setTargetPosition((int)(bleft + 1000 * tileNum));
        robot.frontRightDrive.setPower(0.9);
        robot.frontLeftDrive.setPower(1);
        robot.backRightDrive.setPower(0.9);
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
    public void slide(String height, Integer dir){
        double slidePos1 = robot.LslideMotor.getCurrentPosition();
        double slidePos2 = robot.RslideMotor.getCurrentPosition();
        if (height == "low") {
            robot.LslideMotor.setTargetPosition(dir * 10);
            robot.RslideMotor.setTargetPosition(dir * 10);
        }
        else{
            robot.LslideMotor.setTargetPosition(dir * 6000);
            robot.RslideMotor.setTargetPosition(dir * 6000);
        }

        robot.LslideMotor.setPower(1 * dir);
        robot.RslideMotor.setPower(1 * dir);
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
        robot.wristServo.setPosition(1);
        sleep(1000);
        robot.clawServo.setPosition(1);
        sleep(1000);
        robot.clawServo.setPosition(0);
        sleep(1000);
        tile(-0.2);
        sleep(1000);
        robot.wristServo.setPosition(0);
    }


}

