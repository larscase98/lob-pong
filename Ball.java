package my.game;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Ball extends Entity {

	private static final long serialVersionUID = 1L;

	private static final int ballMeasure = 36; //-- the width and height of the circular ball.
	
	protected static double ticks = 0; //-- ticks since last bounce.
	protected static double t = 0;     //-- seconds since last bounce for gravity calculation.
	protected static double lastVel;   //-- saves the last yvel of the last bounce.
	
	private static double startX = Game.WIDTH - (ballMeasure + 100), startY = (ballMeasure + 25), startVelX = -3, startVelY = 1;
	
	private Clip clip = null;
	private AudioInputStream ais = null;
	
	public Ball() {
		id = ID.Ball;
		
		width = height = ballMeasure;
		
		x = startX;
		y = startY;
		
		velX = startVelX;
		velY = startVelY;
		
		lastVel = velY;
		
		//--Bounce sound effect		
		try { clip = AudioSystem.getClip(); } catch (LineUnavailableException e) { e.printStackTrace(); }
		
		try { ais = AudioSystem.getAudioInputStream(new File("resources/bounce.wav")); } catch (UnsupportedAudioFileException | IOException e) { e.printStackTrace(); }
		try { clip.open(ais); } catch (LineUnavailableException | IOException e) { e.printStackTrace(); }
		
	}
	
	public void tick() {
		velY = lastVel - (9.8 * t); //-- this is the gravity calculation. we don't need all that math since we know the explicit bounce velocity.
		
		//Check paddle intersection for bounce back
		if (Game.entList.get(0).getBounds().intersects(getBounds())) {
			
			velY *= -1;
			ticks = -1;
			
			lastVel = velY - 0.3286; // to make ball not lose any energy on the bounce.
			
			//-- If paddle is moving left, add left ball velocity. if right, add right. 
			if (Paddle.movingLeft) velX -= 2;
			if (Paddle.movingRight) velX += 2;
			
			//-- Bounce sound effect
			clip.setMicrosecondPosition(0);
			clip.start();
		}
		
		
		//-- Bounce off ceiling
		if (y <= 1) {
			velY *= -1;
			y = 2;
		}
		
		//-- Bounce off walls.
		if (x < 0 || x > Game.WIDTH-ballMeasure) velX *= -1;
		
		//-- if ball fell through bottom, reset to launch point.
		if (y >= Game.HEIGHT + 200) {
			ticks = -1;
			
			x = startX;
			y = startY;
			
			velX = startVelX;
			velY = startVelY;
			
			lastVel = startVelY;
			
			Game.lives--;
		}
		
		
		x += velX;
		y -= velY;
		
		ticks++;
		t = ticks / 60;
	}

}
