package jeu.gestion;
//Class regroupant toute les variables communes du jeu

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import jeu.objets.Cube;
import jeu.objets.MurBlanc;
import jeu.objets.Personnage;

import java.util.Vector;

public class Var {
    public static Group root;
    public static Scene scene;
    public static Stage stage;

    protected static Personnage personnage;
    protected static Rectangle barVie;

    public static boolean demmare = false;
    public static boolean n1 = false;
    public static boolean n2 = false;
    protected static boolean n3 = false;
    protected static boolean toucheD = false;
    protected static boolean toucheQ = false;
    protected static boolean toucheT = false;
    protected static boolean toucheSaut = false;

    protected static Cube cubeSelect;

    protected static MurBlanc murEntree;
    protected static MurBlanc murSortie;

    public static Vector<Shape> obstacles = new Vector<>();
    protected static Vector<MurBlanc> listeMurBlanc = new Vector<>();
    protected static Vector<Cube> listeCube = new Vector<>();
    //Gravite
    protected static double vitesseG = 0;
    protected static double vitesseGCube = 0;
}
