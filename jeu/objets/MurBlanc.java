/*
Classe qui permet la creation d'un Mur blanc qui sert pour les portails.
Elle herite de la classe rectangle de javafx car un mur est un rectangle.
Nous avons créé une classe Mur blanc afin de rendre la partie codage plus simple car lors de la creation d'un rectangle sur JavaFX, il y a certains parametres qu'on ne peut pas renseignés.
Grace a cette classe, on evite donc des lignes repetitives.
De plus on peut egalement enregistrer une orientation, afin de faciliter la teleportation du personnage.
 */

package jeu.objets;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import jeu.gestion.Var;

public class MurBlanc extends Rectangle {

    private final String orientation;

    public MurBlanc(double ratio_x, double ratio_y, double ratio_h, double ratio_l, String orientation) {
        super();
        setWidth(Var.scene.getWidth() * ratio_l);
        setHeight(Var.scene.getHeight() * ratio_h);
        setX(Var.scene.getWidth() * ratio_x);
        setY(Var.scene.getHeight() * ratio_y);
        setFill(Color.WHITE);
        this.orientation = orientation;
    }

    public String getOrientation() {
        return orientation;
    }
}
