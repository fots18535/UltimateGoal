package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class Steer extends LinearOpMode {
    DcMotor leftMotor;
    DcMotor rightMotor;

    @Override
    public void runOpMode() throws InterruptedException {
        leftMotor = hardwareMap.get(DcMotor.class, "leftMotor");
        rightMotor = hardwareMap.get(DcMotor.class, "rightMotor");
        leftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        while(opModeIsActive()) {

            float y = gamepad1.right_stick_y;
            leftMotor.setPower(y);
            rightMotor.setPower(y);

            float x = gamepad1.right_stick_x;
            leftMotor.setPower(x);
            rightMotor.setPower(x);


            idle();
        }
        leftMotor.setPower(0.0);
        rightMotor.setPower(0.0);
    }
}
