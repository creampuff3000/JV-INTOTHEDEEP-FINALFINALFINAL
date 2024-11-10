package org.firstinspires.ftc.teamcode.auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Projects.HWMap;

@Autonomous(name = "BASICAUTONOMOUS")

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
            tile(1.5);
            specimen("high");

        }
        if (Alliance == Parking.rRed) {
            tile(1.5);
            specimen("high");
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
    public void tile(double tileNum)
    {
        int fright = robot.frontRightDrive.getCurrentPosition();
        int fleft = robot.frontLeftDrive.getCurrentPosition();
        int bright = robot.backRightDrive.getCurrentPosition();
        int bleft = robot.backLeftDrive.getCurrentPosition();
        robot.frontRightDrive.setTargetPosition((int)(fright + 1350.846 * tileNum));
        robot.frontLeftDrive.setTargetPosition((int)(fleft + 1350.846 * tileNum));
        robot.backRightDrive.setTargetPosition((int)(bright + 1350.846 * tileNum));
        robot.backLeftDrive.setTargetPosition((int)(bleft + 1350.846 * tileNum));
        robot.frontRightDrive.setPower(1);
        robot.frontLeftDrive.setPower(1);
        robot.backRightDrive.setPower(1);
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
    public void specimen(String height){
        double slidePos1 = robot.LslideMotor.getCurrentPosition();
        double slidePos2 = robot.RslideMotor.getCurrentPosition();
        if (height == "low"){
            robot.LslideMotor.setTargetPosition(10);
            robot.RslideMotor.setTargetPosition(10);
        }
        else{
            robot.LslideMotor.setTargetPosition(20);
            robot.RslideMotor.setTargetPosition(20);
        }

        robot.LslideMotor.setPower(1);
        robot.RslideMotor.setPower(1);
        robot.LslideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.RslideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        while (robot.LslideMotor.isBusy() && robot.RslideMotor.isBusy() ) {

        }
        robot.RslideMotor.setPower(0);
        robot.LslideMotor.setPower(0);
        robot.RslideMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.LslideMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

}

