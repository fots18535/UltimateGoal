package org.firstinspires.ftc.teamcode;

import android.graphics.Color;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;

@Autonomous
public class AutonomousSimple extends LinearOpMode {
    HunkOfMetal hunk;

    @Override
    public void runOpMode() throws InterruptedException {
        hunk = new HunkOfMetal(this);
        hunk.initialize();

        waitForStart();

        // Go forward 62 inches, but give a few inches of buffer
        hunk.forward(1, 45);

        // Shoot 3 rings
        hunk.turnOnThrower();
        sleep(2000);
        hunk.throwRing();
        sleep(1000);
        hunk.throwRing();
        sleep(1000);
        hunk.throwRing();
        sleep(1000);
        hunk.turnOffThrower();

        // Park over the line
        hunk.forward(1, 28);

        // Shut down
        hunk.arret();
    }
}

