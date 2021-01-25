package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous
public class AutonomousSimpleVuforia extends LinearOpMode {
    HunkOfMetal hunk;

    @Override
    public void runOpMode() throws InterruptedException {
        hunk = new HunkOfMetal(this);
        hunk.initialize();

        waitForStart();

        // Go forward 62 inches, but give a few inches of buffer
        hunk.forward(1, 58);
        hunk.correct (-14, 36, 0);

        // Shoot 3 rings
        hunk.turnOnThrower();
        sleep(2000);
        hunk.throwRing();
        sleep(1000);
        hunk.throwRing();
        sleep(1000);
        hunk.throwRing();
        hunk.turnOffThrower();

        hunk.forward(1, 10);
        hunk.correct(-4, 36, 0);

        hunk.arret();
    }
}

