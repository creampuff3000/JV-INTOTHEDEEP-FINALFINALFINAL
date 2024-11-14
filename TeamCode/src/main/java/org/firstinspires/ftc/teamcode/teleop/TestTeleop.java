package org.firstinspires.ftc.teamcode.teleop;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Projects.HWMap;

@TeleOp(name = "dasaniwater")
public class TestTeleop extends LinearOpMode {
    public HWMap robot = new HWMap();

    //rthrhyrjytjutyjjdfSDJKasdasdasdF:KLDSJF:LSKDFJajflajldfsjf;lkaskdfj;jsadsdfjks;adfalkj;klfsjdf;adsjg;klsajl;jakfsdfjdsalkfjdsalkjfiuhiuhiuhuhuhuhuhuhuhuhuhuhuhuhuhutyler
    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);
        waitForStart();
        boolean isSpinning = false;
        double speed = 1;
        boolean slowMode = false;
        boolean slowModeToggle = false;

        while (opModeIsActive()) {


            boolean aButtonHeld = false;
            double y = -gamepad1.right_stick_y; // Remember, this is reversed!
            double x = gamepad1.right_stick_x * 1.1; // Counteract imperfect strafing (suspicious)
            double rx = gamepad1.left_stick_x;

            // Denominator is the largest motor power (absolute value) or 1jh
            // This ensures all the powers maintain the same ratio, but only whenoiajhsdouhjouhjoijqoushdouahsduih
            // at least one is out of the range [-1, 1]
            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            double frontLeftPower = (y + x + rx) / denominator;
            double backLeftPower = (y - x + rx) / denominator;
            double frontRightPower = (y - x - rx) / denominator;
            double backRightPower = (y + x - rx) / denominator;

            robot.frontLeftDrive.setPower(frontLeftPower * speed);
            robot.backLeftDrive.setPower(backLeftPower * speed);
            robot.frontRightDrive.setPower(frontRightPower * speed);
            robot.backRightDrive.setPower(backRightPower * speed);

            //start coding here

//            if(gamepad2.y == true){
//                robot.RelbowMotor.setPower(1);
//            }
//            else if (gamepad2.a == true){
//                robot.RelbowMotor.setPower(-1);
//            }
//            else {
//                robot.RelbowMotor.setPower(0);
//            }

            if (robot.clawServo.getPosition() == 1 && gamepad2.x == true) {
                robot.clawServo.setPosition(0);
                sleep(500);
            } else if (robot.clawServo.getPosition() == 0 && gamepad2.x == true) {
                robot.clawServo.setPosition(1);
                sleep(500);
            }
            if (gamepad2.dpad_up == true) {
                robot.LslideMotor.setPower(1);
                robot.RslideMotor.setPower(1);

            } else if (gamepad2.dpad_down == true) {
                robot.LslideMotor.setPower(-1);
                robot.RslideMotor.setPower(-1);

            } else {
                robot.RslideMotor.setPower(0);
                robot.LslideMotor.setPower(0);
            }

            if (gamepad2.y == true) {
                robot.RelbowMotor.setPower(-0.5);
                robot.RelbowMotor.setTargetPosition(0);
            }

            if (gamepad2.a == true) {
                int rVal = robot.RelbowMotor.getCurrentPosition();
                telemetry.addLine("mPos: " + rVal);
                telemetry.update();
                int diff1 = 100 - rVal;
                if (rVal > 100){
                    robot.RelbowMotor.setTargetPosition(rVal - Math.abs(diff1));
                    robot.RelbowMotor.setPower(0.5);
                    robot.RelbowMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    while (robot.RelbowMotor.isBusy()) {

                    }
                    robot.RelbowMotor.setPower(0.2);
                    robot.RelbowMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                }
                if (rVal < 100){
                    robot.RelbowMotor.setTargetPosition(rVal + Math.abs(diff1));
                    robot.RelbowMotor.setPower(0.5);
                    robot.RelbowMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    while (robot.RelbowMotor.isBusy()) {

                    }
                    robot.RelbowMotor.setPower(0.2);
                    robot.RelbowMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                }
                telemetry.addLine("Lift encoder right side: " + robot.RelbowMotor.getCurrentPosition());
                telemetry.update();
            }
//            if (gamepad2.y == true) {
//                int rVal = robot.RelbowMotor.getCurrentPosition();
//                int diff1 = 1200 - rVal;
//                robot.RelbowMotor.setPower(1);
//                if (rVal > 1200){
//                    robot.RelbowMotor.setTargetPosition(rVal - Math.abs(diff1));
//                }
//                if (rVal < 1200){
//                    robot.RelbowMotor.setTargetPosition(rVal + Math.abs(diff1));
//                }
//                telemetry.addLine("Lift encoder right side: " + robot.RelbowMotor.getCurrentPosition());
//                telemetry.update();
//            }
//            if (gamepad2.y == true) {
//                int rVal = robot.slideMotor.getCurrentPosition();
//                int diff1 = 2800 - rVal;
//                robot.slideMotor.setPower(1);
//                if (rVal > 2800){
//                    robot.slideMotor.setTargetPosition(rVal - Math.abs(diff1));
//                }
//                if (rVal < 2800){
//                    robot.slideMotor.setTargetPosition(rVal + Math.abs(diff1));
//                }
//                telemetry.addLine("Lift encoder right side: " + robot.slideMotor.getCurrentPosition());
//                telemetry.update();
//            }
//            if (gamepad2.dpad_left == true) {
//                int rVal = robot.slideMotor.getCurrentPosition();
//                int diff1 = 3200 - rVal;
//                robot.slideMotor.setPower(1);
//                if (rVal > 3200){
//                    robot.slideMotor.setTargetPosition(rVal - Math.abs(diff1));
//                }
//                if (rVal < 3200){
//                    robot.slideMotor.setTargetPosition(rVal + Math.abs(diff1));
//                }
//                telemetry.addLine("Lift encoder right side: " + robot.slideMotor.getCurrentPosition());
//                telemetry.update();
//            }

            if (robot.wristServo.getPosition() == 1 && gamepad2.b == true){
                robot.wristServo.setPosition(0);
                sleep(500);
            } else if (robot.wristServo.getPosition() == 0 && gamepad2.b == true){
                robot.wristServo.setPosition(1);
                sleep(500);
            }
                }
            }
        }