package Entity;

import TileMap.TileMap;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;

public class Banner extends MapObject{

	
	private Player player;
	private TileMap TM;
	
    private BufferedImage[] sprites;
    private BufferedImage[] blankSprites;
    private BufferedImage[] alteredSprites;
    
    private boolean altered;
    private boolean visible;
    
    private int visibleTimer;
    

    public Banner(TileMap tm, Player p){
        super(tm);
        facingRight = true;
        TM = tm;
        
        
        visibleTimer = 0;
        
        player = p;
        player.getInventory().setBanner(this);
        
        width = 300; //width of flag sprite
        height = 30; //height of flag sprite
        cwidth = 0; //collision box size
        cheight = 0; //collision box size
        
        
        
        visible = false;
        altered = false;
        
        

        //load in the sprites
 
        try {

            BufferedImage spritesheet = ImageIO.read(
                    getClass().getResourceAsStream("/Sprites/Banner/Banner.gif"));

            sprites = new BufferedImage[1]; //1 is the number of frames in the animation
            sprites[0] = spritesheet;
            

        }catch(Exception e) {
            e.printStackTrace();
        }
        try {

            BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/Sprites/Banner/blankBanner.gif"));

            blankSprites = new BufferedImage[1]; //1 is the number of frames in the animation
            blankSprites[0] = spritesheet;
      

        }catch(Exception e) {
            e.printStackTrace();
        }
      
        BannerEditor a = new BannerEditor("");
        alteredSprites = new BufferedImage[1]; //1 is the number of frames in the animation
        alteredSprites[0] = a.getImage();

        animation = new Animation();
        animation.setFrames(sprites);
    }

    public int getWidth() {
    	return width;
    }

    public TileMap getTM() {
    	return TM;
    }
    public Player getPlayer() {
    	return player;
    }
    
    public void setInvisible() {visible = false;}
    public void setVisible() {visible = true;}
    
    public void update(){
    
    	if (visible && visibleTimer > 30) {
  			visible = false;
  			altered = false;
  			visibleTimer = 0;
  			
  		}
    	
    	if (altered && visible) {
    		
    		
    		
    		animation.setFrames(alteredSprites); visibleTimer++;}
    	else if (visible) {animation.setFrames(sprites); visibleTimer++;}
    	else animation.setFrames(blankSprites);
    	
    	
     
        animation.update();

    }

    public void createBanner(String m) {
    	BannerEditor a = new BannerEditor(m);
    	
    		alteredSprites[0] = a.getImage();
    		altered = true;
    		visible = true;
    		
    	}
    	
    
    
    public void draw(Graphics2D g){
    	  g.drawImage(
                  animation.getImage(),
                  0,
                 75,
                  null
          );
    }
}


