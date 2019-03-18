package nl.han.ica.MaffiaTrap.shootEffects;

import nl.han.ica.MaffiaTrap.entities.Player;
import nl.han.ica.MaffiaTrap.main.MaffiaTrapApp;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.AnimatedSpriteObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;

/**
 * Bullet.java
 * Hierin worden schietkogels gemaakt die een Bully gebruikt om te schieten op de speler.
 * @author Vu Le
 */

public class Bullet extends AnimatedSpriteObject {

    private MaffiaTrapApp app;
    private int x;
    private int y;
    private Player maffia;

    /**
     * Constructor voor het maken van een schietkogel.
     * @param app Referentie naar de wereld (MaffiaTrapApp)
     * @param x X-coördinaat waar de schietkogel gemaakt moet worden
     * @param y Y-coördinaat waar de schietkogel gemaakt moet worden
     */

    public Bullet(MaffiaTrapApp app, int x, int y) {
        super(new Sprite("src/main/java/nl/han/ica/MaffiaTrap/media/bullet.png"),2);
        this.app = app;
        this.setX(x);
        this.setY(y);
        setCurrentFrameIndex(1);
        setFriction(0.05f);
    }

    /**
     * Stelt de snelheid en bewegingsrichting van de kogel vast
     */

    @Override
    public void update(){
        int speed = 3;
        setDirectionSpeed(270, speed);
    }
}
