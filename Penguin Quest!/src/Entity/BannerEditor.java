package Entity;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


import javax.imageio.ImageIO;
//import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @see https://stackoverflow.com/questions/2658663
 */
public class BannerEditor extends JPanel {

    private BufferedImage image;
    
    private String message;
    
    private boolean successful;
    
    public BannerEditor(String m) {
    	successful = false;
    	message = m;
        try {
            image = ImageIO.read(getClass().getResourceAsStream(
                "/Sprites/Banner/Banner.gif"));
        } catch (IOException e) {
            e.printStackTrace();
        }
      
//edit the image with the text
        image = process(image);
      
       
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(image.getWidth()/2, image.getHeight());
    }

    private BufferedImage process(BufferedImage old) {
        int w = old.getWidth();
        int h = old.getHeight();
        BufferedImage img = new BufferedImage(
            w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();
        g2d.drawImage(old, 0, 0, w, h, this);
        g2d.setPaint(Color.blue);
        g2d.setFont(new Font("Ariel", Font.PLAIN, 10));
        String s = message;
        FontMetrics fm = g2d.getFontMetrics();
        int x = 80;//img.getWidth() - fm.stringWidth(s) - 20;//how far left it starts //gonna move this over because of the bigger hud now
        int y = (img.getHeight() - fm.getHeight() )/2 + fm.getHeight() - 2;//how tall it is
        g2d.drawString(s, x, y);
        g2d.dispose();
        return img;
    }
    
    public boolean getSuccess() {
    	return successful;
    }
 
    public BufferedImage getImage() {
    	return image;
    }
    
}