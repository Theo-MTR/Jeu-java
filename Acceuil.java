package org.openjfx;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;


public class Acceuil {

    public Acceuil() {
        final Button bDemmarre = new Button("Demarrer");
        Var.root.getChildren().add(bDemmarre);
        bDemmarre.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                Var.demmare = true;
            }
        });
    }

}
