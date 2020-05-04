package application;

import javafx.scene.paint.Paint;
import javafx.scene.shape.*;

public class Obstacles {
	
	public static Rectangle createRectangle(double width, double height, double x, double y, boolean traversable, Paint fill) {
		Rectangle r = new Rectangle();
		r.setWidth(width);
		r.setHeight(height);
		r.setX(x);
		r.setY(y);
		r.setFill(fill);
		r.setFocusTraversable(traversable);
		return r;
	}
	
}
