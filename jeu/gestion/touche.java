package jeu.gestion;

import javafx.scene.input.KeyCode;

public class touche {

    private static final double VS = 30;

    public static void toucheEv() {

        Var.personnage.setOnKeyPressed(ke -> {
            if (ke.getText().toUpperCase().equals("D")) {
                Var.toucheD = true; //SI ON APPUIE SUR "D" ALORS Var.toucheD PASSE A TRUE
            }
            else if (ke.getText().toUpperCase().equals("Q")) {
                Var.toucheQ = true; //SI ON APPUIE SUR "D" ALORS Var.toucheD PASSE A TRUE
            }
            else if (ke.getText().toUpperCase().equals("I")) {
                System.out.println(Var.personnage.getEtatInitial() );
            }
            else if (ke.getText().toUpperCase().equals("T")) {
                if (!Var.toucheT) {
                    Var.toucheT = true;
                } else {
                    Var.toucheT = false;
                }
            }
        });

        Var.personnage.setOnKeyReleased(ke -> {
            if (ke.getText().toUpperCase().equals("D")) {
                Var.toucheD = false; //SI ON LACHE "D" ALORS toucheD PASSE A FALSE
            }
            else if (ke.getText().toUpperCase().equals("Q")) {
                Var.toucheQ = false; //SI ON APPUIE SUR "D" ALORS Var.toucheD PASSE A TRUE
            }
            else if (ke.getCode() == KeyCode.SPACE && !Var.personnage.isEnGravite()) {
                Var.personnage.setEtatInitial(Var.personnage.getY());
                Var.toucheSaut = true;
                Var.personnage.setEnGravite(true);
                Var.vitesseG = -VS;
            }
        });

    }
}
