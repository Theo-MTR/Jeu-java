package jeu.gui;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import jeu.gestion.GPopup;
import jeu.gestion.Triage;
import jeu.objets.*;

import java.nio.file.Paths;

public class NiveauIntro {
    public NiveauIntro() {
        GPopup.GestPopup();
        /*Shape[] obj = {p,sol1,sol2,nb1 };

        for(Shape s : obj) {
            Var.obj.add(s);
        }*/
        Personnage p = new Personnage(0.05, 0.886, 0.031, 0.114, Paths.get("Ressources/Face.png").toUri().toString());

        //Creation du sol
        Mur sol1 = new Mur(0, 1, 1, 1);
        //Creation d'un cube

        Cube cb1 = new Cube(0.15,sol1);

        //Creation de bouton

        Bouton b2 = new Bouton(0.25,sol1);


        // Creation d'un mur movible

        MurMovible av1 = new MurMovible(0.35,0.18,0.20,0.02,sol1, b2, Color.DARKGOLDENROD,"v");

        //Creation de mur blanc

        MurBlanc nb1 = new MurBlanc(0.70,0.80,0.20,0.01,"d");
        MurBlanc nb2 = new MurBlanc(0.65,0.80,0.20,0.01,"d");

        //Creation de pieges

        Laser l1 = new Laser(0.5,0.18,0.18,sol1,60,b2);


        Porte porte = new Porte(0.95, sol1, 0);

        Shape[] obj = {p,sol1,nb1,l1,av1,b2,cb1,nb2, porte};
        Triage.trier(obj);

    }
}
