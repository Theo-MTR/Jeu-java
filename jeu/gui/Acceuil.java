package jeu.gui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import jeu.gestion.Var;

import java.nio.file.Paths;

public class Acceuil {

    private static final Image titre = new Image(Paths.get("Ressources/titre.png").toUri().toString());
    private static final Image icon = new Image(Paths.get("Ressources/persoAccueil.png").toUri().toString());
    private static final Image start = new Image(Paths.get("Ressources/entrer.png").toUri().toString());

    public Acceuil() {

        //Creation des images view
        ImageView ivTitre = new ImageView();
        ImageView ivIcon = new ImageView();
        ImageView ivStart = new ImageView();

        //Configuration des images des ImageView
        ivTitre.setImage(titre);
        ivIcon.setImage(icon);
        ivStart.setImage(start);

        //Positionnement X des ImagesView
        ivTitre.setX(Var.scene.getWidth() * 0.5 - (Var.scene.getWidth() * 0.57 * 0.5));
        ivIcon.setX(Var.scene.getWidth() * 0.5 - (Var.scene.getWidth() * 0.031 * 0.5));
        ivStart.setX(Var.scene.getWidth() * 0.5 - (Var.scene.getWidth() * 0.57 * 0.5));

        //Positionnement Y des ImagesView
        ivIcon.setY(Var.scene.getHeight() * 0.5 - (Var.scene.getHeight() * 0.012 * 0.5));
        ivStart.setY(Var.scene.getHeight() * 0.90 - (Var.scene.getHeight() * 0.099 * 0.5));

        ivTitre.setFitWidth(Var.scene.getWidth() * 0.57);
        ivIcon.setFitWidth(Var.scene.getWidth() * 0.031);
        ivStart.setFitWidth(Var.scene.getWidth() * 0.57);

        Var.root.getChildren().addAll(ivTitre, ivIcon, ivStart);
    }

}
