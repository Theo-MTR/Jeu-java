/*
Classe qui permet la creation d'un bouton
Elle herite de la classe rectangle de javafx car nous avions besoins d'un rectangle, tout en lui ajoutant des attributs. (savoir si il est activé, sa position initiale)
 */

package jeu.objets;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import jeu.gestion.Var;

public class Bouton extends Rectangle {
    private boolean on; //Variable pour savoir si le bouton est actif
    private final double initialY; //Variable ou on stock la valeur Y initial du bouton pour pouvoir le remettre dans sa position lorsqu'il n'est plus sur 'on'.
    private final double initialTaille; //Variable ou on stock la valeur de la taille initiale du bouton pour pouvoir le remettre dans sa position lorsqu'il n'est plus sur 'on'.
    private Cube cube;


    public Bouton(double ratio_x, Shape sol) {
        /*
        AFin que les element s'adaptent a l'ecran, on utilise des ratios (ratio_x) pour le placement. aisin il seront toujours au même endroit peut importe l'ecran.
        On ajoute aussi le sol sur lequel sera placé le bouton afin d'utiliser le ratio en fonction de la longueur du sol
         */
        super(); //Appel du constructeur de la classe Rectangle
        on = false;
        setWidth(Var.scene.getWidth() * 0.026); //Taille fixe du bouton
        setHeight(Var.scene.getHeight() * 0.020);
        setX(sol.getBoundsInLocal().getMinX() + sol.getBoundsInLocal().getWidth() * ratio_x); //Position x du bouton en fonction du sol sur lequel on le place
        setY(sol.getBoundsInLocal().getMinY() - Var.scene.getHeight() * 0.019); //Position Y du bouton en fonction du sol (qui correspond a la hauteur du bouton)
        setFill(Color.RED); // Couleur initial
        initialY = sol.getBoundsInLocal().getMinY() - Var.scene.getHeight() * 0.019;
        initialTaille = Var.scene.getHeight() * 0.019;
    }

    public double getInitialY() {
        return initialY;
    }

    public double getInitialTaille() {
        return initialTaille;
    }

    public boolean isOn() {
        return on;
    }

    public void setOn(boolean on) {
        this.on = on;
    }

    public Cube getCube() {
        return cube;
    }

    public void setCube(Cube cube) {
        this.cube = cube;
    }
}
