package SaveState;

public class Lives {
    private int life;

    public Lives(int l){
        life = l;
    }

    public void increment(){
        life++;
    }

    public void decrement(){
        life--;
    }

    public void setLives(int l){
        life = l;
    }

    public int getLives(){
        return life;
    }
    
}
