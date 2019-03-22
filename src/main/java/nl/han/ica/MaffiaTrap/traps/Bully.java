package nl.han.ica.MaffiaTrap.traps;

import nl.han.ica.MaffiaTrap.entities.Chest;
import nl.han.ica.MaffiaTrap.entities.Player;
import nl.han.ica.MaffiaTrap.main.MaffiaTrapApp;
import nl.han.ica.MaffiaTrap.shootEffects.Bullet;
import nl.han.ica.MaffiaTrap.shootEffects.BulletSpawner;
import nl.han.ica.MaffiaTrap.shootEffects.Fireball;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;

import java.util.Random;

/**
 * Bully.java
 * Hierin wordt de gangster geinitialiseerd, die dient als vijand van de speler.
 * @author Vu Le
 */
public class Bully extends SpriteObject {

    private MaffiaTrapApp app;
    private BulletSpawner spawner;
    private int maxXBully;

    /** Constructor
     * @param app Referentie naar de wereld (SuperMeronApp)
     * @param x X-coördinaat waar de gangster aangemaakt moet worden
     * @param y Y-coördinaat waar de gangster aangemaakt moet worden
     */

    public Bully(MaffiaTrapApp app, BulletSpawner spawner, int x, int y){
        super(new Sprite("src/main/java/nl/han/ica/MaffiaTrap/media/bully.png"));
        this.app = app;
        this.spawner = spawner;
        this.maxXBully = app.getWidth() - 200;
        this.setX(x);
        this.setY(y);
        makeBulletSpawner();
    }

    /**
     * Zorgt ervoor dat de Bully in de richting van de player kijkt.
     */
    @Override
    public void update() {
        if (getX() + getWidth() <= getWidth()) {
            setDirection(90);
        }
        if(getX() > maxXBully){
            setX(maxXBully);
        }

    }

    /**
     * Maakt de afvuurder van schietkogels aan, die een gangster gebruikt.
     */
    private void makeBulletSpawner(){
        if(getX() > maxXBully) {
            spawner = new BulletSpawner(app, 0.25, maxXBully, (int) y);
        }
        else{
            spawner = new BulletSpawner(app, 0.25, (int) x, (int) y);
        }
    }


}
