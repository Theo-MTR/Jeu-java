package jeu;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Cube extends Rectangle {
    private boolean enGravite;

    public Cube(double ratio_x, Shape sol) {
        super();
        setWidth(Var.scene.getWidth() * 0.02);
        setHeight(Var.scene.getHeight() * 0.038);
        setX(sol.getBoundsInLocal().getMinX() + sol.getBoundsInLocal().getWidth() * ratio_x);
        setY(sol.getBoundsInLocal().getMinY() - Var.scene.getHeight() * 0.038);
        setFill(Color.GREEN);
        enGravite = false;
    }

    public boolean isEnGravite() {
        return enGravite;
    }

    public void setEnGravite(boolean enGravite) {
        this.enGravite = enGravite;
    }
}
