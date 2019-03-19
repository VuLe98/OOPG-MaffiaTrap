package nl.han.ica.MaffiaTrap.powerUps;

import nl.han.ica.MaffiaTrap.main.MaffiaTrapApp;
import nl.han.ica.MaffiaTrap.entities.interfaces.IPlayer;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;

/**
 * ExtraLife.java
 * Hierin wordt een extra leven voor de speler gemaakt.
 * @author Vu Le
 */

public class ExtraLife extends PowerUp{

    private MaffiaTrapApp app;
    private int x;
    private int y;

    /**
     * Constructor voor het maken van een extra leven.
     * @param app Referentie naar de wereld (MaffiaTrapApp).
     * @param player Referentie naar de interface waar de functies van de power-up ExtraLife (addExtraLife) wordt gehaald.
     * @param x X-coördinaat waar het extra leven geplaatst moet worden.
     * @param y Y-coördinaat waar het extra leven geplaatst moet worden.
     */

    public ExtraLife(MaffiaTrapApp app, IPlayer player, int x, int y) {
        super(new Sprite("src/main/java/nl/han/ica/MaffiaTrap/media/extraLife.png"));
        this.app = app;
        this.player = player;
        this.setX(x);
        this.setY(y);
    }

    /**
     * De actie die wordt uitgevoerd wanneer de speler de sprite van het extra leven aanraakt.
     */

    @Override
    public void doAction(){
        addExtraLifeToPlayer();
    }


    @Override
    public void update() {

    }

    /**
     * Voeg een extra leven toe aan de speler.
     */

    private void addExtraLifeToPlayer(){
        app.addExtraLife();
    }

}
