/*
Classe qui gère la gravité tout en se basant sur ce qu'il y a sur terre, le personnage va chuter d'une certaines valeurs qui elle même augmentera a chaque chute
par la force de gravité du jeu
 */

package jeu.gestion;

import javafx.animation.AnimationTimer;
import jeu.objets.Bouton;

public class Gravite {

    private static final double GRAVITE = 0.0019; //Constante de gravité (pour faire comme sur terre)
    private static double vitesseG = 0; //Acceleration en cours
    private static double vitesseGCube = 0; //Acceleration en cour (pour le cube)
    private static AnimationTimer timer; //Timer déclaré ici afin de pouvoir l'arréter via une autre méthode

    public static void gestGravite() {
        timer = new AnimationTimer() {
            public void handle(long l) {
                if (Var.personnage.isEnGravite()) { //On vérifie que le personnage est bien en gravité (à false quand il y a une collision par exemple..)
                    Var.personnage.setY(Var.personnage.getY() + vitesseG); //On augmente la valeur Y du personnage avec une vitesse de gravité afin de le faire chuter
                    vitesseG = vitesseG + Var.scene.getHeight() * GRAVITE; //On augmente la vitesse de gravité, pour faire comme sur terre
                }
                //SI IL Y A COLLISION AVEC UNE PLATEFORME VITESSEG REVIENT A ZERO SINON LE PERSO TOMBERA PLUS VITE A CHAQUE CHUTE
                else {
                    vitesseG = 0;
                }

                //Faire tomber le personnage lorsqu'il quitte une plateforme
                //dans la grande condition en dessous, on verifie simplement si la valeur X du personnage est supérieur a la valeur minimum de X de l'objet en collision avec le perso (Stocké dans la variablecollisionAvec du personnage) ou maximum.
                //Valeur X minimum de l'objet en collision : son point x puisqu'il se trouve à gauche.
                //Valeur X maximum de l'objet en collision : son point x + sa longueur.
                if ((Var.personnage.getCollisionAvec() != null) && (Var.personnage.getX() + Var.personnage.getWidth() < Var.personnage.getCollisionAvec().getBoundsInLocal().getMinX() || Var.personnage.getX() > Var.personnage.getCollisionAvec().getBoundsInLocal().getMaxX()) && Var.personnage.getY() <= Var.personnage.getCollisionAvec().getBoundsInLocal().getMinY()) {
                    System.out.println("ok");
                    if (Var.personnage.getCollisionAvec() instanceof Bouton && Var.personnage.getY() + Var.personnage.getHeight() <= Var.personnage.getCollisionAvec().getBoundsInLocal().getMinY()) { //Si l'objet en collision est un bouton...
                        System.out.println("ok bouton");
                        ((Bouton) Var.personnage.getCollisionAvec()).setOn(false); //On desactive le bouton, puisqu'on veut que ça soit le cas quand on le quitte
                        Animation.animBouton((Bouton)Var.personnage.getCollisionAvec()); //On lance "l'animation" du bouton
                    }
                    Var.personnage.setEnGravite(true); //On active la gravité pour faire tomber le personnage
                    Var.personnage.setCollisionAvec(null); //On remet la variable collisionAvec à null, puisque si c'ette condition s'execute, c'est qu'on a quitté la plateforme
                }

                //Gestion gravite cube (même principe que le personnage, mais sans vérifier si il quitte une plateforme puisqu'un cube n'avance pas)
                if (Var.personnage.getCubeSelect() != null && Var.personnage.getCubeSelect().isEnGravite()) {
                    Var.personnage.getCubeSelect().setY(Var.personnage.getCubeSelect().getY() + vitesseGCube);
                    vitesseGCube = vitesseGCube + Var.scene.getHeight() *  GRAVITE;
                }
                else {
                    vitesseGCube = 0;
                }
            }
        };
        timer.start();

    }

    //methode pour arreter le timer (utiliser pour changer de niveau et tous relancer)
    public static void stopTimer() {timer.stop();}

    public static void setVitesseG(double vitesseG) {
        Gravite.vitesseG = vitesseG;
    }

    public static double getVitesseG() {
        return vitesseG;
    }
}
