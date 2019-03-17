package nl.han.ica.MaffiaTrap.standardGameObjects;

import nl.han.ica.MaffiaTrap.main.MaffiaTrapApp;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.AnimatedSpriteObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;

/**
 * Door.java
 * Aanmaken van een deur aan het eind van de wereld
 * In Player.java detecteert de player zelf of hij de deur aanraakt
 * @author Vu Le
 */

public class Door extends AnimatedSpriteObject{

    private MaffiaTrapApp app;
    public int x;
    public int y;

    /** Constructor
     * @param app Referentie naar de wereld (MaffiaTrapApp)
     * @param x  x-cöordinaat waar de deur gezet moeten worden
     * @param y  y-cöordinaat waar de deur gezet moeten worden
     */

    public Door (MaffiaTrapApp app, int x, int y){
        super(new Sprite("src/main/java/nl/han/ica/MaffiaTrap/media/door.png"),3);
        this.app = app;
        setCurrentFrameIndex(1);
        setFriction(0.1f);
        this.setX(x);
        this.setY(y);
    }

    /**
     * Zorgt dat deur niet te dicht op het einde van het scherm komt
     */

    @Override
    public void update() {

        final int size = 50;

        if (getX() >= app.getWidth() - size) {
            setX(app.getWidth() - size);
        }
        if (getY() >= app.getHeight() - size) {
            setY(app.getHeight() - size);
        }

    }

}
