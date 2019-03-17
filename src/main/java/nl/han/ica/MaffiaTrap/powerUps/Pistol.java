package nl.han.ica.MaffiaTrap.powerUps;

import nl.han.ica.MaffiaTrap.main.MaffiaTrapApp;
import nl.han.ica.MaffiaTrap.entities.IPlayer;
import nl.han.ica.MaffiaTrap.entities.Player;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;


public class Pistol extends PowerUp {

    public MaffiaTrapApp app;
    public Player maffia;

    public Pistol(MaffiaTrapApp app, IPlayer player, int x, int y){
        super(new Sprite("src/main/java/nl/han/ica/MaffiaTrap/media/Pistol.png"));
        this.app = app;
        this.player = player;
        this.setX(x);
        this.setY(y);
    }

    @Override
    public void doAction(){
        makeFireBall();
    }

    @Override
    public void update(){


    }

    public void makeFireBall(){
        Fireball fireball = new Fireball(app,(int)x,400);
        app.addGameObject(fireball);
    }
}
