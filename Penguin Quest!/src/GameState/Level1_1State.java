package GameState;

import Main.GamePanel;
import TileMap.*;
import Entity.*;
import Entity.Enemies.*;

import SaveState.Lives;

import Handlers.Keys;

import java.awt.*;
import java.util.ArrayList;

public class Level1_1State extends GameState {

    private TileMap tileMap;
    private Background bg;

    private Player player;
	private Lives playerLives;
    
    private ArrayList<Enemy> enemies;
    private ArrayList<Explosion> explosions;
    
    private HUD hud;
            
    private final int pixels = 30; //this value is for how many pixels across/tall the tiles are in the tileset

    public Level1_1State(GameStateManager gsm) {
        this.gsm = gsm;
        init();
    }

    public void init() {
        tileMap = new TileMap(pixels);
        tileMap.loadTiles("/Tilesets/tutorialtileset.gif");
        tileMap.loadMap("/Maps/level1-1.map");
        tileMap.setPosition(0, 0);
        tileMap.setTween(1);

        bg = new Background("/Backgrounds/tutorialbg.gif", 0.1);

        player = new Player(tileMap);
        player.setPosition(100, 100);
		playerLives = new Lives(3); //player gets 3 lives
        
        populateEnemies();
		explosions = new ArrayList<Explosion>();
		hud = new HUD(player, playerLives);
    }
    
    private void populateEnemies() {
		
		enemies = new ArrayList<Enemy>();
		
		Slugger s;
		Point[] points = new Point[] {
			new Point(200, 100),
			//new Point(860, 200),
			//new Point(1525, 200),
			//new Point(1680, 200),
			//new Point(1800, 200)
		};
		for(int i = 0; i < points.length; i++) {
			s = new Slugger(tileMap);
			s.setPosition(points[i].x, points[i].y);
			enemies.add(s);
		}
		
	}

	public void setLives(int l){
        playerLives.setLives(l);
    }

    public void update() {

        handleInput();

        // update player
        player.update();
        tileMap.setPosition(
                GamePanel.WIDTH / 2 - player.getx(),
                GamePanel.HEIGHT / 2 - player.gety()
        );
		//if the player is dead, we want to decrease the number of lives, and if lives = 0, then death screen
        if(player.isDead()){
            playerLives.decrement();
            System.out.println("You died" + "\nLives left: " + playerLives.getLives());
            if(playerLives.getLives() <= 0){
                gsm.setState(gsm.DEATHSCREEN);//this sets the state to the death screen
            } else{
                gsm.setState(gsm.getState(),playerLives.getLives());//restarts the level when you die if you have lieves left
            }
        }
        
        // set background

		bg.setPosition(tileMap.getx(), tileMap.gety());
			
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
