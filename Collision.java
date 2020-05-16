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
                        if (s instanceof Cube && Var.toucheT) {
                            Var.cubeSelect = (Cube) s;
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
            Var.cubeSelect = null;
        }
        Var.personnage.setY(Var.personnage.getEtatInitial());
        //Replacer personnage si il arrive a droite ou a gauche d'un mur et l'empecher d'avancer
        if (s instanceof MurMovible || s instanceof Laser) {
            if (Var.personnage.getX() + Var.personnage.getWidth() >= s.getBoundsInParent().getMinX()) {
                if (s instanceof Laser) Var.personnage.setVie(Var.personnage.getVie() - ((Laser) s).getVie());
                Var.toucheD = false;
                Var.personnage.setX(Var.personnage.getX() - 10);
            }
            if (Var.personnage.getX() >= s.getBoundsInParent().getMaxX()) {
                Var.toucheQ = false;
                Var.personnage.setX(Var.personnage.getX() + 10);
            }

        }
        if (s instanceof MurBlanc && s == Var.murEntree && Var.murSortie != null) {
            Var.personnage.setX(Var.murSortie.getX() + Var.murSortie.getWidth());
            Var.personnage.setY(Var.murSortie.getY() + Var.murSortie.getHeight() - Var.personnage.getHeight());
        }
    }
}
