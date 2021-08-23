package GameState;

public abstract class GameState {

    protected GameStateManager gsm;

    public abstract void init();

    public void setPrevState(int prevState){
        //only used in death screen
    }

    public void setLives(int l){
        //used to save lives between levels
    }

    public void setNextLevel(){
        //used in the victory screen state to determine next level
    }

    public void setScore(int s){
        //this is used in endless levels
    }

    public abstract void update();

    public abstract void draw(java.awt.Graphics2D g);

    public abstract void handleInput();

}
