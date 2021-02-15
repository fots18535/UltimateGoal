package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.robotcore.external.tfod.Recognition;

import java.util.LinkedList;
import java.util.List;

/**
 * Processes a list of TF Recognitions to return the number of rings seen by the camera
 */
public class TFProcessor {
    private static final String LABEL_FIRST_ELEMENT = "Quad";
    private static final String LABEL_SECOND_ELEMENT = "Single";

    public int getRings(List<Recognition> recs) {
        int rings = -1;

        if (recs != null) {

            if (recs.size() == 0) {
                // empty list.  no objects recognized.
                rings = 0;
            } else {
                Recognition rec = findBestRec(recs);

                // check label to see how many rings were recognized.
                if (rec.getLabel().equals("Single")) {
                    rings = 1;
                } else if (rec.getLabel().equals("Quad")) {
                    rings = 4;
                } else {
                    // Unknown
                }
            }
        }

        return rings;
    }

    private Recognition findBestRec(List<Recognition> inrecs) {
        Recognition outrec = null;

        // If there is only one item in the list return it.
        if (inrecs.size() == 1) {
            outrec = inrecs.get(0);
        } else {
            // TODO: if there are multiple items in the list return the best one
            float confidentYeah = 0;
            for (Recognition rec : inrecs) {
                if (confidentYeah < rec.getConfidence()) {
                    outrec = rec;
                    confidentYeah = rec.getConfidence();
                }
                // Use get getTop(), getBottom(), getLeft(), getRight() to find where the object is
                // Use getConfidence() and other functions
                // the ratio of the bounding box to the height of the box
            }
        }

        return outrec;
    }
}
