package jeu.gestion;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import jeu.objets.Bouton;
import jeu.objets.MurBlanc;

import java.nio.file.Paths;

public class Touche {

    private static final double VS = Var.scene.getHeight() * 0.0285; //Vitesse de saut
    private static final double VITESSE_MARCHE = Var.scene.getWidth() * 0.005; //Vitesse de marche

    //Image utilisée quand le perso va vers la droite
    private static final Image d = new Image(Paths.get("Ressources/AvanceDroite.png").toUri().toString());
    private static final Paint droite = new ImagePattern(d);

    //Image utilisée quand le perso va vers la gauche
    private static final Image g = new Image(Paths.get("Ressources/AvanceGauche.png").toUri().toString());
    private static final Paint gauche = new ImagePattern(g);

    //Image utiliser quand le perso ne bouge pas
    private static final Image f = new Image(Paths.get("Ressources/Face.png").toUri().toString());
    private static final Paint face = new ImagePattern(f);

    //Timer
    static AnimationTimer timer;

    //Etat des touches
    private static boolean toucheD = false;
    private static boolean toucheQ = false;
    private static boolean toucheT = false;
    private static boolean toucheSaut = false;

    public static void etatTouche() {

        Var.personnage.setOnKeyPressed(ke -> {
            if (ke.getText().toUpperCase().equals("D")) {
                toucheD = true; //SI ON APPUIE SUR "D" ALORS Var.toucheD PASSE A TRUE
            }
            if (ke.getText().toUpperCase().equals("Q")) {
                toucheQ = true; //SI ON APPUIE SUR "D" ALORS Var.toucheD PASSE A TRUE
            }
            if (ke.getText().toUpperCase().equals("I")) {
                System.out.println(Var.personnage.getEtatInitial());
            }
            if (ke.getText().toUpperCase().equals("T")) {
                toucheT = !toucheT; //La variable qui enregistre l'etat de T prendra le contraire de ce qu'elle a actuellement
            }
            /*if (ke.getCode() == KeyCode.SPACE) {
                Var.toucheSaut = true;
                new Thread(new thread()).start();
            }*/
        });

        Var.personnage.setOnKeyReleased(ke -> {
            if (ke.getText().toUpperCase().equals("D")) {
                toucheD = false; //SI ON LACHE "D" ALORS toucheD PASSE A FALSE
            } else if (ke.getText().toUpperCase().equals("Q")) {
                toucheQ = false; //SI ON APPUIE SUR "D" ALORS Var.toucheD PASSE A TRUE
            } else if (ke.getCode() == KeyCode.SPACE && !Var.personnage.isEnGravite()) {
                Var.personnage.setEtatInitial(Var.personnage.getY());
                toucheSaut = true;
                Var.personnage.setEnGravite(true);
                Gravite.setVitesseG(-VS);
            }

        });

    }

    public static void ActionTouche() {
        timer = new AnimationTimer() {
            public void handle(long l) {
                if (toucheD && Var.personnage.getX() + Var.personnage.getWidth() < Var.scene.getWidth()) { //Si la touche d est activé et que le personnage ne depasse pas l'ecran ( que son X maximum soit inferieur a la longeur de l'ecran)...
                    Var.personnage.setX(Var.personnage.getX() + VITESSE_MARCHE); //On augmente la valeur de X du personnage pour le faire avancer
                    Var.personnage.setFill(droite); //On change l'image
                } else if (toucheQ && Var.personnage.getX() > Var.scene.getX()) {  //Si la touche q est activé et que le personnage ne depasse pas l'ecran ( que son X minimum soit superieur au x de l'ecran)...
                    Var.personnage.setX(Var.personnage.getX() - VITESSE_MARCHE); //On diminue la valeur X du personnage
                    Var.personnage.setFill(gauche); //On change l'image
                } else if (toucheSaut) { //Si on saute..
                    /*
                    On va reutiliser la variable vitesse g. En appuyant sur la touche saut (voir touche.java), on a modifié la valeur de la vitesseG avec une valeur négative.
                    Puisque la gravité est toujours activé, avec une valeur négative le personnage va réalisé le saut (c'est un peu comme s'il volait)
                    Mais la gravité va augmenter a chaque frame, donc à un moment elle redeviendra positive, c'est le moment ou le personnage commencera à retomber. ET c'est donc ce qui nous permet de sauter
                     */
                    Var.personnage.setY(Var.personnage.getY() + Gravite.getVitesseG());
                    if (Var.personnage.isEnGravite())
                        toucheSaut = false; //Eviter de resauter alors qu'on saute déjà
                    if (Var.personnage.getCollisionAvec() != null && Var.personnage.getCollisionAvec() instanceof Bouton) { //Si c'est un bouton et qu'on saute on veut le désactiver donc...
                        ((Bouton) Var.personnage.getCollisionAvec()).setOn(false); //On le met sur off (false)
                        Animation.animBouton((Bouton) Var.personnage.getCollisionAvec()); //Et on lance son animation
                    }
                }
                if (!toucheD && !toucheQ) Var.personnage.setFill(face);

                if (Var.personnage.getCubeSelect() != null) { //Verifier qu'il y a bien un cube en collision avec le personnage sinon erreur
                    if (toucheT) { //Si la touche t est active
                        if (toucheD) { //ici on fait en sorte que le cube soit sur la droite (vu qu'il va à droite) du perso et au niveau de sa hauteur tous le temps, comme ça c'est comme ci le personnage le portait
                            Var.personnage.getCubeSelect().setY(Var.personnage.getY() + Var.personnage.getHeight() * 0.4);
                            Var.personnage.getCubeSelect().setX(Var.personnage.getX() + Var.personnage.getWidth() + 1);
                        } else if (toucheQ) {//ici on fait en sorte que le cube soit sur la gauche (vu qu'il va à gauche) du perso et au niveau de sa hauteur tous le temps, comme ça c'est comme ci le personnage le portait
                            Var.personnage.getCubeSelect().setY(Var.personnage.getY() + Var.personnage.getHeight() * 0.4);
                            Var.personnage.getCubeSelect().setX(Var.personnage.getX() - Var.personnage.getCubeSelect().getWidth() - 1);
                        } else Var.personnage.getCubeSelect().setY(Var.personnage.getY() + Var.personnage.getHeight() * 0.4);
                    } else
                        Var.personnage.getCubeSelect().setEnGravite(true); //Si la touche T est desactivé mais q'un cube a été selectionné alors on le met en gravité car ça signifie qu'il a été laché
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

    public static void stopTimer() {
        timer.stop();
    }

    public static void setToucheD(boolean toucheD) {
        Touche.toucheD = toucheD;
    }

    public static void setToucheQ(boolean toucheQ) {
        Touche.toucheQ = toucheQ;
    }

    public static void setToucheT(boolean toucheT) {
        Touche.toucheT = toucheT;
    }

    public static boolean isToucheD() {
        return toucheD;
    }

    public static boolean isToucheQ() {
        return toucheQ;
    }

    public static boolean isToucheT() {
        return toucheT;
    }
}
