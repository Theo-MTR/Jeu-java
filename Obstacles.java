package jeux;

import javafx.scene.paint.Paint;

public class Obstacles {

    public static Rect createRectangle(double width, double height, double x, double y, boolean traversable, boolean isMovible, Paint fill) {
        Rect r = new Rect();
        r.setWidth(width);
        r.setHeight(height);
        r.setX(x);
        r.setY(y);
        r.setFill(fill);
        r.setFocusTraversable(traversable);
        r.setMovible(isMovible);
        return r;
    }

}
