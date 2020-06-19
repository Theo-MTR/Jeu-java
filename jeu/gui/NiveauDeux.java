package jeu.gui;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import jeu.objets.*;
import jeu.gestion.Triage;

import java.nio.file.Paths;

public class NiveauDeux {

    public NiveauDeux() {

        /*

        CReation mur : Mur NOM_MUR = new Mur(Ratio x, Ratio y, Ratio hauteur, Ratio Largeur);
        Creation Bouton : Bouton NOM_BOUTON = new Bouton(Ratio X, Sol sur lequel on le place);
        Creation mur movible : MurMovible NOM_MUR = new MurMovible(Ratio x, Ratio y, Ratio hauteur, Ratio Largeur, sol sur lequel il est placé, bouton qui l'active, couleur, orientation); !!!! Pour la couleur utiliser Color.LACOULEURE (ex Color.BLACK)
        Creation laser : Laser NOM_LASER = new Laser(ratio_x, ratio_y, ratio_hauteur, sol sur lequel il est posé, vie qu'il retire, Bouton qui l'active);
        Creation scie : Scie NOM_SCIE = new Scie(Ratio x, vie qu'elle retire, sol ou elle est placée);
        Creation pique : Pique NOM_PIQUE = new Pique(Ratio x, sol ou ils sont placés, vie qu'ils retirent);
        Creation d'un cube : Cube NOM_CUBE = new Cube(Ratio x, sol ou on le pose);
         */

        //Creation du personnage
        Personnage p = new Personnage(0.05, 0.886, 0.031, 0.114, Paths.get("Ressources/Face.png").toUri().toString());

        //Creation du sol
        Mur sol1 = new Mur(0, 1, 1, 1);
        Mur sol2 = new Mur(0,0.80,0.02,0.75);
        Mur sol3 = new Mur(0.2,0.60,0.02,0.80);
        Mur sol4 = new Mur(0,0.60,0.02,0.1);
        Mur sol5 = new Mur(0.9,0.80,0.02,0.1);
        Mur sol6 = new Mur(0,0.40, 0.02,0.40);
        Mur sol7 = new Mur(0.6,0.4,0.02,0.4);
        Mur sol8 = new Mur(0.2,0.2,0.02,0.60);
        Mur sol9= new Mur(0.35,0,0.02,0.31);

       //Creation d'un mur

        Mur mur1 = new Mur(0.8,0.4,0.2,0.01);

        //Creation d'un cube

        Cube cb1 = new Cube(0.40,sol5);
        Cube cb2 = new Cube(0.85,sol3);


        //Creation de bouton

        Bouton b1 = new Bouton(0.12,sol1);
        Bouton b2 = new Bouton(0.65,sol2);
        Bouton b3 = new Bouton(.3,sol4);

        // Creation d'un mur movible

        MurMovible av1 = new MurMovible(0.2,0.18,0.20,0.02,sol1, b1, Color.DARKGOLDENROD,"v");
        MurMovible av2 = new MurMovible(0.25,.20,0.2,0.01,sol8,b3,Color.DARKGOLDENROD,"v");
        MurMovible av3 = new MurMovible(0.75,0.20,0.20,0.01,sol8,b3,Color.DARKGOLDENROD,"v");

        //Creation de mur blanc

        MurBlanc nb1 = new MurBlanc(0.89,0.62,0.20,0.01,"d");
        MurBlanc nb2 = new MurBlanc(0.65,0.62,0.18,0.01,"d");
        MurBlanc nb3 = new MurBlanc(0.60,0.62,0.18,0.01,"g");
        MurBlanc nb4 = new MurBlanc(0.99,.62,0.18,0.01,"d");

        MurBlanc nb5 = new MurBlanc(0.1,0.42,0.20,0.01,"g");
        MurBlanc nb6 = new MurBlanc( 0.30,0.42,0.18,0.01,"d");

        MurBlanc nb7 = new MurBlanc(0.4,0.4,0.02,0.2,"h");
        MurBlanc nb8 = new MurBlanc(0.35,0.22,0.18,0.01,"g");
        MurBlanc nb9 = new MurBlanc(0.65,0.22,0.18,0.01,"d");

        MurBlanc nb10 = new MurBlanc(0.79,0.42,0.18,0.01,"d");
        MurBlanc nb11 = new MurBlanc(0.81,0.42,0.18,0.01,"d");

        //Creation de pieges

        Laser l1 = new Laser(0.5,0.18,0.18,sol2,60,b2);
        Scie scie1 = new Scie(0,20,sol3);
        Scie scie2 = new Scie(0,20,sol8);
        Scie scie3 = new Scie(1,20,sol8);
        Scie scie4 = new Scie(0,20,sol9);
        Scie scie5 = new Scie(1,20,sol9);
        Laser l2 = new Laser(0.5,0.18,0.18 ,sol6,100,b3);
        Laser l3 = new Laser(0.485,0.18,0.18,sol7,100,b3);

        Porte porte = new Porte(0.49, sol8, 2);

        final Shape[] obj = {p, sol1,sol2, b1, av1,sol3,scie1,sol4,l1,sol5,nb1,nb2,nb3,nb4,b2, cb1,sol6,sol7,nb5,nb6,nb7,nb8,nb9,sol8,b3,mur1,nb10,nb11,cb2,l2,l3,scie2,scie3,av2,av3,sol9,scie4,scie5, porte};
        Triage.trier(obj);

    }


}
