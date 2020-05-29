package org.openjfx;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Cube extends Rectangle {
    private boolean enGravite;

    private static final Image img = new Image("cube.png");
    private static final Paint ip = new ImagePattern(img);

    public Cube(double ratio_x, Shape sol) {
        super();
        setWidth(Var.scene.getWidth() * 0.02);
        setHeight(Var.scene.getHeight() * 0.038);
        setX(sol.getBoundsInLocal().getMinX() + sol.getBoundsInLocal().getWidth() * ratio_x);
        setY(sol.getBoundsInLocal().getMinY() - Var.scene.getHeight() * 0.038 );
        setFill(ip);
        enGravite = false;
    }

    public boolean isEnGravite() {
        return enGravite;
    }

    public void setEnGravite(boolean enGravite) {
        this.enGravite = enGravite;
    }
}
