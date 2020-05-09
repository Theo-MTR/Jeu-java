package jeux;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.*;
//import javafx.stage.Popup;
import javafx.stage.Stage;

//Salut
public class Main extends Application {


    //REGLAGE DE LA VITESSE DE MARCHE
    private static final double VITESSE_MARCHE = 10;
    //BOOLEAN AVANCEMENT JOUEUR
    private static boolean dclick = false;
    private static boolean qclick = false;
    private static boolean sclick = false;
    private static boolean zclick = false;
    private static boolean jump = false;
    private static boolean iclick = false;
    private static boolean tclick = false;
    //GESTION DE LA GRAVITE
    private static double vitesseG = 0;
    private static final double GRAVITE = 2;
    //GESTION DU SAUT
    private static final int TSVALUE = 23;
    private static final double VS = 30;
    private static int TS;

    //Image du personnage
    private final Image perso = new Image("perso.png");
    private final Paint ip = new ImagePattern(perso);

    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }

    public void start(Stage primaryStage) {

        //BACKGROUND.
        //ImageView bg = new ImageView("background.jpg");
        //Affichage.configBG(bg, scene, root);

         /*
         //test popup
         Label label = new Label("This is a Popup");
         // create a popup
         Popup popup = new Popup(); java --module-path D:\eclipse\eclipse\javafx-sdk-11.0.2\lib --add-modules javafx.controls,javafx.fxml,javafx.graphics,javafx.web -jar JeuxIUT.jar
         // set background
         popup.getContent().add(label);
         popup.setAutoHide(true);
         label.setStyle("-fx-background-color:black;");
         label.setMinWidth(80);
         label.setMinHeight(50);
         */

        Group root = new Group();
        Scene scene = new Scene(root, 1920, 1080, Color.WHITE);

        //Creation personnage
        Rect personnage = Obstacles.createRectangle(60, 120, 150, 500, true, ip); //Ici utilisation de la methode createRectangle qui se trouve dans la class obstacles afin d'economiser des lignes (la methode renvoie un rectangle fait avec les arguments en paramètre)
        //Creation sol
        Rectangle sol = Obstacles.createRectangle(scene.getWidth(), 50, 0, 1030, false, Color.BLACK); //Même principe
        //Creation de plateformes rectangulaires
        Rectangle plat1 = Obstacles.createRectangle(scene.getWidth() / 2, 30, 800, 800, false, Color.BLACK); //Ainsi de suite
        Rectangle plat2 = Obstacles.createRectangle(500, 40, 0, 700, false, Color.BLACK);
        //Creation d'un objet sur la map
        Rectangle obj1 = Obstacles.createRectangle(50, 50, 300, sol.getY() - sol.getHeight(), false, Color.DARKRED);

        Rectangle[] obstacles = {sol, plat1, plat2};

        //BOUCLE DE JEU QUI S'EXECTUTE A CHAQUE FRAME
        AnimationTimer timer = new AnimationTimer() {
            public void handle(long now) {

                Collision.checkCollision(personnage, obstacles);

                //GESTION DE LA GRAVITE
                //SI IL NY A PAS DE COLLISION AVEC LE PERSONNAGE ALORS ON FAIT CHUTER L'OBJET PERSONNAGE
                if (personnage.isGravity()) {
                    personnage.setY(personnage.getY() + vitesseG);
                    vitesseG = vitesseG + GRAVITE;
                }

                //SI IL Y A COLLISION AVEC UNE PLATEFORME VITESSEG REVIENT A ZERO SINON LE PERSO TOMBERA PLUS VITE A CHAQUE CHUTE
                if (!personnage.isGravity()) {
                    vitesseG = 0;
                }

                //FIN DE LA GESTION DE LA GRAVITE

                Shape intersect = Shape.intersect(personnage, obj1);

                if (dclick) {
                    if (!Collision.checkCollision(personnage, plat1))
                        personnage.setX(personnage.getX() + VITESSE_MARCHE);
                    else {
                        personnage.setX(personnage.getX() - VITESSE_MARCHE);
                    }
                }

                if (qclick && !Collision.checkCollision(personnage, plat1))
                    personnage.setX(personnage.getX() - VITESSE_MARCHE);
                else if (qclick && Collision.checkCollision(personnage, plat1))
                    personnage.setX(personnage.getX() + VITESSE_MARCHE);

                if (zclick) {
                    //checkZ();
                    personnage.setY(personnage.getY() - VITESSE_MARCHE);
                }
                if (sclick) {
                    //checkS();
                    personnage.setY(personnage.getY() + VITESSE_MARCHE);
                }
                if (iclick) {
                    personnage.setX(150);
                    personnage.setY(500);
                }
                if (tclick && dclick && intersect.getBoundsInParent().getWidth() != -1) {
                    obj1.setY(personnage.getY() + personnage.getHeight() / 2);
                    obj1.setX(personnage.getX() + 50);
                }
                if (tclick && qclick && intersect.getBoundsInParent().getWidth() != -1) {
                    obj1.setY(personnage.getY() + personnage.getHeight() / 2);
                    obj1.setX(personnage.getX() - 45);
                }
                if (tclick && (jump || !jump) && intersect.getBoundsInParent().getWidth() != -1) {
                    obj1.setY(personnage.getY() + personnage.getHeight() / 2);
                    obj1.setX(obj1.getX());
                }

                if (!tclick) {
                    obj1.setY(obj1.getY());
                    obj1.setX(obj1.getX());
                }

                //CONDITION POUR FAIRE SAUTER LE JOUEUR

                if (jump) {
                    personnage.setY(personnage.getY() + vitesseG);
                    TS--;
                    if (TS <= 0) jump = false;
                }


            }

        };

        timer.start();

        //CONFIGURATION ETAT POUR DEPLACEMENT CERCLE AVEC LES TOUCHES ZQSD

        personnage.setOnKeyPressed(ke -> {

            if (ke.getText().toUpperCase().equals("D")) {
                dclick = true; //SI ON APPUIE SUR "D" ALORS DCLICK PASSE A TRUE
            }
            if (ke.getText().toUpperCase().equals("Q")) {
                qclick = true;
            }
            if (ke.getText().toUpperCase().equals("Z")) {
                zclick = true;
            }
            if (ke.getText().toUpperCase().equals("S")) {
                sclick = true;
            }
            if (ke.getText().toUpperCase().equals("I")) {
                iclick = true;
            }
            if (ke.getText().toUpperCase().equals("T") && !tclick) {
                tclick = true;
            }
            if (ke.getText().toUpperCase().equals("L") && tclick) {
                tclick = false;
            }

        });

        personnage.setOnKeyReleased(ke -> {
            if (ke.getText().toUpperCase().equals("D")) {
                dclick = false; //SI ON RELACHE "D" ALORS DCLICK PASSE A FALSE
            }
            if (ke.getText().toUpperCase().equals("Q")) {
                qclick = false;
            }
            if (ke.getText().toUpperCase().equals("Z")) {
                zclick = false;
            }
            if (ke.getText().toUpperCase().equals("S")) {
                sclick = false;
            }
            if (ke.getText().toUpperCase().equals("I")) {
                iclick = false;
            }
            if (ke.getText().toUpperCase().equals("J") && !jump) {
                personnage.setEtatInitial(personnage.getY());
                personnage.setCollision(false);
                jump = true;
                personnage.setGravity(true);
                TS = TSVALUE;
                vitesseG = -VS;
            }
        });

         /*
         scene.setOnMouseClicked(new EventHandler <MouseEvent>() {
        	 public void handle(MouseEvent e) {
        		 sol.setFill(Color.ALICEBLUE);
        		 if (!popup.isShowing()) popup.show(primaryStage);
        		 //System.out.printf("Bouton %s cliqué sur le nœud, %d click(s) %f x %f.%n", e.getButton(), e.getClickCount(), e.getX(), e.getY());
        	 }
         });
         */


        //root.getChildren().add(bg);
        root.getChildren().addAll(sol, plat1, plat2, personnage, obj1);

        //Réglages fenêtre
        primaryStage.setScene(scene); //Ajout de la scene
        primaryStage.setTitle("Premier jeu java");
        primaryStage.show();

    }


}