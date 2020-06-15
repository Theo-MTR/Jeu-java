/*
Classe qui permet la creation d'un Laser
Elle herite de la classe rectangle de javafx car nous avions besoins d'un rectangle (qui en etant fin va simuler un faisseau de lumière), tout en lui ajoutant des attributs comme la vie qu'il retire ou encore le bouton qui permet de le desactiver
 */

package jeu.objets;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import jeu.gestion.Var;

public class Laser extends Rectangle {
    private final int vie; //Vie que le laser retire

    //position initiale du laser pour pouvoir par la suite l'activer et le desactiver
    private final double positionInitiale;
    private final double hauteurI;

    private final Bouton bouton; //Bouton qui desactive le laser, grace à ça on a juste à regarder son etat, si il est sur on alors on anim l'objet qui lui est lié

    public Laser(double ratio_x, double ratio_y, double ratio_h, Shape sol, int vie, Bouton bouton) {
        super();

        //Configuration automatique de la couleur du laser en fonction de la vie qu'il retire
        if (vie > 0 && vie <= 35) setFill(Color.LIGHTBLUE);
        else if (vie > 35 && vie <= 65) setFill(Color.DARKRED);
        else setFill(Color.PURPLE);

        setX(sol.getBoundsInLocal().getMinX() + sol.getBoundsInLocal().getWidth() * ratio_x);// Position x du laser en fonction du sol ou il est situé
        setY(sol.getBoundsInLocal().getMinY() - Var.scene.getHeight() * ratio_y); // Position Y du laser en fonction du sol ou il est situé
        positionInitiale = sol.getBoundsInLocal().getMinY() - Var.scene.getHeight() * ratio_y; //Enregistrer la position initiale
        setWidth(10); //Taille de base
        setHeight(Var.scene.getHeight() * ratio_h); //Hauteur réglée grace au ratio h ainsi que la taille de la fenêtre de jeu
        hauteurI = Var.scene.getHeight() * ratio_h; //Sauvegarde de la hauteur
        this.vie = vie; //Vie que retire le laser
        this.bouton = bouton; //Bouton qui l'active / desactive
    }

    public int getVie() {
        return vie;
    }

    public Bouton getBouton() {
        return bouton;
    }

    public double getPositionInitiale() {
        return positionInitiale;
    }

    public double getHauteurI() {
        return hauteurI;
    }
}
