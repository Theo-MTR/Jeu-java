package jeu.objets;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import jeu.gestion.Var;

import java.nio.file.Paths;

public class Pique extends Rectangle {

    private final int vie;

    private static final Image img = new Image(Paths.get("Ressources/Pique.png").toUri().toString()); //Image de base d'un pique
    private static final Paint ip = new ImagePattern(img);

    public Pique(double ratio_x, Shape sol, int vie) {
        super();
        setWidth(Var.scene.getWidth() * 0.080);
        setHeight(Var.scene.getHeight() * 0.019);
        setX(sol.getBoundsInLocal().getMinX() + sol.getBoundsInLocal().getWidth() * ratio_x);
        setY(sol.getBoundsInLocal().getMinY() - Var.scene.getHeight() * 0.019);
        setFill(ip);
        this.vie = vie;
    }

    public int getVie() {
        return vie;
    }
}
