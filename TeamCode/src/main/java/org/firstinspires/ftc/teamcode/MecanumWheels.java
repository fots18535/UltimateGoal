
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class MecanumWheels extends LinearOpMode {
    DcMotor leftBack;
    DcMotor leftFront;
    DcMotor rightBack;
    DcMotor rightFront;
    DcMotor rollers;
    DcMotor thrower;
    Servo poddle;
    Servo wrist;
    DcMotor americaForever;

    @Override
    public void runOpMode() throws InterruptedException {
        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        thrower = hardwareMap.get(DcMotor.class, "thrower");
        rollers = hardwareMap.get(DcMotor.class, "rollers");
        poddle = hardwareMap.get(Servo.class, "poddle");
        wrist = hardwareMap.get(Servo.class, "wrist");
        americaForever = hardwareMap.get(DcMotor.class, "americaForever");


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
               thrower.setPower(1);
           } else {
               thrower.setPower(0);
           }


            if (gamepad1.b) {
                rollers.setPower(1.0);
            } else {
                rollers.setPower(0);
            }

            if (gamepad1.right_bumper) {
                poddle.setPosition(0.5);
                sleep(200);
                poddle.setPosition(0.0);
            }

            if(gamepad1.left_bumper) {
                wrist.setPosition(0.0);
            }
            else {
                wrist.setPosition(0.5);
            }

            if(gamepad1.dpad_left) {
                americaForever.setPower(-0.2);
            }
            else if (gamepad1.dpad_right) {
                americaForever.setPower(0.2);
            }
            else {
                americaForever.setPower(0.0);
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