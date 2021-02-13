package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.util.List;

public class TensorFlow {
    private static final String VUFORIA_KEY =
            "AbcG7eL/////AAABmdvNiSBOl058kZ5RZjALlhlCuqa5Gyvfczk9PXfWLJ7cEuz+LEvC9G7lchPyRqA7c41SIEWTCgA+/jBmUra7pPHNWPTDHOkUN7nTuWe/i5+iqWgAljwQXfBcaf6tglGK1MdKjVi24H7FjKgl08EJOwgB3ikIWn4BSg3YdNYO6gXGi+80SWU1loW9E9PzF9lKai+MFeLFOJs8+ebgGSOuxzoQ75fuDhFpT8TpkC4e96bwScZL/G92ZhYoT5nUvKl8BCoX6pxSQ+w2/RXTfnrQbJscAxfn/23tgPCSg6kTZYEpy79QeC1WH9pdL8FcQSZN+ok3Zy6JNDgWLxnVcrYepewZcZmXeh2LCM4bWilRIU74";
    private static final String TFOD_MODEL_ASSET = "UltimateGoal.tflite";
    private static final String LABEL_FIRST_ELEMENT = "Quad";
    private static final String LABEL_SECOND_ELEMENT = "Single";

    private VuforiaLocalizer vuforia = null;
    private TFObjectDetector tfod = null;
    private LinearOpMode op = null;
    public TensorFlow(LinearOpMode o) {
        op = o;
    }


    private void initVuforia() {
        /*
         * Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.
         */
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK.BACK;

        //  Instantiate the Vuforia engine
        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        // Loading trackables is not necessary for the TensorFlow Object Detection engine.
    }

    private void initTfod() {
        int tfodMonitorViewId = op.hardwareMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", op.hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfodParameters.minResultConfidence = 0.8f;
        tfodParameters.useObjectTracker = false;

        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_FIRST_ELEMENT, LABEL_SECOND_ELEMENT);
    }


    public int getRings() {
        initVuforia();
        initTfod();

        int zeroCount = 0;
        int oneCount = 0;
        int fourCount = 0;
        int sampleCount = 0;

        if (tfod != null) {
            tfod.activate();

            // The TensorFlow software will scale the input images from the camera to a lower resolution.
            // This can result in lower detection accuracy at longer distances (> 55cm or 22").
            // If your target is at distance greater than 50 cm (20") you can adjust the magnification value
            // to artificially zoom in to the center of image.  For best results, the "aspectRatio" argument
            // should be set to the value of the images used to create the TensorFlow Object Detection model
            // (typically 16/9).
            tfod.setZoom(1.0, 16.0/9.0);

            while (op.opModeIsActive() && sampleCount < 50) {
                sampleCount = sampleCount + 1;
                if (tfod != null) {
                    // getUpdatedRecognitions() will return null if no new information is available since
                    // the last time that call was made.
                    List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
                    if (updatedRecognitions != null) {
                        // op.telemetry.addData("# Object Detected", updatedRecognitions.size());
                        if (updatedRecognitions.size() == 0 ) {
                            // empty list.  no objects recognized.
                            // op.telemetry.addData("TFOD", "No items detected.");
                            // op.telemetry.addData("Target Zone", "A");
                            zeroCount = zeroCount + 1;
                        } else {
                            // list is not empty.
                            // step through the list of recognitions and display boundary info.
                            int i = 0;
                            for (Recognition recognition : updatedRecognitions) {
                                // op.telemetry.addData(String.format("label (%d)", i), recognition.getLabel());
                                // op.telemetry.addData(String.format("  left,top (%d)", i), "%.03f , %.03f",
                                        // recognition.getLeft(), recognition.getTop());
                                // op.telemetry.addData(String.format("  right,bottom (%d)", i), "%.03f , %.03f",
                                        // recognition.getRight(), recognition.getBottom());

                                // check label to see which target zone to go after.
                                if (recognition.getLabel().equals("Single")) {
                                    // op.telemetry.addData("Target Zone", "B");
                                    oneCount = oneCount + 1;
                                } else if (recognition.getLabel().equals("Quad")) {
                                    // op.telemetry.addData("Target Zone", "C");
                                    fourCount = fourCount + 1;
                                } else {
                                    // op.telemetry.addData("Target Zone", "UNKNOWN");
                                }
                            }
                        }

                        // op.telemetry.update();
                    }
                }
            }

            tfod.shutdown();

            // output the counts
            op.telemetry.addData("zeroCount", zeroCount);
            op.telemetry.addData("oneCount", oneCount);
            op.telemetry.addData("fourCount", fourCount);
            op.telemetry.update();
        }

        // return the result with the highest count
        if (zeroCount > oneCount && zeroCount > fourCount) {
            return 0;
        } else if (oneCount > zeroCount && oneCount > fourCount) {
            return 1;
        } else {
            return 4;
        }
    }
}
