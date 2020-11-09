package org.firstinspires.ftc.teamcode;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;

@Autonomous
public class AutonomousMode extends LinearOpMode {
    DcMotor leftMotor;
    DcMotor rightMotor;
    NormalizedColorSensor sensorColor;
    float ticksPerInch = -102.2f;

    @Override
    public void runOpMode() throws InterruptedException {
        leftMotor = hardwareMap.get(DcMotor.class, "leftMotor");
        rightMotor = hardwareMap.get(DcMotor.class, "rightMotor");
        sensorColor = hardwareMap.get(NormalizedColorSensor.class, "sensor_color");
        leftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        leftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        waitForStart();

        forward(1.0, 64);

        // TODO: Shoot 3 rings
        sleep(3000);

        // Park on the line
        forward(1.0, 12);
    }

    public void forward(double power, double length){
        // Reset the encoder to 0
        leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        // Tells the motor to run until we turn it off
        leftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        leftMotor.setPower(power);
        rightMotor.setPower(power);

        // Go forward and park behind the line
        while(opModeIsActive()) {
            if (leftMotor.getCurrentPosition() < length*ticksPerInch){
                break;
            }

            idle();
        }
        leftMotor.setPower(0.0);
        rightMotor.setPower(0.0);
    }

    public void forwardColor (double power){
        leftMotor.setPower(power);
        rightMotor.setPower(power);
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
        leftMotor.setPower(0.0);
        rightMotor.setPower(0.0);
    }



}

