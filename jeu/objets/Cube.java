/*
Classe qui permet la creation d'un cube
Elle herite de la classe rectangle de javafx car nous avions besoins d'un rectangle, tout en lui ajoutant un attribut pour savoir si le cube est en gravité ou non
 */

package jeu.objets;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import jeu.gestion.Var;

import java.nio.file.Paths;

public class Cube extends Rectangle {
    private boolean enGravite; //Variable qui permet de savoir si le cube doit tomber ou non

    private static final Image img = new Image(Paths.get("Ressources/cube.png").toUri().toString()); //Image de base d'un cube
    private static final Paint ip = new ImagePattern(img);

    public Cube(double ratio_x, Shape sol) {
        super();
        setWidth(Var.scene.getWidth() * 0.02); //Longueur
        setHeight(Var.scene.getHeight() * 0.038); //hauteur
        setX(sol.getBoundsInLocal().getMinX() + sol.getBoundsInLocal().getWidth() * ratio_x); //Position x en fonction du sol grace au ratio_x
        setY(sol.getBoundsInLocal().getMinY() - Var.scene.getHeight() * 0.038 ); //Position y en fonction du sol sur le quel on le place
        setFill(ip);
        enGravite = false;
    }

    public boolean isEnGravite() {
        return enGravite;
    }

    public void setEnGravite(boolean enGravite) {
        this.enGravite = enGravite;
    }
}
