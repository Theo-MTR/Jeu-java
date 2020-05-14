package jeu;

import javafx.application.Application;

import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {

        //Creation de la fenetre principale
        primaryStage.setScene(Var.scene);
        primaryStage.setHeight(Screen.getPrimary().getVisualBounds().getHeight());
        primaryStage.setWidth(Screen.getPrimary().getVisualBounds().getWidth());
        primaryStage.setResizable(false);
        primaryStage.setTitle("Portal2D");
        primaryStage.show();

        System.out.println(Screen.getPrimary().getVisualBounds().getMaxY() + " " + Screen.getPrimary().getVisualBounds().getHeight());
        new NiveauUn();
        new Mouvements();
        new Gravite();
        new Collision();
        new GestionPiege();
        touche.toucheEv();

    }
}
