package GameState;


import Audio.AudioPlayer;
import java.io.IOException;


import java.util.ArrayList;

public class GameStateManager {

    private ArrayList<GameState> gameStates;

    public int currentState;


    public static final int MENUSTATE = 0;
    public static final int LEVELSELECTSTATE = 1;
    public static final int TUTORIALSTATE = 2;
    public static final int ENDLESSSTATE = 3;
    public static final int LEVEL1_1STATE = 4;

    public GameStateManager() throws IOException {

        AudioPlayer.init();


        gameStates = new ArrayList<>();

        currentState = MENUSTATE;
        gameStates.add(new MenuState(this));
        gameStates.add(new LevelSelectState(this));
        gameStates.add(new TutorialState(this));
        gameStates.add(new EndlessState(this));
        gameStates.add(new Level1_1State(this));

    }

    public void setState(int state) {
        currentState = state;
        gameStates.get(currentState).init();
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
