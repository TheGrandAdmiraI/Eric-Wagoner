package Entity.PowerUps;

import Entity.Player;
import TileMap.TileMap;

public class HealthBoost extends PowerUp{

	private boolean didUse;
	
	public HealthBoost(TileMap tm, Player p) {
		super(tm, "Health Boost", p, "/Sprites/PowerUps/Health Boost.gif");
		didUse = false;
		
	}

	public String use() {
		if(!didUse){
			//if this isn't done, then for some reason the code is ran twice
			playerOwnedBy.setHealth(playerOwnedBy.getMaxHealth());
			didUse = true;
		}
		
		return "A " + type + " PowerUp was used.";
	}
	
}
