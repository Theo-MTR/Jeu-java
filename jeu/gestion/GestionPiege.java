package jeu.gestion;

import javafx.animation.AnimationTimer;
import javafx.scene.shape.Shape;
import jeu.objets.Laser;
import jeu.objets.MurMovible;

public class GestionPiege {

    public static final int VITESSE_CHUTE_MUR = 3;

    public GestionPiege() {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                for (Shape s : Var.obstacles) {
                    //Gestion mur movible
                    if (s instanceof MurMovible) {
                        if (((MurMovible) s).getOrientation().equals("v")) {
                            if (((MurMovible) s).getBouton().isOn() && ((MurMovible) s).getHeight() > 0) {
                                ((MurMovible) s).setY(((MurMovible) s).getY() + VITESSE_CHUTE_MUR);
                                ((MurMovible) s).setHeight(((MurMovible) s).getHeight() - VITESSE_CHUTE_MUR);
                            } else if (!((MurMovible) s).getBouton().isOn() && ((MurMovible) s).getPositionInitiale() < ((MurMovible) s).getY()) {
                                ((MurMovible) s).setY(((MurMovible) s).getY() - VITESSE_CHUTE_MUR);
                                ((MurMovible) s).setHeight(((MurMovible) s).getHeight() + VITESSE_CHUTE_MUR);
                            }
                        } else {
                            if (((MurMovible) s).getBouton().isOn() && ((MurMovible) s).getWidth() > 0) {
                                ((MurMovible) s).setWidth(((MurMovible) s).getWidth() - VITESSE_CHUTE_MUR);
                            } else if (!((MurMovible) s).getBouton().isOn() && ((MurMovible) s).getPositionInitiale() > ((MurMovible) s).getX() + ((MurMovible) s).getWidth()) {
                                ((MurMovible) s).setWidth(((MurMovible) s).getWidth() + VITESSE_CHUTE_MUR);
                            }
                        }
                    }
                    //Gestion des lasers
                    else if (s instanceof Laser) {
                        if (((Laser) s).getBouton().isOn() && ((Laser) s).getHeight() > 0) {
                            ((Laser) s).setY(0);
                            ((Laser) s).setHeight(0);
                        } else if (!((Laser) s).getBouton().isOn() && ((Laser) s).getPositionInitiale() > ((Laser) s).getY()) {
                            ((Laser) s).setY(((Laser) s).getPositionInitiale());
                            ((Laser) s).setHeight(((Laser) s).getHauteurI());
                        }
                    }
                }
            }
        };
        timer.start();
    }
}
