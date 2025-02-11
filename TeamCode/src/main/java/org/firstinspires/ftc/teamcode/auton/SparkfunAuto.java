package org.firstinspires.ftc.teamcode.auton;

import com.qualcomm.hardware.sparkfun.SparkFunOTOS;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@Autonomous(name = "Autonomous with OTOS", group = "Autonomous")
public class SparkfunAuto extends LinearOpMode {

    SparkFunOTOS myOtos;

    @Override
    public void runOpMode() throws InterruptedException{
        // Initialize the OTOS sensor
        myOtos = hardwareMap.get(SparkFunOTOS.class, "sensor_otos");
        configureOtos();

        telemetry.addLine("Waiting for start...");
        telemetry.update();

        // Wait for the start signal
        waitForStart();

        // Reset tracking to start at (0, 0)
        myOtos.resetTracking();

        // Example: Move to target (X: 24, Y: 24) while maintaining a heading of 90 degrees
        navigateToTarget(24, 24, 90);

        telemetry.addLine("Navigation complete!");
        telemetry.update();
    }

    private void configureOtos() {
        // Configure OTOS (same as in the TeleOp example)
        myOtos.setLinearUnit(DistanceUnit.INCH);
        myOtos.setAngularUnit(AngleUnit.DEGREES);
        myOtos.setOffset(new SparkFunOTOS.Pose2D(0, 3.95, 0));
        myOtos.setLinearScalar(1.0);
        myOtos.setAngularScalar(1.0);
        myOtos.calibrateImu();
        myOtos.resetTracking();
    }

    private void navigateToTarget(double targetX, double targetY, double targetHeading) {
        double errorTolerance = 1.0; // Acceptable error in inches
        double headingTolerance = 5.0; // Acceptable error in degrees

        while (opModeIsActive()) {
            // Get current position
            SparkFunOTOS.Pose2D currentPos = myOtos.getPosition();
            double currentX = currentPos.x;
            double currentY = currentPos.y;
            double currentHeading = currentPos.h;

            // Calculate deltas
            double deltaX = targetX - currentX;
            double deltaY = targetY - currentY;
            double distanceToTarget = Math.sqrt(deltaX * deltaX + deltaY * deltaY);

            // Calculate heading error
            double headingError = targetHeading - currentHeading;

            telemetry.addData("Current Position", "X: %.2f, Y: %.2f, Heading: %.2f", currentX, currentY, currentHeading);
            telemetry.addData("Target Position", "X: %.2f, Y: %.2f, Heading: %.2f", targetX, targetY, targetHeading);
            telemetry.addData("Distance to Target", "%.2f", distanceToTarget);
            telemetry.addData("Heading Error", "%.2f", headingError);
            telemetry.update();

            // Stop when the robot is close enough to the target
            if (distanceToTarget < errorTolerance && Math.abs(headingError) < headingTolerance) {
                stopMotors();
                break;
            }

            // Adjust motor powers for movement
            double drivePower = Math.min(distanceToTarget / 10, 0.5); // Scale power
            double turnPower = Math.min(headingError / 45, 0.3); // Scale turn power

            moveRobot(drivePower, turnPower);

            // Small delay to prevent excessive updates
            sleep(50);
        }
    }

    private void moveRobot(double drivePower, double turnPower) {
        // Example motor control logic (adjust for your drivetrain)
        double leftPower = drivePower - turnPower;
        double rightPower = drivePower + turnPower;

        // Assuming you have access to motor objects

    }

    private void stopMotors() {
        // Example motor stop logic
        // leftMotor.setPower(0);
        // rightMotor.setPower(0);
    }
}
