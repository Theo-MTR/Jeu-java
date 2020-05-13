package jeu;

import javafx.scene.shape.Rectangle;

public class MurMovible extends Rectangle {

    private Bouton bouton; //Bouton qui actionne le mur
    private double positionInitiale;

    public MurMovible(Bouton bouton, double positionInitiale) {
        this.bouton = bouton;
        this.positionInitiale = positionInitiale;
    }

    public Bouton getBouton() {
        return bouton;
    }

    public void setBouton(Bouton bouton) {
        this.bouton = bouton;
    }

    public double getPositionInitiale() {
        return positionInitiale;
    }

    public void setPositionInitiale(double positionInitiale) {
        this.positionInitiale = positionInitiale;
    }
}
