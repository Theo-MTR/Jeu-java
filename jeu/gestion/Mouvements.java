package jeu.gestion;

import javafx.animation.AnimationTimer;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import jeu.objets.Bouton;
import jeu.objets.MurBlanc;

import java.nio.file.Paths;

public class Mouvements {

    private static final double VITESSE_MARCHE = 10;

    private final Image d = new Image(Paths.get("Ressources/AvanceDroite.png").toUri().toString());
    private final Paint droite = new ImagePattern(d);

    private final Image g = new Image(Paths.get("Ressources/AvanceGauche.png").toUri().toString());
    private final Paint gauche = new ImagePattern(g);

    public Mouvements() {

        Label l = new Label(String.valueOf(Var.personnage.getVie()));
        l.setFont(new Font("Arial", 30));
        l.setTextFill(Color.GREEN);
        l.setTranslateX(Var.scene.getWidth() * 0.95);
        l.setTranslateY(Var.scene.getHeight() * 0.02);
        Var.l = l;
        Var.root.getChildren().add(l);


        AnimationTimer timer = new AnimationTimer() {
            public void handle(long l) {
                if (Var.toucheD && Var.personnage.getX() + Var.personnage.getWidth() < Var.scene.getWidth()) {
                    Var.personnage.setX(Var.personnage.getX() + VITESSE_MARCHE);
                    Var.personnage.setFill(droite);
                }
                else if (Var.toucheQ && Var.personnage.getX() > Var.scene.getX()) {
                    Var.personnage.setX(Var.personnage.getX() - VITESSE_MARCHE);
                    Var.personnage.setFill(gauche);
                }
                else if (Var.toucheSaut) {
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
                        else if (Var.toucheQ) {
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
