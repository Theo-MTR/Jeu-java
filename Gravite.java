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
                //Faire tomber le personnage lorsqu'il quitte une plateforme
                if ((Var.personnage.getCollisionAvec() != null) && (Var.personnage.getX() + Var.personnage.getWidth() < Var.personnage.getCollisionAvec().getBoundsInLocal().getMinX() || Var.personnage.getX() > Var.personnage.getCollisionAvec().getBoundsInLocal().getMaxX()) && Var.personnage.getY() + Var.personnage.getHeight() <= Var.personnage.getCollisionAvec().getBoundsInLocal().getMinY()) {
                    if (Var.personnage.getCollisionAvec() instanceof Bouton) {
                        ((Bouton) Var.personnage.getCollisionAvec()).setOn(false);
                        Animation.animBouton((Bouton)Var.personnage.getCollisionAvec());
                    }
                    Var.personnage.setEnGravite(true);
                    Var.personnage.setCollisionAvec(null);
                }
            }
        };
        timer.start();

    }
}
