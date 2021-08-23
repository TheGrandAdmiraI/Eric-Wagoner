//This enemy flies back and forth in the air
package Entity.Enemies;
import Entity.Animation;

import Entity.Enemy;
import TileMap.TileMap;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
public class Seagull extends Enemy{
    private double initx;
    private double inity;
    private boolean setPos;
    private double yChange;//these 2 variables define how far from the initial spawn the seagull will fly
    private double xChange; 
    private BufferedImage[] sprites;
    public Seagull(TileMap tm) {
        super(tm);

        moveSpeed = .6;
        maxSpeed = .6;
        fallSpeed = 0.2;
        maxFallSpeed = 5.0;
        jumpStart = -4.8;

        yChange = 20;
        xChange = 100;

        width = 30; //width of enemy sprite
        height = 30; //height of enemy sprite
        cwidth = 30; //collision box size
        cheight = 15; //collision box size

        health = maxHealth = 2;
        damage = 1;

        //load sprites
        try {

            BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/Sprites/Enemies/Seagull.png")); //the jumping seal uses the same sprites as the normal one

            sprites = new BufferedImage[4]; //3 is the number of frames in the animation
            for(int i = 0; i < sprites.length; i++){
                sprites[i] = spritesheet.getSubimage(i * width, 0, width, height);
            }

        }catch(Exception e) {
            e.printStackTrace();
        }

        animation = new Animation();
        animation.setFrames(sprites);
        animation.setDelay(150); //change this delay to change speed of animation: smaller number = quicker

        right = true; //starts by moving to the right
        facingRight = true;
        setPos = false; //the inital position as not yet been set when object was created
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

        //if the position is far enough from initial in terms of y, then jump.
        if(y >= inity + yChange){
            jumping = true;
        }

        //jumping
        if (jumping) {
            dy = jumpStart;
            falling = true;
            jumping = false; //only using jumping for initial jump since we don't need dfferent fall speed for jumping up
        }        

        //if x position is too far from inital, turn around
        if(x >= initx + xChange && right){
            dx = 0 - moveSpeed; //need to make sure it's not = 0, because then it thinks it hit a wall
            right = false;
            left = true;
            facingRight = false;
        } else if(x <= initx - xChange && left){
            dx = 0 + moveSpeed; //need to make sure it's not = 0, because then it thinks it hit a wall
            left = false;
            right = true;
            facingRight = true;
        }

    }

    public void update(){
        //set initial x,y on first update
        if(!setPos){
            initx = x;
            inity = y;
            setPos = true;
        }

        //update position
        getNextPosition();
        checkTileMapCollision();
        setPosition(xtemp, ytemp);

        //check flinching
        checkFlinching();

        //if it hits a wall, change direction
        checkWall();

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
