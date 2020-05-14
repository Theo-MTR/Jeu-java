package jeu;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;


public class NiveauUn {

    public NiveauUn() {

        Personnage p = CreerObjet.creerPersonnage(60, 120, 150, 500, "perso.png");
        Var.personnage = p;

        Shape sol1 = CreerObjet.creerMur(Var.scene.getWidth(), 50, 0, 1030, Color.BLACK);
        Var.obstacles.add(sol1);

        Shape sol2 = CreerObjet.creerMur(Var.scene.getWidth() / 2, 30, 800, 800, Color.BLACK);
        Bouton bouton1 = CreerObjet.creerBouton(sol2.getBoundsInLocal().getMinX() + sol2.getBoundsInLocal().getWidth() * 0.5, sol2.getBoundsInLocal().getMinY() - 20);
        MurMovible murM1 = CreerObjet.creerMurMovible(50, 200, 1500, 600, Color.BLACK, bouton1);
        Cube cube1 = CreerObjet.creerCube(sol2.getBoundsInLocal().getMinX() + sol2.getBoundsInLocal().getWidth() * 0.2, sol2.getBoundsInLocal().getMinY() - 40);
        Var.obstacles.add(sol2);
        Var.obstacles.add(bouton1);
        Var.obstacles.add(murM1);
        Var.obstacles.add(cube1);

        Shape sol3 = CreerObjet.creerMur(500, 40, 0, 700, Color.BLACK);
        Var.obstacles.add(sol3);

        Var.root.getChildren().addAll(p, sol1, sol2, sol3, bouton1, murM1, cube1);

    }

}
