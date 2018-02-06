package my.game;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class BasicUFOBullet extends Entity {
	private static final long serialVersionUID = 1L;
	
	private Random r = new Random();
	public static BufferedImage img = null;
	
	public BasicUFOBullet(BasicUFO parent) {
		id = ID.BasicUFOBullet;
		
		x = parent.getPosX() + (parent.getWidth() / 2);
		y = parent.getPosY() + (parent.getHeight() / 2);
		
		width = height = 20;
		
		try {
			img = ImageIO.read(new File("resources/orb1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//-- Super basic firing technique to shoot this entity towards the correct quadrant (up-right, up-left, down-right, down-left) at 4 px/tick.
		if (Game.entList.get(1).getPosX() > getPosX()) velX = (r.nextInt(2) + 1); 
		else velX = -(r.nextInt(2) + 1);
		
		if (Game.entList.get(1).getPosY() > getPosY()) velY = (r.nextInt(2) + 1);
		else velY = -(r.nextInt(2) + 1);
		
		
		
		//velX = r.nextInt(10) - 5;
		//velY = r.nextInt(10) - 5;
		
		//if (velX == 0) velX = 1;
		//if (velY == 0) velY = 1;
	}

	@Override
	public void tick() {
		if(Game.entList.get(1).getBounds().intersects(getBounds())) {
			Game.score -= 100;
			Game.entList.remove(this);
		}
		
		x += velX;
		y += velY;
	}
	
}
