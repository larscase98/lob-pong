package my.game;

import java.util.Random;

public class Spawner {
	
	private Random r = new Random();
	
	public void tick() {
		
		//-- Level one:
			
		if (Game.GAME_COUNT % (int)(500/Game.currentLevel) == 0 && Game.GAME_COUNT > 1) {
			Game.entList.add(new BasicUFO());
		}
		
		if (Game.currentLevel > 2) {
			for(int i = 0; i < Game.entList.size(); i++) {
				if (Game.GAME_COUNT % 25 == 0 && Game.entList.get(i).getID() == ID.BasicUFO) {
					BasicUFO parent = (BasicUFO) Game.entList.get(i);
					if (r.nextInt(100) < (10 * (Game.currentLevel - 2))) {
						// If random number 0-100 is less than ten times (two less than the level number), fire a shot.
						Game.entList.add(new BasicUFOBullet(parent));
					}
				}
			}
		}
		
		
		
		//-- Static spawning is everything below this point. (a.k.a. doesnt vary based on what level you're on.
		if (Game.GAME_COUNT % 500 == 0 && Game.GAME_COUNT > 1) {
			Game.entList.add(new HealthPack());
		}
		
		if ((Game.GAME_COUNT % 250) == 0 && Game.GAME_COUNT > 1) {
			Game.entList.add(new BonusStar());
		}
		
	}
	
	
}
