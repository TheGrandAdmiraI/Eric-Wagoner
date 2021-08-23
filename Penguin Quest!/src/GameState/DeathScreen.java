package GameState;

import Handlers.Keys;
import TileMap.Background;
import java.io.File;

import java.awt.*;

public class DeathScreen extends GameState{
    
    private Background bg;

    private int currentChoice = 0;
    private String[] options = {
        "Restart Level",
        "Menu",
        "Quit"
    };
    
    private Color titleColor;
    private Font titleFont;

    private Font font;
    
    private int prevState;

    public DeathScreen(GameStateManager gsm){
        this.gsm = gsm;

        try {
            bg = new Background("/Backgrounds/menubg.gif", 0);
            bg.setVector(-0, 0); // replace the upper number with 1, and the vector with -0.1, 0 to make it move

            titleColor = new Color(200, 0, 0);
            titleFont = new Font("Century Gothic", Font.PLAIN, 28);
            font = new Font("Arial", Font.PLAIN, 12);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void init() {
        //empty
    }

    public void setPrevState(int prevState){
        this.prevState = prevState;
    }


    public void update() {
        handleInput();
        bg.update();
    }

    public void draw(Graphics2D g) {

        // draw bg
        bg.draw(g);

        // draw title
        g.setColor(titleColor);
        g.setFont(titleFont);
        g.drawString("You Died :(", 60, 70);

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
            gsm.setState(prevState);
        }
        if (currentChoice == 1) {
            gsm.setState(gsm.MENUSTATE);
        }
        if (currentChoice == 2) {
            System.out.println("Before exiting, we must delete files");
            //here we want code to delete all the files we created
            int levelNumber = 1;
            File levelFile = new File("Resources/Maps/Infinite/endless" + levelNumber + ".map");
            while(levelFile.exists()){
                //delete each file that exists
                System.out.println("Deleting file" + "/Maps/Infinite/endless" + levelNumber + ".map");
                levelFile.delete();
                levelNumber++;
                levelFile = new File("Resources/Maps/Infinite/endless" + levelNumber + ".map");
            }
            levelNumber = 1;
            levelFile = new File("Resources/Maps/Infinite/endless" + levelNumber + "entities.map");
            while(levelFile.exists()){
                //delete each file that exists
                System.out.println("Deleting file" + "/Maps/Infinite/endless" + levelNumber + "entities.map");
                levelFile.delete();
                levelNumber++;
                levelFile = new File("Resources/Maps/Infinite/endless" + levelNumber + "entities.map");
            }
            System.exit(0);
        }
    }

    public void handleInput() {
        if (Keys.isPressed(Keys.ENTER)) {
            select();
        }
        if (Keys.isPressed(Keys.UP)) {
            if (currentChoice > 0) {
                currentChoice--;
            }
        }
        if (Keys.isPressed(Keys.DOWN)) {
            if (currentChoice < options.length - 1) {
                currentChoice++;
            }
        }
    }
}
