package Entity.PowerUps;

import Entity.Player;
import TileMap.TileMap;

public class DoubleJump extends PowerUp{

	private boolean didUse;
	
	public DoubleJump(TileMap tm, Player p) {
		super(tm, "Double Jump", p, "/Sprites/PowerUps/Double Jump.gif");
		didUse = false;
		
	}

	public String use() {
		if(!didUse){
			//necessary or run twice
			playerOwnedBy.setDoubleJump();
			didUse = true;
		}
		return "A " + type + " PowerUp was used.";
	}
	
}
