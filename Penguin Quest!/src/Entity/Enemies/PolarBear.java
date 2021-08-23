//This is a big enemy that moves around slowly
//maybe have him pause for a moment and stand?
package Entity.Enemies;
import Entity.Animation;

import Entity.Enemy;
import TileMap.TileMap;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.util.Random;
import java.awt.image.BufferedImage;


public class PolarBear extends Enemy{
    protected long standTimer;
    protected long randomTimer;
    private BufferedImage[] walkSprites;
    private BufferedImage[] standSprites;
    private boolean standing;
    private int wad; //walking animation delay
    private int sad; //standing animation delay
    //variables to deal with the random chance of standing
    Random rand = new Random();
    private int r;
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

        health = maxHealth = 15;
        damage = 1;

        //start the standtimer on init
        standTimer = System.nanoTime();
        randomTimer = System.nanoTime();

        //load sprites
        try {

            BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/Sprites/Enemies/PolarBear.png")); //currently just a rectangle :( simply for demo purposes

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
        standTimer = System.nanoTime();
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
        standTimer = System.nanoTime();
    }

    //needs to define again since it also doesn't move while standing
    public void checkWall(){
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
    }

    

    public void checkStand(){
        //stand at random intervals
        //higher standTimer, greater chance of jumping
        long elapsed = (System.nanoTime() - standTimer) / 1000000;//outputs time since last set to stand or walk in ms
        long rt = (System.nanoTime() - randomTimer) / 1000000; //this timer is used for doing a random calculation at set intervals
        //System.out.println("random timer: " + rt);
        //System.out.println("standTimer (ms): " + elapsed);
        if(!standing && elapsed > 8000){ //if it's been more than 8 seconds, then stand
            setStanding();
        } else if(standing && elapsed > 1000){ //only stand for 1 second
            setWalking();
            //sometimes will stand for 2 seconds if we don't reset the random timer
            randomTimer = System.nanoTime();
        } else if(!standing && rt > 1000){//every 1s we want a 1/5 chance to jump
            randomTimer = System.nanoTime();
            //System.out.println("polar bear checking for random jump");
            r = rand.nextInt(100);//chooses a random from [0,99] =? 100 numbers total
            if(r <20){ //1/5 chance of occuring
                setStanding();
            }
        }
    }

    public void update(){

        checkWall(); //overriden in this class

        //if it's about to fall off a cliff, turn around
        checkCliff();

        //stand at random intervals
        checkStand();
        
        //update position
        getNextPosition();
        checkTileMapCollision();
        setPosition(xtemp, ytemp);

        //check flinching
        checkFlinching();

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
