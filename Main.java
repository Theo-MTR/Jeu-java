package org.openjfx;

import javafx.application.Application;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {
        stage.setHeight(Screen.getPrimary().getVisualBounds().getHeight());
        stage.setWidth(Screen.getPrimary().getVisualBounds().getWidth());
        stage.setResizable(false);
        stage.setTitle("Portal2D");
        stage.show();
        Var.stage = stage;
        demarrer();
        //Creation de la fenetre principale
       Var.scene.setOnMouseClicked(me -> {
            Var.demmare = true;
            demarrer();
        });
    }

    private void demarrer() {
        if (!Var.demmare) {
            Var.root = new Group();
            Var.scene = new Scene(Var.root, 0, 0, Color.LIGHTGRAY);
            Var.stage.setScene(Var.scene);
            new Acceuil();
        } else {
            if (!Var.n1) {
                Var.root = new Group();
                Var.scene = new Scene(Var.root, 0, 0, Color.LIGHTGRAY);
                Var.stage.setScene(Var.scene);
                new NiveauUn();
            } else if (!Var.n2) {
                Var.root = new Group();
                Var.scene = new Scene(Var.root, 0, 0, Color.LIGHTGRAY);
                Var.stage.setScene(Var.scene);
                Var.root.getChildren().removeAll(Var.obstacles);
                Var.obstacles.removeAllElements();
                new NiveauDeux();
            }
            new Mouvements();
            new Gravite();
            new Collision();
            new GestionPiege();
            touche.toucheEv();

        }
    }
}