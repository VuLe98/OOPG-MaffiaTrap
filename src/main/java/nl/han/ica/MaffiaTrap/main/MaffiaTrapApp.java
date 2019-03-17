package nl.han.ica.MaffiaTrap.main;

import nl.han.ica.MaffiaTrap.enemies.Bully;
import nl.han.ica.MaffiaTrap.gameStates.GameOver;
import nl.han.ica.MaffiaTrap.standardGameObjects.Door;
import nl.han.ica.MaffiaTrap.standardGameObjects.Ground;
import nl.han.ica.MaffiaTrap.gameStates.MaffiaState;
import nl.han.ica.MaffiaTrap.enemies.LampSpawner;
import nl.han.ica.MaffiaTrap.entities.Chest;
import nl.han.ica.MaffiaTrap.entities.IPlayer;
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

public class MaffiaTrapApp extends GameEngine implements IPlayer {

    int worldWidth = 1200;
    int worldHeight = 900;
    private int groundBorderY = 370;
    public MaffiaState state;
    private TextObject dashboardText;
    private IPersistence persistence;
    private IPlayer player;
    private int playerStartLives = 1;
    private int currentLives;


    public static void main(String[] args) {

        PApplet.main(new String[]{"nl.han.ica.MaffiaTrap.main.MaffiaTrapApp"});
    }

    /**
     * Creeërt de view zonder viewport
     * Parameters:
     * - worldWidth (breedte van het wereld)
     * - worldHeight (hoogte van het wereld)
     */

    @Override
    public void setupGame() {

        createDashboard(worldWidth, 100);
        initializePersistence();

        createViewWithoutViewport(worldWidth,worldHeight);
        createPlayer();
        createBully();

        createLampSpawner();
        createChest();

        state = MaffiaState.START_OF_GAME;
    }

    /**
     * Creeërt de view zonder viewport
     * @param screenWidth Breedte van het scherm
     * @param screenHeight Hoogte van het scherm
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
            createGameObjects();
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
     * Maakt 'stilstaande' (zonder timer) gameobjecten aan
     */


    private void createGameObjects() {
        Ground ground = new Ground(this);
        makeTileMap(ground);

        Door door = new Door(this,worldWidth, groundBorderY);
        this.addGameObject(door);

    }

    /**
     * Maakt het dashboard aan
     * @param dashboardWidth Gewenste breedte van dashboard
     * @param dashboardHeight Gewenste hoogte van dashboard
     */
    private void createDashboard(int dashboardWidth,int dashboardHeight) {
        Dashboard dashboard = new Dashboard(0,0, dashboardWidth, dashboardHeight);
        dashboardText=new TextObject("");
        dashboard.addGameObject(dashboardText);
        addDashboard(dashboard);
    }


    private void initializePersistence() {
        persistence = new FilePersistence("main/java/nl/han/ica/MaffiaTrap/media/amountOfLives.txt");
        if (persistence.fileExists()) {
            currentLives = playerStartLives;
            refreshDashboardText();
        }
    }

    /**
     * Vernieuwt het dashboard
     */
    public void refreshDashboardText() {
        dashboardText.setText("Amount of lives: "+ currentLives);
    }

    /**
     * Maakt de player aan
     */

    private void createPlayer() {
        Player slachtoffer = new Player(this, this);
        this.addGameObject(slachtoffer, 0, 450.0F);
    }

    private void createChest(){
        Random r = new Random();
        Chest chest = new Chest(this,player, r.nextInt(worldWidth) - 100,groundBorderY + 100);
        this.addGameObject(chest);
    }

    private void createLampSpawner(){
        LampSpawner lampSpawner = new LampSpawner(this,0.5);}


    private void createBully(){
        Random random = new Random();
        Bully bully = new Bully(this,worldWidth - 100, groundBorderY + 100);
        addGameObject(bully);
    }


    /**
     * Initialiseert de tilemap
     */

    private void makeTileMap(Ground ground) {
        tileMap = new TileMap(ground.tileSize, ground.tileTypes, ground.tilesMap);
    }

    @Override
    public void addExtraLife(){
        currentLives++;
        persistence.saveData(Integer.toString(currentLives));
        refreshDashboardText();
    }


    @Override
    public void countOffExtraLife(){
        currentLives--;
        persistence.saveData(Integer.toString(currentLives));
        refreshDashboardText();
    }

    public int getCurrentLives(){
        return currentLives;
    }


}
