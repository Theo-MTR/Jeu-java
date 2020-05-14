package jeu;

import javafx.animation.AnimationTimer;
import javafx.scene.shape.Path;
import javafx.scene.shape.Shape;

public class Collision {

    public Collision() {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                for (Shape s : Var.obstacles) {
                    //Detection de collision
                    if (((Path) Shape.intersect(Var.personnage, s)).getElements().size() > 0) {
                        //gestion des boutons
                        if (s instanceof Bouton && !((Bouton) s).isOn()) {
                            ((Bouton) s).setOn(true);
                            Animation.animBouton((Bouton) s);
                        }
                        //Gestion des chutes
                        if (!(s instanceof Bouton)) {
                            Var.personnage.setEnGravite(false);
                        }
                        Var.personnage.setCollisionAvec(s);
                        replacer(s);
                    }
                }

            }
        };
        timer.start();
    }

    private void replacer(Shape s) {
        //Replacer le personnage en fonction de s'il est au dessus ou au dessous d'une plateforme
        if (!(s instanceof Cube) && Var.personnage.getY() <= s.getBoundsInParent().getMinY() ) {
            Var.personnage.setEtatInitial(s.getBoundsInLocal().getMinY() - Var.personnage.getHeight());
        }
        //Replacer personnage quand il arrive sur un cube
        else if ((s instanceof Cube) && Var.personnage.getY() + Var.personnage.getHeight() >= s.getBoundsInLocal().getMinY() && Var.personnage.getY() + Var.personnage.getHeight() < s.getBoundsInLocal().getMaxY()) {
            Var.personnage.setEtatInitial(s.getBoundsInLocal().getMinY() - Var.personnage.getHeight());
        }
        Var.personnage.setY(Var.personnage.getEtatInitial());
        //Replacer personnage si il arrive a droite ou a gauche d'un mur et l'empecher d'avancer
        if (s instanceof MurMovible) {
            if (Var.personnage.getX() + Var.personnage.getWidth() >= s.getBoundsInParent().getMinX()) {
                Var.toucheD = false;
                Var.personnage.setX(Var.personnage.getX() - 10);
            }
            else if (Var.personnage.getX() >= s.getBoundsInParent().getMaxX()) {
                Var.toucheQ = false;
                Var.personnage.setX(Var.personnage.getX() + 10);
            }

        }
    }
}
