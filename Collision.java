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
                            animBouton((Bouton) s);
                        }
                        //Gestion des chutes
                        else if (!(s instanceof Bouton)) {
                            Var.personnage.setEnGravite(false);
                        }
                        Var.personnage.setCollisionAvec(s);
                        replacer(s);
                    }
                }

                //Faire tomber le personnage lorsqu'il quitte une plateforme
                if (Var.personnage.getCollisionAvec() != null && (Var.personnage.getX() + Var.personnage.getWidth() < Var.personnage.getCollisionAvec().getBoundsInLocal().getMinX() || Var.personnage.getX() > Var.personnage.getCollisionAvec().getBoundsInLocal().getMaxX()) && Var.personnage.getY() + Var.personnage.getHeight() <= Var.personnage.getCollisionAvec().getBoundsInLocal().getMinY()) {
                    if (Var.personnage.getCollisionAvec() instanceof Bouton) {
                        ((Bouton) Var.personnage.getCollisionAvec()).setOn(false);
                        animBouton((Bouton)Var.personnage.getCollisionAvec());
                    }
                    Var.personnage.setEnGravite(true);
                    Var.personnage.setCollisionAvec(null);
                }
            }
        };
        timer.start();
    }

    private void replacer(Shape s) {
        if (Var.personnage.getY() + Var.personnage.getHeight() <= s.getBoundsInParent().getMaxY()) {
            Var.personnage.setEtatInitial(s.getBoundsInLocal().getMinY() - Var.personnage.getHeight());
        }
        Var.personnage.setY(Var.personnage.getEtatInitial());
    }

    private void animBouton(Bouton s) {
        if (s.isOn()) s.setY(s.getY() + s.getHeight() / 2);
        else s.setY(s.getY() - s.getHeight() / 2);
    }
}
