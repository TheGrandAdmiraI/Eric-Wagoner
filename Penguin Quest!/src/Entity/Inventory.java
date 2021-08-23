package Entity;

import java.util.ArrayList;

import Entity.PowerUps.PowerUp;


public class Inventory {

	private ArrayList<PowerUp> objects;
	
	
	private int maxsize = 5;
	private int numObjects;
	private Banner banner;
	
	public Inventory() {
		objects = new ArrayList<PowerUp>();
		numObjects = 0;
	}
	
	public void addObject(PowerUp p) {
		if (numObjects < maxsize) {
			objects.add(p);
			banner.createBanner("You have found a " + objects.get(numObjects).getType() + " Power Up!");
			numObjects++;
		}
	}
	
	
	public void useObject(int i) {
		if (i < maxsize && numObjects > 0) {
			banner.createBanner(objects.get(i).use());
			objects.get(i).use();
			objects.remove(i);
			numObjects--;
		}else 
		banner.createBanner("Sorry, you do not have any powerups to use :(");
	}

	public String nextPowerUp(){
		String p;
		if (numObjects > 0) {
			p = objects.get(0).getType();
		}else {
			p = "";
		}
		return p;
	}
	
	public void setBanner(Banner b) {
		banner = b;
	}
	
}
