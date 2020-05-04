/* A FAIRE 
 * Mettre la vitesse de deplacement dans une variable constante
 *  
 * 
 * 
 * 
 * */

package application;
	
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.*;
import javafx.stage.Popup;
import javafx.stage.Stage;
import application.Affichage;
 
public class Main extends Application {
	
	//Circle cercle = new Circle();
	Rectangle rectangle = new Rectangle();
	Rectangle rectangle2 = new Rectangle();
	Rectangle rectangle3 = new Rectangle();
	Rectangle rectangle4 = new Rectangle();
	
	//Image personnage
	private static Image perso;
	
	//BOOLEAN AVANCEMENT JOUEUR
	private static boolean dclick = false, qclick = false, sclick = false, zclick = false, jump = false, iclick = false, tclick = false;
	
	//GESTION DE LA GRAVITE
	private static boolean gravity = true;
	private static double vitesseG = 0;
	private static double g = 2;
	
	//GESTION DU SAUT
	private static final int TSVALUE = 23;
	private static final double VS = 30;
	private static int TS;
	
	//REGLAGE DE LA VITESSE DE MARCHE
	private static final double VITESSE_MARCHE = 10;
	
	//ENREGISTREMENT DE L'EMPLACEMENT Y DU PERSO AVANT CHAQUE ACTION
	private static double etatInitial;
	
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
    
