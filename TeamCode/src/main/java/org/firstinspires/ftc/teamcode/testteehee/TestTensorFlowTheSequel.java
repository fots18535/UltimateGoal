package org.firstinspires.ftc.teamcode.testteehee;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.TensorFlow;

@Autonomous
public class TestTensorFlowTheSequel extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        TensorFlow goWithTheFlow = new TensorFlow (this);
        waitForStart();

    }
}
