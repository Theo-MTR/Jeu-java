package jeu.gui;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import jeu.objets.*;
import jeu.gestion.Triage;

import java.nio.file.Paths;

public class NiveauTrois {

    public NiveauTrois() {

        //Creation du personnage
        Personnage p = new Personnage(0.2, 0.99, 0.031, 0.114, Paths.get("Ressources/Face.png").toUri().toString());

        //Creation du sol
        Mur sol1 = new Mur(0, 1, 1, 1);

        //Creation d'un cube
        Cube cube1 = new Cube(0.70, sol1);
        Mur mur1 = new Mur(0.98, 0.8, 0.4, 0.1);
        MurBlanc mb1 = new MurBlanc(0.85, 0.8, 0.2, 0.01, "d");
        MurBlanc mb2 = new MurBlanc(0.10, 0.8, 0.2, 0.01, "g");
        Bouton b1 = new Bouton(0.4, sol1);


        Mur sol2 = new Mur(0, 0.75, 0.05, 0.4);
        MurMovible mv1 = new MurMovible(1, 0, 0.05, 0.2, sol2, b1, Color.DARKGOLDENROD, "h");
        Scie scie1 = new Scie(0.1, 30, sol2);
        Pique p1 = new Pique(0.3, sol2, 20);

        Mur sol3 = new Mur(0.6, 0.75, 0.05, 0.4);
        Bouton b2 = new Bouton(0.2, sol3);
        MurMovible mv2 = new MurMovible(0.5, 0.4, 0.40, 0.02, sol3, b2, Color.DARKGOLDENROD, "v");

        MurBlanc mb3 = new MurBlanc(0.4, 0.2, 0.05, 0.2, "b");
        MurBlanc mb4 = new MurBlanc(0.2, 0.35, 0.4, 0.01, "d");


        final Shape[] obj = {p, sol1, cube1, mur1, sol2, mb1, mb2, sol3, mv1, b1, scie1, b2, mv2, p1, mb3, mb4};
        Triage.trier(obj);

    }


}
