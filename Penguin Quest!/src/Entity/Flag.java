package Entity;

import TileMap.TileMap;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;

public class Flag extends MapObject{

    private BufferedImage[] sprites;

    public Flag(TileMap tm){
        super(tm);

        width = 30; //width of flag sprite
        height = 30; //height of flag sprite
        cwidth = 30; //collision box size
        cheight = 30; //collision box size

        //load in the sprites
        try {

            BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/Sprites/Flag/flag.png"));

            sprites = new BufferedImage[1]; //3 is the number of frames in the animation
            for(int i = 0; i < sprites.length; i++){
                sprites[i] = spritesheet.getSubimage(i * width, 0, width, height);
            }

        }catch(Exception e) {
            e.printStackTrace();
        }

        animation = new Animation();
        animation.setFrames(sprites);
        animation.setDelay(200);
    }

    public void update(){
        animation.update();
    }

    public void draw(Graphics2D g){
        setMapPosition();
        super.draw(g);
    }
}