/*
Fonctionnement générale de cette classe:
On va a chaque frame parcourir une liste d'obstacles. Si il y a intersection entre 2 objets, c'est qu'il y a collision. On execute ensuite une methode en fonction du type de l'objet en collision.
Pour executer du code à chaque frame, on utilise un Animation Timer, qui va agir comme une boucle
 */
package jeu.gestion;

import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import jeu.gui.Main;
import jeu.objets.*;


public class Collision {

    private static AnimationTimer timer;
    private static AnimationTimer timer2;

    public static void CheckCollision() {
        //Collision personnage
        //Utilisation d'un timer qui nous permet d'executer le code à l'interieur à chaque frame
        timer = new AnimationTimer() {
            @Override
            public void handle(long l) {

                for (Shape s : Var.obstacles) {
                    if (Var.personnage.intersects(s.getBoundsInParent())) {
                        if (!(s instanceof Cube)) {
                            Var.personnage.setCollisionAvec(s); //Enregistrer objet en collision avec le personnage (utile pour la gestion de la gravité par exemple qui permet de reactiver la gravité lorsqu'on quitte une plateforme à la marche)
                        }
                        if (s instanceof Mur || s instanceof MurMovible)
                            ActionPersoMur(s); //Action lorsqu'on entre en collision avec un mur
                        else if (s instanceof Scie || s instanceof Laser || s instanceof Pique)
                            actionPiege(s); //Action quand on rentre en collision avec une scie
                        else if (s instanceof Bouton && !((Bouton) s).isOn())
                            ActionPersonBouton((Bouton) s); //Activer animation bouton lorsqu'il y a collision avec un bouton
                            //else if (s instanceof Cube && !Var.toucheT); // ActionPersoCube();
                        else if (s instanceof MurBlanc)
                            ActionPersoMurBlanc((MurBlanc) s); //Action realisé lorsque le personnage entre en collision avec un portail
                        else if (s instanceof Cube && Touche.isToucheT() && Var.personnage.getCubeSelect() == null)
                            Var.personnage.setCubeSelect((Cube) s); //Enregistrer le cube entrain d'être porté
                        else if (s instanceof Porte) {
                            niveauSuivant(((Porte) s));
                            break;
                        }
                    }
                }
            }
        };
        //Collision cube
        timer2 = new AnimationTimer() {
            @Override
            public void handle(long l) {
                for (Shape s : Var.obstacles) {
                    if (Var.personnage.getCubeSelect() != null && !(s instanceof Cube) && Var.personnage.getCubeSelect().intersects(s.getBoundsInParent())) { //Ne pas oublier de verifier qu'il y a un cube de selectionné sinon erreur
                        if (!(s instanceof MurBlanc) && !(s instanceof Bouton)) { // Arreter la chute du cube lorsqu'il entre en collision avec un objet autre qu'un portail ou un bouton
                            Var.personnage.getCubeSelect().setEnGravite(false); //Arreter de le faire tomber
                            replacerCube(s); //Replacer le cube sur le sol en collision
                        }
                        if (s instanceof MurBlanc) cubeMB((MurBlanc) s); //Collision cube avec portail
                        if (s instanceof Bouton && !((Bouton) s).isOn())
                            ActionCubeBouton((Bouton) s); //Activer bouton lorsq'un cube entre en collision avec
                    }
                }
            }
        };
        timer.start();
        timer2.start();
    }

    //Methode utiliser pour "redemarrer" la classe lors d'un chagement de niveau (Presente dans toutes les classes utilisant un animation timer)
    public static void stopTimer() {
        timer.stop();
        timer2.stop();
    }

