package org.firstinspires.ftc.teamcode.testteehee;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.teamcode.Graph;
import org.firstinspires.ftc.teamcode.TFProcessor;
import org.firstinspires.ftc.teamcode.TensorFlow;

import java.util.List;

@Autonomous
public class TestTensorFlowTheSequel extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        TFProcessor p = new TFProcessor();
        Graph g = new Graph(this);
        g.turnOn();
        g.turnOnTfod();

        while (opModeIsActive()) {
            List<Recognition> recs = g.getTFDetections();
            int ringCount = p.getRings(recs);

            telemetry.addData("chicken? RING COUNT", ringCount);
            telemetry.update();
        }

        g.turnOffTfod();
        g.turnOff();
    }
}
