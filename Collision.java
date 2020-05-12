package jeu;

import javafx.animation.AnimationTimer;
import javafx.scene.shape.Shape;

public class Collision {
    public Collision() {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                for (Shape s : Var.obstacles) {
                    if (Shape.intersect(Var.personnage, s).getBoundsInLocal().getWidth() != -1) {
                        Var.personnage.setCollisionAvec(s);
                        Var.personnage.setEnGravite(false);
                        replacer(s);
                    }
                }
                if (Var.personnage.getCollisionAvec() != null && (Var.personnage.getX() + Var.personnage.getWidth() < Var.personnage.getCollisionAvec().getBoundsInLocal().getMinX() || Var.personnage.getX() > Var.personnage.getCollisionAvec().getBoundsInLocal().getMaxX()) && Var.personnage.getY() + Var.personnage.getHeight() <= Var.personnage.getCollisionAvec().getBoundsInLocal().getMinY()) {
                    Var.personnage.setEnGravite(true);
                    Var.personnage.setCollisionAvec(null);
                }
            }
        };
        timer.start();
    }

    private void replacer(Shape s) {
        if (Var.personnage.getY() + Var.personnage.getHeight() > s.getBoundsInParent().getMinY()) {
            Var.personnage.setEtatInitial(s.getBoundsInLocal().getMinY() - Var.personnage.getHeight());
        }
        Var.personnage.setY(Var.personnage.getEtatInitial());
    }
}
