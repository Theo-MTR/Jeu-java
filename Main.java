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
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.stage.Popup;
import javafx.stage.Stage;
 
public class Main extends Application {
	
	//Image du personnage
	Image perso = new Image("perso.png");
	Paint ip = new ImagePattern(perso);	
	
	//BOOLEAN AVANCEMENT JOUEUR
	private static boolean dclick = false;
	private static boolean qclick = false;
	private static boolean sclick = false;
	private static boolean zclick = false;
	private static boolean jump = false;
	private static boolean iclick = false;
	private static boolean tclick = false;
	
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
         Scene scene = new Scene(root, 1920, 1080, Color.WHITE);
         
         //BACKGROUND.
         //ImageView bg = new ImageView("background.jpg");
         //Affichage.configBG(bg, scene, root);
         
         //Creation personnage
         Rectangle personnage = Obstacles.createRectangle(60, 120, 150, 500, true, ip); //Ici utilisation de la methode createRectangle qui se trouve dans la class obstacles afin d'economiser des lignes (la methode renvoie un rectangle fait avec les arguments en paramètre)
         //Creation sol
         Rectangle sol = Obstacles.createRectangle(scene.getWidth(), 50, 0, 1030, false, Color.BLACK); //Même principe
         //Creation d'une plateforme rectangulaire
         Rectangle plat1 = Obstacles.createRectangle(scene.getWidth() / 2, 30, 800, 800, false, Color.BLACK); //Ainsi de suite
         //Creation d'un objet sur la map
         Rectangle obj1 = Obstacles.createRectangle(50, 50, 300, sol.getY() - sol.getHeight(), false, Color.DARKRED);
         
         //test popup
         Label label = new Label("This is a Popup"); 
         
         // create a popup 
         Popup popup = new Popup(); 
         // set background 
         popup.getContent().add(label); 
         popup.setAutoHide(true);
         label.setStyle("-fx-background-color:black;");
         label.setMinWidth(80); 
         label.setMinHeight(50); 
         
         
         
         //BOUCLE DE JEU QUI S'EXECTUTE A CHAQUE FRAME
         AnimationTimer timer = new AnimationTimer() {
             public void handle(long now) {	 

            	 //GESTION DE LA GRAVITE
            	if (gravity) {
            		//GESTION DE COLLISION GRACE A UNE METHODE CREE EN DESSOUS
            		boolean i1 = checkIntersect(personnage, sol); 
                 	boolean i2 = checkIntersect(personnage, plat1);
                 	 
                 	 //SI IL NY A PAS DE COLLISION (I1 ET I2 A TRUE) ALORS ON FAIT CHUTER L'OBJET PERSONNAGE
                	if (i1 && i2) {
                		personnage.setY(personnage.getY() + vitesseG);
                		 vitesseG = vitesseG + g;
                	}
                	 
                	 //SI IL Y A COLLISION AVEC UNE PLATEFORME (ICI i1, LE SOL, ET DONC i1 A FALSE) LE CERCLE REPREND UNE CONFIGURATION CORRECT
            		 if (!i1) {
            			 personnage.setY(sol.getY() - personnage.getHeight());
            			 vitesseG = 0;
            			 gravity = false;
            			 jump = false;
            		 } 
            		 //MEME PRINCIPE QU4AU DESSUS MAIS POUR i2
            		 else if (!i2) {
            			 jump = false;
            			 if (personnage.getY() + personnage.getHeight() > plat1.getY() + plat1.getHeight()) {
            				 personnage.setY(etatInitial); // Permet de reinitialiser l'emplacement en fonction de l'endroit de la collision
            				 vitesseG = 0;
            			 }
            			 else {
            				 personnage.setY(plat1.getY() - personnage.getHeight());
            			 vitesseG = 0;
            			 gravity = false;
            			 }
            		 }
            		 
            		 
            	 }
            	 //CE ELSE PERMET DE GERER LES CHUTE, DES QUON QUITTE UNE PLATEFORM EN HAUTEUR, LA GRAVITE REPREND
            	 
            	 else {
            		 if (personnage.getX() < plat1.getX() && personnage.getY() + personnage.getHeight() <= plat1.getY()) {
            			 gravity = true;
            		 }
            	 }
            	 

            	 //FIN DE LA GESTION DE LA GRAVITE
            	 

            	 
            	 // Conditions pour faire avancer le joueur
            	Shape intersect = Shape.intersect(personnage, obj1);

            	 if (dclick) {
                	 //checkD(); //Verifier s'il n'y a pas de collision (pour la touche D, d'où "checkD")
            		 personnage.setX(personnage.getX() + VITESSE_MARCHE);
                 }
                 if (qclick) {
                	//checkQ();
                	 personnage.setX(personnage.getX() - VITESSE_MARCHE);
                 }
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
         
         personnage.setOnKeyPressed(new EventHandler<KeyEvent>() {
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
         
         personnage.setOnKeyReleased(new EventHandler<KeyEvent>() {
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
        				 etatInitial = personnage.getY();
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
        		 sol.setFill(Color.ALICEBLUE);
        		 if (!popup.isShowing()) popup.show(primaryStage);
        		 //System.out.printf("Bouton %s cliqué sur le nœud, %d click(s) %f x %f.%n", e.getButton(), e.getClickCount(), e.getX(), e.getY());
        	 }
         });
         
         
         //root.getChildren().add(bg);
         //root.getChildren().add(perso);
         //root.getChildren().add(cercle);
         root.getChildren().add(sol);
         root.getChildren().add(plat1);
         root.getChildren().add(personnage);
         root.getChildren().add(obj1);
         
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
    
    /*
    
    //4 METHODES POUR REPLACER LE JOUEUR D'UNE BONNE MANIERE SI JAMAIS IL Y A COLLISION (SINON IMPOSSIBLE D'UTILISER LES TOUCHE LORS D4UNE COLLISION°
    public void checkD() {
    	Shape intersect = Shape.intersect(personnage, sol);
    	Shape intersect2 = Shape.intersect(personnage, plat1);
    	if (intersect.getBoundsInParent().getWidth() != -1 || intersect2.getBoundsInParent().getWidth() != -1) {
    		personnage.setX(personnage.getX() - 10);
    	}
    }
    
    public void checkQ() {
    	Shape intersect = Shape.intersect(personnage, sol);
    	Shape intersect2 = Shape.intersect(personnage, plat1);
    	if (intersect.getBoundsInLocal().getWidth() != -1 || intersect2.getBoundsInLocal().getWidth() != -1) personnage.setX(personnage.getX() + 10);;
    	
    }
    
    public void checkZ() {
    	Shape intersect = Shape.intersect(personnage, sol);
    	Shape intersect2 = Shape.intersect(personnage, plat1);
    	if (intersect.getBoundsInLocal().getWidth() != -1 || intersect2.getBoundsInLocal().getWidth() != -1) personnage.setY(personnage.getY() + 10);
    	
    }
    
    public void checkS() {
    	Shape intersect = Shape.intersect(personnage, sol);
    	Shape intersect2 = Shape.intersect(personnage, plat1);
    	if (intersect.getBoundsInLocal().getWidth() != -1 || intersect2.getBoundsInLocal().getWidth() != -1) personnage.setY(personnage.getY() + 10);
    	
    }
    
    */
    
    //FIN DES 4 METHODES
 
    
}
