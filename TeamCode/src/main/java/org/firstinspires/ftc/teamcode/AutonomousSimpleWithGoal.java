package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous
public class AutonomousSimpleWithGoal extends LinearOpMode {
    HunkOfMetal hunk;

    @Override
    public void runOpMode() throws InterruptedException {
        hunk = new HunkOfMetal(this);
        hunk.initialize();

        waitForStart();

        // Grab goal
        hunk.wristUp();

        // Go forward 62 inches, but give a few inches of buffer
        hunk.forward(1, 60);

        // Shoot 3 rings SUPRISE SHAWTY
        hunk.turnOnThrower();
        sleep(4000);
        hunk.throwRing();
        sleep(1000);
        hunk.throwRing();
        sleep(1000);
        hunk.throwRing();
        sleep(1000);
        hunk.turnOffThrower();

        // g0 F0rwArD 46 iNcHeS
        hunk.forward(1,41);

        // Slide to the left (EVERYBODY CLAP YOUR HANDS)
        hunk.chaChaRealSmooth(1.0, 10);

        // Dr0p GoAl AHHHHHHHHHHH U R GOING TO DIE
        hunk.wristDown();
        sleep(500);
        hunk.clawOpen();

        // SUCK IT!
        // tHAT WAS jasmine
        // WE WILL DESTROY YOU!
        // YES.

        // BACK UP TO FART OVER THE LINE
        hunk.forward(-1, 30);

        // Goodnight sonny
        hunk.arret();
    }
}

// STAN LANA DEL RAY OR DIE A PAINFUL DEATH OF TORTURE
