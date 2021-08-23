package Entity;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import Entity.PowerUps.PowerUp;
import SaveState.*;

public class HUD {
    
    private Player player;
    private BufferedImage image;
    private Font font;
    private Font powerUpFont;
    private Lives life;
    private String power;
    private int score = 0;

    public HUD(Player p, Lives l){
        player = p;
        life = l;
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/HUD/hudfinal.png"));
            font = new Font("Arial", Font.PLAIN, 14);
            powerUpFont = new Font("Arial", Font.PLAIN, 10);


        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void setScore(int s){
        score = s;
    }

    public void draw(Graphics2D g){
        
        g.drawImage(image, 0, 10, null);
        g.setFont(font);
        g.setColor(Color.WHITE);
        g.drawString(player.getHealth() + "/" + player.getMaxHealth(), 30,25); //health
        g.setFont(powerUpFont); //we use a different font size for the powerups since the string is so long
        g.drawString(player.getInventory().nextPowerUp(), 20, 65); //this tells you what powerup you have
        g.setFont(font);
        g.drawString("" + life.getLives(), 20, 86); //lives
        g.drawString("" + score, 20, 106); //this is how many coins have been collected

        //g.drawString(player.getFire() / 100 + "/" + player.getMaxFire() / 100, 30, 45);
        g.setColor(Color.CYAN);
        g.fillRect(30, 35, player.getFire() / 50, 10);
        
    }


}
