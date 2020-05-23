package jeu;

import javafx.scene.shape.Shape;

public class NiveauDeux {
    public NiveauDeux() {
        /*
        NE PAS OUBLIER D4AJOUTER CHAQUE OBJET CREER A LA FIN DU FICHIER
         */

        //Creation du personnage
        Personnage p = new Personnage(0.2, 0.99, 0.031, 0.114, "Face.png");

        //Creation du sol
        Mur sol1 = new Mur(0, 1, 1, 1);

        Mur sol2 = new Mur(0, 0.75, 0.05, 0.4);


        final Shape[] obj = {p, sol1, sol2};
        Triage.trier(obj);
    }
}
