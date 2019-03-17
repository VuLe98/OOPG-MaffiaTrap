package nl.han.ica.MaffiaTrap.gameStates;

import nl.han.ica.MaffiaTrap.MaffiaTrapApp;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;

/**
 * winnerScreen.java
 * Initialiseert het scherm wanneer je wint.
 * @author Lucas Hopman & Vu Le
 */


public class Winner extends SpriteObject {
    private MaffiaTrapApp app;

    /** Constructor
     * @param app Referentie naar de wereld (SuperMeronApp)
     */
    public Winner(MaffiaTrapApp app) {
        super(new Sprite("src/main/java/nl/han/ica/MaffiaTrap/media/ontsnapt.png"));
        this.app = app;
        setFriction(0.1f);

    }

    @Override
    public void update() {

    }
}
