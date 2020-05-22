package jeu;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Mouvements {

    private static final double VITESSE_MARCHE = 10;

    Image d = new Image("AvanceDroite.png");
    Paint droite = new ImagePattern(d);

    Image g = new Image("AvanceGauche.png");
    Paint gauche = new ImagePattern(g);

    public Mouvements() {

        Rectangle r1 = new Rectangle();
        Rectangle r2 = new Rectangle();
        r1.setX(Var.scene.getWidth() * 0.83);
        r1.setY(Var.scene.getHeight() * 0.02);
        r1.setHeight(Var.scene.getHeight() * 0.02);
        r1.setWidth(Var.scene.getWidth() * 0.15 * (Var.personnage.getVie() / 100));
        r1.setFill(Color.RED);
        Var.barVie = r1;
        Var.root.getChildren().add(r1);

        AnimationTimer timer = new AnimationTimer() {
            public void handle(long l) {
                if (Var.toucheD) {
                    Var.personnage.setX(Var.personnage.getX() + VITESSE_MARCHE);
                    Var.personnage.setFill(droite);
                }
                if (Var.toucheQ) {
                    Var.personnage.setX(Var.personnage.getX() - VITESSE_MARCHE);
                    Var.personnage.setFill(gauche);
                }
                if (Var.toucheSaut) {
                    Var.personnage.setY(Var.personnage.getY() + Var.vitesseG);
                    if (Var.personnage.isEnGravite()) Var.toucheSaut = false;
                    if (Var.personnage.getCollisionAvec() != null && Var.personnage.getCollisionAvec() instanceof Bouton) {
                        ((Bouton) Var.personnage.getCollisionAvec()).setOn(false);
                        Animation.animBouton((Bouton) Var.personnage.getCollisionAvec());
                    }
                }

                if (Var.cubeSelect != null) {
                    if (Var.toucheT) {
                        if (Var.toucheD) {
                            Var.cubeSelect.setY(Var.personnage.getY() + Var.personnage.getHeight() * 0.4);
                            Var.cubeSelect.setX(Var.personnage.getX() + Var.personnage.getWidth() + 1);
                        }
                        if (Var.toucheQ) {
                            Var.cubeSelect.setY(Var.personnage.getY() + Var.personnage.getHeight() * 0.4);
                            Var.cubeSelect.setX(Var.personnage.getX() - Var.cubeSelect.getWidth() - 1);
                        } else Var.cubeSelect.setY(Var.personnage.getY() + Var.personnage.getHeight() * 0.4);
                    } else Var.cubeSelect.setEnGravite(true);
                }

                //A CHANGER D4EMPLACEMENT
                if (Var.personnage.getVie() <= 0) {
                    ((Stage) (Var.personnage.getScene().getWindow())).close();
                }

                for (MurBlanc mb : Var.listeMurBlanc) {
                    mb.setOnMouseClicked(mouseEvent -> {
                        if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                            if (Var.murEntree == null) {
                                Var.murEntree = mb;
                                mb.setFill(Color.BLUE);
                            } else if (Var.murEntree == mb) {
                                Var.murEntree = null;
                                mb.setFill(Color.WHITE);
                            }
                        } else if (mouseEvent.getButton() == MouseButton.SECONDARY) {
                            if (Var.murSortie == null) {
                                Var.murSortie = mb;
                                mb.setFill(Color.ORANGE);
                            } else if (Var.murSortie == mb) {
                                Var.murSortie = null;
                                mb.setFill(Color.WHITE);
                            }
                        }
                    });
                }


            }
        };
        timer.start();
    }
}
