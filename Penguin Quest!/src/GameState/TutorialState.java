package GameState;

import Main.GamePanel;
import TileMap.*;
import Entity.*;
import Entity.Enemies.*;
import Entity.PowerUps.*;
import EntityMap.*;
import Handlers.Keys;
import SaveState.*;

import java.awt.*;
import java.util.ArrayList;

public class TutorialState extends GameState {

    // World Record is 11.90 seconds
    
    private TileMap tileMap;
    private Background bg;

    private Player player;
    private Lives playerLives;
    private Banner banner;

    private EntityMap entityMap;
    private ArrayList<Enemy> enemies;
    private ArrayList<Explosion> explosions;
    private ArrayList<Coin> coins;
    private ArrayList<PowerUp> powerUps;
    private ArrayList<Flag> flags;
    
    private HUD hud;
    
    private int score = 0;

    private final int pixels = 30; //this value is for how many pixels across/tall the tiles are in the tileset

    public TutorialState(GameStateManager gsm) {
        this.gsm = gsm;
        init();
    }

    public void init() {
        tileMap = new TileMap(30);
        tileMap.loadTiles("/Tilesets/tutorialtileset.gif");
        tileMap.loadMap("/Maps/tutorial.map");
        tileMap.setPosition(0, 0);
        //tileMap.setTween(1);

        bg = new Background("/Backgrounds/tutorialbg.gif", 0.1);//0.1 is the scroll speed if we have it enabled (check update function)

        player = new Player(tileMap);
        player.setPosition(75, 200);
        tileMap.setTween(1);

        playerLives = player.getLives(); //sets the number of lives to 3
        banner = new Banner(tileMap, player);
        
        /*
        flag = new Flag(tileMap);
        flag.setPosition(830, 110);
        */
        entityMap = new EntityMap("/Maps/tutorialEntities.map");
        entityMap.loadEntities(tileMap, player);
        populateCoins();
        populatePowerUps();
        populateEnemies();
        populateFlags();
        explosions = new ArrayList<Explosion>();
        hud = new HUD(player, playerLives);
    }


    public void setLives(int l){
        playerLives.setLives(l);
    }

    public void setScore(int s){
        score = s;
        hud.setScore(score);
    }
    
    private void populatePowerUps() {
      powerUps = new ArrayList<PowerUp>();
      powerUps = entityMap.returnPowerUps();
    	/*
    	HealthBoost hb;
    	 hb = new HealthBoost(tileMap, player);
         hb.setPosition(400, 170);
         powerups.add(hb);
         
         SpeedBoost sb;
         sb = new SpeedBoost(tileMap, player);
         sb.setPosition(510, 140);
         powerups.add(sb);
      
         DoubleJump dj;
         dj = new DoubleJump(tileMap, player);
         dj.setPosition(610, 200);
         powerups.add(dj);
         
         FireballPowerIncrease fp;
         fp = new FireballPowerIncrease(tileMap, player);
         fp.setPosition(250, 200);
         powerups.add(fp);
         */
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
        s.setPosition(1110, 140);
        enemies.add(s);

        Seal js;
        js = new Seal(tileMap);
        js.setPosition(700, 170);
        enemies.add(js);

        Seagull sg;
        sg = new Seagull(tileMap);
        sg.setPosition(1590, 40);
        enemies.add(sg);

        PolarBear pb;
        pb = new PolarBear(tileMap);
        pb.setPosition(1590, 40);
        enemies.add(pb);
        

        //spike pit
        for (int i = 0; i < 8; i++) {
            Spike r = new Spike(tileMap);
            r.setPosition(1005 + (30 * i), 200);
            enemies.add(r);
        }        
        //red spike pit
        for (int i = 0; i < 14; i++) {
            RedSpike r = new RedSpike(tileMap);
            r.setPosition(1395 + (30 * i), 170);
            enemies.add(r);
        }
        */
        
    }
    