    //Replacer le cube lorsqu'il est en collision
    private static void replacerCube(Shape s) {
        if (Var.personnage.getX() < s.getBoundsInParent().getMinX()) {
            Touche.setToucheD(false); //Arrêter le personnage (mettre la touche d a false car on vérifie que la valeur x du perso est inférieure a celle de l'objet en collision, si c'est le cas le perso se trouve alors sur la gauche et entre en collision avec un objet a sa droite d'ou la touche d a false
            Var.personnage.setX(Var.personnage.getX() - (Var.scene.getWidth() * 0.005)); //Replacer le personnage
            Var.personnage.getCubeSelect().setX(Var.personnage.getX() + Var.personnage.getWidth() + 1); //Replacer le cube
        } else if (Var.personnage.getX() + Var.personnage.getWidth() > s.getBoundsInParent().getMaxX()) {
            Touche.setToucheQ(false); //Arrêter le personnage (mettre la touche d à false car on vérifie que la valeur x du perso est supérieure à celle de l'objet en collision, si c'est le cas le perso se trouve alors sur la droite et entre en collision avec un objet à sa gauche d'ou la touche q à false
            Var.personnage.setX(Var.personnage.getX() + (Var.scene.getWidth() * 0.005)); //Replacer le personnage
            Var.personnage.getCubeSelect().setX(Var.personnage.getX() - Var.personnage.getCubeSelect().getWidth() - 1); //Replacer le cube
        } else { //Le dernier type de collision possible d'un cube est quand il tombe vers le bas, ce qui est géré avec ce else
            Var.personnage.getCubeSelect().setY(s.getBoundsInParent().getMinY() - Var.personnage.getCubeSelect().getHeight() - 1); //Replacer le cube
            Var.personnage.setCubeSelect(null); //Si il y a se type de collision, c'est qu'un cube a été sélectionné, on remet donc la variable qui enregistre le cube sélectionné a false puisque si il y a collision c'est que le cube a été laché
        }
    }


    //Action quand le perso entre en collision avec un mur
    private static void ActionPersoMur(Shape s) {
        if (Var.personnage.getX() < s.getBoundsInParent().getMinX() && Var.personnage.getY() > s.getBoundsInParent().getMinY()) {
            /*Meme principe que la collision avec les cubes, ici le personnage se trouve a gauche de l'objet sélectionné (on ajoute la comparaison de la valeur Y afin de verifier que l'obstacles est bien plus grand que
            le personnage, sinon le perso n'avancera plus même s'il est au dessus d'un mur*/
            Touche.setToucheD(false);//On l'empeche d'avancer en remettant la variable de la touche a false
            Var.personnage.setX(Var.personnage.getX() - (Var.scene.getWidth() * 0.005)); //On enleve le point d'intersection entre le personnage et l'objet en deplaçant le personnage
        } else if (Var.personnage.getX() + Var.personnage.getWidth() > s.getBoundsInParent().getMaxX() && Var.personnage.getY() > s.getBoundsInParent().getMinY()) { //Pareil, mais cette fois ci le perso est à droite
            Touche.setToucheQ(false);
            Var.personnage.setX(Var.personnage.getX() + (Var.scene.getWidth() * 0.005));
        }
        if (Var.personnage.getY() + (Var.personnage.getHeight() / 2) > s.getBoundsInParent().getMaxY()) { //Ici le perso entre en collision un mur de dessous (qui est aussi un sol)
            Var.personnage.setY(Var.personnage.getEtatInitial()); //On replace le personnage dans son etat initial avant de sauté (puisque le fait d'entrer dans un mur par dessous signifie qu'on a sauté..)
            /*Cette methode est executer quand le perso saute, comme le saut utilise la variable vitesseG (utilie pour la gravité), et qu'on veut dans ce cas precis que le perso retombe
            (donc que la gravité reprenne le dessus par rapport au saut), on remet la valeur de la vitesseG a 0*/
            Gravite.setVitesseG(0);
            /*On reactive la gravité du personnage, comme ça si il se prend un mur et qu'il retombe sans sol en dessous, alors il continuera de tomber (sinon il y avais un probleme
            comme on replace le personnage a sa valeur initiale, si c'etait une petite plateforme il arrivait que le personnage retombe mais s'arrete dans le vide a coté de la plateforme)*/
            Var.personnage.setEnGravite(true);
        } else if (Var.personnage.getY() <= s.getBoundsInParent().getMinY()) { //Ici le perso arrive par dessus sur un mur
            Var.personnage.setEnGravite(false); //On arrete donc la gravité car on ne veux pas que le perso traverse le mur
            Var.personnage.setY(s.getBoundsInParent().getMinY() - Var.personnage.getHeight()); //On replace correctement le personnage sur son sol
        }
        Touche.setToucheSaut(false);
    }

