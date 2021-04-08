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

        HunkOfMetal hunk = new HunkOfMetal(
                this);
        hunk.initialize();

        // TODO: initialize a Graph object and turn on Vuforia and TF

        Graph graph = new Graph(this);
        graph.turnOn();
        graph.turnOnTfod();

        sleep(1000);

        telemetry.addData("Status", "Ready");
        telemetry.update();

        waitForStart();

        // Move forward to get within 20 inches of rings :)
        hunk.forward(1,12);
        sleep(2000);

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

        hunk.wristUp();
        hunk.forward(1, 44);
        hunk.throwRings();

        // Do the path for the correct number of rings
        if (zerp > onerp && zerp > fourerp) {
            hunk.forward(1, 8);
            hunk.turnLeft(180, 0.8);
             hunk.chaChaRealSmooth(-1, 8);
            hunk.wristDown();
            sleep(500);
            hunk.clawOpen();
            sleep(800);
            hunk.wristUp();
            // hunk.chaChaRealSmooth(1, 12);
        }
        else if (onerp > zerp && onerp > fourerp){
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
        else if (fourerp > zerp && fourerp > onerp){
            hunk.turnLeft(170,1);
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

    }
}
