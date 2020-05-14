package jeu;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.stage.Screen;


public class NiveauUn {

    public NiveauUn() {

        Personnage p = CreerObjet.creerPersonnage(Var.scene.getWidth() * 0.031, Var.scene.getHeight() * 0.114, 150, 500, "perso.png");
        Var.personnage = p;

        Shape sol1 = CreerObjet.creerMur(Var.scene.getWidth(), 50, 0, Var.scene.getHeight() * 1, Color.BLACK);
        Var.obstacles.add(sol1);

        Shape sol2 = CreerObjet.creerMur(Var.scene.getWidth() * 0.5, Var.scene.getHeight() * 0.028, Var.scene.getWidth() * 0.42, Var.scene.getHeight() * 0.76, Color.BLACK);
        Bouton bouton1 = CreerObjet.creerBouton(sol2.getBoundsInLocal().getMinX() + sol2.getBoundsInLocal().getWidth() * 0.5, sol2.getBoundsInLocal().getMinY() - 20);
        MurMovible murM1 = CreerObjet.creerMurMovible(Var.scene.getWidth() * 0.026, Var.scene.getHeight() * 0.20, sol2.getBoundsInLocal().getMinX() + sol2.getBoundsInLocal().getWidth() * 0.7, sol2.getBoundsInLocal().getMinY() - 200, Color.BLACK, bouton1);
        Cube cube1 = CreerObjet.creerCube(sol2.getBoundsInLocal().getMinX() + sol2.getBoundsInLocal().getWidth() * 0.2, sol2.getBoundsInLocal().getMinY() - 40);
        Var.obstacles.add(sol2);
        Var.obstacles.add(bouton1);
        Var.obstacles.add(murM1);
        Var.obstacles.add(cube1);

        Shape sol3 = CreerObjet.creerMur(Var.scene.getWidth() * 0.26, Var.scene.getHeight() * 0.028, 0, Var.scene.getHeight() * 0.67, Color.BLACK);
        Var.obstacles.add(sol3);

        Var.root.getChildren().addAll(p, sol1, sol2, sol3, bouton1, murM1, cube1);

    }

}
