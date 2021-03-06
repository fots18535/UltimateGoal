package org.firstinspires.ftc.teamcode.testteehee;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.HunkOfMetal;

public class TestNavigation extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        HunkOfMetal hunk = new HunkOfMetal(this);

        hunk.initialize();

        hunk.correct(36, 36, 0);

        hunk.arret();
    }
}
