package GameState;

import Audio.AudioPlayer;
import Main.GamePanel;
import TileMap.*;
import Entity.*;
import Handlers.Keys;
import Endless.MapWriter;
import Endless.BetterMapWriter;
import Endless.EvenBetterMapWriter;

import java.awt.*;
import java.io.IOException;


public class EndlessState extends GameState {

    private TileMap tileMap;
    private Background bg;

    private Player player;
    
    

    public EndlessState(GameStateManager gsm) throws IOException {
        this.gsm = gsm;
        EvenBetterMapWriter x = new EvenBetterMapWriter();
        init();
    }

    public void init() {
        tileMap = new TileMap(30);
        tileMap.loadTiles("/Tilesets/tutorialtileset.gif");
        tileMap.loadMap("/Maps/endless.map");
        tileMap.setPosition(0, 0);

        bg = new Background("/Backgrounds/tutorialbg.gif", 0.1);

        player = new Player(tileMap);
        player.setPosition(75, 200);
        
        
//      AudioPlayer.load("/Music/level1-1.mp3", "tutorial");
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


    }

    public void draw(Graphics2D g) {

        // draw bg
        bg.draw(g);

        // draw tilemap
        tileMap.draw(g);

        // draw player
        player.draw(g);

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
	}
}
