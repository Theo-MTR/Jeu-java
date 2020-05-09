package jeux;


import javafx.scene.shape.Rectangle;

public class Rect extends Rectangle {

    private boolean collision;
    private boolean gravity;
    private double etatInitial;
    private Rectangle collisionWith;

    public Rect() {
        super();
        this.collision = false;
        this.gravity = true;
        this.etatInitial = 0;
    }

    public boolean isCollision() {
        return collision;
    }

    public void setCollision(boolean collision) {
        this.collision = collision;
    }

    public boolean isGravity() {
        return gravity;
    }

    public void setGravity(boolean gravity) {
        this.gravity = gravity;
    }

    public double getEtatInitial() {
        return etatInitial;
    }

    public void setEtatInitial(double etatInitial) {
        this.etatInitial = etatInitial;
    }

    public Rectangle getCollisionWith() {
        return collisionWith;
    }

    public void setCollisionWith(Rectangle collisionWith) {
        this.collisionWith = collisionWith;
    }
}
