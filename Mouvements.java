package jeu;

import javafx.animation.AnimationTimer;

public class Mouvements {

    private static final double VITESSE_MARCHE = 10;


    public Mouvements() {
        AnimationTimer timer = new AnimationTimer() {
            public void handle(long l) {
                if (Var.toucheD) Var.personnage.setX(Var.personnage.getX() + VITESSE_MARCHE);
                if (Var.toucheQ) Var.personnage.setX(Var.personnage.getX() - VITESSE_MARCHE);
                if (Var.toucheSaut) {
                    Var.personnage.setY(Var.personnage.getY() + Var.vitesseG);
                    if (Var.personnage.isEnGravite()) Var.toucheSaut = false;
                }
                /*
                if (Var.cubeSelect != null) {
                    if (Var.toucheT) {
                        Var.cubeSelect.setY(Var.personnage.getY() + Var.personnage.getHeight() * 0.4);
                        Var.cubeSelect.setX(Var.personnage.getX() + Var.personnage.getWidth());
                    } else {
                        Var.cubeSelect.setY(((Cube) Var.personnage.getCollisionAvec()).getY());
                        Var.cubeSelect.setX(((Cube) Var.personnage.getCollisionAvec()).getX());
                    }
                }
                */
            }
        };
        timer.start();
    }
}
