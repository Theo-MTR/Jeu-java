/*
Cette classe permet de gérer l'animation des pièges lorsqu'on appuie sur un bouton qui est censé le désactiver.
On utilise encore un timer qui nous permet d'executer du code à chaque frame (c'est comme une boucle)
 */

package jeu.gestion;

import javafx.animation.AnimationTimer;
import javafx.scene.shape.Shape;
import jeu.objets.Laser;
import jeu.objets.MurMovible;

public class GestionPiege {

    private static final int VITESSE_CHUTE_MUR = 3; //Constante pour la vitesse d'ouverture et de fermeture d'un mur movible
    private static AnimationTimer timer; //Timer. On l'a déclaré dans une variable comme ça on peut l'arreter grace a une methode. Ca nous permet de pouvoir changer de niveau sans bug

    public static void GPiege() {
        timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                for (Shape s : Var.obstacles) { //Parcour de la liste d'obstacle pour vérifier pour chaque objet compatible si son bouton est sur on
                    //Gestion mur movible
                    if (s instanceof MurMovible) { //Si l'objet est un mur movible...
                        if (((MurMovible) s).getOrientation().equals("v")) { //SI c'est un mur verticale..
                            if (((MurMovible) s).getBouton().isOn() && ((MurMovible) s).getHeight() > 0) { //ET que son bouton est activé
                                ((MurMovible) s).setY(((MurMovible) s).getY() + VITESSE_CHUTE_MUR); //On modifie sa valeur Y pour qu'a chaque frame elle augmente de la vitesse choisit (et avoir un dacallage du mur vers le bas)
                                ((MurMovible) s).setHeight(((MurMovible) s).getHeight() - VITESSE_CHUTE_MUR); //ET on modifie sa taille à chaque frame pour donner l'impression que le mur entre dans le sol
                            } else if (!((MurMovible) s).getBouton().isOn() && ((MurMovible) s).getPositionInitiale() < ((MurMovible) s).getY() && s != Var.personnage.getCollisionAvec()) { //Sinon si le boutton est sur off et que la valeur actuelle de la taille est superieur à l'etat initial du mur..
                                /*
                                On diminue la valeur Y pour que le mur remonte et on augmente sa taille pour donné une impression d'animation.
                                Une fois l'objet revenue dans son etat initial, cette condition n'est plus éxécuté
                                 */
                                ((MurMovible) s).setY(((MurMovible) s).getY() - VITESSE_CHUTE_MUR);
                                ((MurMovible) s).setHeight(((MurMovible) s).getHeight() + VITESSE_CHUTE_MUR);
                            }
                        } else { //Sinon si le mur est horizontal
                            if (((MurMovible) s).getBouton().isOn() && ((MurMovible) s).getWidth() > 0) { //Si le bouton est activé
                                ((MurMovible) s).setWidth(((MurMovible) s).getWidth() - VITESSE_CHUTE_MUR); //On diminue simplement sa taille, la valeur de X n'a pas besoin d'etre changée, et puisqu'elle se trouve sur la gauche, le mur sera animé de droite à gauche
                            } else if (!((MurMovible) s).getBouton().isOn() && ((MurMovible) s).getPositionInitiale() > ((MurMovible) s).getX() + ((MurMovible) s).getWidth()) {
                                ((MurMovible) s).setWidth(((MurMovible) s).getWidth() + VITESSE_CHUTE_MUR); ////Sinon si le boutton est sur off et que la valeur actuelle de la taille est superieur à l'etat initial du mur..
                            }
                        }
                    }
                    //Gestion des lasers
                    else if (s instanceof Laser) { //Si l'objet s est un laser...
                        if (((Laser) s).getBouton().isOn() && ((Laser) s).getHeight() > 0) { //ET que son bouton est activé...
                            //Alors on met sa taille et sa valeur Y à 0 pour qu'il disparrait de l'ecran
                            ((Laser) s).setY(0);
                            ((Laser) s).setHeight(0);
                        } else if (!((Laser) s).getBouton().isOn() && ((Laser) s).getPositionInitiale() != ((Laser) s).getY()) { //et sinon si son bouton est sur off et que les valeurs sont différentes (ajouté pour eviter une execution constante)
                            // on replace le laser dans sa configuration initiale
                            ((Laser) s).setY(((Laser) s).getPositionInitiale());
                            ((Laser) s).setHeight(((Laser) s).getHauteurI());
                        }
                    }
                }
            }
        };
        timer.start();
    }

    //Methode qui permet d'arreter le timer pour pouvoir changer de niveau
    public static void stopTimer() {
        timer.stop();
    }
}
