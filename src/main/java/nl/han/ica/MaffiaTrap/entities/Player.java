package nl.han.ica.MaffiaTrap.entities;

import nl.han.ica.MaffiaTrap.shootEffects.Bullet;
import nl.han.ica.MaffiaTrap.traps.Bully;
import nl.han.ica.MaffiaTrap.traps.Lamp;
import nl.han.ica.MaffiaTrap.standardGameObjects.Door;
import nl.han.ica.MaffiaTrap.gameStates.MaffiaState;
import nl.han.ica.MaffiaTrap.main.MaffiaTrapApp;
import nl.han.ica.MaffiaTrap.powerUps.PowerUp;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.*;
import nl.han.ica.OOPDProcessingEngineHAN.Engine.GameEngine;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.AnimatedSpriteObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Persistence.IPersistence;

import java.util.List;

/**
 * Player.java
 * De spelersklasse.
 * @author Vu Le
 */

public class Player extends AnimatedSpriteObject implements ICollidableWithGameObjects{

    private int speed = 3;
    private GameEngine world;
    private IPersistence persistence;
    private String keyGiven;
    private MaffiaTrapApp app;

    /** Constructor
     * @param world Referentie naar de binnenkant van de game (GameEngine).
     * @param app Referentie naar de wereld (SuperMeronApp).
     */

    public Player(GameEngine world, MaffiaTrapApp app) {

        super(new Sprite("src/main/java/nl/han/ica/MaffiaTrap/media/playersprite.png"), 2);
        this.world = world;
        this.app= app;
        setCurrentFrameIndex(1);
        setFriction(0.05f);
    }


    public void gameObjectCollisionOccurred(List<nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject> collidedGameObjects) {

        for (GameObject g:collidedGameObjects) {
            //Als speler de deur opent
            if (g instanceof Door) {
                setX(world.getWidth() - 90);
                speed = 0;
                app.state = MaffiaState.GAMEWIN;
            }

            //Als speler de schatkist aanraakt
            if (g instanceof Chest) {
                ((Chest) g).initializePowerUp();
                app.deleteGameObject(g);
            }

            //Als speler de random gekozen powerup uit de schatkist aanraakt
            if(g instanceof PowerUp){
                ((PowerUp) g).doAction();
                app.deleteGameObject(g);
            }


        }

        for(GameObject l: collidedGameObjects){
            if (l instanceof Lamp || l instanceof Bully || l instanceof Bullet) {
                removeLife(l);
            }
        }

    }

    /**
     * Verlaagt het levensaantal van de speler met 1, checkt ook wanneer speler doodgaat.
     * @param g Het object dat verwijderd moet worden.
     */

    private void removeLife(GameObject g){
        app.deleteGameObject(g);
        app.countOffExtraLife();
        if(app.getCurrentLives() == 0){
            speed = 0;
            setxSpeed(0);
            setY(0);
            setX(0);
            app.state = MaffiaState.GAMEOVER;
        }
    }

    /**
     * Initialiseert player-bewegingen.
     */

    @Override
    public void update() {
        final int size = 10;

        setySpeed(3);

        if (getX() <= 0) {
            setxSpeed(5);
            setX(0);
        }
        if (getY() <= 0) {
            setySpeed(0);
            setY(0);
        }
        if (getX() >= world.getWidth() - size) {
            setxSpeed(0);
            setX(world.getWidth() - size);

        }
        if (getY() >= world.getHeight() - size) {
            setySpeed(0);
            setY(world.getHeight() - size);
        }


        //Zorgt dat player niet naar BENEDEN kan bewegen
        int grensY = 445;
        if (getY() >= grensY){
            setY(grensY);
        }


    }

    /**
     * Initialiseert de toetsenbordkeys voor het spelen.
     * @param keyCode Pijltjestoetsen in dit geval.
     * @param key Springtoets in dit geval.
     */
    @Override
    public void keyPressed(int keyCode, char key) {

        if (keyCode == world.LEFT) {
            setDirectionSpeed(270, speed);
            setCurrentFrameIndex(0);
        }
        if (keyCode == world.UP) {
            setDirectionSpeed(180, speed);
            setY(360);
        }
        if (keyCode == world.RIGHT) {
            setDirectionSpeed(90, speed);
            setCurrentFrameIndex(1);
            keyGiven = "RIGHT";
        }

        if (key == ' ') {
            //springen
            setDirectionSpeed(180, speed);
            setY(410);

        }

    }


}