    public void start(Stage primaryStage) {
        
    	 Group root = new Group();
         Scene scene = new Scene(root, 1920, 1080, Color.BLACK);
         
         //BACKGROUND.
         //ImageView bg = new ImageView("background.jpg");
         //Affichage.configBG(bg, scene, root);
         
         //"PERSO"
         perso = new Image("perso.png");
         rectangle3.setWidth(60);
         rectangle3.setHeight(120);
         rectangle3.setX(150);
         rectangle3.setY(500);
         ImagePattern ip = new ImagePattern(perso);
         rectangle3.setFill(ip);
         rectangle3.setFocusTraversable(true);
         rectangle3.setBlendMode(BlendMode.SRC_ATOP);
         Circle circle = new Circle();
         circle.setRadius(100);

         circle.setFill(Color.rgb(255, 255, 255, 0.5));
         
         //SOL
         rectangle.setX(0); //POSITION X DU COIN HAUT GAUCHE DU PREMIER CARRE
         rectangle.setY(1030); //POSITION Y DU COIN HAUT GAUCHE DU PREMIER CARRE
         rectangle.setWidth(scene.getWidth()); //REGLAGE LARGEUR DU CARRE
         rectangle.setHeight(50); //REGLAGE HAUTEUR DU CARRE
         rectangle.setFill(Color.BLACK); //COULEUR DU CARRE
         
         rectangle2.setWidth(scene.getWidth());
         rectangle2.setHeight(scene.getHeight() / 60);
         rectangle2.setX(800);
         rectangle2.setY(800);
         rectangle2.setFill(Color.BLACK);
         
         rectangle4.setWidth(50);
         rectangle4.setHeight(50);
         rectangle4.setX(300);
         rectangle4.setY(rectangle.getY() - rectangle.getHeight());
         rectangle4.setFill(Color.WHITE);
         
         //test popup
         Label label = new Label("This is a Popup"); 
         
         // create a popup 
         Popup popup = new Popup(); 
         // set background 
         popup.getContent().add(label); 
         popup.setAutoHide(true);
         label.setStyle("-fx-background-color:white;");
         label.setMinWidth(80); 
         label.setMinHeight(50); 
         
         
         
         //BOUCLE DE JEU QUI S'EXECTUTE A CHAQUE FRAME
         AnimationTimer timer = new AnimationTimer() {
             public void handle(long now) {	 
            	 
            	 circle.setCenterX(rectangle3.getX() + rectangle3.getWidth() / 2);
                 circle.setCenterY(rectangle3.getY() + rectangle3.getHeight() / 2);
            	 

            	 
            	 //GESTION DE LA GRAVITE
            	if (gravity) {
            		//GESTION DE COLLISION GRACE A UNE METHODE CREE EN DESSOUS
            		boolean i1 = checkIntersect(rectangle3, rectangle); 
                 	boolean i2 = checkIntersect(rectangle3, rectangle2);
                 	 
                 	 //SI IL NY A PAS DE COLLISION (I1 ET I2 A TRUE) ALORS ON FAIT CHUTER L'OBJET PERSONNAGE
                	if (i1 && i2) {
                		rectangle3.setY(rectangle3.getY() + vitesseG);
                		 vitesseG = vitesseG + g;
                	}
                	 
                	 //SI IL Y A COLLISION AVEC UNE PLATEFORME (ICI i1, LE SOL, ET DONC i1 A FALSE) LE CERCLE REPREND UNE CONFIGURATION CORRECT
            		 if (!i1) {
            			 rectangle3.setY(rectangle.getY() - rectangle3.getHeight());
            			 vitesseG = 0;
            			 gravity = false;
            			 jump = false;
            		 } 
            		 //MEME PRINCIPE QU4AU DESSUS MAIS POUR i2
            		 else if (!i2) {
            			 jump = false;
            			 if (rectangle3.getY() + rectangle3.getHeight() > rectangle2.getY() + rectangle2.getHeight()) {
            				 rectangle3.setY(etatInitial); // Permet de reinitialiser l'emplacement en fonction de l'endroit de la collision
            				 vitesseG = 0;
            			 }
            			 else {
            				 rectangle3.setY(rectangle2.getY() - rectangle3.getHeight());
            			 vitesseG = 0;
            			 gravity = false;
            			 }
            		 }
            		 
            		 
            	 }
            	 //CE ELSE PERMET DE GERER LES CHUTE, DES QUON QUITTE UNE PLATEFORM EN HAUTEUR, LA GRAVITE REPREND
            	 
            	 else {
            		 if (rectangle3.getX() < rectangle2.getX() && rectangle3.getY() + rectangle3.getHeight() <= rectangle2.getY()) {
            			 gravity = true;
            		 }
            	 }
            	 

            	 //FIN DE LA GESTION DE LA GRAVITE
            	 

            	 
            	 // Conditions pour faire avancer le joueur
            	Shape intersect = Shape.intersect(rectangle3, rectangle4);

            	 if (dclick) {
                	 checkD(); //Verifier s'il n'y a pas de collision (pour la touche D, d'où "checkD")
            		 rectangle3.setX(rectangle3.getX() + VITESSE_MARCHE);
                 }
                 if (qclick) {
                	checkQ();
                	 rectangle3.setX(rectangle3.getX() - VITESSE_MARCHE);
                 }
                 if (zclick) {
                	checkZ();
                	 rectangle3.setY(rectangle3.getY() - VITESSE_MARCHE);
                 }
                 if (sclick) {
                	 checkS();
                	 rectangle3.setY(rectangle3.getY() + VITESSE_MARCHE);
                 }
                 if (iclick) {
                     rectangle3.setX(150);
                     rectangle3.setY(500);
                 }
                 if (tclick && dclick && intersect.getBoundsInParent().getWidth() != -1) {
                	 rectangle4.setY(rectangle3.getY() + rectangle3.getHeight() / 2);
                	 rectangle4.setX(rectangle3.getX() + 50);
                 }
                 if (tclick && qclick && intersect.getBoundsInParent().getWidth() != -1) {
                	 rectangle4.setY(rectangle3.getY() + rectangle3.getHeight() / 2);
                	 rectangle4.setX(rectangle3.getX() - 45);
                 }
                 if (tclick && (jump || !jump) && intersect.getBoundsInParent().getWidth() != -1) {
                	 rectangle4.setY(rectangle3.getY() + rectangle3.getHeight() / 2);
                	 rectangle4.setX(rectangle4.getX());
                 }
                 
                 if (!tclick) {
                	 rectangle4.setY(rectangle4.getY());
                	 rectangle4.setX(rectangle4.getX());
                 }
                 
                 //CONDITION POUR FAIRE SAUTER LE JOUEUR 
 
                 if (jump) {
                	 rectangle3.setY(rectangle3.getY() + vitesseG);
                	 TS--;
                	 if (TS <= 0) jump = false;
                 }
             
                 
                 }

         };
         
         timer.start();
         
       //CONFIGURATION ETAT POUR DEPLACEMENT CERCLE AVEC LES TOUCHES ZQSD
         
         rectangle3.setOnKeyPressed(new EventHandler<KeyEvent>() {
        	 public void handle(KeyEvent ke) {

        		 if (ke.getText().toUpperCase().equals("D") ) {
        			 dclick = true; //SI ON APPUIE SUR "D" ALORS DCLICK PASSE A TRUE
        		 }
        		 if (ke.getText().toUpperCase().equals("Q") ) {	 
        			 qclick = true;
        		 }
        		 if (ke.getText().toUpperCase().equals("Z") ) {
        			 zclick = true;
        		 }
        		 if (ke.getText().toUpperCase().equals("S") ) {
        			 sclick = true; 
        		 }
        		 if (ke.getText().toUpperCase().equals("I") ) {
        			 iclick = true; 
        		 }
        		 if (ke.getText().toUpperCase().equals("T") && !tclick) {
        			 tclick = true; 
        		 }
        		 if (ke.getText().toUpperCase().equals("L") && tclick) {
        			 tclick = false; 
        		 }

        	 }
         });
         
         rectangle3.setOnKeyReleased(new EventHandler<KeyEvent>() {
        	 public void handle(KeyEvent ke) {
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
        		 if (ke.getText().toUpperCase().equals("I") ) {
        			 iclick = false; 
        		 }
        		 if (ke.getText().toUpperCase().equals("J") ) {
        			 if (!jump) {
        				 etatInitial = rectangle3.getY();
        				 jump = true;
        				 gravity = true;
        				 TS = TSVALUE;
        				 vitesseG = - VS;
        			 }
        		 }
        	 }
         });
         
         scene.setOnMouseClicked(new EventHandler <MouseEvent>() {
        	 public void handle(MouseEvent e) {
        		 rectangle.setFill(Color.ALICEBLUE);
        		 if (!popup.isShowing()) popup.show(primaryStage);
        		 //System.out.printf("Bouton %s cliqué sur le nœud, %d click(s) %f x %f.%n", e.getButton(), e.getClickCount(), e.getX(), e.getY());
        	 }
         });
         
         
         //root.getChildren().add(bg);
         //root.getChildren().add(perso);
         //root.getChildren().add(cercle);
         root.getChildren().add(rectangle);
         root.getChildren().add(rectangle2);
         root.getChildren().add(rectangle3);
         root.getChildren().add(rectangle4);
         root.getChildren().add(circle);
         
         //Réglages fenêtre
         primaryStage.setScene(scene); //Ajout de la scene 
         primaryStage.setTitle("Premier jeu java");
         primaryStage.show();
        
    }
    
    
    
