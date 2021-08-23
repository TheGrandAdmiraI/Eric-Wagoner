package Entity.Enemies;
import Entity.Animation;

import Entity.Enemy;
import TileMap.TileMap;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.util.Random;
import java.awt.image.BufferedImage;

public class BigBoss extends Enemy{
    protected long actionTimer;
    protected long randomTimer;
    protected long printTimer;
    
    private BufferedImage[] idleSprites;
    private BufferedImage[] walkSprites;
    private BufferedImage[] jumpSprites;
    private BufferedImage[] scratchSprites;
    private BufferedImage[] smileSprites;
    private boolean walking;
    private boolean injump;
    private boolean smiling;
    private boolean scratching;
    private boolean idle;
    private int wad; //walking animation delay
    private int scad; //scratching animation delay
    private int jad; //jumping animation delay
    private int smad; //smiling animation delay
    private int iad; //idle animation delay
    //variables to deal with the random chance of actions
    Random rand = new Random();
    private int r;
    public BigBoss(TileMap tm){
        super(tm);

        
        moveSpeed = .3;
        maxSpeed = .3;
        fallSpeed = 0.2;
        maxFallSpeed = 1.0;
        jumpStart = -4.8;

        width = 90; //width of enemy sprite
        height = 90; //height of enemy sprite
        //for some reason there are problems with a big collision box
        cwidth = 70; //collision box size
        cheight = 60; //collision box size

        health = maxHealth = 50;
        damage = 2;

        //start the standtimer on init
        actionTimer = System.nanoTime();
        randomTimer = System.nanoTime();

        //load sprites
        try {

            BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/Sprites/Enemies/BigBoss.png")); //currently just a rectangle :( simply for demo purposes

            idleSprites = new BufferedImage[4]; //4 is the number of frames in the animation
            for(int i = 0; i < idleSprites.length; i++){
                idleSprites[i] = spritesheet.getSubimage(i * width, 0, width, height);
            }
            walkSprites = new BufferedImage[4];
            for(int i = 0; i< walkSprites.length; i++){
                walkSprites[i] = spritesheet.getSubimage(i*width, height, width, height);
            }
            jumpSprites = new BufferedImage[4];
            for(int i = 0; i < jumpSprites.length; i++){
                jumpSprites[i] = spritesheet.getSubimage(i*width, height * 2, width, height);
            }
            smileSprites = new BufferedImage[4];
            for(int i = 0; i < smileSprites.length; i++){
                smileSprites[i] = spritesheet.getSubimage(i*width, height * 3, width, height);
            }
            scratchSprites = new BufferedImage[4];
            for(int i = 0; i < scratchSprites.length; i++){
                scratchSprites[i] = spritesheet.getSubimage(i*width, height * 6, width, height);
            }

        }catch(Exception e) {
            e.printStackTrace();
        }

        //animation delays
        wad = 200; 
        scad = 50;
        smad = 50;
        iad = 500;
        jad = 100;

        animation = new Animation();
        animation.setFrames(idleSprites);
        animation.setDelay(iad); //change this delay to change speed of animation: smaller number = quicker

        right = true; //starts by moving to the right
        facingRight = true;
        //by default he's just idle
        walking = true;
        jumping = false;
        smiling = false;
        scratching = false;
        idle = false;
        injump = false;
    }

    private void getNextPosition(){
        //copied movement from player
        // movement
        //only move if walking or jumping
        if (left && (walking || injump)) {
            dx -= moveSpeed;
            if (dx < -maxSpeed) {
                dx = -maxSpeed;
            }
        } else if (right && (walking || injump)) {
            dx += moveSpeed;
            if (dx > maxSpeed) {
                dx = maxSpeed;
            }
        } else { //stop moving if idle, smiling, scratching
            dx = 0;
        }
        //falling
        
        if(falling){
            //System.out.println("falling seal");
            dy += fallSpeed; 
            //System.out.println("jumping seal falling");
        }

        //jumping
        
        if (jumping && !falling) {
            dy = jumpStart;
            falling = true;
            jumping = false; //only using jumping for initial jump since we don't need dfferent fall speed for jumping up
        } 
        

    }

    public void actionReset(){
        walking = false;
        injump = false;
        idle = false;
        scratching = false;
        smiling = false;
    }

    public void setIdle(){
        if(idle){
            return;
        }
        actionReset();
        idle = true;
        actionTimer = System.nanoTime();
        setAnimation();
    }

    public void setWalking(){
        if(walking){
            return;
        }
        actionReset();
        walking = true;
        actionTimer = System.nanoTime();
        setAnimation();
    }

