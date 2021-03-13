package org.firstinspires.ftc.teamcode.testteehee;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.teamcode.Graph;
import org.firstinspires.ftc.teamcode.HunkOfMetal;
import org.firstinspires.ftc.teamcode.TFProcessor;
import org.firstinspires.ftc.teamcode.TensorFlow;

import java.util.List;

@Autonomous
public class TestTensorFlowTheSequel extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        // TODO: initialize a hunk of metal
        telemetry.addData("Status", "Initializing");
        telemetry.update();

        HunkOfMetal hunk = new HunkOfMetal(this);
        hunk.initialize();

        Graph graph = new Graph(this);
        graph.turnOn();
        graph.turnOnTfod();

        sleep(2000);

        telemetry.addData("Status", "Ready");
        telemetry.update();

        waitForStart();

        // Move forward to get within 20 inches of rings :)
        hunk.forward(1, 12);

        //VARIABLES :)
        int zerp = 0;
        int onerp = 0;
        int fourerp = 0;
        int rings = 0;

        for(int i = 0; i < 10; i++) {
            // Get list of recognitions and send to TFProcessor to get number of rings
            List<Recognition> recs = graph.getTFDetections();
            TFProcessor tf = new TFProcessor();
            rings = tf.getRings(recs);
            while (rings == -1 && opModeIsActive()) {
                recs = graph.getTFDetections();
                //DEMOLISH THEM FRODO :) ;)
                rings = tf.getRings(recs);
            }

            if(rings == 0){
                zerp++;
            } else if(rings == 1) {
                onerp++;
            } else if (rings == 4) {
                fourerp++;
            }
        }

        telemetry.addData("zero", zerp);
        telemetry.addData("one", onerp);
        telemetry.addData("four", fourerp);
        telemetry.update();

        while (opModeIsActive()) {
            idle(); //
        }
    }
}
