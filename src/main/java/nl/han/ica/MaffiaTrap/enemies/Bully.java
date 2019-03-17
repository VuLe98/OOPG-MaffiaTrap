package nl.han.ica.MaffiaTrap.enemies;

import nl.han.ica.MaffiaTrap.main.MaffiaTrapApp;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;

import java.util.Random;

public class Bully extends SpriteObject {

    private MaffiaTrapApp app;

    /** Constructor
     * @param app Referentie naar de wereld (SuperMeronApp)
     */

    public Bully(MaffiaTrapApp app, int x, int y){
        super(new Sprite("src/main/java/nl/han/ica/MaffiaTrap/media/bully.png"));
        this.app = app;
        this.setX(x);
        this.setY(y);
    }

    /**
     * Zorgt ervoor dat de Bully in de richting van de player kijkt.
     */
    @Override
    public void update() {
        int speed = 3;
        if (getX() + getWidth() <= getWidth()) {
            setDirection(90);
            setDirectionSpeed(180,speed);
        }

    }
}
