package nl.han.ica.MaffiaTrap.powerUps;

import nl.han.ica.MaffiaTrap.entities.IPlayer;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;

/**
 * PowerUp.java
 * Hierin worden de functies van een power-up geinitialiseerd.
 * @author Vu Le
 */
public abstract class PowerUp extends SpriteObject {

    protected IPlayer player;

    /**
     * Constructor voor het op scherm krijgen van de sprite van een power-up.
     * @param sprite De sprite van een powerup.
     */

    public PowerUp(Sprite sprite){ super(sprite);}

    /**
     * Voert de actie van een power-up uit.
     */
    public abstract void doAction();

}
