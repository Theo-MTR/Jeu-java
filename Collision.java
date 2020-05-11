package jeux;
/*
SetCollision ne sert a rien
 */
import javafx.scene.shape.Shape;

public class Collision {

    //Tester collision entre 2 objets uniques
    public static boolean checkCollision(Shape a, Shape b) {
        return Shape.intersect(a, b).getBoundsInLocal().getWidth() != -1;
    }

    public static void checkCollision(Rect a, Rect[] b) {
        for (Rect s : b) {
            if (checkCollision(a, s)) {
                a.setCollision(true);
                a.setCollisionWith(s);
                a.setGravity(false);
                replacer(a, s);
            }
        }
        if (a.getCollisionWith() != null && (a.getX() + a.getWidth() < a.getCollisionWith().getX() || a.getX() > a.getCollisionWith().getX() + a.getCollisionWith().getWidth()) && a.getY() + a.getHeight() <= a.getCollisionWith().getY()) {
            a.setGravity(true);
            a.setCollisionWith(null);
        }
    }

    public static void replacer(Rect a, Rect b) {
        if (!(b.isMovible())) {
            if (a.getY() + a.getHeight() <= b.getY() + b.getHeight()) {
                a.setEtatInitial(b.getY() - a.getHeight());
            }
            a.setY(a.getEtatInitial()); // Permet de reinitialiser l'emplacement en fonction de l'endroit de la collision
        }
        else if (b.isMovible() && a.getY() + a.getHeight() < a.getCollisionWith().getY() - a.getCollisionWith().getHeight() && ( a.getX() + a.getWidth() >= a.getCollisionWith().getX() || a.getX() <= a.getCollisionWith().getX() + a.getCollisionWith().getWidth() )) {
            a.setEtatInitial(b.getY() - a.getHeight() - 1);
            a.setY(a.getEtatInitial());

        }
    }


}
