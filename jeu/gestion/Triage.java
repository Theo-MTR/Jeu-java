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