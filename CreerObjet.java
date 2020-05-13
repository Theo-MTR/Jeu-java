package jeu;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class CreerObjet {

    public static Personnage creerPersonnage(double longueur, double hauteur, double x, double y, String image) {
        //Image du personnage
        Image i = new Image(image);
        Paint ip = new ImagePattern(i);
        Personnage p = new Personnage();
        p.setWidth(longueur);
        p.setHeight(hauteur);
        p.setX(x);
        p.setY(y);
        p.setFill(ip);
        p.setFocusTraversable(true);
        return p;
    }

    public static Rectangle creerMur(double longueur, double hauteur, double x, double y, Paint couleur) {
        Rectangle r = new Rectangle();
        r.setWidth(longueur);
        r.setHeight(hauteur);
        r.setX(x);
        r.setY(y);
        r.setFill(couleur);
        return r;
    }

    public static MurMovible creerMurMovible(double longueur, double hauteur, double x, double y, Paint couleur, Bouton bouton) {
        MurMovible r = new MurMovible(bouton, y);
        r.setWidth(longueur);
        r.setHeight(hauteur);
        r.setX(x);
        r.setY(y);
        r.setFill(couleur);
        return r;
    }

    public static Bouton creerBouton(double x, double y) {
        Bouton b = new Bouton();
        b.setWidth(50);
        b.setHeight(20);
        b.setX(x);
        b.setY(y);
        b.setFill(Color.RED);
        return b;
    }

}
