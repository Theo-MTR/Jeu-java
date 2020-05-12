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
                    Var.TS--;
                    if (Var.TS <= 0) Var.toucheSaut = false;
                }
            }
        };
        timer.start();
    }
}
