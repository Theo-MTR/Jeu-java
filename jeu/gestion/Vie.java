package jeu.gestion;

import javafx.animation.AnimationTimer;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import jeu.gui.Main;


public class Vie {


    //Timer
    private static AnimationTimer timer;
    private static Label l;

    public static void configVie() {

        l = new Label(String.valueOf(Var.personnage.getVie()));
        l.setFont(new Font("Arial", 30));
        l.setTextFill(Color.GREEN);
        l.setTranslateX(Var.scene.getWidth() * 0.95);
        l.setTranslateY(Var.scene.getHeight() * 0.02);
        Var.root.getChildren().add(l);


        //le timer nous permet d'executer le code qui suit à chaque frame
        timer = new AnimationTimer() {
            public void handle(long l) {
                if (Var.personnage.getVie() <= 0) {
                    Var.root.getChildren().removeAll(Var.root.getChildren());
                    Var.init();
                    Var.demmare = true;
                    Touche.stopTimer();
                    Gravite.stopTimer();
                    GestionPiege.stopTimer();
                    Collision.stopTimer();
                    Vie.stopTimer();
                    Main.demarrer();
                }
            }
        };
        timer.start();
    }

    public static void MajVie() {
        l.setText(String.valueOf(Var.personnage.getVie())); //On change le texte du label vie pour qu'il corresponde a la nouvelle valeur
        // Empecher de pouvoir avancer pour eviter de perdre toute sa vie d'un coup
        Touche.setToucheQ(false);
        Touche.setToucheD(false);
        Var.personnage.setCollisionAvec(null); //On remet la variable qui enregistre l'objet en collision a null car c'est inutile de garder le piege en memoire
        if (Var.personnage.getVie() <= 40) {
            l.setTextFill(Color.RED);
        }
    }

    public static void stopTimer() {
        timer.stop();
    }
}
