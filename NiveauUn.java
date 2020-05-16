package jeu;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public class NiveauUn {

    public NiveauUn() {

        /*
        NE PAS OUBLIER D4AJOUTER CHAQUE OBJET CREER A LA FIN DU FICHIER
         */

        Personnage p = new Personnage(0.078, 0.48, 0.031, 0.114, "Perso.png");

        Mur sol1 = new Mur(0, 1, 1, 1);

        Mur sol2 = new Mur(0.42, 0.76, 0.028, 0.5);
        Bouton bouton1 = new Bouton(0.5, sol2);
        MurMovible murM1 = new MurMovible(0.7, 0.20, 0.2, 0.02, sol2, bouton1, Color.BLACK);
        Cube cube1 = new Cube(0.2, sol2);

        Mur sol3 = new Mur(0, 0.67, 0.028, 0.26);
        Bouton bouton2 = new Bouton(0.4, sol3);
        Laser laser1 = new Laser(0.2, 0.2, 0.2, sol3, 20, bouton2);

        MurBlanc mb1 = new MurBlanc(0.52, 0.81, 0.19, 0.026);
        MurBlanc mb2 = new MurBlanc(0.052, 0.81, 0.19, 0.026);

        final Shape[] obj = {p, sol1, sol2, sol3, bouton1, murM1, cube1, laser1, bouton2, mb1, mb2};
        Triage.trier(obj);

    }



}
