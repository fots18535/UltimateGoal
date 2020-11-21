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
    Gyro gyro;

    float ticksPerInch = -122.15f;

    @Override
    public void runOpMode() throws InterruptedException {
        BNO055IMU imu = hardwareMap.get(BNO055IMU.class, "imu");
        gyro = new Gyro(imu, this);

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
        gyro.StartGyro();

        turnLeft (200, 1);
        
       // turnRight(100, 1);

      //  forwardColor(1.0);

        //forward(1.0, 24);
        // 64

        // TODO: Shoot 3 rings
        //sleep(3000);

        // Park on the line
        //forward(1.0, 12);
    }

    public void forward(double power, double length){
        // Reset the encoder to 0
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        // Tells the motor to run until we turn it off
        rightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // Setting the motor power based on the input
        leftBack.setPower(1);
        leftFront.setPower(1);
        rightBack.setPower(-1);
        rightFront.setPower(-1);


        // Go forward and park behind the line
        while(opModeIsActive()) {
            if (rightBack.getCurrentPosition() < length*ticksPerInch){
                break;
            }

            idle();
        }

        leftBack.setPower(0);
        leftFront.setPower(0);
        rightBack.setPower(0);
        rightFront.setPower(0);
    }

    public void forwardColor (double power){
        leftBack.setPower(1);
        leftFront.setPower(1);
        rightBack.setPower(-1);
        rightFront.setPower(-1);
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
        gyro.resetWithDirection(Gyro.RIGHT);
        leftBack.setPower(-1);
        leftFront.setPower(-1);
        rightBack.setPower(-1);
        rightFront.setPower(-1);

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
        gyro.resetWithDirection(Gyro.LEFT);
        leftBack.setPower(1);
        leftFront.setPower(1);
        rightBack.setPower(1);
        rightFront.setPower(1);

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

