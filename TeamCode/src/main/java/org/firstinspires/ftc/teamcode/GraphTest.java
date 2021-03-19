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
               // TODO: if result.orientation > 5 degrees then turn right result.orientation degrees
               if(result.orientation > 5) {
                   telemetry.addData("turn right", result.orientation);
               }

               // TODO: if result.orientation < -5 degrees then turn left abs(result.orientation degrees)
               if(result.orientation < -5) {
                   telemetry.addData("turn left", -result.orientation);
               }

               // TODO: if result.x < 36 then move forward 36 - result.x +
               //  inches
               if(result.x < 36) {
                   telemetry.addData("forward", 36-result.x);
               }

               // TODO: if result.x > 36 then move backwards result.x - 36 inches
               if(result.x > 36) {
                   telemetry.addData("backward", result.x-36);
               }

               // TODO: if result.y < 36 then slide left 36 - result.y
               if(result.y < 36) {
                   telemetry.addData("slide left", 36-result.y);
               }

               // TODO: if result.y > 36 then slide right result.y - 36
               if(result.y > 36) {
                   telemetry.addData("slide right", result.y-36);
               }
           }
           else {
               telemetry.addData("no image :(", "");
           }
           telemetry.update();
        }
    }
}
