package jeu;

import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Personnage extends Rectangle{

    private Shape collisionAvec;
    private boolean enGravite;
    private double etatInitial;

    public Personnage() {
        super();
        enGravite = true;
    }

    public boolean isEnGravite() {
        return enGravite;
    }

    public void setEnGravite(boolean enGravite) {
        this.enGravite = enGravite;
    }

    public Shape getCollisionAvec() {
        return collisionAvec;
    }

    public void setCollisionAvec(Shape collisionAvec) {
        this.collisionAvec = collisionAvec;
    }

    public double getEtatInitial() {
        return etatInitial;
    }

    public void setEtatInitial(double etatInitial) {
        this.etatInitial = etatInitial;
    }
}
