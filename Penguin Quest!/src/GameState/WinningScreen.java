package GameState;

import TileMap.Background;
import java.awt.*;
import java.awt.event.KeyEvent;
import GameState.*;
import Entity.Coin;

public class WinningScreen extends GameState {
     
    private Background bg;

    private int currentChoice = 5;
    private String[] options = {
        "Return to the Menu",
        "Continue to the next level",
        "Restart the same level"
    };

    private Color titleColor;
    private Font titleFont;

    private Font font;
    
    public WinningScreen(GameStateManager gsm) {
        this.gsm = gsm;
        try {
            bg = new Background("/Backgrounds/menubg.gif", 0);
            bg.setVector(-0, 0); // replace the upper number with 1, and the vector with -0.1, 0 to make it move

            titleColor = new Color(0, 180, 250);
            titleFont = new Font("Century Gothic", Font.PLAIN, 28);
            font = new Font("Arial", Font.PLAIN, 12);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void init() {
        //empty
    }

    public void update() {
        bg.update();
    }

    public void draw(Graphics2D g) {

        // draw bg
        bg.draw(g);

        // draw title
        g.setColor(titleColor);
        g.setFont(titleFont);
        g.drawString("Congratulations on completing this level!", 60, 70);
        //g.drawString("You collected " + TutorialState.getScore() + " coins.", 80 , 70);
        

        // draw menu options
        g.setFont(font);
        for (int i = 0; i < options.length; i++) {
            if (i == currentChoice) {
                g.setColor(Color.CYAN);
            } else {
                g.setColor(Color.WHITE);
            }
            g.drawString(options[i], 145, 140 + i * 15);
        }

    }
    
    private void select() {
        if (currentChoice == 0) {
            gsm.setState(GameStateManager.LEVELSELECTSTATE);
        }
        if (currentChoice == 1) {
            gsm.setState(GameStateManager.TUTORIALSTATE);
        }
        if (currentChoice == 2) {
            System.exit(0);
        }
    }
    public void handleInput() {
		//empty
	}
}
