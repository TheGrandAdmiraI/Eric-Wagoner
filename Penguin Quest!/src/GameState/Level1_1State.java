package GameState;

import Audio.AudioPlayer;
import Main.GamePanel;
import TileMap.*;
import Entity.*;
import Entity.Enemies.Seal;
import Handlers.Keys;

import java.awt.*;
import java.util.ArrayList;

public class Level1_1State extends GameState {

    private TileMap tileMap;
    private Background bg;

    private Player player;

    private ArrayList<Enemy> enemies;

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

        bg = new Background("/Backgrounds/tutorialbg.gif", 0.1);

        player = new Player(tileMap);
        player.setPosition(75, 380);

        enemies = new ArrayList<Enemy>();
        Seal s1 = new Seal(tileMap);
        s1.setPosition(585, 380);
        enemies.add(s1);
        Seal s2 = new Seal(tileMap);
        s2.setPosition(650, 380);
        enemies.add(s2);
        Seal s3 = new Seal(tileMap);
        s3.setPosition(915, 410);
        enemies.add(s3);
        Seal s4 = new Seal(tileMap);
        s4.setPosition(1100, 410);
        enemies.add(s4);
        Seal s5 = new Seal(tileMap);
        s5.setPosition(1285, 410);
        enemies.add(s5);
        Seal s6 = new Seal(tileMap);
        s6.setPosition(2355, 230);
        enemies.add(s6);
        Seal s7 = new Seal(tileMap);
        s7.setPosition(2015, 380);
        enemies.add(s7);
        Seal s8 = new Seal(tileMap);
        s8.setPosition(2250, 380);
        enemies.add(s8);
        Seal s9 = new Seal(tileMap);
        s9.setPosition(2485, 380);
        enemies.add(s9);
        Seal s10 = new Seal(tileMap);
        s10.setPosition(2720, 380);
        enemies.add(s10);

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

        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).update();
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
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).draw(g);
        }

    }

    public void handleInput() {

        player.setUp(Keys.keyState[Keys.UP]);
        player.setLeft(Keys.keyState[Keys.LEFT]);
        player.setDown(Keys.keyState[Keys.DOWN]);
        player.setRight(Keys.keyState[Keys.RIGHT]);
        player.setJumping(Keys.keyState[Keys.SPACE]);
        player.setGliding(Keys.keyState[Keys.BUTTON2]);
        if (Keys.isPressed(Keys.BUTTON5)) {
            player.printPosition();
        }
        if (Keys.isPressed(Keys.BUTTON3)) {
            player.setScratching();
        }
        if (Keys.isPressed(Keys.BUTTON4)) {
            player.setFiring();
        }
    }
}
