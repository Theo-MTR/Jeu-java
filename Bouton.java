package jeu;

import javafx.scene.shape.Rectangle;

public class Bouton extends Rectangle {
    private boolean actif;
    private boolean on;
    public Bouton() {
        super();
        actif = false;
        on = false;
    }

    public boolean isActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }

    public boolean isOn() {
        return on;
    }

    public void setOn(boolean on) {
        this.on = on;
    }
}
