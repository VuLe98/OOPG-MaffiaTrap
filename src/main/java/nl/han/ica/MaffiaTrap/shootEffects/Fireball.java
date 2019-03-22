package nl.han.ica.MaffiaTrap.shootEffects;

import nl.han.ica.MaffiaTrap.traps.Bully;
import nl.han.ica.MaffiaTrap.main.MaffiaTrapApp;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.AnimatedSpriteObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;

import java.util.List;

/**
 * Fireball.java
 * Hierin wordt een vuurbal aangemaakt die door Pistol afgevuurd wordt.
 * @author Vu Le
 */
public class Fireball extends AnimatedSpriteObject implements ICollidableWithGameObjects {

    private MaffiaTrapApp app;
    /**
     * Constructor voor het maken van een vuurbal.
     * @param app Referentie naar de wereld (MaffiaTrapApp).
     * @param x X-coördinaat waar de vuurbal afgevuurd moet worden.
     * @param y Y-coördinaat waar de vuurbal afgevuurd moet worden.
     */
    public Fireball(MaffiaTrapApp app, int x, int y) {
        super(new Sprite("src/main/java/nl/han/ica/MaffiaTrap/media/fireball.png"),2);
        this.app = app;
        this.setX(x);
        this.setY(y);
        setCurrentFrameIndex(1);
        setFriction(0.05f);
    }

    /**
     * Stelt de snelheid en bewegingsrichting van de vuurbal vast
     */

    @Override
    public void update(){
        //Richting + snelheid vuurbal//
        int speed = 3;
        setDirectionSpeed(90, speed);
    }

    /**
     * Actie wanneer de vuurbal Bully raakt, wordt Bully verwijderd.
     * @param collidedGameObjects Lijst van objecten waartegen aangebotst kan worden.
     */
    public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {

        for (GameObject g : collidedGameObjects) {
            //Als de vuurbal de gangster raakt
            if (g instanceof Bully) {
                app.deleteGameObject(g);
                app.bullyExists = false;
            }
        }
    }

}
