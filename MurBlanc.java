package org.openjfx;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class MurBlanc extends Rectangle {

    private String orientation;

    public MurBlanc(double ratio_x, double ratio_y, double ratio_h, double ratio_l, String orientation) {
        super();
        setWidth(Var.scene.getWidth() * ratio_l);
        setHeight(Var.scene.getHeight() * ratio_h);
        setX(Var.scene.getWidth() * ratio_x);
        setY(Var.scene.getHeight() * ratio_y);
        setFill(Color.WHITE);
        this.orientation = orientation;
    }

    public String getOrientation() {
        return orientation;
    }
}
