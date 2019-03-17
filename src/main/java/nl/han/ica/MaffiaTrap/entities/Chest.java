package nl.han.ica.MaffiaTrap.entities;

import nl.han.ica.MaffiaTrap.main.MaffiaTrapApp;
import nl.han.ica.MaffiaTrap.powerUps.ExtraLife;
import nl.han.ica.MaffiaTrap.powerUps.Pistol;
import nl.han.ica.MaffiaTrap.powerUps.PowerUp;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;

import java.util.Random;

public class Chest extends SpriteObject {

    private IPlayer player;

    private MaffiaTrapApp app;

    public Chest(MaffiaTrapApp app, IPlayer player, int x, int y){
        super(new Sprite("src/main/java/nl/han/ica/MaffiaTrap/media/chest.png"));
        this.app = app;
        this.player = player;
        this.setX(x);
        this.setY(y);
    }

    @Override
    public void update(){
    }

    public void initializePowerUp(){
        int amountOfPowerUps = generateRandomNumber(2);
        PowerUp powerUp = null;
        int xPowerUp = (int) x + 50;
        switch(amountOfPowerUps) {
            case 1:
                powerUp = new Pistol(app, player, xPowerUp, (int) y);
                break;
            case 2:
                powerUp = new ExtraLife(app, player, xPowerUp, (int) y);
                break;
        }
        app.addGameObject(powerUp);
    }

    private int generateRandomNumber(int max) {
        Random rand = new Random();
        return rand.nextInt(max) + 1;
    }


}
