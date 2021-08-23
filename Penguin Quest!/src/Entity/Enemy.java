package Entity;

import TileMap.TileMap;

public class Enemy extends MapObject {

    protected int health;
    protected int maxHealth;
    protected boolean dead;
    protected int damage;

    protected boolean flinching;
    protected long flinchTimer;
    
    public Enemy(TileMap tm) {
        super(tm);
    }

    public boolean isDead(){
        return dead;
    }

    public int getDamage() {
        return damage;
    }

    public void hit(int damage) {
        if(dead || flinching) {
            return;
        }
        health -= damage;
        if(health < 0) {
            health = 0;
        }
        if(health == 0) {
            dead = true;
        }
        flinching = true;
        flinchTimer = System.nanoTime();
    }

    public void checkWall(){
        if(right && dx == 0) {//if it hits a wall, dx is set to 0
            right = false;
            left = true;
            facingRight = false;
        }else if(left && dx == 0){
            left = false;
            right = true;
            facingRight = true;
        }
    }

    public void checkCliff(){
        if(right && !bottomRight){ //bottomRight checks if the bottom right of the collision box is over air or not
            right = false;
            left = true;
            facingRight = false;
        } else if(left && !bottomLeft){ //bottomLeft checks if the bottom left of the collision box is over air or not
            left = false;
            right = true;
            facingRight = true;
        }
    }

    public void checkFlinching(){
        if(flinching) {
            long elapsed = (System.nanoTime() - flinchTimer) / 1000000;
            if(elapsed > 400) {
                flinching = false;
            }
        }
    }

    public void update(){

    }

}
