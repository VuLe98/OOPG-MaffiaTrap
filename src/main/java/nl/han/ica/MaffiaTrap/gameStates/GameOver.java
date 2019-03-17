package nl.han.ica.MaffiaTrap.gameStates;

import nl.han.ica.MaffiaTrap.main.MaffiaTrapApp;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;

public class GameOver extends SpriteObject {

    private MaffiaTrapApp app;

    /** Constructor
     * @param app Referentie naar de wereld (SuperMeronApp)
     */
    public GameOver(MaffiaTrapApp app) {
        super(new Sprite("src/main/java/nl/han/ica/MaffiaTrap/media/endgame.png"));
        this.app = app;

    }

    @Override
    public void update() {
    }
}
