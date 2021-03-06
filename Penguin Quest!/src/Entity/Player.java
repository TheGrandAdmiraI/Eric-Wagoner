package Entity;

//import Audio.AudioPlayer;
import TileMap.*;

import java.util.ArrayList;
import javax.imageio.ImageIO;

import Entity.PowerUps.PowerUp;
import SaveState.Lives;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends MapObject {

    // player stuff
    private int health;
    private int maxHealth;
    private int fire;
    private int maxFire;
    private boolean dead;
    private boolean flinching;
    private long flinchTimer;
    private Lives lives;
    
    private Inventory myInventory;

    // fireball
    private boolean firing;
    private int fireCost;
    private int fireBallDamage;
    private ArrayList<FireBall> fireBalls;

    // scratch
    private boolean scratching;
    private int scratchDamage;
    private int scratchRange;

    // gliding
    private boolean gliding;

    // animations
    private ArrayList<BufferedImage[]> sprites;

    private final int[] numFrames = {

       2, 4, 4, 2, 4, 2, 4
    };
  /*
  private final int[] numFrames = {
        2, 4, 1, 2, 4, 2, 4
    };
*/
    // animation actions
    private static final int IDLE = 0;
    private static final int WALKING = 1;
    private static final int JUMPING = 2;
    private static final int FALLING = 3;
    private static final int GLIDING = 4;
    private static final int FIREBALL = 5;
    private static final int SCRATCHING = 6;
  
    //movement variables
    private double maxGlideFallSpeed; 
    private double jumpingModifier; //changes how fast the player stops jumping when holding spacebar
    private double glidingModifier; //changes how much gliding diminishes the fallspeed

    //Timer & other variables for powerups
    //private int timer;
    private double formerValue;
    private static final int SPEEDCHANGE = 0;
    private static final int DOUBLEJUMP = 1;
    private static final int FIREBALLDAMAGE = 2;
    private int powerUpChange;
    private long powerupTimer;
    private boolean powerUpActive;

    public Player(TileMap tm) {

        super(tm);
        

        width = 30;
        height = 30;
        cwidth = 20;
        cheight = 20;

        myInventory = new Inventory();
        
       /*
        moveSpeed = 0.3;
        maxSpeed = 1.6;
        stopSpeed = 0.4;
        fallSpeed = 0.15;
        maxFallSpeed = 4.0;
        jumpStart = -4.8;
        stopJumpSpeed = 0.3;
*/
        moveSpeed = 0.2;
        maxSpeed = 2.5;
        stopSpeed = .6;
        fallSpeed = 0.15;
        maxFallSpeed = 4;
        maxGlideFallSpeed = 2;
        glidingModifier = .5; //this is a percent modifier
        jumpStart = -4.8;
        stopJumpSpeed = .4;
        jumpingModifier = .35; //this is a percent modifier

        facingRight = true;
        dead = false;

        health = maxHealth = 5;
        fire = maxFire = 2500;

        lives = new Lives(3); //sets the default number of lives, which is 3

        fireCost = 200;
        fireBallDamage = 5;
        fireBalls = new ArrayList<FireBall>();

        scratchDamage = 8;
        scratchRange = 40;

        //initialize powerup variables
        //timer = -100;
        formerValue  = -100;
        powerUpChange = -100;
        powerUpActive = false;
        
        // load sprites
        try {

            BufferedImage spritesheet = ImageIO.read(
                    getClass().getResourceAsStream(
                            "/Sprites/Player/penguinsprites1.gif"
                    )
            );

            sprites = new ArrayList<BufferedImage[]>();
            for (int i = 0; i < 7; i++) {

                BufferedImage[] bi
                        = new BufferedImage[numFrames[i]];

                for (int j = 0; j < numFrames[i]; j++) {

                    if (i != 6) {
                        bi[j] = spritesheet.getSubimage(
                                j * width,
                                i * height,
                                width,
                                height
                        );
                    } else {
                        bi[j] = spritesheet.getSubimage(
                                j * width,
                                i * height,
                                width,
                                height
                        );
                    }

                }

                sprites.add(bi);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        animation = new Animation();
        currentAction = IDLE;
        animation.setFrames(sprites.get(IDLE));
        animation.setDelay(400);
        
        //AudioPlayer.load("/SFX/jump.mp3", "playerjump");
    }

    public int getHealth() {
        return health;
    }

    public Lives getLives(){
        return lives;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public boolean isDead(){
        return dead;
    }

    public int getFire() {
        return fire;
    }

    public int getMaxFire() {
        return maxFire;
    }

    public void setFiring() {
        firing = true;
    }

    public void setScratching() {
        scratching = true;
    }

    public void setGliding(boolean b) {
        gliding = b;
    }

    public void checkAttack(ArrayList<Enemy> enemies) {	
        // loop through enemies
        for(int i = 0; i < enemies.size(); i++) { 	
                Enemy e = enemies.get(i);	
                // scratch attack
                if(scratching) {
                    if(facingRight) {
                        if(
                        // it checks to make sure that enemy is to our right since we are facing right	
                        e.getx() > x &&
                        e.getx() < x + scratchRange && 
                        e.gety() > y - height / 2 &&
                        e.gety() < y + height / 2
                        ) {
                            e.hit(scratchDamage);
                        }
                    } else {
                        if(
                            // the enemy is to our left	
                            e.getx() < x &&
                            e.getx() > x - scratchRange &&
                            e.gety() > y - height / 2 &&
                            e.gety() < y + height / 2
                        ) {
                            e.hit(scratchDamage);
                        }
                    }
                }		
                // fireballs
                for(int j = 0; j < fireBalls.size(); j++) { //loop through fireballs
                    if(fireBalls.get(j).intersects(e)) {
                        e.hit(fireBallDamage);
                        fireBalls.get(j).setHit();
                        break;
                    }
                }	
                // check enemy collision
                if(intersects(e)) {
                    hit(e.getDamage());
                }		
        }
    }

    public void hit(int damage) {
        if(flinching) return;
        health -= damage;
        if(health < 0 ){
            health = 0;
        }
        if(health == 0){
            dead = true;
        }
        flinching = true;
        flinchTimer = System.nanoTime();
    }



    private void getNextPosition() {
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
        } else {
            if (dx > 0) {
                dx -= stopSpeed;
                if (dx < 0) {
                    dx = 0;
                }
            } else if (dx < 0) {
                dx += stopSpeed;
                if (dx > 0) {
                    dx = 0;
                }
            }
        }

        // cannot move while attacking, except in air
        if ((currentAction == SCRATCHING || currentAction == FIREBALL) && !(jumping || falling)) {
            dx = 0;
        }

        // jumping
        if (jumping && !falling) {
            dy = jumpStart;
            falling = true;
        }

        /*
        
        longer you hold spacebar, the higher you jump
        press spacebar = short jump
        hold = long
        if jumping (holding spacebar) bigger jump, stop slower
        if going up, not jumping, stop quicker
        
        */ 

        // falling
        if (falling) {
            if (dy > 0) { //if moving down, not jumping
                jumping = false;
            }
            if (dy < 0 && jumping) { //still holding spacebar because still jumping
                dy += stopJumpSpeed *jumpingModifier; //slower stop when still holding space
            } else if(dy < 0 && !jumping){//no longer holding spacebar because not jumping
                dy += stopJumpSpeed; //stop quicker for shorting spacebar
            } 
            else if (dy > 0 && gliding) {
                dy += fallSpeed * glidingModifier;
            } else {
                dy += fallSpeed;
            }

            if(dy > maxGlideFallSpeed && gliding){
                dy = maxGlideFallSpeed;
            } else if (dy > maxFallSpeed) {
                dy = maxFallSpeed;
            }

            if(y < 10){
                dy = 1;
            }

        }

    }
    
    public void printPosition() {
        System.out.println(x + " " + y);
    }

    public void update() {

        // update position
        getNextPosition();
        checkTileMapCollision();
        setPosition(xtemp, ytemp);

        //check attack has stopped
        if(currentAction == SCRATCHING) {
            if(animation.hasPlayedOnce()){
                scratching = false;
            }
        }

        if(currentAction == FIREBALL) {
            if(animation.hasPlayedOnce()){
                firing = false;
            }
        }

        //fireball attack
        fire += 1;
        if(fire > maxFire){
            fire = maxFire;
        } 
        if(firing && currentAction != FIREBALL) {
            if(fire > fireCost) {
                fire -= fireCost;
                FireBall fb = new FireBall(tileMap, facingRight);
                fb.setPosition(x, y);
                fireBalls.add(fb);
            }
        }
        //update fireballs
        for(int i = 0; i < fireBalls.size(); i++){
            fireBalls.get(i).update();
            if(fireBalls.get(i).shouldRemove()){
                fireBalls.remove(i);
                i--;
            }
        }

        //check done flinching
        if(flinching) {
            long elapsed = (System.nanoTime()-flinchTimer)/1000000;
            if(elapsed > 1000) {
                flinching = false;
            }
        }


        // set animation
        if (scratching) {
            if (currentAction != SCRATCHING) {
                currentAction = SCRATCHING;
                animation.setFrames(sprites.get(SCRATCHING));
                animation.setDelay(50);
                width = 30;
            }
        } else if (firing) {
            if (currentAction != FIREBALL) {
                currentAction = FIREBALL;
                animation.setFrames(sprites.get(FIREBALL));
                animation.setDelay(100);
                width = 30;
            }
        } else if (dy > 0) {
            if (gliding) {
                if (currentAction != GLIDING) {
                    currentAction = GLIDING;
                    animation.setFrames(sprites.get(GLIDING));
                    animation.setDelay(100);
                    width = 30;
                }
            } else if (currentAction != FALLING) {
                currentAction = FALLING;
                animation.setFrames(sprites.get(FALLING));
                animation.setDelay(100);
                width = 30;
            }
        } else if (dy < 0) {
            if (currentAction != JUMPING) {
                currentAction = JUMPING;
                animation.setFrames(sprites.get(JUMPING));
                animation.setDelay(-1);
                width = 30;
            }
        } else if (left || right) {
            if (currentAction != WALKING) {
                currentAction = WALKING;
                animation.setFrames(sprites.get(WALKING));
                animation.setDelay(40);
                width = 30;
            }
        } else {
            if (currentAction != IDLE) {
                currentAction = IDLE;
                animation.setFrames(sprites.get(IDLE));
                animation.setDelay(400);
                width = 30;
            }
        }

        animation.update();

        // set direction
        if (currentAction != SCRATCHING && currentAction != FIREBALL) {
            if (right) {
                facingRight = true;
            }
            if (left) {
                facingRight = false;
            }
        }

        //update Powerup timer
        checkTimer();
    }

    public void draw(Graphics2D g) {

        setMapPosition();

        //draw fireballs
        for(int i = 0; i < fireBalls.size(); i++){
            fireBalls.get(i).draw(g);
        }

        // draw player
        if (flinching) {
            long elapsed
                    = (System.nanoTime() - flinchTimer) / 1000000;
            if (elapsed / 100 % 2 == 0) {
                return;
            }
        }

        if (facingRight) {
            g.drawImage(
                    animation.getImage(),
                    (int) (x + xmap - width / 2),
                    (int) (y + ymap - height / 2),
                    null
            );
        } else {
            g.drawImage(
                    animation.getImage(),
                    (int) (x + xmap - width / 2 + width),
                    (int) (y + ymap - height / 2),
                    -width,
                    height,
                    null
            );

        }

    }

    //Additional methods for powerups
    public double getMaxSpeed() {
    	return maxSpeed;
    }
    
    public void setHealth(int x) {health = x;}
    
    public void setSpeed(double x) {formerValue = maxSpeed; maxSpeed = x; /*timer = 0;*/ powerupTimer = System.nanoTime(); powerUpChange = SPEEDCHANGE; powerUpActive = true;}
    
    public void setDoubleJump() {formerValue = jumpingModifier; jumpingModifier = jumpingModifier/2; /*timer = 0;*/ powerupTimer = System.nanoTime(); powerUpChange = DOUBLEJUMP; powerUpActive = true;}
    
    public void setFireballDamage() {formerValue = fireBallDamage; fireBallDamage = fireBallDamage*10; /*timer = 0;*/ powerupTimer = System.nanoTime(); powerUpChange = FIREBALLDAMAGE; powerUpActive = true;}
    
    public void checkTimer() { //the powerup only lasts for 1 second
        if(powerUpActive){
            long elapsed = (System.nanoTime() - powerupTimer) / 1000000;
            //System.out.println(elapsed);
            if (elapsed >= 5000) {
                //if 2000 ms, (2 second) has elapsed since the powerup began
                if (powerUpChange == SPEEDCHANGE) {
                    maxSpeed = formerValue;
                    formerValue = -100;
                    powerUpChange = -100;
                    powerUpActive = false;
                }else if (powerUpChange == DOUBLEJUMP) {
                    jumpingModifier = formerValue;
                    formerValue = -100;
                    powerUpChange = -100;
                    powerUpActive = false;
                }else if (powerUpChange == FIREBALLDAMAGE) {
                    fireBallDamage = (int)formerValue;
                    formerValue = -100;
                    powerUpChange = -100;
                    powerUpActive = false;
                }
                
            }
        }
        
    }
    
    public void addToInventory(PowerUp p) {
    	myInventory.addObject(p);
    }
   
    public Inventory getInventory() {return myInventory;}
    public void usePowerUp() {
    	myInventory.useObject(0);
    }
}