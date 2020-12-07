package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class GraphTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Graph graph = new Graph(this);
        graph.turnOn();
        waitForStart();

        while(opModeIsActive()) {
           GraphResult result = graph.getPosition();
           if(result.imageSee) {
               telemetry.addData("x",result.x);
               telemetry.addData("y",result.y);
               telemetry.addData("orientation",result.orientation);
           }
           else {
               telemetry.addData("no image :(", "");
           }
           telemetry.update();
        }
    }
}
