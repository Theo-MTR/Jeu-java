package jeu.objets;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.nio.file.Paths;

public class Scie extends Circle {

    private static final Image i = new Image(Paths.get("Ressources/scie.png").toUri().toString());
    private static final Paint ip = new ImagePattern(i);

    private final int vie;

    public Scie(double ratio_x, int vie, Shape sol) {
        super();
        setCenterX(sol.getBoundsInParent().getMinX() + sol.getBoundsInParent().getWidth() * ratio_x);
        setCenterY((sol.getBoundsInParent().getMinY() + sol.getBoundsInParent().getMaxY()) / 2);
        setRadius(50.0f);
        setFill(ip);
        this.vie = vie;
        Rotation();
    }

    private void Rotation() {
        RotateTransition rotate = new RotateTransition();
        rotate.setAxis(Rotate.Z_AXIS);
        rotate.setByAngle(-360);
        rotate.setCycleCount(-1);
        rotate.setDuration(Duration.millis(3000));
        rotate.setAutoReverse(false);
        rotate.setInterpolator(Interpolator.LINEAR);
        rotate.setNode(this);
        rotate.playFromStart();
    }

    public int getVie() {
        return vie;
    }
}
