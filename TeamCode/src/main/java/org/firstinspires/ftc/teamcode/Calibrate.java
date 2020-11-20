package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp
public class Calibrate extends LinearOpMode {
    DcMotor leftBack;
    DcMotor leftFront;
    DcMotor rightBack;
    DcMotor rightFront;

    @Override
    public void runOpMode() throws InterruptedException {
        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");

        // Stops coasting
        leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftBack.setDirection(DcMotorSimple.Direction.REVERSE);
        rightBack.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        // Reset the encoder to 0
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        // Tells the motor to run until we turn it off
        rightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // Turn on the motors
        //Get the input from the gamepad controller
        double leftX =   0;
        double leftY =   0;
        double rightX =  0;
        double rightY =  1;


        // Setting the motor power based on the input
        leftBack.setPower(rightX + rightY + leftX);
        leftFront.setPower(rightX + rightY - leftX);
        rightBack.setPower(rightX - rightY + leftX);
        rightFront.setPower(rightX - rightY - leftX);

        ElapsedTime t = new ElapsedTime();
        t.reset();

        while(opModeIsActive() && t.seconds() < 2 ) {
            // Get current position = how many ticks rotated
            telemetry.addData("position ", rightBack.getCurrentPosition());
            telemetry.update();
        }

        leftBack.setPower(0);
        leftFront.setPower(0);
        rightBack.setPower(0);
        rightFront.setPower(0);

        while(opModeIsActive()){
        }
    }
}
