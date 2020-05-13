package jeu;

import javafx.animation.AnimationTimer;
import javafx.scene.shape.Shape;

public class GestionPiege {

    public static final int VITESSE_CHUTE_MUR = 3;

    public GestionPiege() {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                for (Shape s : Var.obstacles) {
                    //Gestion mur movible
                    if (s instanceof MurMovible) {
                        if (((MurMovible) s).getBouton().isOn() && ((MurMovible) s).getHeight() > 0) {
                            ((MurMovible) s).setY(((MurMovible) s).getY() + VITESSE_CHUTE_MUR);
                            ((MurMovible) s).setHeight(((MurMovible) s).getHeight() - VITESSE_CHUTE_MUR);
                        }
                        else if (!((MurMovible) s).getBouton().isOn() && ((MurMovible) s).getPositionInitiale() < ((MurMovible) s).getY()) {
                            ((MurMovible) s).setY(((MurMovible) s).getY() - VITESSE_CHUTE_MUR);
                            ((MurMovible) s).setHeight(((MurMovible) s).getHeight() + VITESSE_CHUTE_MUR);
                        }
                    }
                }
            }
        };
        timer.start();
    }
}
