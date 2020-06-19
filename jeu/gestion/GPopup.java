/*
Class qui sera appellée uniquement pendant le niveau d'introduction afin d'afficher un popup d'aide
 */

package jeu.gestion; //mettre dans la package gestion

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Popup;
import jeu.objets.*;

public class GPopup {

    private static Label label ;
    private static Popup popup;

    private static AnimationTimer timer;

    public static void GestPopup(){


        // Creation du Popup
        Button b1 = new Button("Bouton");
        b1.setText("Fermer");

        label = new Label();
        popup = new Popup();

        //style label
        label.setStyle(" -fx-background-color: gray;");
        label.setFont(new Font(40) );
        popup.setWidth(Var.scene.getHeight() * 0.75);


        //Gere la taille du background
        label.setMinWidth(Var.scene.getWidth());
        label.setTranslateY(Var.scene.getHeight() * 0.0245);

        label.setAlignment(Pos.CENTER);
        popup.setX(Var.scene.getX());
        label.setTextAlignment(TextAlignment.CENTER);

        popup.getContent().add(label);
        popup.getContent().add(b1);

        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                popup.hide();
            }
        };
        b1.setOnAction(event);

        timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                for (Shape s : Var.obstacles) {
                    s.setOnMouseClicked(mouseEvent -> {
                        if (mouseEvent.getButton() == MouseButton.MIDDLE) {
                            if (s instanceof Bouton) {
                                label.setText("Le Bouton, il active ou désactive des mécanismes comme le laser ou le mur coulissant.\nOn peut l'activer avec un cube également.");
                            } else if (s instanceof Laser) {
                                label.setText("Le Laser, évitez de le traverser ou cela pourrait vous blesser ou même vous tuer\nsi le laser est plus puissant celui-ci aura une couleur plus foncé\npour le désactiver, trouvez le bon bouton.");
                            } else if (s instanceof Cube) {
                                label.setText("Le cube, celui-ci peut être posé sur un bouton pour laisser un mécanisme activé.\nPour interagir avec, pressez la touche T");
                            } else if (s instanceof MurMovible) {
                                label.setText("Le Mur coulissant doit être activé par un bouton\nCelui-ci peut donner accès à des nombreux endroits. ");
                            } else if (s instanceof Porte) {
                                label.setText("La Porte, vous ramènera au prochain niveau ");
                            }
                        }
                        popup.show(Var.stage);
                    });
                }
            }

        };
        timer.start();

    }

    public static Label getLabel() {
        return label;
    }

    public static void stopTimer() {
        timer.stop();
    }
}
