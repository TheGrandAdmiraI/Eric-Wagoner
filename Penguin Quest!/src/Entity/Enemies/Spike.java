package Entity.Enemies;
import Entity.Animation;

import Entity.Enemy;
import TileMap.TileMap;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;

import java.awt.image.BufferedImage;

public class Spike extends Enemy{

private BufferedImage[] sprites;

    public Spike(TileMap tm){
        super(tm);

        moveSpeed = 0;
        maxSpeed = 0;
        fallSpeed = 0.2;
        maxFallSpeed = 10.0;

        width = 30; //width of enemy sprite
        height = 30; //height of enemy sprite
        cwidth = 30; //collision box size
        cheight = 15; //collision box size

        health = maxHealth = Integer.MAX_VALUE;
        damage = 1;
        //load sprites
        try {

            BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/Sprites/Enemies/Spike.png"));

            sprites = new BufferedImage[1]; //3 is the number of frames in the animation
            for(int i = 0; i < sprites.length; i++){
                sprites[i] = spritesheet.getSubimage(i * width, 0, width, height);
            }

        }catch(Exception e) {
            e.printStackTrace();
        }

        animation = new Animation();
        animation.setFrames(sprites);
        animation.setDelay(200); //change this delay to change speed of animation: smaller number = quicker

        right = true; //starts by moving to the right
        facingRight = true;
        
    }

    private void getNextPosition(){
        //copied movement from player
        // movement
        //falling
        if(falling){
            //System.out.println("falling seal");
            dy += fallSpeed; 
            
        }

    }

    public void update(){
        //update position
        getNextPosition();
        checkTileMapCollision();
        setPosition(xtemp, ytemp);

        //check flinching
        checkFlinching();

        //if it hits a wall, change direction
        //checkWall();

        //if it's about to fall off a cliff, turn around
        checkCliff();

        //update animation
        animation.update();
    }

    public void draw(Graphics2D g) {
        setMapPosition(); //if this is after the if statement, then the enemy won't load if it goes offscreen.
        /*if(notOnScreen()){
            return;
        }*/
        super.draw(g);
    }
    
}