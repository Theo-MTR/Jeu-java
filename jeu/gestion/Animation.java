/*
Simple classe qui nous permet d'animer un bouton lorsque quelque chose est en contact avec.
Quand on dit animer, c'est juste de le faire monter ou redescendre.
 */

package jeu.gestion;
import jeu.objets.Bouton;

public class Animation {

    //Animation bouton On / Off
    //Methode en protected car elle est utilisée uniquement dans le package et n'a pas besoin d'être utilisé en dehors
    protected static void animBouton(Bouton s) {
        if (s.isOn()) { //Lorsqu'un objet entre en contact avec un bouton, l'attribut "on" du bouton va passer a true ce qui va nous permettre de declencher l'animation
            //Sur les 2 lignes suivante, on modifie la valeur Y du bouton ce qui nous permet de le descendre, et ensuite on diminue sa taille par la meme valeur afin qu'il ne se contente pas que de bouger et qu'il y ai un effet d'enfoncement.
            s.setY(s.getY() + s.getInitialTaille() / 2);
            s.setHeight(s.getInitialTaille() - s.getInitialTaille() / 2);
        } else { //Lorsqu'il n'y a plus de contact avec un bouton, l'attribut "on" du bouton va passer a false ce qui va nous permettre de remettre le bouton dans sont etat initial
            s.setY(s.getInitialY());
            s.setHeight(s.getInitialTaille());
        }
    }

}
