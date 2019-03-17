package nl.han.ica.MaffiaTrap.enemies;

import nl.han.ica.MaffiaTrap.MaffiaTrapApp;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;

public abstract class Enemy extends SpriteObject {

    private MaffiaTrapApp app;

    public Enemy(Sprite sprite, int TotalFrames, MaffiaTrapApp app){
        super(sprite);
        this.app = app;
    }

}
