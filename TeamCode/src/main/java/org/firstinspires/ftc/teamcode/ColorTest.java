package org.firstinspires.ftc.teamcode;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;

@Autonomous
public class ColorTest extends LinearOpMode {
    NormalizedColorSensor sensorColor;
    @Override
    public void runOpMode() throws InterruptedException {
        sensorColor = hardwareMap.get(NormalizedColorSensor.class, "sensor_color");
            // hsvValues is an array that will hold the hue, saturation, and value information.
            float hsvValues[] = {0F, 0F, 0F};
            waitForStart();

            // Go forward and park behind the line
            while(opModeIsActive()) {
                NormalizedRGBA colors = sensorColor.getNormalizedColors();

                Color.colorToHSV(colors.toColor(), hsvValues);
                // changes rbg to hsv

                float h = hsvValues[0];
                float s = hsvValues[1];
                float v = hsvValues[2];

                if (h >= 326.2398 && h <= 337.5946 && s >= 0.46798 && s<= 0.6815 && v >= 0.0864 && v <= 0.4998) {
                    break;
                }
                telemetry.addData("status","lookingfortheline");
                telemetry.update();
                idle();
            }

    }
}
