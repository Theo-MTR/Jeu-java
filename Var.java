package org.openjfx;
//Class regroupant toute les variables communes du jeu

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

import java.util.Vector;

public class Var {
    protected static Group root;
    protected static Scene scene;
    protected static Stage stage;

    protected static Personnage personnage;
    protected static Rectangle barVie;

    protected static boolean demmare = false;
    protected static boolean n1 = false;
    protected static boolean n2 = false;
    protected static boolean n3 = false;
    protected static boolean toucheD = false;
    protected static boolean toucheQ = false;
    protected static boolean toucheT = false;
    protected static boolean toucheSaut = false;

    protected static Cube cubeSelect;

    protected static MurBlanc murEntree;
    protected static MurBlanc murSortie;

    protected static Vector<Shape> obstacles = new Vector<>();
    protected static Vector<MurBlanc> listeMurBlanc = new Vector<>();
    protected static Vector<Cube> listeCube = new Vector<>();
    //Gravite
    protected static double vitesseG = 0;
    protected static double vitesseGCube = 0;
}
