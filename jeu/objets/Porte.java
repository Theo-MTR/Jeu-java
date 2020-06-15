package jeu.objets;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import jeu.gestion.Var;

import java.nio.file.Paths;

public class Porte extends Rectangle {

    private static final Image img = new Image(Paths.get("Ressources/Porte.png").toUri().toString()); //Image de base d'un cube
    private static final Paint ip = new ImagePattern(img);

    private final int niveau;

    public Porte(double ratio_x, Mur sol, int niveau) {
        super();
        setWidth(Var.scene.getWidth() * 0.035); //Longueur
        setHeight(Var.scene.getHeight() * 0.117); //hauteur
        setX(sol.getBoundsInLocal().getMinX() + sol.getBoundsInLocal().getWidth() * ratio_x); //Position x en fonction du sol grace au ratio_x
        setY(sol.getBoundsInLocal().getMinY() - Var.scene.getHeight() * 0.117 ); //Position y en fonction du sol sur le quel on le place
        setFill(ip);
        this.niveau = niveau;
    }

    public int getNiveau() {
        return niveau;
    }
}
