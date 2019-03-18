package nl.han.ica.MaffiaTrap.traps;

import nl.han.ica.MaffiaTrap.main.MaffiaTrapApp;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;

import java.util.Random;
/**
 * Lamp.java
 * Initialiseert de vallende lampen
 * @author Vu Le
 */
public class Lamp extends SpriteObject {

    private MaffiaTrapApp app;

    /**Constructor
     * @param app Referentie naar de wereld (SuperMeronApp)
     */

    public Lamp (MaffiaTrapApp app){
        super(new Sprite("src/main/java/nl/han/ica/MaffiaTrap/media/lamp.png"));
        this.app = app;

    }

    /**
     * Zorgt dat er lampen vallen en dat de lampen vallen tot een bepaald punt (Y-cöordinaat)
     */

    @Override
    public void update() {
        //Valsnelheid + richting lampen//
        int speed = 1;
        setDirectionSpeed(180, speed);

        //Zorgt dat lampen niet verder dan op het ondergrond vallen
        int valgrensY = 300;
        if (getY() >= valgrensY) {
            app.deleteGameObject(this);
        }
    }
}
