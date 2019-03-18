package nl.han.ica.MaffiaTrap.traps;

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
    private int currentAmountofLamps; //Huidige hoeveelheid lamps op het scherm.
    private int maxAmountOfLamps; //Maximale hoeveelheid lamps in de game.
    private double lampsPerSecond; //Aantal lamps per seconde.

    /** Constructor
     * @param app Referentie naar de wereld (SuperMeronApp)
     * @param lampsPerSecond Hoeveelheid lampen die per seconde tevoorschijn moeten komen
     */

    public LampSpawner(MaffiaTrapApp app, double lampsPerSecond){
        this.app = app;
        this.currentAmountofLamps = 0;
        Random rand = new Random();
        this.maxAmountOfLamps = rand.nextInt(3) + 1;
        System.out.println(maxAmountOfLamps);
        this.lampsPerSecond = lampsPerSecond;
        startAlarm();
    }

    /**
     * Maakt een lamp
     */

    public void createObject(){
        Random x = new Random();
        Lamp l = new Lamp(app);
        app.addGameObject(l, x.nextInt(600) + 300, 0);
    }

    /**
     * Start de timer om lampen te laden
     */

    private void startAlarm() {
        Alarm alarm = new Alarm("Lamp", (1/lampsPerSecond));
        alarm.addTarget(this);
        alarm.start();
    }

    /**
     * Maakt lampen aan zolang het aantal lampen onder maxAmountOfLamps zit.
     * @param alarmName Naam van het alarm
     */

    @Override
    public void triggerAlarm(String alarmName){

        if (currentAmountofLamps <= maxAmountOfLamps) {
            createObject();
            currentAmountofLamps++;

        }
        startAlarm();


    }










}
