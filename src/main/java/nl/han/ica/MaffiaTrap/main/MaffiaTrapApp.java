package nl.han.ica.MaffiaTrap.main;

import nl.han.ica.MaffiaTrap.shootEffects.BulletSpawner;
import nl.han.ica.MaffiaTrap.shootEffects.Fireball;
import nl.han.ica.MaffiaTrap.traps.Bully;
import nl.han.ica.MaffiaTrap.gameStates.GameOver;
import nl.han.ica.MaffiaTrap.standardGameObjects.Door;
import nl.han.ica.MaffiaTrap.standardGameObjects.Ground;
import nl.han.ica.MaffiaTrap.gameStates.MaffiaState;
import nl.han.ica.MaffiaTrap.traps.LampSpawner;
import nl.han.ica.MaffiaTrap.entities.Chest;
import nl.han.ica.MaffiaTrap.entities.interfaces.IPlayer;
import nl.han.ica.MaffiaTrap.entities.Player;
import nl.han.ica.MaffiaTrap.gameStates.Winner;
import nl.han.ica.OOPDProcessingEngineHAN.Dashboard.Dashboard;
import nl.han.ica.OOPDProcessingEngineHAN.Engine.GameEngine;
import nl.han.ica.OOPDProcessingEngineHAN.Persistence.FilePersistence;
import nl.han.ica.OOPDProcessingEngineHAN.Persistence.IPersistence;
import nl.han.ica.OOPDProcessingEngineHAN.Tile.TileMap;
import nl.han.ica.OOPDProcessingEngineHAN.View.View;
import nl.han.ica.waterworld.TextObject;
import processing.core.PApplet;

import java.util.Random;

/**
 * @author Vu Le
 */
@SuppressWarnings("serial")
public class MaffiaTrapApp extends GameEngine implements IPlayer {

    public MaffiaState state;
    private TextObject dashboardText;
    private IPersistence persistence;
    private IPlayer player;
    private BulletSpawner spawner;

    private int worldWidth = 1200;

    private int groundBorderY = 370;
    private int currentLives;

    private Random r = new Random();
    private int xChest = r.nextInt(worldWidth) - 100;

    public static void main(String[] args) {

        PApplet.main(new String[]{"nl.han.ica.MaffiaTrap.main.MaffiaTrapApp"});
    }

    /**
     * Creeërt de view zonder viewport.
     * Parameters:
     * - worldWidth (breedte van het wereld).
     * - worldHeight (hoogte van het wereld).
     */

    @Override
    public void setupGame() {

        int worldHeight = 900;

        createDashboard(worldWidth, 100);
        createViewWithoutViewport(worldWidth,worldHeight);

        state = MaffiaState.START_OF_GAME;

        createActiveGameObjects();
    }

    /**
     * Creeërt de view zonder viewport.
     * @param screenWidth Breedte van het scherm.
     * @param screenHeight Hoogte van het scherm.
     */
    private void createViewWithoutViewport(int screenWidth, int screenHeight) {
        View view = new View(screenWidth,screenHeight);
        view.setBackground(loadImage("src/main/java/nl/han/ica/MaffiaTrap/media/MaffiaTrap_Background.jpg"));

        setView(view);
        size(screenWidth, screenHeight);
    }

    @Override
    public void update() {

        if(state == MaffiaState.START_OF_GAME) {
            createPassiveGameObjects();
        }
        else if (state == MaffiaState.GAMEWIN) {
            Winner doorWin = new Winner(this);
            this.addGameObject(doorWin, 0, 0);
        }
        else if(state == MaffiaState.GAMEOVER){
            GameOver loser = new GameOver(this);
            this.addGameObject(loser,0,0);
        }
    }


    /**
     * Maakt 'actieve' gameobjecten aan (objecten die bewegingen uitvoeren).
     */

    public void createActiveGameObjects(){
        initializePersistence();
        createPlayer();
        createBully();

        createLampSpawner();
        createChest();

    }

    /**
     * Maakt 'passieve' (stilstaande) gameobjecten aan.
     */


    private void createPassiveGameObjects() {
        Ground ground = new Ground(this);
        makeTileMap(ground);

        Door door = new Door(this,worldWidth, groundBorderY);
        this.addGameObject(door);

    }

    /**
     * Maakt het dashboard aan.
     * @param dashboardWidth Gewenste breedte van dashboard.
     * @param dashboardHeight Gewenste hoogte van dashboard.
     */
    private void createDashboard(int dashboardWidth,int dashboardHeight) {
        Dashboard dashboard = new Dashboard(0,0, dashboardWidth, dashboardHeight);
        dashboardText=new TextObject("");
        dashboard.addGameObject(dashboardText);
        addDashboard(dashboard);
    }

    /**
     * Initialiseert de opslag van de levensteller
     * en laadt indien mogelijk de eerder opgeslagen
     * waarde.
     */

    private void initializePersistence() {
        int playerStartLives = 1;
        persistence = new FilePersistence("main/java/nl/han/ica/MaffiaTrap/media/amountOfLives.txt");
        if (persistence.fileExists()) {
            currentLives = playerStartLives;
            refreshDashboardText();
        }
    }

    /**
     * Vernieuwt het dashboard.
     */
    public void refreshDashboardText() {
        dashboardText.setText("Amount of lives: "+ currentLives);
    }

    /**
     * Maakt de player aan.
     */

    private void createPlayer() {
        Player slachtoffer = new Player(this, this);
        int xPlayer = 0;
        float yPlayer = 450.0F;
        this.addGameObject(slachtoffer, xPlayer, yPlayer);
    }

    /**
     * Maakt de schatkist aan waar de speler power-ups uit kan krijgen.
     */

    private void createChest(){
        int yChest = groundBorderY + 100;
        Chest chest = new Chest(this, player, xChest, yChest);
        this.addGameObject(chest);
    }

    /**
     * Maakt de lampen die een speler kan doden.
     */

    private void createLampSpawner(){
        double lampsPerSec = 0.5;
        LampSpawner lampSpawner = new LampSpawner(this, lampsPerSec);
    }

    /**
     * Maakt de vijand van de speler aan.
     */

    private void createBully(){
        int xBully = worldWidth - 200;
        int yBully = groundBorderY + 100;
        Bully bully = new Bully(this, spawner, xBully, yBully);
        addGameObject(bully);
    }


    /**
     * Initialiseert de tilemap.
     */

    private void makeTileMap(Ground ground) {
        tileMap = new TileMap(ground.tileSize, ground.tileTypes, ground.tilesMap);
    }

    /**
     * Verhoogt de teller van het aantal
     * levens van de speler met 1.
     */

    @Override
    public void addExtraLife(){
        currentLives++;
        persistence.saveData(Integer.toString(currentLives));
        refreshDashboardText();
    }

    /**
     * Verlaagt de teller van het aantal
     * levens van de speler met 1.
     */

    @Override
    public void countOffExtraLife(){
        currentLives--;
        persistence.saveData(Integer.toString(currentLives));
        refreshDashboardText();
    }

    /**
     * Maakt vuurbal aan.
     */

    @Override
    public void makeFireball(){
        int xFireball = xChest + 50;
        int yFireball = 400;
        Fireball fireball = new Fireball(this,xFireball,yFireball);
        this.addGameObject(fireball);
    }

    /**
     * Verkrijg het aantal levens van de speler.
     */

    public int getCurrentLives(){
        return currentLives;
    }


}
