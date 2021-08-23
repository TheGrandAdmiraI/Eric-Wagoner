//This is a big enemy that moves around slowly
//maybe have him pause for a moment and stand?
package Entity.Enemies;
import Entity.Animation;

import Entity.Enemy;
import TileMap.TileMap;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;

import java.awt.image.BufferedImage;


public class PolarBear extends Enemy{
    protected long standTimer;
    private BufferedImage[] walkSprites;
    private BufferedImage[] standSprites;
    private boolean standing;
    private int wad; //walking animation delay
    private int sad; //standing animation delay
    public PolarBear(TileMap tm){
        super(tm);

        
        moveSpeed = .3;
        maxSpeed = .3;
        fallSpeed = 0.4;
        maxFallSpeed = 10.0;
        //jumpStart = -4.8;

        width = 60; //width of enemy sprite
        height = 60; //height of enemy sprite
        cwidth = 60; //collision box size
        cheight = 30; //collision box size

        health = maxHealth = 50;
        damage = 1;

        //start the standtimer on init
        standTimer = System.nanoTime();

        //load sprites
        try {

            BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/Sprites/Enemies/notapolarbear.png")); //currently just a rectangle :( simply for demo purposes

            walkSprites = new BufferedImage[2]; //2 is the number of frames in the animation
            for(int i = 0; i < walkSprites.length; i++){
                walkSprites[i] = spritesheet.getSubimage(i * width, 0, width, height);
            }
            standSprites = new BufferedImage[2];
            for(int i = 0; i<standSprites.length; i++){
                standSprites[i] = spritesheet.getSubimage(i*width, height, width, height);
            }

        }catch(Exception e) {
            e.printStackTrace();
        }

        wad = 500; //setting the walking and standing animation delays
        sad = 50;

        animation = new Animation();
        animation.setFrames(walkSprites);
        animation.setDelay(wad); //change this delay to change speed of animation: smaller number = quicker

        right = true; //starts by moving to the right
        facingRight = true;
        standing = false; //by default my man is walking
    }

    private void getNextPosition(){
        //copied movement from player
        // movement
        //only move if walking, not standing
        if (left && !standing) {
            dx -= moveSpeed;
            if (dx < -maxSpeed) {
                dx = -maxSpeed;
            }
        } else if (right && !standing) {
            dx += moveSpeed;
            if (dx > maxSpeed) {
                dx = maxSpeed;
            }
        } 
        //falling
        if(falling){
            //System.out.println("falling seal");
            dy += fallSpeed; 
            //System.out.println("jumping seal falling");
        }

    }

    public void setStanding(){
        if(standing){
            return;
        }
        standing = true;
        animation.setFrames(standSprites);
        animation.setDelay(sad);

        cwidth = 30; //collision box size
        cheight = 60; //collision box size

        dx = 0; //we don't want the polar bear walking while standing
    }

    public void setWalking(){
        if(!standing){
            return;
        }
        standing = false;
        animation.setFrames(walkSprites);
        animation.setDelay(wad);

        cwidth = 60; //collision box size
        cheight = 30; //collision box size
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
        if(right && dx == 0 && !standing) {//if it hits a wall, dx is set to 0 unless standing
            right = false;
            left = true;
            facingRight = false;
        }else if(left && dx == 0 && !standing){
            left = false;
            right = true;
            facingRight = true;
        }

        
        //if it's about to fall off a cliff, turn around
        if(right && !bottomRight && !falling){ //bottomRight checks if the bottom right of the collision box is over air or not
            right = false;
            left = true;
            facingRight = false;
        } else if(left && !bottomLeft && !falling){ //bottomLeft checks if the bottom left of the collision box is over air or not
            left = false;
            right = true;
            facingRight = true;
        }
        

        //stand at random intervals
        //higher standTimer, greater chance of jumping
        long elapsed = (System.nanoTime() - standTimer) / 1000000;
        //System.out.println("standTimer (ms): " + elapsed);
        if(!standing && elapsed > 5000){
            setStanding();
            standTimer = System.nanoTime();
        } else if(standing && elapsed > 1000){
            setWalking();
            standTimer = System.nanoTime();
        }


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
