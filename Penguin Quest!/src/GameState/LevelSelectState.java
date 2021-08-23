package GameState;

import Handlers.Keys;
import TileMap.Background;

import java.awt.*;

public class LevelSelectState extends GameState {

    private Background bg;

    private int currentChoice = 0;
    private String[] levels = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "Back"};

    private int chosenLevel = 0;

    private Color titleColor;
    private Font titleFont;

    private Font font;

    public LevelSelectState(GameStateManager gsm) {

        this.gsm = gsm;

        try {
            bg = new Background("/Backgrounds/menubg.gif", 0);
            bg.setVector(-0, 0); //makes it so the background doesn't move, but can be changed if we want it to move, like in menu state

            titleColor = new Color(0, 180, 250);
            titleFont = new Font("Century Gothic", Font.PLAIN, 20);
            font = new Font("Arial", Font.PLAIN, 12);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void init() {
        //empty
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
        g.drawString("Level Select:", 100, 130);

        // draw menu options
        g.setFont(font);
        /*
        for (int i = 0; i < levels.length; i++) {
            if (i == currentChoice) {
                g.setColor(Color.CYAN);
            } else {
                g.setColor(Color.WHITE);
            }
            if (i >= 0 && i <= 4) {
                g.drawString(levels[i], 70 + i * 35, 100);
            }
            if (i > 4 && i <= 9) {
                g.drawString(levels[i], 70 + (i - 5) * 35, 130);
            }
            if (i > 9 && i <= 14) {
                g.drawString(levels[i], 70 + (i - 10) * 35, 160);
            }
            if (i == 15) {
                g.drawString(levels[i], 135, 200);
            }
        }
        */
        for(int i = 0; i < 2; i++){
            if (i == currentChoice) {
                g.setColor(Color.CYAN);
            } else {
                g.setColor(Color.WHITE);
            }
            if(i == 0){
                g.drawString(String.valueOf(chosenLevel), 135, 150);
            }else {
                g.drawString("back", 135, 180);
            }
        }
    }

    private void select() {
        /*switch (currentChoice) {
            case 0:
                gsm.setState(GameStateManager.ENDLESSSTATE);
                break;
            case 13:
                gsm.setState(GameStateManager.DEATHSCREEN);
                break;
            case 14:
                gsm.setState(GameStateManager.ENDLESSSTATE);
                break;
            case 15:
                gsm.setState(GameStateManager.MENUSTATE);
                break;
        }*/
        //we want the level number to choose the number level selected
        /*
        if(currentChoice == 15){
            gsm.setState(GameStateManager.MENUSTATE);
        } else{
            //first we check if the state is possible to load
            if(currentChoice < gsm.numLevels()){ //it's only less than since case 0 = first level and so on
                gsm.setState(currentChoice + gsm.NUMMENUSTATES); //the first endless level will be after the number of menu states, and if there are 5 menu states, first level will be position 5 in the array since it starts at 0
            } else{
                System.out.println("Sorry, that level hasn't been created yet, please choose another one.");
            }
        }
        */
        if(currentChoice == 1){
            gsm.setState(GameStateManager.MENUSTATE);
        } else{
            //first we check if the state is possible to load
            if(chosenLevel < gsm.numLevels() && chosenLevel >= 0){ //it's only less than since case 0 = first level and so on
                gsm.setState(chosenLevel + gsm.NUMMENUSTATES); //the first endless level will be after the number of menu states, and if there are 5 menu states, first level will be position 5 in the array since it starts at 0
            } else{
                System.out.println("Sorry, that level hasn't been created yet, please choose another one.");
            }
        }
    }

    public void handleInput() {
        if (Keys.isPressed(Keys.ENTER)) {
            select();
        }
        if (Keys.isPressed(Keys.LEFT)) {
            /*
            currentChoice--;
            if (currentChoice == -1) {
                currentChoice = levels.length - 1;
            }
            */
            if(currentChoice == 0){
                chosenLevel--;
            }
        }
        if (Keys.isPressed(Keys.RIGHT)) {
            /*
            currentChoice++;
            if (currentChoice == levels.length) {
                currentChoice = 0;
            }
            */
            if(currentChoice == 0){
                chosenLevel++;
            }
        }
        if (Keys.isPressed(Keys.UP)) {
            /*
            currentChoice -= 5;
            if (currentChoice < 0) {
                currentChoice = levels.length - 1;
            }
            */
            currentChoice--;
            if(currentChoice < 0){
                currentChoice = 1;
            }
        }
        if (Keys.isPressed(Keys.DOWN)) {
            /*
            currentChoice += 5;
            if (currentChoice >= 15 && currentChoice <= 19) {
                currentChoice = 15;
            }
            if (currentChoice >= 20) {
                currentChoice = 0;
            }
            */
            currentChoice++;
            if(currentChoice > 1){
                currentChoice = 0;
            }
        }
    }




}
