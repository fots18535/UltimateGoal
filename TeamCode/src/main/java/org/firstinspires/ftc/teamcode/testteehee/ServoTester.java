package org.firstinspires.ftc.teamcode.testteehee;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.HunkOfMetal;

@TeleOp(group = "testteehee")
public class ServoTester extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Servo servo = hardwareMap.get(Servo.class, "port0");

        waitForStart();

        while (opModeIsActive()) {
            if(gamepad1.dpad_right) {
                servo.setPosition(1.0);
            } else if(gamepad1.dpad_left) {
                servo.setPosition(-1.0);

            }
        }

    }
}
