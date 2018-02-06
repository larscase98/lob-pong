package my.game;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class HealthPack extends Entity {
	
	private Random r = new Random();
	public static BufferedImage img = null;
	
	public HealthPack() {
		id = ID.HealthPack;
		
		x = (r.nextInt(Game.WIDTH) - 200) + 100;
		y = (r.nextInt(Game.HEIGHT) - 500) + 200;
		
		width = 59;
		height = 48;
		
		velX = velY = 0;
		
		try {
			img = ImageIO.read(new File("resources/health1.png"));
		} catch (IOException e) {
			System.err.println("The health pack image was not found. Subsituting...");
		}
		
	}
	
	public void tick() {
		//probably won't move, but add velocities anyway.
		
		if(getBounds().intersects(Game.entList.get(1).getBounds())) {
			Game.entList.remove(this);
			Game.lives++;
		}
		
		x += velX;
		y += velY;
	}
	
}