    //Action quand le perso entre en collision avec un bouton
    private static void ActionPersonBouton(Bouton s) {
        Var.personnage.setEnGravite(false); //On ne veut pas que le personnage traverse le bouton, donc gravité a false
        s.setOn(true); //Activation du bouton (ce qui permet de dire au piège qui lui est relié qu'il peut se désactiver)
        Animation.animBouton(s); //Animation du bouton (qui permet simplement de l'abaisser
        Var.personnage.setEtatInitial(s.getY() - Var.personnage.getHeight());
        Var.personnage.setY(Var.personnage.getEtatInitial());
    }

    //Action qaund un cube entre en collision avec un bouton
    private static void ActionCubeBouton(Bouton s) {
        s.setCube(Var.personnage.getCubeSelect());
        s.setOn(true); //Cube en position on
        Animation.animBouton(s); //animation du bouton
        if (Var.personnage.getCubeSelect().getY() < s.getY()) { //Reglages lorsque le cube arrive au dessus d'un obstacles
            Var.personnage.getCubeSelect().setY(s.getY() - Var.personnage.getCubeSelect().getHeight() - 1); //Replacer le cube
            Var.personnage.getCubeSelect().setEnGravite(false); //Arreter de faire tomber le cube (mettre la gravité à false
            Var.personnage.setCubeSelect(null); //Comme on enregistre le cube selectionné par le perso, lorsqu'il y a une collision par dessus c'est que le cube a été laché et n'est plus séléctionné par le perso, on remet donc la variable cubeSelect a null;
        }
    }

    private static void cubeMB(MurBlanc m) {
        if (m == Var.murEntree && Var.murSortie != null) { //On verifie d'abord que l'objet en collision avec le cube est bien le portail d'entré et que le portail de sortie a été sélectionné (que sa variable n'est pas a null)
            if (Var.murSortie.getOrientation().equals("d")) { //Si la sortie du portail se situe sur la droite....
                Var.personnage.getCubeSelect().setY(Var.murSortie.getY() + Var.murSortie.getHeight() - Var.personnage.getHeight() - 1);
                Var.personnage.getCubeSelect().setX(Var.murSortie.getX() + Var.murSortie.getWidth() + (Var.scene.getWidth() * 0.005));
            }
            if (Var.murSortie.getOrientation().equals("g")) { //Si la sortie du portail est a gauche....
                Var.personnage.getCubeSelect().setY(Var.murSortie.getY() + Var.murSortie.getHeight() - Var.personnage.getHeight() - 1);
                Var.personnage.getCubeSelect().setX(Var.murSortie.getX() - Var.personnage.getWidth() - 1);
            }
            Touche.setToucheT(false);
        }
    }

