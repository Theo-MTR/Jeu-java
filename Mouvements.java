package jeu;

import javafx.animation.AnimationTimer;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Mouvements {

    private static final double VITESSE_MARCHE = 10;


    public Mouvements() {
        AnimationTimer timer = new AnimationTimer() {
            public void handle(long l) {
                if (Var.toucheD) Var.personnage.setX(Var.personnage.getX() + VITESSE_MARCHE);
                if (Var.toucheQ) Var.personnage.setX(Var.personnage.getX() - VITESSE_MARCHE);
                if (Var.toucheSaut) {
                    Var.personnage.setY(Var.personnage.getY() + Var.vitesseG);
                    if (Var.personnage.isEnGravite()) Var.toucheSaut = false;
                }

                if (Var.cubeSelect != null) {
                    if (Var.toucheT) {
                        Var.cubeSelect.setY(Var.personnage.getY() + Var.personnage.getHeight() * 0.4);
                        Var.cubeSelect.setX(Var.personnage.getX() + Var.personnage.getWidth());
                    } else {
                        Var.cubeSelect.setY(((Cube) Var.personnage.getCollisionAvec()).getY());
                        Var.cubeSelect.setX(((Cube) Var.personnage.getCollisionAvec()).getX());
                        Var.personnage.setCollisionAvec(null);
                        Var.cubeSelect = null;
                    }
                }

                //A CHANGER D4EMPLACEMENT
                if (Var.personnage.getVie() <= 0) {
                    ((Stage)(Var.personnage.getScene().getWindow())).close();
                }

                for (MurBlanc mb : Var.listeMurBlanc) {
                    mb.setOnMouseClicked(mouseEvent -> {
                        if (mouseEvent.getButton() == MouseButton.PRIMARY && Var.murEntree == null) {
                            Var.murEntree = mb;
                            mb.setFill(Color.BLUE);
                        }
                        else if (mouseEvent.getButton() == MouseButton.SECONDARY && Var.murSortie == null) {
                            Var.murSortie = mb;
                            mb.setFill(Color.ORANGE);
                        }
                    });
                }
            }
        };
        timer.start();
    }
}
