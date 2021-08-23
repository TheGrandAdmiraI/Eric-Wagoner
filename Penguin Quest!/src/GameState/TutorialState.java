
package GameState;

import Audio.AudioPlayer;
import Main.GamePanel;
import TileMap.*;
import Entity.*;
import Entity.Enemies.*;
import Handlers.Keys;

import java.awt.*;
import java.util.ArrayList;

public class TutorialState extends GameState {

    private TileMap tileMap;
    private Background bg;

    private Player player;

    private ArrayList<Enemy> enemies;

    private HUD hud;

    public TutorialState(GameStateManager gsm) {
        this.gsm = gsm;
        init();
    }

    public void init() {
        tileMap = new TileMap(30);
        tileMap.loadTiles("/Tilesets/tutorialtileset.gif");
        tileMap.loadMap("/Maps/tutorial.map");
        tileMap.setPosition(0, 0);

        bg = new Background("/Backgrounds/tutorialbg.gif", 0.1);

        player = new Player(tileMap);
        player.setPosition(75, 200);
        tileMap.setTween(1);

        enemies = new ArrayList<Enemy>();
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

        PolarBear pb;
        pb = new PolarBear(tileMap);
        pb.setPosition(200, 150);
        enemies.add(pb);

        hud = new HUD(player);

      
      //        AudioPlayer.load("/Music/level1-1.mp3", "tutorial");
//	AudioPlayer.loop("tutorial", 600, AudioPlayer.getFrames("tutorial") - 2200);
        
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

        //scroll background if we want it:
        //bg.setPosition(tileMap.getx(), tileMap.gety());

        //attack enemies
        player.checkAttack(enemies);


        //update all enemies
        for(int i = 0; i < enemies.size(); i++) {
            enemies.get(i).update();
            if(enemies.get(i).isDead()){
                enemies.remove(i);
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

        //draw enemies
        for(int i = 0; i < enemies.size(); i++){
            enemies.get(i).draw(g);
        }

        //draw HUD
        hud.draw(g);

    }

    public void handleInput() {

		player.setUp(Keys.keyState[Keys.UP]);
		player.setLeft(Keys.keyState[Keys.LEFT]);
		player.setDown(Keys.keyState[Keys.DOWN]);
		player.setRight(Keys.keyState[Keys.RIGHT]);
		player.setJumping(Keys.keyState[Keys.SPACE]);
                player.setGliding(Keys.keyState[Keys.BUTTON2]);
                if(Keys.isPressed(Keys.BUTTON5)) player.printPosition();
                if(Keys.isPressed(Keys.BUTTON3)) player.setScratching();
		if(Keys.isPressed(Keys.BUTTON4)) player.setFiring();
        if(Keys.isPressed(Keys.POSITION)){
            System.out.println("X position: " + player.getx() + "\nY position: " + player.gety());
        }
	}

}
