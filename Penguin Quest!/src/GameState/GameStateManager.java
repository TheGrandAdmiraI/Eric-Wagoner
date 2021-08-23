package GameState;


//import Audio.AudioPlayer;
import java.io.IOException;


import java.util.ArrayList;

public class GameStateManager {

    private ArrayList<GameState> gameStates;

    public int currentState;
    public int prevState;


    public static final int MENUSTATE = 0;
    public static final int LEVELSELECTSTATE = 1;
    public static final int DEATHSCREEN = 2;
    public static final int VICTORYSCREEN = 3;
    public static final int TUTORIALSTATE = 4;
    //public static final int LEVEL1_1STATE = 4;
    public static final int ENDLESSSTATE = 5;
    //public static final int ENDLESSSTATE2 = 6;

    public static final int NUMMENUSTATES = 5 - 1; //this is the number of non level states that come first in the array, aka menu states, used for calculations. we do - 1 since we want the level number to start with 1
    
    

    public GameStateManager() throws IOException {


        //AudioPlayer.init();

        gameStates = new ArrayList<>();

        currentState = MENUSTATE;
        gameStates.add(new MenuState(this));
        gameStates.add(new LevelSelectState(this));
        gameStates.add(new DeathScreen(this));
        gameStates.add(new VictoryScreen(this));
        gameStates.add(new TutorialState(this));
        gameStates.add(new EndlessState2(this, 1));
    }

    public void setState(int state) {
        try {
            prevState = currentState;
            currentState = state;
            //if the new state is out of bounds for the array, then we expand the array with a new endless level and init that
            if(currentState >= gameStates.size()){
                gameStates.add(new EndlessState2(this, currentState - NUMMENUSTATES)); //the "level number" is minus 5 since the first 5 in the array aren't actual levels, just menus
            }
            gameStates.get(currentState).init();
            if(currentState == DEATHSCREEN){
                gameStates.get(currentState).setPrevState(prevState); //gives the death screen the ability to repeat levels
            } else if(currentState == VICTORYSCREEN){
                gameStates.get(currentState).setPrevState(prevState); //gives the victory screen the ability to repeat levels
                gameStates.get(currentState).setNextLevel();//next level auto calculated if in ascending order
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("couldn't initialize Level");
            System.exit(0);
        }
        
        
    }
    //if we need to pass the number of lives, we do so
    public void setState(int state, int l) {
        //this method does the same thing as the other setstate, but also sets the number of lives
        setState(state);
        gameStates.get(currentState).setLives(l);
    }

    public void setState(int state, int l, int s) {
        //this method does the same thing as the other setstate, but also sets the number of lives
        setState(state);
        gameStates.get(currentState).setLives(l);
        gameStates.get(currentState).setScore(s);
    }

    public int getState(){
        return currentState;
    }

    public int numLevels(){ //this is used by the level select state
        return gameStates.size() - NUMMENUSTATES; //we reduce the number by 5 since the first 5 states aren't levels.
    }

    public void update() {
        try {
            gameStates.get(currentState).update();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(java.awt.Graphics2D g) {
        try {
            //gameStates[currentState].draw(g);

            gameStates.get(currentState).draw(g);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
