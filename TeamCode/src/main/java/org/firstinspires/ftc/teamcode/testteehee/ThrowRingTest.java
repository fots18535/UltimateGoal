package org.firstinspires.ftc.teamcode.testteehee;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.HunkOfMetal;

@Autonomous
public class ThrowRingTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        HunkOfMetal hunk = new HunkOfMetal(this);

        hunk.initialize();

        hunk.turnOnThrower();

         while(opModeIsActive()) {
             idle();
         }

         hunk.turnOffThrower();

         hunk.arret();
    }
}
