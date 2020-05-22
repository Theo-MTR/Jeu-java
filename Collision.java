package jeu;

import javafx.animation.AnimationTimer;
import javafx.scene.shape.Shape;

public class Collision {
    public Collision() {
        //Collision personnage
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                for (Shape s : Var.obstacles) {
                    if (Var.personnage.intersects(s.getBoundsInParent())) {
                        if (!(s instanceof Bouton)) Var.personnage.setEnGravite(false);
                        if (!(s instanceof Cube)) Var.personnage.setCollisionAvec(s);
                        if (s instanceof Bouton && !((Bouton) s).isOn()) ActionPersonBouton((Bouton) s);
                        else if (s instanceof Cube && !Var.toucheT); // ActionPersoCube();
                        else if (s instanceof Cube && Var.toucheT && Var.cubeSelect == null) Var.cubeSelect = (Cube) s;
                        else if (s instanceof Mur) ActionPersoMur((Mur) s);
                        else if (s instanceof MurBlanc) ActionPersoMurBlanc((MurBlanc) s);
                    }
                }
            }
        };

        //Collision cube
        AnimationTimer timer2 = new AnimationTimer() {
            @Override
            public void handle(long l) {
                for (Shape s : Var.obstacles) {
                    if (Var.cubeSelect != null && !(s instanceof Cube) && Var.cubeSelect.intersects(s.getBoundsInParent())) {
                        if (!(s instanceof MurBlanc) && !(s instanceof Bouton)) {
                            Var.cubeSelect.setEnGravite(false);
                            replacerCube(s);
                        }
                        if (s instanceof Bouton && !((Bouton) s).isOn()) ActionCubeBouton((Bouton) s);
                    }
                }
            }
        };
        timer.start();
        timer2.start();
    }

    private void replacerCube(Shape s) {
        if (Var.personnage.getX() < s.getBoundsInParent().getMinX()) {
            Var.toucheD = false;
            Var.personnage.setX(Var.personnage.getX() - 10);
            Var.cubeSelect.setX(Var.personnage.getX() + Var.personnage.getWidth() + 1);
        }
        else if (Var.personnage.getX() + Var.personnage.getWidth() > s.getBoundsInParent().getMaxX()) {
            Var.toucheQ = false;
            Var.personnage.setX(Var.personnage.getX() + 10);
            Var.cubeSelect.setX(Var.personnage.getX() - Var.cubeSelect.getWidth() - 1);
        }
        else {
            Var.cubeSelect.setY(s.getBoundsInParent().getMinY() - Var.cubeSelect.getHeight() - 1);
            Var.cubeSelect = null;
        }
    }


    //Action quand le perso entre en collision avec un mur
    private void ActionPersoMur(Mur s) {
        if (Var.personnage.getX() < s.getX() && Var.personnage.getY() > s.getY()) {
            Var.toucheD = false;
            Var.personnage.setX(Var.personnage.getX() - 10);
        }
        else if (Var.personnage.getX() + Var.personnage.getWidth() > s.getX() + s.getWidth() && Var.personnage.getY() > s.getY()) {
            Var.toucheQ = false;
            Var.personnage.setX(Var.personnage.getX() + 10);
        }
        if (Var.personnage.getY() <= s.getY()) {
            Var.personnage.setEtatInitial(s.getY() - Var.personnage.getHeight());
        }
        Var.personnage.setY(Var.personnage.getEtatInitial());
    }

    //Action quand le perso entre en collision avec un bouton
    private void ActionPersonBouton(Bouton s) {
        s.setOn(true);
        Animation.animBouton(s);
        Var.personnage.setEtatInitial(s.getY() - Var.personnage.getHeight());
        Var.personnage.setY(Var.personnage.getEtatInitial());
    }

    //Action qaund un cube entre en collision avec un bouton
    private void ActionCubeBouton(Bouton s) {
        s.setOn(true);
        Animation.animBouton(s);
        if (Var.cubeSelect.getY() < s.getY()) {
            Var.cubeSelect.setY(s.getY() - Var.cubeSelect.getHeight() - 1);
            Var.cubeSelect.setEnGravite(false);
            Var.cubeSelect = null;
        }
    }

    //Action quand le perso entre en collision avec un mur blanc (avec et sans portail)
    private void ActionPersoMurBlanc(MurBlanc s) {
        if (s instanceof MurBlanc) {
            if (s == Var.murEntree && Var.murSortie != null) {
                if (Var.murSortie.getOrientation().equals("d")) {
                    Var.personnage.setX(Var.murSortie.getX() + Var.murSortie.getWidth() + 1);
                    Var.personnage.setY(Var.murSortie.getY() + Var.murSortie.getHeight() - Var.personnage.getHeight());
                } else if (Var.murSortie.getOrientation().equals("g")) {
                    Var.personnage.setX(Var.murSortie.getX() - Var.personnage.getWidth() - 1);
                    Var.personnage.setY(Var.murSortie.getY() + Var.murSortie.getHeight() - Var.personnage.getHeight());
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
