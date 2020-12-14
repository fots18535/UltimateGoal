package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class OdoTest extends LinearOpMode {
    DcMotor leftMotor;

    @Override
    public void runOpMode() throws InterruptedException {
        leftMotor = hardwareMap.get(DcMotor.class, "leftMotor");

        waitForStart();

        leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        
        leftMotor.setPower(0.5);
        while(opModeIsActive()) {
            telemetry.addData("Odom", "value %7d", leftMotor.getCurrentPosition());
            telemetry.update();
        }
        leftMotor.setPower(0.0);
    }
}
