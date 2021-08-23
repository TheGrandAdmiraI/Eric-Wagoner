package Entity.PowerUps;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Entity.Animation;
import Entity.MapObject;
import Entity.Player;
import TileMap.TileMap;

public abstract class PowerUp extends MapObject{

	protected String type;
	protected Player playerOwnedBy;
	protected boolean standingOver;
	protected boolean pickedUp;
	protected boolean used;
	
    // animations
	private ArrayList<BufferedImage[]> sprites;
    private final int[] numFrames = {
        1, 1, 1
    };
    
 // animation actions
    private static final int UNFOUND = 0;
    private static final int OBTAINING = 1;
    private static final int OBTAINED = 2;
	
	public PowerUp(TileMap tm, String t, Player p, String s) {
		super(tm);
		type = t;
		facingRight = true;
		playerOwnedBy = p;
		used = false;
		
		maxSpeed = 0;
		
		 width = 30;
	     height = 30;
	     cwidth = 5;
	     cheight = 5;

		
		// load sprites
        try {

            BufferedImage spritesheet = ImageIO.read(
                    getClass().getResourceAsStream(
                            s
                    )
            );

            sprites = new ArrayList<BufferedImage[]>();
            for (int i = 0; i < 3; i++) {

                BufferedImage[] bi
                        = new BufferedImage[numFrames[i]];

                for (int j = 0; j < numFrames[i]; j++) {

                        bi[j] = spritesheet.getSubimage(
                                j * width,
                                i * height,
                                width,
                                height
                        );
                   
                }

                sprites.add(bi);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        animation = new Animation();
        currentAction = UNFOUND;
        animation.setFrames(sprites.get(UNFOUND));
        animation.setDelay(400);
	}
	
	public String getType() {return type;}
	
	public String use() {
		return "";
		//Different for every subclass	
	}
	
	public boolean intersects(Player p) {
		 Rectangle r1 = getRectangle();
	        Rectangle r2 = p.getRectangle();
	        return r1.intersects(r2);
	}

	public void setStandingOver() {
		standingOver = true;
	}
	
	public void setPickedUp() {
		pickedUp = true;
	}
	
	private void getNextPosition() {
		if (this.intersects(playerOwnedBy)) {
        	if (!used) {
    			setStandingOver();
        		playerOwnedBy.addToInventory(this);
        		used = true;
        	}
		}
		else if (standingOver) {
			standingOver = false;
			setPickedUp();
		}
	}
	 public void update() {
		 	getNextPosition();
		 	setMapPosition();
		 
		 
	        if (standingOver) {
	            if (currentAction != OBTAINING) {
	                currentAction = OBTAINING;
	                animation.setFrames(sprites.get(OBTAINING));
	                animation.setDelay(50);
	                width = 60;
	            }
	        } else if (pickedUp) {
	            if (currentAction != OBTAINED) {
	                currentAction = OBTAINED;
	                animation.setFrames(sprites.get(OBTAINED));
	                animation.setDelay(100);
	                width = 30;
	            }
	        }

	        animation.update();
	 }

	 public void draw(Graphics2D g) {
		 setMapPosition();
		 super.draw(g);
	 }

}