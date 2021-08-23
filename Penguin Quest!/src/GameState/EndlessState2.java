package GameState;

//import Audio.AudioPlayer;
import Main.GamePanel;
import TileMap.*;
import Entity.*;
import Entity.Enemies.*;
import EntityMap.*;
import Entity.PowerUps.*;
import Handlers.Keys;
import Endless.*;
import SaveState.*;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

import java.io.File; //used for file map checking

public class EndlessState2 extends GameState {

    private TileMap tileMap;
    private Background bg;
    private EvenBetterMapWriter2 mapWriter;

    private Player player;
    private Lives playerLives;
    
    private HUD hud;
    private Banner banner;

    private int score = 0;
    private int levelNumber;

    private EntityMap entityMap;
    private ArrayList<Enemy> enemies;
    private ArrayList<Explosion> explosions;
    private ArrayList<Coin> coins;
    private ArrayList<PowerUp> powerUps;
    private ArrayList<Flag> flags;

    private boolean firstFileLoad = false;

    public EndlessState2(GameStateManager gsm, int level) throws IOException { //int level = level number
        this.gsm = gsm;
        levelNumber = level;
        //the inclusion of level creates a new level file to be read from by the game so it's actually different as opposed to the prev file
        
        if(levelNumber == 1){
            mapWriter = new EvenBetterMapWriter2("Resources/Maps/Infinite/endless" + (levelNumber), levelNumber);
        }

        //testing, load map one level ahead so the pause isn't needed
        mapWriter = new EvenBetterMapWriter2("Resources/Maps/Infinite/endless" + (levelNumber + 1), levelNumber + 1); //we include the .map in the class itself to make naming with the entitymapwriter easier
        //init(); //somehow commenting this out fixed the map loading correctly each time. Now each time it's random!!! 
        //and if you restart it's still the same level since it's defined in the constructor
    }

    public void init() {
        tileMap = new TileMap(30);
        tileMap.loadTiles("/Tilesets/tutorialtileset.gif");

        
        //if the file is being loaded for the first time, we need a pause so an error doesn't occur
        if(firstFileLoad){
            System.out.println("loading level file for first time, pause needed");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ee) {
                ee.printStackTrace();
            }
            tileMap.loadMap("/Maps/Infinite/endless" + levelNumber + ".map");
            firstFileLoad = false;
        } else {
            tileMap.loadMap("/Maps/Infinite/endless" + levelNumber + ".map");
        }

        tileMap.setPosition(0, 0);

        bg = new Background("/Backgrounds/tutorialbg.gif", 0.1);

        player = new Player(tileMap);
        player.setPosition(75, 200);

        playerLives = player.getLives();//retrieves the life object from player for easy manipulation
        banner = new Banner(tileMap, player);

        entityMap = new EntityMap("/Maps/Infinite/endless" + levelNumber + "entities.map");
        entityMap.loadEntities(tileMap, player);
        populateCoins();
        populatePowerUps();
        populateEnemies();
        populateFlags();

