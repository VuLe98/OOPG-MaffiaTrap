package nl.han.ica.MaffiaTrap.powerUps;

import nl.han.ica.MaffiaTrap.main.MaffiaTrapApp;
import nl.han.ica.MaffiaTrap.entities.IPlayer;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;


public class ExtraLife extends PowerUp{

    private MaffiaTrapApp app;
    private int x;
    private int y;

    public ExtraLife(MaffiaTrapApp app, IPlayer player, int x, int y) {
        super(new Sprite("src/main/java/nl/han/ica/MaffiaTrap/media/extraLife.png"));
        this.app = app;
        this.player = player;
        this.setX(x);
        this.setY(y);
    }

    @Override
    public void doAction(){
        addExtraLifeToPlayer();
    }


    @Override
    public void update() {

    }

    private void addExtraLifeToPlayer(){
        app.addExtraLife();
    }

}
