package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.tfod.Recognition;

import java.util.List;

public class AutonomousComplete extends LinearOpMode{

    @Override
    public void runOpMode() throws InterruptedException {
        // TODO: initialize a hunk of metal

        HunkOfMetal hunk = new HunkOfMetal(this);
        hunk.initialize();

        // TODO: initialize a Graph object and turn on Vuforia and TF

        Graph graph = new Graph(this);
        graph.turnOn();
        graph.turnOnTfod();

        waitForStart();

        // TODO: move forward to get within 20 inches of rings

        hunk.forward(1,20);
        sleep(1000);

        // TODO: get list of recognitions and send to TFProcessor to get number of rings

        List<Recognition> recs = graph.getTFDetections();
        TFProcessor tf = new TFProcessor();
        int rings = tf.getRings(recs);

        // TODO: if ring count == 0 then do this, else if ring count == 1 then do this...
    }
}
