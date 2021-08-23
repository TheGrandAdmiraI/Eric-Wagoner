package Entity.Enemies;
import Entity.Animation;

import Entity.Enemy;
import TileMap.TileMap;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;

import java.awt.image.BufferedImage;

public class Seal extends Enemy{

private BufferedImage[] sprites;

    public Seal(TileMap tm){
        super(tm);

        moveSpeed = .6;
        maxSpeed = .6;
        fallSpeed = 0.2;
        maxFallSpeed = 10.0;

        width = 30; //width of enemy sprite
        height = 30; //height of enemy sprite
        cwidth = 30; //collision box size
        cheight = 15; //collision box size

        health = maxHealth = 2;
        damage = 1;
        //load sprites
        try {

            BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/Sprites/Enemies/seal.png"));

            sprites = new BufferedImage[4]; //3 is the number of frames in the animation
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

        //if it's about to fall off a cliff, turn around
        if(right && !bottomRight){ //bottomRight checks if the bottom right of the collision box is over air or not
            right = false;
            left = true;
            facingRight = false;
        } else if(left && !bottomLeft){ //bottomLeft checks if the bottom left of the collision box is over air or not
            left = false;
            right = true;
            facingRight = true;
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
