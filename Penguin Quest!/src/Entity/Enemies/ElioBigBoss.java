package Entity.Enemies;

import Entity.Animation;
import Entity.Enemy;
import Entity.FireBall;
import TileMap.TileMap;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.util.Random;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ElioBigBoss extends Enemy{
    
    //big boss stuff 
    //private int health;
    //private int maxHealth;
    //private int fire;
    //private int maxFire;
    //private boolean dead;
    //private boolean flinching;
    //private long flinchTimer;
    
    
    // fireball
    private boolean firing;
    //private int fireCost;
    //private int fireBallDamage;
    private ArrayList<FireBall> fireBalls;
    
    // scratch
    private boolean scratching;
    //private int scratchDamage;
    //private int scratchRange;
           
    protected long jumpTimer;
    protected long scratchTimer;
    protected long fireTimer;
    protected long randomTimer;
    Random rand = new Random();
    private int r;
    
    private ArrayList<BufferedImage[]> sprites;
    private final int[] numFrames = {4, 4, 4, 4, 4, 4, 4};
    
    // animation actions
    private static final int IDLE = 0;
    private static final int WALKING = 1;
    private static final int JUMPING = 2;
    private static final int FALLING = 3;
    private static final int GLIDING = 4;
    private static final int FIREBALL = 5;
    private static final int SCRATCHING = 6;
    
    public ElioBigBoss(TileMap tm) {
        super(tm);
        
        moveSpeed = .6;
        maxSpeed = .6;
        fallSpeed = 0.2;
        maxFallSpeed = 5.0;
        jumpStart = -4.8;

        width = 90; //width of enemy sprite
        height = 90; //height of enemy sprite
        cwidth = 90; //collision box size
        cheight = 90; //collision box size

        //health = maxHealth = 30;
        //damage = 3;
        //fire = maxFire = 2500;
        
        //fireCost = 200;
        //fireBallDamage = 5;
        fireBalls = new ArrayList<FireBall>();

        //scratchDamage = 8;
        //scratchRange = 40;

        //start the jumptimer on init
        jumpTimer = System.nanoTime();
        randomTimer = System.nanoTime();
        scratchTimer = System.nanoTime();
        
        // load sprites
        try {

            BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/Sprites/Enemies/BigBoss.png"));

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
        currentAction = SCRATCHING;
        animation.setFrames(sprites.get(SCRATCHING));
        animation.setDelay(400);
        
        right = true; //starts by moving to the right
        facingRight = true;
        
    }
    
    //public int getHealth() {
    //    return health;
    //}

    //public int getMaxHealth() {
    //    return maxHealth;
    //}

    //public boolean isDead(){
    //    return dead;
    //}

    //public int getFire() {
    //    return fire;
    //}

    //public int getMaxFire() {
    //    return maxFire;
    //}

    public void setFiring() {
        firing = true;
    }

    public void setScratching() {
        scratching = true;
    }
    
    /*public void checkAttackBoss(ArrayList<Player> players) {	
        // loop through enemies
        for(int i = 0; i < players.size(); i++) { 	
                Player p = players.get(i);	
                // scratch attack
                if(scratching) {
                    if(facingRight) {
                        if(
                        // it checks to make sure that enemy is to our right since we are facing right	
                        p.getx() > x &&
                        p.getx() < x + scratchRange && 
                        p.gety() > y - height / 2 &&
                        p.gety() < y + height / 2
                        ) {
                            p.hit(scratchDamage);
                        }
                    } else {
                        if(
                            // the enemy is to our left	
                            p.getx() < x &&
                            p.getx() > x - scratchRange &&
                            p.gety() > y - height / 2 &&
                            p.gety() < y + height / 2
                        ) {
                            p.hit(scratchDamage);
                        }
                    }
                }		
                // fireballs
                for(int j = 0; j < fireBalls.size(); j++) { //loop through fireballs
                    if(fireBalls.get(j).intersects(p)) {
                        p.hit(fireBallDamage);
                        fireBalls.get(j).setHit();
                        break;
                    }
                }	
                // check enemy collision
                if(intersects(p)) {
                //    hit(p.getDamage());
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
    */
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

        //jumping
        if (jumping && !falling) {
            dy = jumpStart;
            falling = true;
            jumping = false; //only using jumping for initial jump since we don't need dfferent fall speed for jumping up
        }        
        
        //cannot move while attacking, except in air
        if ((currentAction == SCRATCHING || currentAction == FIREBALL) && !(jumping || falling)) {
            dx = 0;
        }
    }
    
   
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
    
    public void checkJump(){
        //jump at random intervals
        //higher jumpTimer, greater chance of jumping
        if(!jumping){
            long elapsed = (System.nanoTime() - jumpTimer) / 1000000;
            long rt = (System.nanoTime() - randomTimer) / 1000000;
            if(elapsed > 8000){
                jumping = true;
                jumpTimer = System.nanoTime();
                randomTimer = System.nanoTime();
            } else if(!falling && rt > 1000){//every 1s we want a 1/5 chance to jump
                randomTimer = System.nanoTime();
                //System.out.println("jumping seal checking for random jump");
                r = rand.nextInt(99);//chooses a random from [0,99] =? 100 numbers total
                if(r <20){ //1/5 chance of occuring
                    jumping = true;
                }
            }
        }
    }
    
    public void checkScratching(){
        //scratch at random intervals
        if(!scratching){
            long elapsed = (System.nanoTime() - scratchTimer) / 1000000;
            long rt = (System.nanoTime() - randomTimer) / 1000000;
            if(elapsed > 8000){
                scratching = true;
                scratchTimer = System.nanoTime();
                randomTimer = System.nanoTime();
            } else if(!scratching && rt > 1000){//every 1s we want a 1/5 chance to jump
                randomTimer = System.nanoTime();
                //System.out.println("jumping seal checking for random jump");
                r = rand.nextInt(99);//chooses a random from [0,99] =? 100 numbers total
                if(r <20){ //1/5 chance of occuring
                    scratching = true;
                }
            }
        }
    }
    public void checkFire(){
        //shoot fire at random intervals
        if(!firing){
            long elapsed = (System.nanoTime() - fireTimer) / 1000000;
            long rt = (System.nanoTime() - randomTimer) / 1000000;
            if(elapsed > 8000){
                firing = true;
                fireTimer = System.nanoTime();
                randomTimer = System.nanoTime();
            } else if(!firing && rt > 1000){//every 1s we want a 1/5 chance to jump
                randomTimer = System.nanoTime();
                //System.out.println("jumping seal checking for random jump");
                r = rand.nextInt(99);//chooses a random from [0,99] =? 100 numbers total
                if(r <20){ //1/5 chance of occuring
                    firing = true;
                }
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

        //if it hits a wall, change direction
        checkWall();

        //if it's about to fall off a cliff, turn around
        checkCliff(); //overriden in this class

        //jump at random intervals
        checkJump();
        
        //scratch at random intervals
        checkScratching();
        
        /*
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
            if (currentAction != FALLING) {
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
        */
        
        //update animation
        animation.update();
    }
    
    public void draw(Graphics2D g) {
        setMapPosition(); //if this is after the if statement, then the enemy won't load if it goes offscreen.
        //if(notOnScreen()){
        //    return;
        //}
        super.draw(g);
    }
}