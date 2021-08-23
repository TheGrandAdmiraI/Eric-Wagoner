package GameState;

//import Audio.AudioPlayer;
import Main.GamePanel;
import TileMap.*;
import Entity.*;
import Entity.Enemies.*;
import Handlers.Keys;
import Endless.MapWriter;
import Endless.BetterMapWriter;
import Endless.EvenBetterMapWriter;
import SaveState.*;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;


public class EndlessState extends GameState {

    private TileMap tileMap;
    private Background bg;
    private EvenBetterMapWriter mapWriter;

    private Player player;
    private Lives playerLives;
    
    private Flag flag;
    
    private HUD hud;

    private ArrayList<Enemy> enemies;
    private ArrayList<Explosion> explosions;

    public EndlessState(GameStateManager gsm) throws IOException {
        this.gsm = gsm;
        mapWriter = new EvenBetterMapWriter("Resources/Maps/endless.map");
        //init(); //somehow commenting this out fixed the map loading correctly each time. Now each time it's random!!! 
        //and if you restart it's still the same level since it's defined in the constructor
    }

    public void init() {
        tileMap = new TileMap(30);
        tileMap.loadTiles("/Tilesets/tutorialtileset.gif");
        tileMap.loadMap("/Maps/endless.map");
        tileMap.setPosition(0, 0);

        bg = new Background("/Backgrounds/tutorialbg.gif", 0.1);

        player = new Player(tileMap);
        player.setPosition(75, 200);

        playerLives = new Lives(3); //sets the number of lives to 3

        flag = new Flag(tileMap);
        flag.setPosition(1365, 140);

        populateEnemies();
	      explosions = new ArrayList<Explosion>();
        hud = new HUD(player, playerLives);
        
//      AudioPlayer.load("/Music/level1-1.mp3", "tutorial");
//	AudioPlayer.loop("tutorial", 600, AudioPlayer.getFrames("tutorial") - 2200);
    }

    private void populateEnemies() {
		
		enemies = new ArrayList<Enemy>();
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
        */

        //currently spawning in to test lives
        PolarBear pb;
        pb = new PolarBear(tileMap);
        pb.setPosition(200, 50);
        enemies.add(pb);
        
		
	}

    public void setLives(int l){
        playerLives.setLives(l);
    }

    public void update() {

        //update keys
        handleInput();
        // update player
        player.update();
        tileMap.setPosition(
                GamePanel.WIDTH / 2 - player.getx(),
                GamePanel.HEIGHT / 2 - player.gety()
        );
        if(player.isDead()){
            playerLives.decrement();
            System.out.println("You died" + "\nLives left: " + playerLives.getLives());
            if(playerLives.getLives() <= 0){
                gsm.setState(gsm.DEATHSCREEN);//this sets the state to the death screen
            } else{
                gsm.setState(gsm.getState(),playerLives.getLives());//restarts the level when you die if you have lieves left
            }
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

        //update the flag, for the purpose of animation
        flag.update();

        //check for player intersection with flag
        if(player.intersects(flag)){
            gsm.setState(gsm.VICTORYSCREEN,playerLives.getLives()); //this sets the state to the winning screen
        }

    }

    public void draw(Graphics2D g) {

        // draw bg
        bg.draw(g);

        // draw tilemap
        tileMap.draw(g);

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
		
        //draw flag
        flag.draw(g);
        
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
                if(Keys.isPressed(Keys.BUTTON3)) player.setScratching();
		if(Keys.isPressed(Keys.BUTTON4)) player.setFiring();
        if(Keys.isPressed(Keys.POSITION)){
            player.printPosition();
        }
	}
}
