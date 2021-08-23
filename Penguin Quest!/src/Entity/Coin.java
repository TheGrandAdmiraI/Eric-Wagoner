package Entity;

import TileMap.TileMap;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;

public class Coin extends MapObject {
    
    private BufferedImage[] sprites;
    
    private boolean remove;
    
    public Coin(TileMap tm) {
        super(tm);
        
        width = 30; //width of coin sprite
        height = 30; //height of coin sprite
        cwidth = 30; //collision box size
        cheight = 30; //collision box size
        
        try {

            BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/Sprites/Coin/coin.art.png"));

            sprites = new BufferedImage[6];
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
    
    public boolean shouldRemove() { 
        return remove; 
    }
    
    public void update(){
        animation.update();
    }

    public void draw(Graphics2D g){
        setMapPosition();
        super.draw(g);
    }
}