    public void setJumping(){
        if(injump){
            return;
        }
        actionReset();
        jumping = true;
        injump = true;
        actionTimer = System.nanoTime();
        setAnimation();
    }

    public void setSmiling(){
        if(smiling){
            return;
        }
        actionReset();
        smiling = true;
        actionTimer = System.nanoTime();
        setAnimation();
    }

    public void setScratching(){
        if(scratching){
            return;
        }
        actionReset();
        scratching = true;
        actionTimer = System.nanoTime();
        setAnimation();
    }

    public void setAnimation(){
        if(idle){
            animation.setFrames(idleSprites);
            animation.setDelay(iad);
        } else if(injump){ //really the jumping animation happens when falling
            animation.setFrames(jumpSprites);
            animation.setDelay(jad);
        } else if(walking){
            animation.setFrames(walkSprites);
            animation.setDelay(wad);
        } else if(scratching){
            animation.setFrames(scratchSprites);
            animation.setDelay(scad);
        } else if(smiling){
            animation.setFrames(smileSprites);
            animation.setDelay(smad);
        }
    }

    //define again since different if jumping is a thing
    
    public void checkCliff(){
        
        if(right && !bottomRight && !falling){ //bottomRight checks if the bottom right of the collision box is over air or not
            right = false;
            left = true;
            facingRight = false;
        } else if(left && !bottomLeft && !falling){ //bottomLeft checks if the bottom left of the collision box is over air or not
            left = false;
            right = true;
            facingRight = true;
        }
        
    }
    

    public void checkWall(){
        
        if(right && dx == 0 && (walking || injump)) {//if it hits a wall, dx is set to 0
            right = false;
            left = true;
            facingRight = false;
        }else if(left && dx == 0 && (walking || injump)){
            left = false;
            right = true;
            facingRight = true;
        }
        
    }
    
    

    public void checkAction(){

    

        //only choice from idle is walking
        long elapsed = (System.nanoTime() - actionTimer) / 1000000;
        long rt = (System.nanoTime() - randomTimer) /1000000;
        long pt = (System.nanoTime() - printTimer) / 1000000;
        //System.out.println(elapsed);

        //print out action every half second
        
        if(pt > 1000){
            /*
            System.out.println("Status of walking" + walking);
            System.out.println("Status of idle" + idle);
            System.out.println("Status of jumping" + jumping);
            System.out.println("Status of scratching" + scratching);
            System.out.println("Status of smiling" + smiling);
            */
            //System.out.println("status of falling " + falling);
            printTimer = System.nanoTime();
        }
        
        //we need to check to see if the jump has finished
        
        if(injump && !falling && elapsed > 100){
            setWalking();
            //System.out.println("Setting walking");
        }

        if(idle && elapsed > 3000){ //basically if idle for 3 seconds, walk
            setWalking(); //if idle for 3 seconds, walk
            //System.out.println("Setting walking");
            randomTimer = System.nanoTime();
        } else if(scratching && elapsed > 500){ //scratching lasts .5 seconds
            setWalking();
            //System.out.println("Setting walking");
            randomTimer = System.nanoTime();
        } else if(smiling && elapsed > 1000){
            setWalking();
            //System.out.println("Setting walking");
            randomTimer = System.nanoTime();
        } else if(walking && elapsed > 8000){//if its been 8 seconds or more, do something
            randomTimer = System.nanoTime();
            r = rand.nextInt(4); //0,1,2,3
            if(r == 0){
                setIdle();
                //System.out.println("Setting idle");
            } else if(r == 1){
                setJumping();
                //System.out.println("Setting jumping");
            } else if(r == 2){
                setSmiling();
                //System.out.println("Setting smilling");
            } else if(r == 3){
                setScratching();
                //System.out.println("Setting scratching");
            }
        } else if(walking && rt > 1000){ //attempt an action every second
            randomTimer = System.nanoTime();
            r = rand.nextInt(10);
            
            if(r == 0){
                setIdle();
                //System.out.println("Setting idle");
            } else if(r == 1){ //editing for jumping testing
                setJumping();
                //System.out.println("Setting jumping");
            } else if(r == 2){
                setSmiling();
                //System.out.println("Setting smilling");
            } else if(r == 3){
                setScratching();
                //System.out.println("Setting scratching");
            }
            
        }
        
    }

    public void update(){
        //update position
        getNextPosition();
        checkTileMapCollision();
        setPosition(xtemp, ytemp);

        //check flinching
        checkFlinching();

        checkWall(); //overriden in this class

        //if it's about to fall off a cliff, turn around
        checkCliff();

        //do random actions
        checkAction();

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
