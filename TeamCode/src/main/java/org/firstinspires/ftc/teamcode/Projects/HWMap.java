package org.firstinspires.ftc.teamcode.Projects;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

//hyubihyba
public class HWMap extends Project{
    public DcMotor  frontLeftDrive   = null;
    public DcMotor  frontRightDrive  = null;
    public DcMotor  backLeftDrive = null;
    public DcMotor  backRightDrive     = null;
    public Servo LelbowServo = null;
    public Servo RelbowServo = null;
    public DcMotor RslideMotor = null;
    public DcMotor LslideMotor = null;
    public Servo clawServo = null;
    public Servo wristServo = null;

    @Override
    public void init(HardwareMap hwMap) {
        // Define and Initialize Motors

        frontLeftDrive  = hwMap.get(DcMotor.class, "frontLeftDrive");
        frontRightDrive = hwMap.get(DcMotor.class, "frontRightDrive");
        backLeftDrive = hwMap.get(DcMotor.class, "backLeftDrive");
        backRightDrive = hwMap.get(DcMotor.class, "backRightDrive");
        RslideMotor = hwMap.get(DcMotor.class, "RslideMotor");
        LslideMotor = hwMap.get(DcMotor.class, "LslideMotor");
        clawServo = hwMap.get(Servo.class, "clawServo");
        wristServo = hwMap.get(Servo.class, "wristServo");
        RelbowServo = hwMap.get(Servo.class, "RelbowServo");
        LelbowServo = hwMap.get(Servo.class, "LelbowServo");
        frontLeftDrive.setDirection(DcMotor.Direction.FORWARD);
        frontRightDrive.setDirection(DcMotor.Direction.REVERSE);
        backLeftDrive.setDirection(DcMotor.Direction.FORWARD);
        backRightDrive.setDirection(DcMotor.Direction.REVERSE);
        RslideMotor.setDirection(DcMotor.Direction.FORWARD);
        LslideMotor.setDirection(DcMotor.Direction.REVERSE);
        RelbowServo.setDirection(Servo.Direction.FORWARD);
        LelbowServo.setDirection(Servo.Direction.REVERSE);
//        change prob
//        also change prob
        frontRightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontLeftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        slideMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        slideMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        camera = hwMap.get(WebcamName.class, "webcam");
        Stop();
    }
    public void Stop(){
        frontRightDrive.setPower(0);
        frontLeftDrive.setPower(0);
        backRightDrive.setPower(0);
        backLeftDrive.setPower(0);
//        elbowMotor.setPower(0);
    }
}
