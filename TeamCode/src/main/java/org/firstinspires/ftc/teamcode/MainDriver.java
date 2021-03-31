
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

@TeleOp
public class MainDriver extends LinearOpMode {
    DcMotor leftBack;
    DcMotor leftFront;
    DcMotor rightBack;
    DcMotor rightFront;
    DcMotor rollers;
    DcMotor thrower;
    Servo poddle;
    Servo wrist;
    DcMotor americaForever;
    TouchSensor cmax;
    TouchSensor cmin;

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
        cmax = hardwareMap.get(TouchSensor.class, "clawMax");
        cmin = hardwareMap.get(TouchSensor.class, "clawMin");

        // Stops coasting
       leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
       leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
       rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
       rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
       americaForever.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        waitForStart();

        HunkOfMetal hunk = new HunkOfMetal(this);
        hunk.initialize();

        // thrower speed calculation variables
        long oldPosition = 0;
        long newPosition = 0;
        long elapsedPosition = 0;
        long oldTime = 0;
        long newTime = 0;
        long elapsedTime = 0;
        float clicksPerMilliSecond =0;
        long scaledClicksPerSecond =0;
        boolean aButtonHeld = false;



        while (opModeIsActive()) {
            //Get the input from the gamepad controller
           double leftX =   gamepad1.left_stick_x;
           double leftY =   gamepad1.left_stick_y;
           double rightX =  -gamepad1.right_stick_x;
           double rightY =  gamepad1.right_stick_y;

            // Ring thrower controller logic
            if (gamepad1.a) {
                if (aButtonHeld==false) {
                    aButtonHeld=true;
                    oldPosition = thrower.getCurrentPosition();
                    oldTime = System.currentTimeMillis();
                }
                thrower.setPower(0.9);
                newPosition = thrower.getCurrentPosition();
                newTime = System.currentTimeMillis();
                elapsedPosition = newPosition - oldPosition;
                elapsedTime = newTime - oldTime;

                //only measure after x milliseconds
                if (elapsedTime > 1000) {
                    clicksPerMilliSecond = elapsedPosition / elapsedTime;
                    scaledClicksPerSecond = elapsedPosition * 1000 / elapsedTime;

                    telemetry.addData("Clicks", elapsedPosition);
                    telemetry.addData("Time", elapsedTime);
                    telemetry.addData("ClicksPerMilSec", clicksPerMilliSecond);
                    telemetry.addData("scaledClicksPerSecond", scaledClicksPerSecond);
                    telemetry.update();

                    oldPosition = newPosition;
                    oldTime = newTime;
                }

            } else if(gamepad1.b){
                // One Push Power Shots
                thrower.setPower(0.78);
                sleep(2000);
                hunk.throwRing();
                sleep(2000);
                hunk.chaChaRealSmooth(-1,6);
                hunk.turnRight(2,0.5);
                hunk.throwRing();
                sleep(2000);
                hunk.chaChaRealSmooth(-1,6);
                //hunk.turnRight(2,0.5);
                hunk.throwRing();
                sleep(2000);
            } else {
                thrower.setPower(0);
                aButtonHeld=false;
            }



            // Ring conveyor control logic
            if (gamepad1.dpad_up || gamepad1.right_stick_y > 0.25) {
                rollers.setPower(1.0);
            } else if (gamepad1.dpad_down) {
                rollers.setPower(-1.0);
            } else {
                rollers.setPower(0);
            }


            // Poddle control logic
            // Poddle = ring flipper (paddle)
            if (gamepad1.right_bumper) {
                poddle.setPosition(0.65);
            } else {
                poddle.setPosition(1.0);
            }

            // Wrist control logic
            // Picks up the goal
            if(gamepad2.dpad_down) {
                wrist.setPosition(0.5);
            }
            else if (gamepad2.dpad_up){
                wrist.setPosition(0.0);
            }

            // Claw control logic
            if(gamepad2.b && !cmax.isPressed()) {
                americaForever.setPower(-0.4);
            }
            else if (gamepad2.x && !cmin.isPressed()) {
                americaForever.setPower(0.4);

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