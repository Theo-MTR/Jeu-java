package jeu.gestion;

import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import jeu.gui.Main;
import jeu.objets.*;



public class Collision {
    public Collision() {
        //Collision personnage
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                for (Shape s : Var.obstacles) {
                    if (Var.personnage.intersects(s.getBoundsInParent())) {
                        /* Enlever gravité generale */
                        //if (!(s instanceof Bouton)) Var.personnage.setEnGravite(false);
                        if (!(s instanceof Cube)) {
                            Var.personnage.setCollisionAvec(s); //Enregistrer objet en collision avec le personnage (utile pour la gestion de la gravité par exemple)
                        }
                        if (s instanceof Bouton && !((Bouton) s).isOn()) ActionPersonBouton((Bouton) s); //Activer animation bouton lorsqu'il y a collision avec un bouton
                        //else if (s instanceof Cube && !Var.toucheT); // ActionPersoCube();
                        else if (s instanceof MurBlanc) ActionPersoMurBlanc((MurBlanc) s); //Action realisé lorsque le personnage entre en collision avec un portail
                        else if (s instanceof Cube && Var.toucheT && Var.cubeSelect == null) Var.cubeSelect = (Cube) s; //Enregistrer le cube entrain d'être porté
                        else if (s instanceof Mur || s instanceof MurMovible) ActionPersoMur(s); //Action lorsqu'on entre en collision avec un mur
                        else if (s instanceof Scie) actionPiege(); //Action quaand on rentre en collision avec une scie
                        else if (s instanceof Porte) {
                            niveauSuivant(((Porte)s));
                            break;
                        }
                    }
                }
            }
        };
        //Collision cube
        AnimationTimer timer2 = new AnimationTimer() {
            @Override
            public void handle(long l) {
                for (Shape s : Var.obstacles) {
                    if (Var.cubeSelect != null && !(s instanceof Cube) && Var.cubeSelect.intersects(s.getBoundsInParent())) { //Ne pas oublier de verifier qu'il y a un cube de selectionné sinon erreur
                        if (!(s instanceof MurBlanc) && !(s instanceof Bouton)) { // Arreter la chute du cube lorsqu'il entre en collision avec un objet autre qu'un portail ou un bouton
                            Var.cubeSelect.setEnGravite(false);
                            replacerCube(s);
                        }
                        if (s instanceof Bouton && !((Bouton) s).isOn()) ActionCubeBouton((Bouton) s); //Activer bouton lorsq'un cube entre en collision avec
                    }
                }
            }
        };
        timer.start();
        timer2.start();
    }

    //Replacer le cube lorsqu'il est en collision
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
    private void ActionPersoMur(Shape s) {
        if (Var.personnage.getX() < s.getBoundsInParent().getMinX() && Var.personnage.getY() > s.getBoundsInParent().getMinY()) {
            Var.toucheD = false;
            Var.personnage.setX(Var.personnage.getX() - 10);
            Var.personnage.setEnGravite(true);
        }
        else if (Var.personnage.getX() + Var.personnage.getWidth() > s.getBoundsInParent().getMaxX() && Var.personnage.getY() > s.getBoundsInParent().getMinY()) {
            Var.toucheQ = false;
            Var.personnage.setX(Var.personnage.getX() + 10);
            Var.personnage.setEnGravite(true);
        }
        if (Var.personnage.getY() + Var.personnage.getHeight() > s.getBoundsInParent().getMaxY()) {
            Var.personnage.setY(Var.personnage.getEtatInitial());
        }
        else if (Var.personnage.getY() <= s.getBoundsInParent().getMinY()) {
            Var.personnage.setEnGravite(false);
            Var.personnage.setEtatInitial(s.getBoundsInParent().getMinY() - Var.personnage.getHeight());
            Var.personnage.setY(Var.personnage.getEtatInitial());

        }

    }

    //Action quand le perso entre en collision avec un bouton
    private void ActionPersonBouton(Bouton s) {
        Var.personnage.setEnGravite(false);
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
            if (s == Var.murEntree && Var.murSortie != null) {
                if (Var.murSortie.getOrientation().equals("d")) {
                    Var.personnage.setEtatInitial(Var.murSortie.getY() + Var.murSortie.getHeight() - Var.personnage.getHeight() - 1);
                    Var.personnage.setX(Var.murSortie.getX() + Var.murSortie.getWidth() + 10);
                } if (Var.murSortie.getOrientation().equals("g")) {
                    Var.personnage.setEtatInitial(Var.murSortie.getY() + Var.murSortie.getHeight() - Var.personnage.getHeight() - 1);
                    Var.personnage.setX(Var.murSortie.getX() - Var.personnage.getWidth() - 1);
                }
                if (Var.murSortie.getOrientation().equals("b")) {
                    Var.personnage.setX(Var.murSortie.getX() + Var.personnage.getWidth() - 1);
                    Var.personnage.setEtatInitial(Var.murSortie.getY() + Var.murSortie.getHeight() + 1);
                    Var.personnage.setEnGravite(true);
                }
                Var.personnage.setY(Var.personnage.getEtatInitial());
                Var.murEntree.setFill(Color.WHITE);
                Var.murSortie.setFill(Color.WHITE);
                Var.murSortie = null;
                Var.murEntree = null;
        }
    }

    private void niveauSuivant(Porte p) {
        if (Var.personnage.getX() > p.getX()) {
            switch (p.getNiveau()) {
                case 1:
                    Var.n1 = true;
                    System.out.println("ok");
                    break;
                case 2:
                    Var.n2 = true;
                    break;
            }
            Main.demarrer();
        }
    }
    private void actionPiege() {
        if (Var.toucheQ) {
            Var.personnage.setX(Var.personnage.getX() + Var.personnage.getWidth() + 10);
        }
        else if (Var.toucheD) {
            Var.personnage.setX(Var.personnage.getX() - 10);
        }
        Var.personnage.setVie(Var.personnage.getVie() - ((Scie) Var.personnage.getCollisionAvec()).getVie());
        Var.l.setText(String.valueOf(Var.personnage.getVie()));
        configVie();
        Var.toucheQ = false;
        Var.toucheD = false;
        Var.personnage.setCollisionAvec(null);
    }

    private void configVie() {
        if (Var.personnage.getVie() <= 40) {
            Var.l.setTextFill(Color.RED);
        }
    }

}
