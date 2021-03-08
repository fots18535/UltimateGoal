package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.tfod.Recognition;

import java.util.List;
@TeleOp
public class AutonomousComplete extends LinearOpMode{

    @Override
    public void runOpMode() throws InterruptedException {
        // TODO: initialize a hunk of metal
        telemetry.addData("Status", "Initializing");
        telemetry.update();

        HunkOfMetal hunk = new HunkOfMetal(this);
        hunk.initialize();

        // TODO: initialize a Graph object and turn on Vuforia and TF

        Graph graph = new Graph(this);
        graph.turnOn();
        graph.turnOnTfod();

        telemetry.addData("Status", "Ready");
        telemetry.update();

        waitForStart();

        // TODO: move forward to get within 20 inches of rings

        //hunk.forward(1,20);
        sleep(1000);

        // TODO: get list of recognitions and send to TFProcessor to get number of rings

        List<Recognition> recs = graph.getTFDetections();
        TFProcessor tf = new TFProcessor();
        int rings = tf.getRings(recs);
        while (rings == -1 && opModeIsActive()) {
            recs = graph.getTFDetections();
            rings = tf.getRings(recs);
        }
        telemetry.addData("Rings", rings);
        telemetry.update();

        if (rings==0){
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
        }
        else if (rings==1){
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
            hunk.chaChaRealSmooth(1.0, 19);

            // Dr0p GoAl AHHHHHHHHHHH U R GOING TO DIE
            hunk.wristDown();
            sleep(500);
            hunk.clawOpen();
            sleep(800);
            hunk.wristUp();

            // BACK UP OVER THE LINE
            hunk.forward(-1, 30);
        }
        else if (rings==4){
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

        graph.turnOffTfod();
        graph.turnOff();

        // TODO: if ring count == 0 then do this, else if ring count == 1 then do this...
    }
}
