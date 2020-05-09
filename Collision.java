package application;

import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Collision {

    //Tester collision entre 2 objets uniques
    public static boolean checkCollision(Shape a, Shape b) {
        return Shape.intersect(a, b).getBoundsInLocal().getWidth() != -1;
    }

    public static void checkCollision(Rect a, Rectangle[] b) {
        for (Rectangle s : b) {
            if (checkCollision(a, s)) {
                a.setCollision(true);
                a.setGravity(false);
                replacer(a, s);
            } else if ((a.getX() + a.getWidth() < s.getX() || a.getX() > s.getX() + s.getWidth()) && a.getY() + a.getHeight() <= s.getY()) a.setGravity(true);
        }
    }

    public static void replacer(Rect a, Rectangle b) {
        if (a.getY() + a.getHeight() > b.getY() + b.getHeight()) {
            a.setY(a.getEtatInitial()); // Permet de reinitialiser l'emplacement en fonction de l'endroit de la collision
        }
        else {
            a.setY(b.getY() - a.getHeight());
        }
    }


}
