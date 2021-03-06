package org.firstinspires.ftc.teamcode.testteehee;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.HunkOfMetal;

@Autonomous
public class Test4RingPath extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        HunkOfMetal hunk = new HunkOfMetal(this);
        hunk.initialize();
        waitForStart();
        hunk.forward(1, 60);
        hunk.throwRings();
        hunk.turnLeft(180,1);
        hunk.forward(-1,60);
        hunk.wristDown();
        sleep(500);
        hunk.clawOpen();
        sleep(800);
        hunk.wristUp();
        hunk.forward(1,42);

    }
}
