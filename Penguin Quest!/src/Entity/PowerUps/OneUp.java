package Entity.PowerUps;

import Entity.Player;
import TileMap.TileMap;
import SaveState.Lives;

public class OneUp extends PowerUp{

	private Lives life;
	private boolean didUse;
	
	public OneUp(TileMap tm, Player p) {
		super(tm, "OneUp", p, "/Sprites/PowerUps/OneUp.png");
		life = p.getLives();
		didUse = false;
		
	}

	public String use() {
		if(!didUse){
			life.increment();
			didUse = true;
		} 
		return "A " + type + " PowerUp was used.";
	}
	
}
