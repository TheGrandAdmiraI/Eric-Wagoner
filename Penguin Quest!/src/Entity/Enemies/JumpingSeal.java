//simple enemy like the seal, but it jumps at random intervals and will walk off edges

package Entity.Enemies;
import Entity.Animation;

import Entity.Enemy;
import TileMap.TileMap;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.util.Random;

import java.awt.image.BufferedImage;

public class JumpingSeal extends Enemy{

    protected long jumpTimer;
    protected long randomTimer;
    private Random rand = new Random();
    private int r;
    private BufferedImage[] sprites;

    public JumpingSeal(TileMap tm){
        super(tm);

        moveSpeed = .6;
        maxSpeed = .6;
        fallSpeed = 0.2;
        maxFallSpeed = 5.0;
        jumpStart = -4.8;

        width = 30; //width of enemy sprite
        height = 30; //height of enemy sprite
        cwidth = 30; //collision box size
        cheight = 15; //collision box size

        health = maxHealth = 2;
        damage = 1;

        //start the jumptimer on init
        jumpTimer = System.nanoTime();
        randomTimer = System.nanoTime();

        //load sprites
        try {

            BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/Sprites/Enemies/seal.png")); //the jumping seal uses the same sprites as the normal one

            sprites = new BufferedImage[4]; //4 is the number of frames in the animation
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
                r = rand.nextInt(100);//chooses a random from [0,99] =? 100 numbers total
                if(r <20){ //1/5 chance of occuring
                    jumping = true;
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
