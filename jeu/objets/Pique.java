package jeu.objets;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import jeu.gestion.Var;

public class Pique extends Rectangle {

    private int vie;

    public Pique(double ratio_x, Shape sol, int vie) {
        super();
        setWidth(Var.scene.getWidth() * 0.080);
        setHeight(Var.scene.getHeight() * 0.019);
        setX(sol.getBoundsInLocal().getMinX() + sol.getBoundsInLocal().getWidth() * ratio_x);
        setY(sol.getBoundsInLocal().getMinY() - Var.scene.getHeight() * 0.019);
        setFill(Color.DARKORANGE);
        this.vie = vie;
    }

    public int getVie() {
        return vie;
    }
}
