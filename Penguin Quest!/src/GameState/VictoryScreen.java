package GameState;

import Handlers.Keys;

import TileMap.Background;
//import jdk.nashorn.internal.ir.LiteralNode.PrimitiveLiteralNode;

import java.awt.*;

import SaveState.*;

public class VictoryScreen extends GameState {

    private Background bg;

    private int currentChoice = 0;
    private String[] options = {
        "Next Level",
        "Restart Level",
        "Main Menu"
    };

    private Color titleColor;
    private Font titleFont;

    private Font font;
    private int prevState;
    //used to pass the number of lives on between levels
    private Lives playerLives;
    private int score = 0;

    private int nextLevel;
    
    public VictoryScreen(GameStateManager gsm) {
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
        playerLives = new Lives(3);
    }
    
    public void init() {
        //empty
    }

    public void setLives(int l){
        playerLives.setLives(l);
    }

    public void setScore(int s){
        score = s;
        //System.out.println("setting score of " + score);
    }

    public void setPrevState(int prevState){
        this.prevState = prevState;
    }

    //if we have the level numbers in ascending order, then the next level would just be previous + 1
    public void setNextLevel(){
        nextLevel = prevState + 1;
        System.out.println("next level number:" + (nextLevel - gsm.NUMMENUSTATES));
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
        g.drawString("Completed Level " + (prevState - gsm.NUMMENUSTATES) +"!", 20, 70);

        //draw score
        g.setColor(Color.BLACK);
        g.setFont(font);
        g.drawString("Total Score: " + score, 60, 100);

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
            gsm.setState(nextLevel, playerLives.getLives(), score);
        }
        if (currentChoice == 1) {
            gsm.setState(prevState, playerLives.getLives(), score);
        }
        if (currentChoice == 2) {
            gsm.setState(GameStateManager.MENUSTATE);
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