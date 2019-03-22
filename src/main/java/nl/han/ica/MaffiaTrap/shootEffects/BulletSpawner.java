package nl.han.ica.MaffiaTrap.shootEffects;

import nl.han.ica.MaffiaTrap.gameStates.MaffiaState;
import nl.han.ica.MaffiaTrap.main.MaffiaTrapApp;
import nl.han.ica.OOPDProcessingEngineHAN.Alarm.Alarm;
import nl.han.ica.OOPDProcessingEngineHAN.Alarm.IAlarmListener;

/**
 * BulletSpawner.java
 * Bel-klasse voor schietkogels van Bully.
 * Zorgt dat schietkogels van Bully door middel van een timer tevoorschijn komen.
 * @author Vu Le
 */

public class BulletSpawner implements IAlarmListener {

    private MaffiaTrapApp app;
    private double bulletsPerSecond; //Aantal bullets per seconde.
    private int x;
    private int y;
    private Alarm alarm;


    /**
     * Constructor voor het maken van een afvuurder van schietkogels.
     * @param app Referentie naar de wereld (MaffiaTrapApp).
     * @param bulletsPerSecond Aantal schietkogels die per seconde afgevuurd moeten worden.
     * @param x X-coördinaat waar de schietkogel aangemaakt moet worden.
     * @param y Y-coördinaat waar de schietkogel aangemaakt moet worden.
     */

    public BulletSpawner(MaffiaTrapApp app, double bulletsPerSecond, int x, int y){
        this.app = app;
        this.bulletsPerSecond = bulletsPerSecond;
        this.x = x;
        this.y = y;
        startAlarm();
    }

    /**
     * Functie die een schietkogel aanmaakt
     */

    private void createObject(){
        Bullet bullet = new Bullet(app, x, y);
        app.addGameObject(bullet);
    }

    /**
     * Start de timer om schietkogels af te vuren
     */


    private void startAlarm() {
        alarm = new Alarm("Lamp", (1/bulletsPerSecond));
        alarm.addTarget(this);
        alarm.start();
    }

    /**
     * Stop de timer om schiefkogels af te vuren
     */

    private void stopAlarm() {
        alarm.stop();
    }

    /**
     * Maakt schietkogels aan.
     * @param alarmName Naam van het alarm
     */

    @Override
    public void triggerAlarm(String alarmName){
        if(!app.bullyExists || app.state == MaffiaState.GAMEOVER || app.state == MaffiaState.GAMEWIN){
            stopAlarm();
        }
        else {
            createObject();
            startAlarm();
        }
    }
}
