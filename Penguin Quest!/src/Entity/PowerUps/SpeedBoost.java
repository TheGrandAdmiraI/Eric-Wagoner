package Entity.PowerUps;

import Entity.Player;
import TileMap.TileMap;

public class SpeedBoost extends PowerUp{

	private boolean didUse;
	
	public SpeedBoost(TileMap tm, Player p) {
		super(tm, "Speed Boost", p, "/Sprites/PowerUps/Speed Boost.gif");
		didUse = false;
		
	}

	public String use() {
		
		if(!didUse){
			System.out.println("using speedboost");
			//if this isn't done, then for some reason the code is ran twice
			playerOwnedBy.setSpeed(playerOwnedBy.getMaxSpeed()*3);
			didUse = true;
		}
		return "A " + type + " PowerUp was used.";
	}
	
}
