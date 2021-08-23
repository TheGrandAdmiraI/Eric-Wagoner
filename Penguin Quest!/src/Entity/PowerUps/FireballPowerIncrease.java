package Entity.PowerUps;

import Entity.Player;
import TileMap.TileMap;

public class FireballPowerIncrease extends PowerUp {

	private boolean didUse;

	public FireballPowerIncrease(TileMap tm, Player p) {
		super(tm, "Fireball Power Increase", p, "/Sprites/PowerUps/Fireball Damage.gif");
		
		didUse = false;
	}

	public String use() {
		
		if(!didUse){
			//if this isn't done, then for some reason the code is ran twice
			playerOwnedBy.setFireballDamage();
			didUse = true;
		}
		return "A " + type + " PowerUp was used.";
	}
}