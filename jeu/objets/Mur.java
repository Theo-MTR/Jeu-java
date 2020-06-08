/*
Classe qui permet la creation d'un Mur
Elle herite de la classe rectangle de javafx car un mur est un rectangle.
Nous avons créé une classe Mur afin de rendre la partie codage plus simple car lors de la creation d'un rectangle sur JavaFX, il y a certains parametres qu'on ne peut pas renseignés.
Grace a cette classe, on evite donc des lignes repetitives.
 */

package jeu.objets;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import jeu.gestion.Var;

public class Mur extends Rectangle {

    public Mur(double ratio_x, double ratio_y, double ratio_h, double ratio_l) {
        super();
        setWidth(Var.scene.getWidth() * ratio_l); //Configuration largeur
        setHeight(Var.scene.getHeight() * ratio_h); //Configuration Hauteur
        setX(Var.scene.getWidth() * ratio_x); //Configuration position X
        setY(Var.scene.getHeight() * ratio_y); //Configuration position Y
        setFill(Color.BLACK); //Configuration couleur
    }
}
