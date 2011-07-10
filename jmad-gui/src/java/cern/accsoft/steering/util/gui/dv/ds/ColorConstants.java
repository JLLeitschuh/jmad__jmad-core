/*
 * $Id: ColorConstants.java,v 1.1 2008-11-19 15:35:08 kfuchsbe Exp $
 * 
 * $Date: 2008-11-19 15:35:08 $ $Revision: 1.1 $ $Author: kfuchsbe $
 * 
 * Copyright CERN, All Rights Reserved.
 */
package cern.accsoft.steering.util.gui.dv.ds;

import java.awt.Color;

/**
 * This interface contains color - constants for the dataviewer
 * 
 * @author kfuchsbe
 */
public interface ColorConstants {
    /* colors used */
    public final static Color COLOR_RED = new Color(127, 0, 0);
    public final static Color COLOR_GREEN = new Color(0, 127, 0);
    public final static Color COLOR_BLUE = new Color(0, 0, 127);

    /* colors for certain purposes */
    /* a light blue */
    public final static Color COLOR_MEAS_DATA_STROKE = new Color(114, 167, 255);
    // public final static Color COLOR_MEAS_DATA_FILL = COLOR_MEAS_DATA_STROKE;
    public final static Color COLOR_MEAS_DATA_FILL = new Color(218, 232, 255);

    public final static Color COLOR_MEAS_ERROR_FILL = new Color(36, 119, 255);
    public final static Color COLOR_MEAS_ERROR_STROKE = COLOR_MEAS_ERROR_FILL;

    /* a kind of red */
    public final static Color COLOR_MODEL_DATA = new Color(187, 19, 0);

    // public final static Color COLOR_MEAS_DATA = new Color(30, 170, 110);
    // public final static Color COLOR_MEAS_DATA = new Color(80, 130, 190);
    // public final static Color COLOR_MEAS_ERROR = new Color(10, 130, 80);
    // public final static Color COLOR_MODEL_DATA = new Color(75, 80, 160);
    // public final static Color COLOR_MODEL_DATA = new Color(170, 20, 0);

    public final static Color COLOR_INVALID_DATA = new Color(255, 0, 0);

    public final static Color COLOR_X = new Color(20, 130, 190);
    public final static Color COLOR_Y = new Color(190, 20, 60);

    /* light blue */
    public static final Color CHARTBG_BEAM_1 = new Color(204, 220, 255);

    /* ocre */
    public static final Color CHARTBG_BEAM_2 = new Color(255, 240, 200);

}