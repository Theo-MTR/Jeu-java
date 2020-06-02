package jeu.gui;

import javafx.scene.shape.Shape;
import jeu.objets.Mur;
import jeu.gestion.Triage;

public class Acceuil {

    public Acceuil() {
        Mur m = new Mur(0, 0, 1, 0.3);
        final Shape[] obj = {m};
        Triage.trier(obj);
    }

}
