package jeu;
//Class regroupant toute les variables communes du jeu

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.stage.Screen;

import java.util.Vector;

public class Var {
    protected static Group root = new Group();
    protected static Scene scene = new Scene(root, 0, 0, Color.WHITE);
    protected static Personnage personnage;
    protected static boolean toucheD = false;
    protected static boolean toucheQ = false;
    protected static boolean toucheT = false;
    protected static boolean toucheSaut = false;
    protected static Cube cubeSelect;
    protected static Vector<Shape> obstacles = new Vector<>();
    //Gravite
    protected static double vitesseG = 0;
}