	    explosions = new ArrayList<Explosion>();
        hud = new HUD(player, playerLives);
        
//      AudioPlayer.load("/Music/level1-1.mp3", "tutorial");
//	AudioPlayer.loop("tutorial", 600, AudioPlayer.getFrames("tutorial") - 2200);
    }

    private void populateEnemies() {
		enemies = new ArrayList<Enemy>();
        enemies = entityMap.returnEnemies();
		//enemies = new ArrayList<Enemy>();
		/*
		Slugger s;
		Point[] points = new Point[] { //it uses an array 
			new Point(200, 200),
			//new Point(650, 110),
			//new Point(512, 140),
			//new Point(200, 100),
			//new Point(200, 150)
		};
		for(int i = 0; i < points.length; i++) { //it will fill at the positions provided above
			s = new Slugger(tileMap);
			s.setPosition(points[i].x, points[i].y);
			enemies.add(s);
		}
        */

        /*
        Seal s;
        s = new Seal(tileMap);
        s.setPosition(650, 110);
        enemies.add(s);

        JumpingSeal js;
        js = new JumpingSeal(tileMap);
        js.setPosition(512, 140);
        enemies.add(js);

        Seagull sg;
        sg = new Seagull(tileMap);
        sg.setPosition(200, 100);
        enemies.add(sg);
        

        //currently spawning in to test lives
        PolarBear pb;
        pb = new PolarBear(tileMap);
        pb.setPosition(200, 50);
        enemies.add(pb);
        */
		
	}

    private void populateCoins() {
        coins = new ArrayList<Coin>();
        coins = entityMap.returnCoins();
    }

    private void populatePowerUps(){
        powerUps = new ArrayList<PowerUp>();
        powerUps = entityMap.returnPowerUps();
    }

    private void populateFlags(){
        flags = new ArrayList<Flag>();
        flags = entityMap.returnFlags();
    }

    public void setLives(int l){
        playerLives.setLives(l);
    }

    public void setScore(int s){
        score = s;
        hud.setScore(score);
    }

    public void update() {

        //update keys
        handleInput();

        //we need to update the banner before the player :)
        banner.update();

        // update player
        player.update();
        tileMap.setPosition(
                GamePanel.WIDTH / 2 - player.getx(),
                GamePanel.HEIGHT / 2 - player.gety()
        );
        if(player.isDead()){
            playerLives.decrement();
            //System.out.println("You died" + "\nLives left: " + playerLives.getLives());
            
            if(playerLives.getLives() <= 0){
                score = 0;
                hud.setScore(score);
                gsm.setState(gsm.DEATHSCREEN);//this sets the state to the death screen
            } else{
                gsm.setState(gsm.getState(),playerLives.getLives(), score);//restarts the level when you die if you have lieves left
                banner.createBanner("You died. Lives left: " + playerLives.getLives());         
            }
        }

        //update powerups
        //hb.update();
        for (int i = 0; i < powerUps.size(); i++){
            powerUps.get(i).update();
        }

        //scroll background if we want it:
	    //bg.setPosition(tileMap.getx(), tileMap.gety());
		
	    // attack enemies
	    player.checkAttack(enemies);
		
        // update all enemies
        for(int i = 0; i < enemies.size(); i++) {
            Enemy e = enemies.get(i);
            e.update();
            if(e.isDead()) { //if the enemy is dead, it should be removed
                enemies.remove(i);
                i--;
                explosions.add( // if an enemy has died, we add a new explosion after it has been removed
                                new Explosion(e.getx(), e.gety()));
            }
        }
		
        // update explosions
        for(int i = 0; i < explosions.size(); i++) {
            explosions.get(i).update();
            if(explosions.get(i).shouldRemove()) { //check if the explosions should be removed
                explosions.remove(i);
                i--;
            }
        }

        //check for player intersection with flag and update the flag
        for(int i = 0; i < flags.size(); i++){
            Flag f = flags.get(i);
            f.update();
            if(player.intersects(f)){
                gsm.setState(gsm.VICTORYSCREEN,playerLives.getLives(), score); //this sets the state to the winning screen
            }
        }

        //check for player intersections with the coin
        for (int i = 0; i < coins.size(); i++) {
            coins.get(i).update();
            Coin c = coins.get(i);
            if(player.intersects(c)){
                score++;
                hud.setScore(score);
                coins.remove(i);
                i--;
                //System.out.println("The number of coins collected is: " + score);
                banner.createBanner("Coin Collected");
            }
        }

    }

    public void draw(Graphics2D g) {

        // draw bg
        bg.draw(g);

        // draw tilemap
        tileMap.draw(g);

        //draw powerups
        for(int i = 0; i < powerUps.size(); i++){
            powerUps.get(i).draw(g);
        }

        //draw banner before player
        banner.draw(g);

        // draw player
        player.draw(g);

        // draw enemies
        for(int i = 0; i < enemies.size(); i++) {
            enemies.get(i).draw(g);
        }
            
        // draw explosions
        for(int i = 0; i < explosions.size(); i++) {
            explosions.get(i).setMapPosition(
                (int)tileMap.getx(), (int)tileMap.gety());
            explosions.get(i).draw(g);
        }

        //draw coin
	    for (int i = 0; i < coins.size(); i++) {
            coins.get(i).draw(g);
        }
		
        //draw flag
        for(int i = 0; i < flags.size(); i++){
            flags.get(i).draw(g);
        }

        // draw hud
        hud.draw(g);

    }

	public void handleInput() {

		player.setUp(Keys.keyState[Keys.UP]);
		player.setLeft(Keys.keyState[Keys.LEFT]);
		player.setDown(Keys.keyState[Keys.DOWN]);
		player.setRight(Keys.keyState[Keys.RIGHT]);
		player.setJumping(Keys.keyState[Keys.SPACE]);
        player.setGliding(Keys.keyState[Keys.BUTTON2]);
        if (Keys.isPressed(Keys.BUTTON3)) {
            player.setScratching();
        }
        if (Keys.isPressed(Keys.BUTTON4)) {
            player.setFiring();
        }
        if (Keys.isPressed(Keys.POSITION)) {
            player.printPosition();
        }

        if(Keys.isPressed(Keys.SPECIALBUTTON)) {
        	player.usePowerUp();
        }
	}
}