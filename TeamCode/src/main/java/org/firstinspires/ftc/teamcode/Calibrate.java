package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp
public class Calibrate extends LinearOpMode {
    DcMotor leftMotor;
    DcMotor rightMotor;

    @Override
    public void runOpMode() throws InterruptedException {
        leftMotor = hardwareMap.get(DcMotor.class, "leftMotor");
        rightMotor = hardwareMap.get(DcMotor.class, "rightMotor");
        leftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        // Reset the encoder to 0
        leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        // Tells the motor to run until we turn it off
        leftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // Turn on the motors
        leftMotor.setPower(1);
        rightMotor.setPower(1);

        ElapsedTime t = new ElapsedTime();
        t.reset();

        while(opModeIsActive() && t.seconds() < 2 ) {
            // Get current position = how many ticks rotated
            telemetry.addData("position ", leftMotor.getCurrentPosition());
            telemetry.update();
        }
        leftMotor.setPower(0.0);
        rightMotor.setPower(0.0);

        while(opModeIsActive()){
        }
    }
}
