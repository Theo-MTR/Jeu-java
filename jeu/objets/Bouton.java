package jeu.objets;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import jeu.gestion.Var;

public class Bouton extends Rectangle {
    private final String nom = "Bouton";
    private boolean on;
    private double initialY;
    private double initialTaille;
    public Bouton(double ratio_x,  Shape sol) {
        super();
        on = false;
        setWidth(Var.scene.getWidth() * 0.026);
        setHeight(Var.scene.getHeight() * 0.019);
        setX(sol.getBoundsInLocal().getMinX() + sol.getBoundsInLocal().getWidth() * ratio_x);
        setY(sol.getBoundsInLocal().getMinY() - Var.scene.getHeight() * 0.019);
        setFill(Color.RED);
        initialY = sol.getBoundsInLocal().getMinY() - Var.scene.getHeight() * 0.019;
        initialTaille = Var.scene.getHeight() * 0.019;
    }

    public double getInitialY() {
        return initialY;
    }

    public double getInitialTaille() {
        return initialTaille;
    }

    public boolean isOn() {
        return on;
    }

    public void setOn(boolean on) {
        this.on = on;
    }


    @Override
    public String toString() {
        return "Bouton{" + "nom='" + nom + '\'' + ", on=" + on + ", initialY=" + initialY + ", initialTaille=" + initialTaille + '}';
    }
}
