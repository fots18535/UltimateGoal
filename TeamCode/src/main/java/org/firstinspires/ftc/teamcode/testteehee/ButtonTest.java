package org.firstinspires.ftc.teamcode.testteehee;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.TouchSensor;

@TeleOp
public class ButtonTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        TouchSensor cmax = hardwareMap.get(TouchSensor.class, "clawMax");
        TouchSensor cmin = hardwareMap.get(TouchSensor.class, "clawMin");


        // get a reference to our digitalTouch object.
        //DigitalChannel digitalTouch = hardwareMap.get(DigitalChannel.class, "clawMax");

        // set the digital channel to input.
        //digitalTouch.setMode(DigitalChannel.Mode.INPUT);

        waitForStart();

        while(opModeIsActive()) {
            //if (digitalTouch.getState() == true) {
            //    telemetry.addData("Digital Touch", "Is Not Pressed");
            //} else {
            //    telemetry.addData("Digital Touch", "Is Pressed");
           // }

            //telemetry.update();

            telemetry.addData("CMAX", cmax.isPressed());
            telemetry.addData("CMIN", cmin.isPressed());
            telemetry.update();
        }
    }
}
