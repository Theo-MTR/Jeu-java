package jeu;

import javafx.animation.AnimationTimer;

public class Gravite {

    private final static int GRAVITE = 2;

    public Gravite() {
        AnimationTimer timer = new AnimationTimer() {
            public void handle(long l) {
                if (Var.personnage.isEnGravite()) {
                    Var.personnage.setY(Var.personnage.getY() + Var.vitesseG);
                    Var.vitesseG = Var.vitesseG + GRAVITE;
                }
                //SI IL Y A COLLISION AVEC UNE PLATEFORME VITESSEG REVIENT A ZERO SINON LE PERSO TOMBERA PLUS VITE A CHAQUE CHUTE
                else {
                    Var.vitesseG = 0;
                }
            }
        };
        timer.start();

    }
}
