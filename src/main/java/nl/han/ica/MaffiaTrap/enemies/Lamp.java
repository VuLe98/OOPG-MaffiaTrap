package nl.han.ica.MaffiaTrap.enemies;

import nl.han.ica.MaffiaTrap.MaffiaTrapApp;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.AnimatedSpriteObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;

import java.util.Random;

public class Lamp extends AnimatedSpriteObject {

    private MaffiaTrapApp app;

    /**Constructor
     * @param app Referentie naar de wereld (SuperMeronApp)
     */

    public Lamp (MaffiaTrapApp app){
        super(new Sprite("src/main/java/nl/han/ica/superMeron/media/lamp.png"),3);
        this.app = app;
        setCurrentFrameIndex(1);

    }

    /**
     * Zorgt dat er lampen vallen en dat de lampen vallen tot een bepaald punt (Y-cöordinaat)
     */

    @Override
    public void update() {
        //Valsnelheid lampen//
        Random getal = new Random();
        int speed = getal.nextInt(2);
        setDirectionSpeed(180, speed);

        //Zorgt dat lampen niet verder dan op het ondergrond vallen
        int valgrensY = 300;
        if (getY() >= valgrensY) {
            setY(valgrensY);
        }
    }
}
