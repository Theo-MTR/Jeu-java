/*
Cette classe permet de gérer ce qui sera lié à la vie du personnage.
 */
package jeu.gestion;

import javafx.animation.AnimationTimer;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import jeu.gui.Main;
import jeu.objets.Laser;
import jeu.objets.Pique;
import jeu.objets.Scie;


public class Vie {


    //Timer
    private static AnimationTimer timer;
    private static Label l;

    public static void configVie() {

        //Affichage de la vie en haut à droite
        l = new Label(String.valueOf(Var.personnage.getVie()));
        l.setFont(new Font("Arial", 30));
        l.setTextFill(Color.GREEN);
        l.setTranslateX(Var.scene.getWidth() * 0.95);
        l.setTranslateY(Var.scene.getHeight() * 0.02);
        Var.root.getChildren().add(l);


        //le timer nous permet d'executer le code qui suit à chaque frame
        timer = new AnimationTimer() {
            public void handle(long l) {
                //Si le personnage n'a plus de vie on redemarre le niveau en arretant tous les timers lancés et en relançant la méthode démarrer du fichier Main.java
                //Ce qui va permettre de relancer le niveau de 0, si on arrete pas les timer, ils continueront à fonctionner avec les valeurs du précédent niveau
                //Ce qui va soit provoquer des erreurs (puisque par exemple on utilise des vecteurs mais on les vides à chaque changement / relance du niveau ducoup parcours d'un vecteur vide = erreur)
                //Soit provoquer des bugs, par exemple à chaque mort le perso allez de plus en plus vite car un timer était relancé à chaque fois mais jamais arrété
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

    //Methode qui permet de retirer de la vie au personnage en fonction de l'objet touché.
    //Nous sommes ici obligé d'utiliser des conditions car on utilise la variable collisionAvec du personnage qui est de type shape, et qui ne possède pas de variable vie.
    //Cependant comme nos piège sont des objets héritant de la classe shape, et possédant une variable vie, on vérifie le type précis de l'objet en collision
    //afin de pouvoir utiliser ce type précis pour retirer la vie (car on ne peut pas retirer de vie avec un objet de type shape)
    public static void retraitVie() {
        if (Var.personnage.getCollisionAvec() instanceof Scie) Var.personnage.setVie(Var.personnage.getVie() - ((Scie) Var.personnage.getCollisionAvec()).getVie()); //On reconfigure la vie du personnage en lui enlevant celle que le piege retire
        else if (Var.personnage.getCollisionAvec() instanceof Laser) Var.personnage.setVie(Var.personnage.getVie() - ((Laser) Var.personnage.getCollisionAvec()).getVie()); //On reconfigure la vie du personnage en lui enlevant celle que le piege retire
        else Var.personnage.setVie(Var.personnage.getVie() - ((Pique) Var.personnage.getCollisionAvec()).getVie()); //On reconfigure la vie du personnage en lui enlevant celle que le piege retire
        MajVie(); //Quelque réglages necessaires après une perte de vie
    }

    public static void MajVie() {
        l.setText(String.valueOf(Var.personnage.getVie())); //On change le texte du label vie pour qu'il corresponde a la nouvelle valeur
        // Empecher de pouvoir avancer pour eviter de perdre toute sa vie d'un coup
        Touche.setToucheQ(false);
        Touche.setToucheD(false);
        Var.personnage.setCollisionAvec(null); //On remet la variable qui enregistre l'objet en collision a null car c'est inutile de garder le piege en memoire
        if (Var.personnage.getVie() <= 40) { //On change la couleur de l'affichage de la vie en rouge dès que le perso atteint le seuil de 40 PV restant
            l.setTextFill(Color.RED);
        }
    }

    public static void stopTimer() {
        timer.stop();
    }
}
