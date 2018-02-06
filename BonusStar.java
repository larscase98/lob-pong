package my.game;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class BonusStar extends Entity {

	private Random r = new Random();
	
	public static BufferedImage img = null;
	
	public BonusStar() {
		id = ID.BonusStar;
		
		velX = velY = 0;
		
		x = r.nextInt(Game.WIDTH - 100) + 50;
		y = r.nextInt(Game.HEIGHT - (500 + 100)) + 100;
		
		width = 94;
		height = 80;
		
		try { img = ImageIO.read(new File("resources/star.png")); } catch (IOException e) { e.printStackTrace(); }
		
	}

	
	public void tick() {
		if (Game.entList.get(1).getBounds().intersects(getBounds())) {
			Game.score += 100;
			Game.entList.remove(this);
			
			if (Game.levelTimer < 5) Game.levelTimer = 0;
			else Game.levelTimer -= 5;
		}
		
		x += velX;
		y += velY;
	}

}
