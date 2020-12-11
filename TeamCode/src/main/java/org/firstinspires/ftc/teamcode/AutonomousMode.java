package org.firstinspires.ftc.teamcode;

import android.graphics.Color;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;

@Autonomous
public class AutonomousMode extends LinearOpMode {
    DcMotor leftBack;
    DcMotor leftFront;
    DcMotor rightBack;
    DcMotor rightFront;
    NormalizedColorSensor sensorColor;
    Gyro2 gyro;

    float ticksPerInch = -122.15f;

    @Override
    public void runOpMode() throws InterruptedException {
        BNO055IMU imu = hardwareMap.get(BNO055IMU.class, "imu");
        gyro = new Gyro2(imu, this);
        Graph graph = new Graph(this);
        graph.turnOn();

        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        sensorColor = hardwareMap.get(NormalizedColorSensor.class, "sensor_color");
        leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftBack.setDirection(DcMotorSimple.Direction.REVERSE);
        rightBack.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();
        gyro.startGyro();

        //turnLeft (200, 1);
        
        //turnRight(200, 1);

        //forwardColor(1.0);

        //forward(1.0, 64);
        // 64

        //TODO: Shoot 3 rings
        //sleep(3000);

        // Park on the line
        //forward(1.0, 12);

        forward(1, 108);
        GraphResult result = graph.getPosition();
        if(result.imageSee) {
            telemetry.addData("x",result.x);
            telemetry.addData("y",result.y);
            telemetry.addData("orientation",result.orientation);

            // TODO: if result.orientation > 5 degrees then turn right result.orientation degrees
            if(result.orientation > 5) {
                turnRight(result.orientation, 1);
            }

            // TODO: if result.orientation < -5 degrees then turn left abs(result.orientation degrees)
            if(result.orientation < -5) {
                turnLeft(-result.orientation, 1);
            }

            // TODO: if result.x < 36 then move forward 36 - result.x inches
            if(result.x < 36) {
                forward(1, 36-result.x);
            }


            // TODO: if result.x > 36 then move backwards result.x - 36 inches
            if(result.x > 36) {
                forward(-1, result.x-36);
            }

            // TODO: if result.y < 36 then slide left 36 - result.y
            if(result.y < 36) {
                chaChaRealSmooth(1, 36-result.y);
            }

            // TODO: if result.y > 36 then slide right result.y - 36
            if(result.y > 36) {
                chaChaRealSmooth(-1, result.y-36);
            }

        }
        else {
            telemetry.addData("no image :(", "");
        }

        graph.turnOff();
    }

    public void chaChaRealSmooth(double power, double length) {
        // Turn on motors to slide
        leftBack.setPower(-power);
        leftFront.setPower(power);
        rightBack.setPower(-power);
        rightFront.setPower(power);

        // Slide until encoder ticks are sufficient
        while(opModeIsActive()) {
            //absolute value of getCurrentPosition()
            int tics = rightBack.getCurrentPosition();
            if (tics < 0) {
                tics = tics * -1;
            }

            if (tics > length*ticksPerInch){
                break;
            }
            idle();
        }

        // Turn off motors
        leftBack.setPower(0);
        leftFront.setPower(0);
        rightBack.setPower(0);
        rightFront.setPower(0);
    }

    public void forward(double power, double length){
        // Reset the encoder to 0
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        // Tells the motor to run until we turn it off
        rightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        gyro.reset();
        // Setting the motor power based on the input
        motorsForward(1);

        // Go forward and park behind the line
        while(opModeIsActive()) {
            //absolute value of getCurrentPosition()
            int tics = rightBack.getCurrentPosition();
            if (tics < 0) {
                tics = tics * -1;
            }

            if (tics > length*ticksPerInch){
                break;
            }

            // Check the angle and correct if needed
            if (gyro.getAngle() >4) {
                gyro.store();
                turnRight(3, .3);
                gyro.recall();
                motorsForward(1);
            } else if (gyro.getAngle() <-4) {
                gyro.store();
                turnLeft(3, .3);
                gyro.recall();
                motorsForward(1);
            }


            idle();
        }

        leftBack.setPower(0);
        leftFront.setPower(0);
        rightBack.setPower(0);
        rightFront.setPower(0);
    }

    public void motorsForward( double power) {
        leftBack.setPower(-power);
        leftFront.setPower(-power);
        rightBack.setPower(power);
        rightFront.setPower(power);
    }

    public void forwardColor (double power){
       motorsForward(power);
        // hsvValues is an array that will hold the hue, saturation, and value information.
        float hsvValues[] = {0F, 0F, 0F};

        // Go forward and park behind the line
        while(opModeIsActive()) {
            NormalizedRGBA colors = sensorColor.getNormalizedColors();

            Color.colorToHSV(colors.toColor(), hsvValues);
            // changes rbg to hsv

            float h = hsvValues[0];
            float s = hsvValues[1];
            float v = hsvValues[2];

            if (h >= 326.2398 && h <= 337.5946 && s >= 0.46798 && s<= 0.6815 && v >= 0.0864 && v <= 0.4998)
            {
                break;
            }

            idle();
        }

        leftBack.setPower(0);
        leftFront.setPower(0);
        rightBack.setPower(0);
        rightFront.setPower(0);
    }

    public void turnRight(double howFar, double speed) {
        //gyro.resetWithDirection(Gyro.RIGHT);
        gyro.reset();
        leftBack.setPower(-speed);
        leftFront.setPower(-speed);
        rightBack.setPower(-speed);
        rightFront.setPower(-speed);

        // Go forward and park behind the line
        while(opModeIsActive()) {
            if (gyro.getAngle()<= -howFar){
                break;
            }

            idle();
        }

        leftBack.setPower(0);
        leftFront.setPower(0);
        rightBack.setPower(0);
        rightFront.setPower(0);
    }

    public void turnLeft(double howFar, double speed) {
        //gyro.resetWithDirection(Gyro.LEFT);
        gyro.reset();
        leftBack.setPower(speed);
        leftFront.setPower(speed);
        rightBack.setPower(speed);
        rightFront.setPower(speed);

        // Go forward and park behind the line
        while(opModeIsActive()) {
            if (gyro.getAngle()>= howFar){
                break;
            }

            idle();
        }

        leftBack.setPower(0);
        leftFront.setPower(0);
        rightBack.setPower(0);
        rightFront.setPower(0);
    }

}

