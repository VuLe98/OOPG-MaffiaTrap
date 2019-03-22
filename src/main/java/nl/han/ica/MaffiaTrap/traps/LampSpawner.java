package nl.han.ica.MaffiaTrap.traps;

import nl.han.ica.MaffiaTrap.gameStates.MaffiaState;
import nl.han.ica.MaffiaTrap.main.MaffiaTrapApp;
import nl.han.ica.OOPDProcessingEngineHAN.Alarm.Alarm;
import nl.han.ica.OOPDProcessingEngineHAN.Alarm.IAlarmListener;

import java.util.Random;

/**
 * LampSpawner.java
 * Bel-klasse voor de lampen
 * Zorgt dat lampen door middel van een timer tevoorschijn komen.
 * @author Vu Le
 */
public class LampSpawner implements IAlarmListener {

    private MaffiaTrapApp app;
    private double lampsPerSecond; //Aantal lamps per seconde.
    private Alarm alarm;

    /** Constructor
     * @param app Referentie naar de wereld (SuperMeronApp)
     * @param lampsPerSecond Hoeveelheid lampen die per seconde tevoorschijn moeten komen
     */

    public LampSpawner(MaffiaTrapApp app, double lampsPerSecond){
        this.app = app;
        this.lampsPerSecond = lampsPerSecond;
        startAlarm();
    }

    /**
     * Maakt een lamp
     */

    private void createObject(){
        Random x = new Random();
        Lamp l = new Lamp(app);
        app.addGameObject(l, x.nextInt(600) + 300, 0);
    }

    /**
     * Start de timer om lampen te laden
     */

    private void startAlarm() {
        alarm = new Alarm("Lamp", (1/lampsPerSecond));
        alarm.addTarget(this);
        alarm.start();
    }

    private void stopAlarm(){
        alarm.stop();
    }

    /**
     * Maakt lampen aan zolang het aantal lampen onder maxAmountOfLamps zit.
     * @param alarmName Naam van het alarm
     */

    @Override
    public void triggerAlarm(String alarmName){
        if(app.state == MaffiaState.GAMEOVER || app.state == MaffiaState.GAMEWIN){
            stopAlarm();
        }
        else{
            createObject();
            startAlarm();
        }
    }










}
