package nl.han.ica.MaffiaTrap.enemies;

import nl.han.ica.MaffiaTrap.main.MaffiaTrapApp;
import nl.han.ica.OOPDProcessingEngineHAN.Alarm.Alarm;
import nl.han.ica.OOPDProcessingEngineHAN.Alarm.IAlarmListener;

import java.util.Random;

public class LampSpawner implements IAlarmListener {

    private MaffiaTrapApp app;
    private int currentAmountofLamps; //Huidige hoeveelheid lamps op het scherm.
    private int maxAmountOfLamps; //Maximale hoeveelheid lamps in de game.
    private double lampsPerSecond; //Aantal lamps per seconde.

    public LampSpawner(MaffiaTrapApp app, double lampsPerSecond){
        this.app = app;
        this.currentAmountofLamps = 0;
        Random rand = new Random();
        this.maxAmountOfLamps = rand.nextInt(3) + 1;
        System.out.println(maxAmountOfLamps);
        this.lampsPerSecond = lampsPerSecond;
        startAlarm();
    }

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
     * Maakt vijanden aan zolang het aantal vijanden onder maxAmountOfEnemies zit.
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
