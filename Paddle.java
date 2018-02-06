package my.game;

public class Paddle extends Entity {
	
	private static final long serialVersionUID = 1L;
	public static final int PADDLE_WIDTH = 200;
	
	public static boolean movingLeft = false, movingRight = false;
	
	public Paddle() {
		id = ID.Player;
		x = (Game.WIDTH / 2) + 200;
		y = Game.PADDLE_HEIGHT;
		height = 24;
		width = PADDLE_WIDTH;
		velY = 0;
		velX = 0;
	}
	
	public void tick() {
		//Set side boundaries
		if (x <= 0) x = 0;
		if (x >= (Game.WIDTH - 200)) x = Game.WIDTH - 201;
		
		//-- Update which direction the paddle is currently moving.
		if (velX > 0) {
			movingRight = true;
			movingLeft = false;
		} else if (velX < 0) {
			movingRight = false;
			movingLeft = true;
		}
		
		if (velX == 0)
			movingRight = movingLeft = false;
		
		x += velX;
		y += velY;
	}
	
}
