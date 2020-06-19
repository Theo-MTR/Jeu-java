package jeu.gui;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import jeu.gestion.Triage;
import jeu.objets.*;

import java.nio.file.Paths;

public class NiveauQuatre {
    public NiveauQuatre() {

        /*

        /!\ NE PAS OUBLIER D'AJOUTER CHAQUE OBJET CREE A LA FIN DU FICHIER /!\

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
        Mur sol2 = new Mur(0.4,0.50,0.02,0.6);
        Mur sol3 = new Mur(0,0.75,0.02,0.60);
        Mur sol4 = new Mur(0.8,0.75,0.02,0.2);
        Mur sol5 = new Mur(0.7,0.25,0.02,0.3);
        Mur sol6 = new Mur(0,0.5,0.02,0.2);

        //Creation d'un mur

        /*Mur mur1 = new Mur(0.8,0.4,0.2,0.01);*/

        //Creation d'un cube

        Cube cb1 = new Cube(0.37,sol1);
        Cube cb2 = new Cube(0.5,sol4);
        Cube cb3= new Cube(0.75,sol5);

        //Creation de bouton

        Bouton b1 = new Bouton(0.9,sol1);
        Bouton b2 = new Bouton(0.85,sol3);
        Bouton b3 = new Bouton(0.2,sol3);

        // Creation d'un mur movible

        MurMovible av1 = new MurMovible(0.6,0.25,0.02,0.2,sol1, b1, Color.DARKGOLDENROD,"h");
        MurMovible av2 = new MurMovible(0.33,0.25,0.02,0.21,sol3,b3,Color.DARKGOLDENROD,"h");


        //Creation de mur blanc

        MurBlanc nb1 = new MurBlanc(0.25,0.77,0.25,0.01,"d");
        MurBlanc nb2 = new MurBlanc(0.50,0.77,0.25,0.01,"d");
        MurBlanc nb3 = new MurBlanc(0.4,0.52,0.23,0.01,"g");
        MurBlanc nb4 = new MurBlanc(0.85,0,0.25,0.01,"d");



        //Creation de pieges

        Laser l1 = new Laser(0,0.23,0.23,sol4,60,b2);
        Scie scie1 = new Scie(0,100,sol5);

        Porte porte = new Porte(0.2, sol5, 2);

        final Shape[] obj = {p, sol1,sol2,sol3,sol4,sol5,nb1,av1,b1,nb2,porte,cb1,sol6,b3,l1,b2,nb3,cb2,nb4,cb3,av2,scie1};
        Triage.trier(obj);

    }
}
