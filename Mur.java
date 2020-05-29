package org.openjfx;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Mur extends Rectangle {

    public Mur(double ratio_x, double ratio_y, double ratio_h, double ratio_l) {
        super();
        setWidth(Var.scene.getWidth() * ratio_l);
        setHeight(Var.scene.getHeight() * ratio_h);
        setX(Var.scene.getWidth() * ratio_x);
        setY(Var.scene.getHeight() * ratio_y);
        setFill(Color.BLACK);
    }
}
