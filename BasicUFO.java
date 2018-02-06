package my.game;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class BasicUFO extends Entity {
	private static final long serialVersionUID = 1L;
	
	private Random r = new Random();
	public static BufferedImage img = null;
	
	public BasicUFO() {
		
		id = ID.BasicUFO;
		
		x = -200;
		y = r.nextInt((int) (Game.HEIGHT - 400));
		
		width = 56;
		height = 149;
		
		velX = (5 * r.nextDouble()) + 3;
		
		try {
			//Dimensions are inherited from the png
			img = ImageIO.read(new File("resources/ufo1.png"));
		} catch (IOException e) {
			System.err.println("UFO image file not found! Substituting...");
			System.err.flush();
		}
	}

	@Override
	public void tick() {		
		if (this.getBounds().intersects(Game.entList.get(1).getBounds())) {
			//Check ball-intersection
			
			Game.entList.remove(this);
			Game.lives--;
			
			Game.entList.get(1).setVelX(r.nextInt(10) - 5);
		}
		
		x += velX;
		y += velY;
	}

}
