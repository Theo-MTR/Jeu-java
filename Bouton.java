package jeu;

import javafx.scene.shape.Rectangle;

public class Bouton extends Rectangle {
    private boolean on;
    private double initialY;
    private double initialTaille;
    public Bouton() {
        super();
        on = false;
    }

    public double getInitialY() {
        return initialY;
    }

    public void setInitialY(double initialY) {
        this.initialY = initialY;
    }

    public double getInitialTaille() {
        return initialTaille;
    }

    public void setInitialTaille(double initialTaille) {
        this.initialTaille = initialTaille;
    }

    public boolean isOn() {
        return on;
    }

    public void setOn(boolean on) {
        this.on = on;
    }



}
