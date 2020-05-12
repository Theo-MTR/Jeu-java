package jeu;

import javafx.scene.input.KeyCode;

public class touche {

    private static final int TSVALUE = 23;
    private static final double VS = 30;

    public static void touche() {

        Var.personnage.setOnKeyPressed(ke -> {
            if (ke.getText().toUpperCase().equals("D")) {
                Var.toucheD = true; //SI ON APPUIE SUR "D" ALORS Var.toucheD PASSE A TRUE
            }
            if (ke.getText().toUpperCase().equals("Q")) {
                Var.toucheQ = true; //SI ON APPUIE SUR "D" ALORS Var.toucheD PASSE A TRUE
            }
        });

        Var.personnage.setOnKeyReleased(ke -> {
            if (ke.getText().toUpperCase().equals("D")) {
                Var.toucheD = false; //SI ON LACHE "D" ALORS toucheD PASSE A FALSE
            }
            if (ke.getText().toUpperCase().equals("Q")) {
                Var.toucheQ = false; //SI ON APPUIE SUR "D" ALORS Var.toucheD PASSE A TRUE
            }
            if (ke.getCode() == KeyCode.SPACE) {
                Var.toucheSaut = true;
                Var.personnage.setEtatInitial(Var.personnage.getY());
                Var.personnage.setEnGravite(true);
                Var.TS = TSVALUE;
                Var.vitesseG = -VS;
            }
        });

    }
}
