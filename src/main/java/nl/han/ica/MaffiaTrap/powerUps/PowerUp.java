package nl.han.ica.MaffiaTrap.powerUps;

import nl.han.ica.MaffiaTrap.entities.IPlayer;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;

public abstract class PowerUp extends SpriteObject {

    protected IPlayer player;

    public PowerUp(Sprite sprite){ super(sprite);}

    /**
     * Executes the action of a power-up.
     */
    public abstract void doAction();

}
