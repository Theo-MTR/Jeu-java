/*
Il sagit d'une classe qui nous facilite la programmation.
Elle permet de classer dans les bonnes variables chaques objet en fonction de leur type afin qu'il puissent être utilisé efficacement dans les autres parties du programme
Ca nous permet de gagner des lignes et du temps
 */
package jeu.gestion;

import javafx.scene.shape.Shape;
import jeu.objets.Cube;
import jeu.objets.MurBlanc;
import jeu.objets.Personnage;

public class Triage {
    public static void trier(Shape[] liste) {
        for (Shape s : liste) {
            Var.root.getChildren().add(s);
            if (!(s instanceof Personnage)) Var.obstacles.add(s);
            else Var.personnage = (Personnage) s;
            if (s instanceof MurBlanc) Var.listeMurBlanc.add((MurBlanc) s);
            else if (s instanceof Cube) Var.listeCube.add((Cube) s);
        }
    }
}
