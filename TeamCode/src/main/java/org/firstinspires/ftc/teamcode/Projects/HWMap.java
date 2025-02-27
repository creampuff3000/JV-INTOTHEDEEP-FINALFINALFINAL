package org.firstinspires.ftc.teamcode.Projects;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

//hyubihyba
public class HWMap extends Project{
    public DcMotor  frontLeftDrive   = null;
    public DcMotor  frontRightDrive  = null;
    public DcMotor  backLeftDrive = null;
    public DcMotor  backRightDrive     = null;
    public DcMotor RelbowMotor = null;
    public DcMotor RslideMotor = null;
    public DcMotor LslideMotor = null;
    public Servo clawServo = null;
    public DistanceSensor DistSensor = null;
//    public Servo wristServo = null;

    @Override
    public void init(HardwareMap hwMap) {
        // Define and Initialize Motors

        frontLeftDrive  = hwMap.get(DcMotor.class, "frontLeftDrive");
        frontRightDrive = hwMap.get(DcMotor.class, "frontRightDrive");
        backLeftDrive = hwMap.get(DcMotor.class, "backLeftDrive");
        backRightDrive = hwMap.get(DcMotor.class, "backRightDrive");
        RslideMotor = hwMap.get(DcMotor.class, "RslideMotor");
        LslideMotor = hwMap.get(DcMotor.class, "LslideMotor");
        DistSensor = hwMap.get(DistanceSensor.class, "DistSensor");
        clawServo = hwMap.get(Servo.class, "clawServo");
//        wristServo = hwMap.get(Servo.class, "wristServo");
        RelbowMotor = hwMap.get(DcMotor.class, "RelbowMotor");
        frontLeftDrive.setDirection(DcMotor.Direction.FORWARD);
        frontRightDrive.setDirection(DcMotor.Direction.REVERSE);
        backLeftDrive.setDirection(DcMotor.Direction.FORWARD);
        backRightDrive.setDirection(DcMotor.Direction.REVERSE);
        RslideMotor.setDirection(DcMotor.Direction.FORWARD);
        LslideMotor.setDirection(DcMotor.Direction.REVERSE);
        RelbowMotor.setDirection(DcMotor.Direction.FORWARD);
//        change prob
//        also change prob
        frontRightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontLeftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        RslideMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        LslideMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        RelbowMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

//        slideMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RslideMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        LslideMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RelbowMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

//        slideMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        camera = hwMap.get(WebcamName.class, "webcam");
        Stop();
    }
    public void Stop(){
        frontRightDrive.setPower(0);
        frontLeftDrive.setPower(0);
        backRightDrive.setPower(0);
        backLeftDrive.setPower(0);
        RslideMotor.setPower(0);
        LslideMotor.setPower(0);
        RelbowMotor.setPower(0.5);
        clawServo.setPosition(0.65);
//        wristServo.setPosition(1);
    }
}
