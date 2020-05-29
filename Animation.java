package org.openjfx;

public class Animation {
    protected static void animBouton(Bouton s) {
        if (s.isOn()) {
            s.setY(s.getY() + s.getInitialTaille() / 2);
            s.setHeight(s.getInitialTaille() - s.getInitialTaille() / 2);
        } else {
            s.setY(s.getInitialY());
            s.setHeight(s.getInitialTaille());
        }
    }
}
