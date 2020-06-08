package jeu.gui;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import jeu.objets.*;
import jeu.gestion.Triage;

import java.nio.file.Paths;

public class NiveauUn {

    public NiveauUn() {

        /*

        /!\ NE PAS OUBLIER D'AJOUTER CHAQUE OBJET CREE A LA FIN DU FICHIER /!\

        CReation mur : Mur NOM_MUR = new Mur(Ratio x, Ratio y, Ratio hauteur, Ratio Largeur);
        Creation Bouton : Bouton NOM_BOUTON = new Bouton(Ratio X, Sol sur lequel on le place);
        Creation mur movible : MurMovible NOM_MUR = new MurMovible(Ratio x, Ratio y, Ratio hauteur, Ratio Largeur, sol sur lequel il est placé, bouton qui l'active, couleur, orientation); !!!! Pour la couleur utiliser Color.LACOULEURE (ex Color.BLACK)
        Creation laser : Laser NOM_LASER = new Laser(ratio_x, ratio_y, ratio_hauteur, sol sur lequel il est posé, vie qu'il retire, Bouton qui l'active);
        Creation scie : Scie NOM_SCIE = new Scie(Ratio x, vie qu'elle retire, sol ou elle est placée);
        Creation pique : Pique NOM_PIQUE = new Pique(Ratio x, sol ou ils sont placés, vie qu'ils retirent);
         */

        //Creation du personnage
        Personnage p = new Personnage(0.06, 0.50, 0.031, 0.114, Paths.get("Ressources/Face.png").toUri().toString());

        //Creation du sol
        Mur sol1= new Mur(0, 1, 1, 1);
        Mur mur1= new Mur(0.13, 0.03,0.97,0.02 );
        Mur sol2= new Mur(0.01,0.79,0.02,0.03);
        Mur sol3= new Mur(0.10, 0.6,0.02,0.03);
        Mur sol4= new Mur(0.05,0.3, 0.02,0.04);
        Mur mur2= new Mur(0,0.03,0.97,0.01);
        MurBlanc mb1= new MurBlanc(0, 0.03,0.02,0.13,"b");
        MurBlanc mb2= new MurBlanc(0.14,0.6,0.2,0.02,"d");
        Bouton b1= new Bouton(0.68,sol1);
        MurMovible mv1= new MurMovible(0.73,0.2,0.2,0.02,sol1,b1,Color.BLACK,"v");
        Mur sol6= new Mur(0.25,0.80,0.04,0.5);
        Mur sol7= new Mur(0.24,0.80,0.2,0.02);
        Mur sol8= new Mur(0.15,0.80,0.04,0.2);
        MurBlanc mb3= new MurBlanc(0.26,0.84,0.18,0.02,"d");
        Mur sol9= new Mur(0.19,0.91,0.01,0.01);
        Scie s1= new Scie(0.50,50,sol9);
        Mur sol10= new Mur(0.73,0.57,0.04,0.3);
        Mur sol12= new Mur(0.73,0.60,0.2,0.02);
        Laser l1= new Laser(0.4,-0.04,0.40,sol10,100,b1);
        Bouton b2= new Bouton(0.17, sol10);
        MurMovible mv2= new MurMovible(0.35,0.2,0.2,0.02,sol10,b2,Color.BLACK,"v");
        Laser l2= new Laser(0.65,0.20,0.2,sol10,50,b2);
        Mur sol13= new Mur(0.21,0.25,0.04,0.22);
        Bouton b3= new Bouton(0.60,sol13);
        MurMovible mv3= new MurMovible(0.4,0.22,0.25,0.02,sol13,b3,Color.BLACK,"v");
        Mur sol14= new Mur(0.55,0.43,0.03,0.03);
        Cube c2= new Cube(0.2,sol13);
        Mur sol15= new Mur(0.15,0.03,0.03,0.16);
        Mur sol16= new Mur(0.84,0.37,0.03,0.2);
        Cube c1= new Cube(0.55, sol16);
        Pique p1= new Pique(0.07,sol6,25);
        MurBlanc mb4= new MurBlanc(0.99,0.40,0.17,0.02,"g");

        final Shape[] obj = {p,sol1,mur1,mb1,mb2,sol2,sol3,sol4,mur2,c1,b1,mv1,sol6,mb3,sol7,sol8,s1,sol9,sol10,sol12,l1,b2,mv2,l2,sol13,b3,mv3,sol14,c2,sol15,sol16,p1,mb4};
        Triage.trier(obj);

    }


}
