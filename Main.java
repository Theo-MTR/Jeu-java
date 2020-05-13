package jeu;

import javafx.application.Application;

import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {

        //Creation de la fenetre principale
        primaryStage.setScene(Var.scene);
        primaryStage.setTitle("Portal2D");
        primaryStage.show();

        new NiveauUn();
        new Mouvements();
        new Gravite();
        new Collision();
        new GestionPiege();
        touche.touche();

    }
}
