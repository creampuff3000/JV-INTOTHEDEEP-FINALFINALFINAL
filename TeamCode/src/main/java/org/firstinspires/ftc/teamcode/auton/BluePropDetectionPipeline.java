package org.firstinspires.ftc.teamcode.auton;
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

//@Autonomous(name = "BluePropDetech")
//OpenCV to cycle a cone during autonomous
public class BluePropDetectionPipeline extends OpenCvPipeline {
    Telemetry telemetry;
    //video frame of camera, is our input for processFrame()
    Mat mat = new Mat();
//a
    public enum BluePropLocation {
        LEFT,
        MIDDLE,
        RIGHT,
        UNKNOWN,
        CLOSE
    }

    private boolean closeToPole = false;


    private BluePropLocation elementLocation;

    //defining regions of interest (ROI)
    //Divide the camera frame into three rectangles
    //rectangles are made from defining two opposite vertices of a triangle,
    //which are connected by the diagonals
    static final Rect leftROI = new Rect(
            new Point( 0, 0),
            new Point(350, 720)
    );
    static final Rect middleROI = new Rect(
            new Point( 0, 0),
            new Point(930, 720)
    );
    static final Rect rightROI = new Rect(
            new Point( 0, 0),
            new Point(1280, 720)

    );



    public BluePropDetectionPipeline(Telemetry t) { telemetry = t; }

    @Override
    public Mat processFrame(Mat input) {


        //HSV = hue(color), saturation(intensity), value (brightness)
        Imgproc.cvtColor(input, mat, Imgproc.COLOR_RGB2HSV);

        //define HSV range to identify the color blue
        Scalar lowHSV = new Scalar (95,160,90);
        Scalar highHSV = new Scalar (235,255,235);

        //applies a threshold (everything that is blue will be white,
        // everything else will be black)
        //returns a new mat with this threshold
        Core.inRange(mat,lowHSV, highHSV, mat);

        //extract regions of interest from camera frame
        //submat = sub-matrix, a portion of the original
        Mat left = mat.submat(leftROI);
        Mat middle = mat.submat(middleROI);
        Mat right = mat.submat(rightROI);

        //calculate what percentage of the ROI became white
        //(add all the pixels together, divide by its area, divide by 255)
        double leftPercentage = Core.sumElems(left).val[0] / leftROI.area() / 255;
        double middlePercentage = Core.sumElems(middle).val[0] / middleROI.area() / 255;
        double rightPercentage = Core.sumElems(right).val[0] / rightROI.area() / 255;
        double polePercentage = leftPercentage + middlePercentage + rightPercentage;

        //deallocates the Matrix data from the memory
        left.release();
        middle.release();
        right.release();

        if (Math.round(polePercentage * 100) > 60) {
            elementLocation = BluePropLocation.CLOSE;
        }
        else if(leftPercentage > middlePercentage && leftPercentage > rightPercentage){
            elementLocation = BluePropLocation.LEFT;
        }
        else if(middlePercentage > leftPercentage && middlePercentage > rightPercentage){
            elementLocation = BluePropLocation.MIDDLE;
        }
        else if(rightPercentage > leftPercentage && rightPercentage > middlePercentage){
            elementLocation = BluePropLocation.RIGHT;
        }
//        else if(Math.round(polePercentage * 100) < 60){
//            elementLocation = PropLocation.FAR;
//        }
        else{
            elementLocation = BluePropLocation.UNKNOWN;
        }
        telemetry.addData("element location: ", elementLocation );
        telemetry.addData("left percentage", Math.round(leftPercentage * 100) + "%");
        telemetry.addData("middle percentage", Math.round(middlePercentage * 100) + "%");
        telemetry.addData("right percentage", Math.round(rightPercentage * 100) + "%");
        telemetry.addData("total pole percentage", Math.round(polePercentage * 100) + "%");
        //telemetry.addData("total pole percentage", polePercentage);



        telemetry.update();
        return mat;

    }

    public BluePropLocation getPropLocation(){
        return elementLocation;
    }

}