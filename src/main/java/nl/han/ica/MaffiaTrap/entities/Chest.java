package nl.han.ica.MaffiaTrap.entities;

import nl.han.ica.MaffiaTrap.entities.interfaces.IPlayer;
import nl.han.ica.MaffiaTrap.main.MaffiaTrapApp;
import nl.han.ica.MaffiaTrap.powerUps.ExtraLife;
import nl.han.ica.MaffiaTrap.powerUps.Pistol;
import nl.han.ica.MaffiaTrap.powerUps.PowerUp;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;

import java.util.Random;

/**
 * Chest.java
 * De klasse waarin de schatkist wordt aangemaakt.
 * @author Vu Le
 */

public class Chest extends SpriteObject {

    private IPlayer player;
    private MaffiaTrapApp app;
    private int xPowerUp;

    /**
     * Constructor van de klasse Chest
     * @param app Referentie naar de wereld (MaffiaTrapApp).
     * @param player Referentie naar de interface waar functies voor een powerup zitten (IPlayer).
     * @param x X-coördinaat waar de chest geplaatst moet worden tijdens de game.
     * @param y Y-coördinaat waar de chest geplaatst moet worden tijdens de game.
     */

    public Chest(MaffiaTrapApp app, IPlayer player, int x, int y){
        super(new Sprite("src/main/java/nl/han/ica/MaffiaTrap/media/chest.png"));
        this.app = app;
        this.player = player;
        this.xPowerUp = x + 50;
        this.setX(x);
        this.setY(y);
    }

    @Override
    public void update(){

        int permXChest;
        final int size = 400;

        int maxXChest = app.getWidth() - size;
        int minXChest = 300;

        int distanceBetweenChestAndPowerUp = 50;

        if(getX() >= maxXChest) {
            permXChest = maxXChest;
            setX(permXChest);
            xPowerUp = permXChest + distanceBetweenChestAndPowerUp;
        }
        else if(getX() <= minXChest){
            permXChest = minXChest;
            setX(permXChest);
            xPowerUp = permXChest + distanceBetweenChestAndPowerUp;
        }
    }


    /**
     * Kiest een random power-up wanneer de speler de schatkist aanraakt.
     */

    public void initializePowerUp(){
        int amountOfPowerUps = generateRandomNumber(2);
        PowerUp powerUp = null;
        switch(amountOfPowerUps) {
            case 1:
                powerUp = new Pistol(app, player, xPowerUp, (int) y);
                break;
            case 2:
                int yExtraLife = (int) y - 100; //sprite was te groot, dus eerst leek het alsof het extra leven door de grond zakte (Y-coördinaat)
                powerUp = new ExtraLife(app, player, xPowerUp, yExtraLife);
                break;
        }
        app.addGameObject(powerUp);
    }

    /**
     * Genereert een willekeurig getal.
     * @param max Het ingevoerde maximum.
     * @return Random getal binnen de grenzen van het maximale getal.
     */
    private int generateRandomNumber(int max) {
        Random rand = new Random();
        return rand.nextInt(max) + 1;
    }


}
