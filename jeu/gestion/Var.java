package jeu.gestion;
//Classe regroupant toutes les variables devant être utilisé dans plusieurs fichiers

import javafx.scene.Group;
import javafx.scene.Scene;
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

    public static boolean demmare = false;
    public static boolean n0 = false;
    public static boolean n1 = false;
    public static boolean n2 = false;
    //protected static boolean n3 = false;

    protected static MurBlanc murEntree;
    protected static MurBlanc murSortie;

    public static Vector<Shape> obstacles = new Vector<>();
    protected static Vector<MurBlanc> listeMurBlanc = new Vector<>();
    protected static Vector<Cube> listeCube = new Vector<>();

    public static void init() {
        personnage = null;
        murEntree = null;
        murSortie = null;
        obstacles = new Vector<>();
        listeMurBlanc = new Vector<>();
        listeCube = new Vector<>();
    }
}
