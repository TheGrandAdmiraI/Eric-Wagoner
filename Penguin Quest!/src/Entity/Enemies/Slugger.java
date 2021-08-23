package Entity.Enemies;
import Entity.Animation;

import Entity.Enemy;
import TileMap.TileMap;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;

import java.awt.image.BufferedImage;

public class Slugger extends Enemy{

private BufferedImage[] sprites;

    public Slugger(TileMap tm){
        super(tm);

        moveSpeed = 0.3;
        maxSpeed = 0.3;
        fallSpeed = 0.2;
        maxFallSpeed = 10.0;

        width = 30; //width of enemy sprite
        height = 30; //height of enemy sprite
        cwidth = 20; //collision box size
        cheight = 20; //collision box size

        health = maxHealth = 2;
        damage = 1;
        //load sprites
        try {

            BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/Sprites/Enemies/slugger.gif"));

            sprites = new BufferedImage[3]; //3 is the number of frames in the animation
            for(int i = 0; i < sprites.length; i++){
                sprites[i] = spritesheet.getSubimage(i * width, 0, width, height);
            }

        }catch(Exception e) {
            e.printStackTrace();
        }

        animation = new Animation();
        animation.setFrames(sprites);
        animation.setDelay(300); //change this delay to change speed of animation

        right = true; //starts by moving to the right
        facingRight = true;
        
    }

    private void getNextPosition(){
        //copied movement from player
        // movement
        if (left) {
            dx -= moveSpeed;
            if (dx < -maxSpeed) {
                dx = -maxSpeed;
            }
        } else if (right) {
            dx += moveSpeed;
            if (dx > maxSpeed) {
                dx = maxSpeed;
            }
        } 
        //falling
        if(falling){
            dy += fallSpeed;
        }

    }

    public void update(){
        //update position
        getNextPosition();
        checkTileMapCollision();
        setPosition(xtemp, ytemp);

        //check flinching
        if(flinching) {
            long elapsed = (System.nanoTime() - flinchTimer) / 1000000;
            if(elapsed > 400) {
                flinching = false;
            }
        }

        //if it hits a wall, change direction
        if(right && dx == 0) {//if it hits a wall, dx is set to 0
            right = false;
            left = true;
            facingRight = false;
        }else if(left && dx == 0){
            left = false;
            right = true;
            facingRight = true;
        }

        //update animation
        animation.update();
    }

    public void draw(Graphics2D g) {
        if(notOnScreen()){
            return;
        }
        setMapPosition();
        super.draw(g);
    }
    
}