    //METHODE PERMETTANT DE TESTER SI 2 OBJETS ONT UNE INTERSECTION ET DONC UNE COLLISION
    public boolean checkIntersect(Shape o, Shape o2) {
    	Shape intersect = Shape.intersect(o, o2);
    	return intersect.getBoundsInLocal().getWidth() == -1;
    }
    
    //4 METHODES POUR REPLACER LE JOUEUR D'UNE BONNE MANIERE SI JAMAIS IL Y A COLLISION (SINON IMPOSSIBLE D'UTILISER LES TOUCHE LORS D4UNE COLLISION°
    public void checkD() {
    	Shape intersect = Shape.intersect(rectangle3, rectangle);
    	Shape intersect2 = Shape.intersect(rectangle3, rectangle2);
    	if (intersect.getBoundsInParent().getWidth() != -1 || intersect2.getBoundsInParent().getWidth() != -1) {
    		rectangle3.setX(rectangle3.getX() - 10);
    	}
    }
    
    public void checkQ() {
    	Shape intersect = Shape.intersect(rectangle3, rectangle);
    	Shape intersect2 = Shape.intersect(rectangle3, rectangle2);
    	if (intersect.getBoundsInLocal().getWidth() != -1 || intersect2.getBoundsInLocal().getWidth() != -1) rectangle3.setX(rectangle3.getX() + 10);;
    	
    }
    
    public void checkZ() {
    	Shape intersect = Shape.intersect(rectangle3, rectangle);
    	Shape intersect2 = Shape.intersect(rectangle3, rectangle2);
    	if (intersect.getBoundsInLocal().getWidth() != -1 || intersect2.getBoundsInLocal().getWidth() != -1) rectangle3.setY(rectangle3.getY() + 10);
    	
    }
    
    public void checkS() {
    	Shape intersect = Shape.intersect(rectangle3, rectangle);
    	Shape intersect2 = Shape.intersect(rectangle3, rectangle2);
    	if (intersect.getBoundsInLocal().getWidth() != -1 || intersect2.getBoundsInLocal().getWidth() != -1) rectangle3.setY(rectangle3.getY() + 10);
    	
    }
    
    //FIN DES 4 METHODES
 
    
}
