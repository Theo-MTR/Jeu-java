package jeu.objets;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import jeu.gestion.Var;

public class Personnage extends Rectangle{

    private Shape collisionAvec;
    private boolean enGravite;
    private double etatInitial;
    private int vie;
    private Cube cubeSelect;

    public Personnage(double ratio_x, double ratio_y, double ratio_l, double ratio_h, String image) {
        super();
        Image i = new Image(image);
        Paint ip = new ImagePattern(i);
        setWidth(Var.scene.getWidth() * ratio_l);
        setHeight(Var.scene.getHeight() * ratio_h);
        setX(Var.scene.getWidth() * ratio_x);
        setY(Var.scene.getHeight() * ratio_y);
        setFill(ip);
        setFocusTraversable(true);
        this.enGravite = true;
        this.vie = 100;
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

    public int getVie() {
        return vie;
    }

    public void setVie(int vie) {
        this.vie = vie;
    }

    public Cube getCubeSelect() {
        return cubeSelect;
    }

    public void setCubeSelect(Cube cubeSelect) {
        this.cubeSelect = cubeSelect;
    }
}
