package my.game;

import java.awt.Rectangle;

import javax.swing.JComponent;

public abstract class Entity extends JComponent {
	private static final long serialVersionUID = 1L;
	
	protected double x, y, velX, velY, width, height;
	protected ID id;
	
	public abstract void tick();
	
	public double getPosX() { return x; }
	public double getPosY() { return y; }
	public double getVelX() { return velX; }
	public double getVelY() { return velY; }
	public int getWidth() { return (int) width; }
	public int getHeight() { return (int) height; }
	public ID getID() { return id; }
	
	public void setX (double x) { this.x = x; }
	public void setY (double y) { this.y = y; }
	public void setVelX (double velX) { this.velX = velX; }
	public void setVelY (double velY) { this.velY = velY; }
	public void setWidth (double width) { this.width = width; }
	public void setHeight (double height) { this.height = height; }
	public void setID (ID id) { this.id = id; }
	
	//-- Other useful methods.
	public Rectangle getBounds() { return new Rectangle((int) x, (int) y, (int) width, (int) height); }
	
	
}
