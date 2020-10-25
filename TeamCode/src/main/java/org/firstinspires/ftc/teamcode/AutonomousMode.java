package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous
public class AutonomousMode extends LinearOpMode {
    DcMotor leftMotor;
    DcMotor rightMotor;
    float ticksPerInch = -102.2f;

    @Override
    public void runOpMode() throws InterruptedException {
        leftMotor = hardwareMap.get(DcMotor.class, "leftMotor");
        rightMotor = hardwareMap.get(DcMotor.class, "rightMotor");
        leftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        leftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        waitForStart();

        // Reset the encoder to 0
        leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        // Tells the motor to run until we turn it off
        leftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        leftMotor.setPower(1);
        rightMotor.setPower(1);

        // Go forward and park behind the line
        while(opModeIsActive()) {
            if (leftMotor.getCurrentPosition() < 64*ticksPerInch){
                break;
            }

            idle();
        }
        leftMotor.setPower(0.0);
        rightMotor.setPower(0.0);

        // TODO: Shoot 3 rings
        sleep(3000);

        // Park on the line
        // Reset the encoder to 0
        leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        // Tells the motor to run until we turn it off
        leftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        leftMotor.setPower(1);
        rightMotor.setPower(1);

        // Go forward and park behind the line
        while(opModeIsActive()) {
            if (leftMotor.getCurrentPosition() < 12*ticksPerInch){
                break;
            }

            idle();
        }

        leftMotor.setPower(0.0);
        rightMotor.setPower(0.0);
    }

}
