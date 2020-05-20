package jeu;

import javafx.animation.AnimationTimer;
import javafx.scene.shape.Path;
import javafx.scene.shape.Shape;

public class Collision {

    public Collision() {
        //Detection de collision avec le personnage
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                for (Shape s : Var.obstacles) {
                    //Gestion de collision cube
                    if (Var.cubeSelect != null && !(s instanceof Cube) && Var.cubeSelect.intersects(s.getBoundsInParent())) {
                        if (s instanceof Bouton && !((Bouton) s).isOn()) {
                            ((Bouton) s).setOn(true);
                            Animation.animBouton((Bouton) s);
                        }
                        if (!(s instanceof MurBlanc)) {
                            Var.cubeSelect.setEnGravite(false);
                            replacerCube(s);
                        }
                    }
                    //Detection de collision personnage
                    if (Var.personnage.getBoundsInParent().intersects(s.getBoundsInParent())) {
                        //gestion des boutons
                        if (s instanceof Bouton && !((Bouton) s).isOn()) {
                            ((Bouton) s).setOn(true);
                            Animation.animBouton((Bouton) s);
                        }
                        //Gestion des chutes
                        if (!(s instanceof Bouton)) {
                            Var.personnage.setEnGravite(false);
                        }
                        if (s instanceof Cube && Var.toucheT && Var.cubeSelect == null) {
                            Var.cubeSelect = (Cube) s;
                        }
                        if (!(s instanceof Cube)) {
                            Var.personnage.setCollisionAvec(s);
                        }
                        replacer(s);
                    }

                }

            }
        };
        timer.start();
    }

    private void replacerCube(Shape s) {
        Var.cubeSelect.setY(s.getBoundsInParent().getMinY() - Var.cubeSelect.getHeight());
        Var.cubeSelect = null;
    }

    private void replacer(Shape s) {
        //Replacer le personnage en fonction de s'il est au dessus ou au dessous d'une plateforme
        if (!(s instanceof Cube) && Var.personnage.getY() <= s.getBoundsInParent().getMinY()) {
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
            if (Var.personnage.getY() > s.getBoundsInParent().getMinY()) {
                if (Var.personnage.getX() < s.getBoundsInParent().getMinX()) {
                    if (s instanceof Laser) Var.personnage.setVie(Var.personnage.getVie() - ((Laser) s).getVie());
                    Var.toucheD = false;
                    Var.personnage.setX(Var.personnage.getX() - 10);
                }
                if (Var.personnage.getX() + Var.personnage.getWidth() > s.getBoundsInParent().getMaxX()) {
                    Var.toucheQ = false;
                    Var.personnage.setX(Var.personnage.getX() + 10);
                }

            }
        }
        if (s instanceof MurBlanc) {
            if (s == Var.murEntree && Var.murSortie != null) {
                if (Var.murSortie.getOrientation().equals("d")) {
                    Var.personnage.setX(Var.murSortie.getX() + Var.murSortie.getWidth() + 1);
                    Var.personnage.setY(Var.murSortie.getY() + Var.murSortie.getHeight() - Var.personnage.getHeight());
                    if (Var.cubeSelect != null) {
                        Var.cubeSelect.setY(Var.personnage.getY() + Var.personnage.getHeight() * 0.4);
                        Var.cubeSelect.setX(Var.personnage.getX() + Var.personnage.getWidth() + 1);
                    }
                } else if (Var.murSortie.getOrientation().equals("g")) {
                    Var.personnage.setX(Var.murSortie.getX() - Var.personnage.getWidth() - 1);
                    Var.personnage.setY(Var.murSortie.getY() + Var.murSortie.getHeight() - Var.personnage.getHeight());
                    if (Var.cubeSelect != null) {
                        Var.cubeSelect.setY(Var.personnage.getY() + Var.personnage.getHeight() * 0.4);
                        Var.cubeSelect.setX(Var.personnage.getX() - Var.cubeSelect.getWidth() - 1);
                    }
                }
            }
            else {
                if (Var.personnage.getX() < s.getBoundsInParent().getMinX()) {
                    Var.toucheD = false;
                    Var.personnage.setX(Var.personnage.getX() - 10);
                }
                if (Var.personnage.getX() + Var.personnage.getWidth() > s.getBoundsInParent().getMaxX()) {
                    Var.toucheQ = false;
                    Var.personnage.setX(Var.personnage.getX() + 10);
                }
            }
        }
    }
}
