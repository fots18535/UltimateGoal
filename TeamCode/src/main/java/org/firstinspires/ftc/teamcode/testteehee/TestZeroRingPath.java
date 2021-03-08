package org.firstinspires.ftc.teamcode.testteehee;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.HunkOfMetal;

@Autonomous
public class TestZeroRingPath extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        HunkOfMetal hunk = new HunkOfMetal(this);
        hunk.initialize();
        waitForStart();
        hunk.forward(1, 60);
        hunk.throwRings();
        hunk.forward(1, 8);
        hunk.turnLeft(180, 0.8);
       // hunk.chaChaRealSmooth(-1, 20);
        hunk.wristDown();
        sleep(500);
        hunk.clawOpen();
        sleep(800);
        hunk.wristUp();
       // hunk.chaChaRealSmooth(1, 12);
        hunk.arret();
    }
}