    //Action quand le perso entre en collision avec un mur blanc (avec et sans portail)
    private static void ActionPersoMurBlanc(MurBlanc s) {
        if (s == Var.murEntree && Var.murSortie != null) { //On verifie d'abord que l'objet en collision avec le perso est bien le portail d'entré et que le portail de sortie a été sélectionné (que sa variable n'est pas a null)
            if (Var.murSortie.getOrientation().equals("d")) { //Si la sortie du portail se situe sur la droite....
                Var.personnage.setEtatInitial(Var.murSortie.getY() + Var.murSortie.getHeight() - Var.personnage.getHeight() - 1); //On enregistre le nouveau placement du joueur (qui est le point Y du mur + sa hauteur (ce qui ramene a sa base) ou on vient retirer la hauteur du personnage car le point Y se trouve en haut a gauche de celui ci, sans sa il se téléporterai dans le sol du portail)
                Var.personnage.setX(Var.murSortie.getX() + Var.murSortie.getWidth() + (Var.scene.getWidth() * 0.005)); //On change la valeur du point x du personnage pour qu'il corresponde à celui du portail de sortie (son point x en haut a gauche + sa largeur pour etre pile au portail + 10 pour eviter une collision constante)
            }
            if (Var.murSortie.getOrientation().equals("g")) { //Si la sortie du portail est a gauche....
                Var.personnage.setEtatInitial(Var.murSortie.getY() + Var.murSortie.getHeight() - Var.personnage.getHeight() - 1); //Meme principe qu'au dessus
                Var.personnage.setX(Var.murSortie.getX() - Var.personnage.getWidth() - 1); //Meme principe qu'au dessus mais en changeant les signes pour que la sortie se fasse à gauche
            }
            if (Var.murSortie.getOrientation().equals("b")) { //Si la sortie du mur est vers le bas...
                Var.personnage.setX(Var.murSortie.getX() + Var.personnage.getWidth() - 1); //Changement de la valeur de x du perso pour qu'il corresponde a celui du portail
                Var.personnage.setEtatInitial(Var.murSortie.getY() + Var.murSortie.getHeight() + 1); //Pareil pour la valeur de l'etat initial

            }
            Var.personnage.setY(Var.personnage.getEtatInitial()); //On applique le nouvel etat initial comme nouvel valeur de y du perso
            Var.personnage.setEnGravite(true); //On active la gravité sur le personnage car comme il sort par le bas du portail, on veut forcement qu'il chute
                /*
                Les 4 lignes qui suivent permettent d'enlever les portails une fois traversé, on les remet en blanc et on les retires des variables
                 */
            Var.murEntree.setFill(Color.WHITE);
            Var.murSortie.setFill(Color.WHITE);
            Var.murSortie = null;
            Var.murEntree = null;
        } else
            ActionPersoMur(s); //Si l'objet en collision avec le perso n'est pas le mur d'entré ou alors le mur de sortie n'a pas été selectionné, on fait en sorte que le portail se comporte comme un simple mur puisqu'on ne veut pas qu'il soit traversé
    }

    //Methode lancé lorsque le personnage entre en collision avec la porte de sortie
    private static void niveauSuivant(Porte p) {
        if (Var.personnage.getX() > p.getX()) { //On fait en sorte que ça s'execute lorsque le perso est bien derriere la porte
            switch (p.getNiveau()) { //On verifie quel niveau est terminé par la porte
                case 0: //niveau d'intro
                    Var.n0 = true;
                    break;
                case 1: //si c'est le 1...
                    Var.n1 = true; //On met n1 a true pour indiquer que le niveau 1 est terminé
                    break;
                case 2:
                    Var.n2 = true;
                    break;
                default:
                    break;
            }
            Main.demarrer(); //On réexecute la fonction démarrer du fichier main
        }
    }

    //Methode executer lors de la collision avec un piège
    private static void actionPiege(Shape s) {
        if (s instanceof Pique) { //Si c'est un pique...
            if (Var.personnage.getX() + (Var.personnage.getWidth() / 2) >= ((Pique) Var.personnage.getCollisionAvec()).getX() + (((Pique) Var.personnage.getCollisionAvec()).getWidth() / 2)) { //Si le perso est avant la moitié du piége
                Var.personnage.setX(((Pique) Var.personnage.getCollisionAvec()).getX() + ((Pique) Var.personnage.getCollisionAvec()).getWidth() + 1); //On replace le perso légérement à droite du piège
            } else {//Si la touche q est utilisé, donc si le perso arrive de la gauche et va vers la droite
                Var.personnage.setX(((Pique) Var.personnage.getCollisionAvec()).getX() - Var.personnage.getWidth() - 1);//On replace le perso légérement à gauche du piège
            }
        }
        else {
            if (Touche.isToucheQ()) { //Si la touche q est utilisé, donc si le perso arrive de la droite et va vers la gauche
                Var.personnage.setX(Var.personnage.getX() + Var.personnage.getWidth() + (Var.scene.getWidth() * 0.005)); //On replace le perso légérement à droite du piège
            } else if (Touche.isToucheD()) {//Si la touche q est utilisé, donc si le perso arrive de la gauche et va vers la droite
                Var.personnage.setX(Var.personnage.getX() - (Var.scene.getWidth() * 0.005));//On replace le perso légérement à droite du piège
            }
        }
        Vie.retraitVie();
    }

    //Changer la couleur du label de vie lorqu'on est en dessous d'un certains seuil


}
