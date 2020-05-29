package org.openjfx;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class MurMovible extends Rectangle {

    private Bouton bouton; //Bouton qui actionne le mur
    private double positionInitiale;
    private String orientation;

    public MurMovible(double ratio_x, double ratio_y, double ratio_hauteur, double ratio_largeur, Shape sol, Bouton bouton, Paint couleur, String orientation) {
        this.bouton = bouton;
        setWidth(Var.scene.getWidth() * ratio_largeur);
        setHeight(Var.scene.getHeight() * ratio_hauteur);
        setX(sol.getBoundsInLocal().getMinX() + sol.getBoundsInLocal().getWidth() * ratio_x);
        setY(sol.getBoundsInLocal().getMinY() - Var.scene.getHeight() * ratio_y);
        setFill(couleur);
        this.orientation = orientation;
        if ("v".equals(orientation))
            this.positionInitiale = sol.getBoundsInLocal().getMinY() - Var.scene.getHeight() * ratio_y;
        else this.positionInitiale = getX() + getWidth();
    }

    public Bouton getBouton() {
        return bouton;
    }

    public double getPositionInitiale() {
        return positionInitiale;
    }

    public String getOrientation() {
        return orientation;
    }
}