    private void populateCoins() {

        coins = new ArrayList<Coin>();
        coins = entityMap.returnCoins();

        /*
        coins = new ArrayList<Coin>();
       
		Coin c;
		Point[] points = new Point[] { //it uses an array 
			new Point(200, 200),
			new Point(300, 110),
			new Point(400, 140),
		};
		for(int i = 0; i < points.length; i++) { //it will fill at the positions provided above
			c = new Coin(tileMap);
			c.setPosition(points[i].x, points[i].y);
			coins.add(c);
		}
        */
    }

    private void populateFlags(){
        flags = new ArrayList<Flag>();
        flags = entityMap.returnFlags();
    }

    public void update() {
        handleInput();

        //update banner first
        banner.update();

        // update player
        player.update();
        tileMap.setPosition(
                GamePanel.WIDTH / 2 - player.getx(),
                GamePanel.HEIGHT / 2 - player.gety()
        );

        //if the player is dead, we want to decrease the number of lives, and if lives = 0, then death screen
        if(player.isDead()){
            playerLives.decrement();
            //System.out.println("You died" + "\nLives left: " + playerLives.getLives());
            
            if(playerLives.getLives() <= 0){
                score = 0;
                hud.setScore(0);
                gsm.setState(gsm.DEATHSCREEN);//this sets the state to the death screen
            } else{
                gsm.setState(gsm.getState(),playerLives.getLives(), score);//restarts the level when you die if you have lieves left
                banner.createBanner("You died. Lives left: " + playerLives.getLives());
            }
        }
        
        //update powerups

        updatePowerUps();
       
        //scroll background if we want it:
        //bg.setPosition(tileMap.getx(), tileMap.gety());
        // attack enemies
        player.checkAttack(enemies);

        // update all enemies
        for (int i = 0; i < enemies.size(); i++) {
            Enemy e = enemies.get(i);
            e.update();
            if (e.isDead()) { //if the enemy is dead, it should be removed
                enemies.remove(i);
                i--;
                explosions.add( // if an enemy has died, we add a new explosion after it has been removed
                        new Explosion(e.getx(), e.gety()));
            }
        }

        // update explosions
        for (int i = 0; i < explosions.size(); i++) {
            explosions.get(i).update();
            if (explosions.get(i).shouldRemove()) { //check if the explosions should be removed
                explosions.remove(i);
                i--;
            }
        }


        //check for player intersection with flag and update the flag
        for(int i = 0; i < flags.size(); i++){
            Flag f = flags.get(i);
            f.update();
            if(player.intersects(f)){
                score = 0; //we don't keep the score from the tutorial state moving forward
                hud.setScore(score);
                gsm.setState(gsm.VICTORYSCREEN,playerLives.getLives()); //this sets the state to the winning screen
            }
        }
        
        //update the coin, for the purpose of animation
        //coin.update();
        
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

    public void updatePowerUps() {
    	 for (int i = 0; i < powerUps.size(); i++) {
    		 powerUps.get(i).update();
    	 }
    }
    
    public int getScore(){
        return score;
    }


    public void draw(Graphics2D g) {

        // draw bg
        bg.draw(g);

        // draw tilemap
        tileMap.draw(g);

        //draw banner
        banner.draw(g);
   
        //draw powerups
        drawPowerUps(g);
        
        // draw player
        player.draw(g);
        
        // draw enemies
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).draw(g);
        }

        // draw explosions
        for (int i = 0; i < explosions.size(); i++) {
            explosions.get(i).setMapPosition(
                    (int) tileMap.getx(), (int) tileMap.gety());
            explosions.get(i).draw(g);
        }

        //draw flag
        for(int i = 0; i < flags.size(); i++){
            flags.get(i).draw(g);
        }
        
        //draw coin
        //coin.draw(g);
	    for (int i = 0; i < coins.size(); i++) {
            coins.get(i).draw(g);
        }

        // draw hud
        hud.draw(g);

    }

    public void drawPowerUps(Graphics2D g) {
    	for (int i = 0; i < powerUps.size(); i++) {
    		powerUps.get(i).draw(g);
    	}
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
