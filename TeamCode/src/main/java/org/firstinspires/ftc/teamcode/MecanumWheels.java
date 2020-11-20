package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class MecanumWheels extends LinearOpMode {
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

        while (opModeIsActive()) {
            //Get the input from the gamepad controller
           double leftX =   gamepad1.left_stick_x;
           double leftY =   gamepad1.left_stick_y;
           double rightX =  -gamepad1.right_stick_x;
           double rightY =  gamepad1.right_stick_y;

           if (gamepad1.a) {
               leftBack.setPower(1);
           } else {
               leftBack.setPower(0);
           }


           // Setting the motor power based on the input
           leftBack.setPower(rightX + rightY + leftX);
           leftFront.setPower(rightX + rightY - leftX);
           rightBack.setPower(rightX - rightY + leftX);
           rightFront.setPower(rightX - rightY - leftX);
        }
        // Stop the motors
        leftBack.setPower(0);
        leftFront.setPower(0);
        rightBack.setPower(0);
        rightFront.setPower(0);

    }
}