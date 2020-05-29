package org.openjfx;


import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Laser extends Rectangle {
    private int vie;
    private double positionInitiale;
    private double hauteurI;
    private Bouton bouton;

    public Laser(double ratio_x, double ratio_y, double ratio_h, Shape sol, int vie, Bouton bouton) {
        super();
        if (vie > 0 && vie <= 35) setFill(Color.LIGHTBLUE);
        else if (vie > 35 && vie <= 65) setFill(Color.DARKRED);
        else setFill(Color.PURPLE);
        setX(sol.getBoundsInLocal().getMinX() + sol.getBoundsInLocal().getWidth() * ratio_x);
        setY(sol.getBoundsInLocal().getMinY() - Var.scene.getHeight() * ratio_y);
        positionInitiale = sol.getBoundsInLocal().getMinY() - Var.scene.getHeight() * ratio_y;
        setWidth(10);
        setHeight(Var.scene.getHeight() * ratio_h);
        hauteurI = Var.scene.getHeight() * ratio_h;
        this.vie = vie;
        this.bouton = bouton;
    }
    public int getVie() {
        return vie;
    }

    public Bouton getBouton() {
        return bouton;
    }

    public double getPositionInitiale() {
        return positionInitiale;
    }

    public double getHauteurI() {
        return hauteurI;
    }
}
