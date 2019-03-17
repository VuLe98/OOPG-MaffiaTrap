package nl.han.ica.MaffiaTrap.powerUps;

import nl.han.ica.MaffiaTrap.MaffiaTrapApp;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.AnimatedSpriteObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;

import java.util.Random;

public class Fireball extends AnimatedSpriteObject {

    private MaffiaTrapApp app;
    private int x;
    private int y;

    public Fireball(MaffiaTrapApp app, int x, int y) {
        super(new Sprite("src/main/java/nl/han/ica/MaffiaTrap/media/fireball.png"),2);
        this.app = app;
        this.setX(x);
        this.setY(y);
        setCurrentFrameIndex(1);
        setFriction(0.05f);
    }

    @Override
    public void update(){
        fireballProperties();
    }


    private void fireballProperties(){
        //Richting + snelheid vuurbal//
        int speed = 3;
        setDirectionSpeed(90, speed);
    }
}
