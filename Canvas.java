package my.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.LinkedList;

import javax.swing.JPanel;

public class Canvas extends JPanel {
	private static final long serialVersionUID = -4228217111148620802L;
	
	private LinkedList<Entity> entList = new LinkedList<Entity>();
	
	public static final Color c = new Color(9, 1, 25);
	public static final Color purple = new Color(201, 103, 234);
	public static final Color ballColor = new Color(74, 7, 175);
	public static final Color timerColor = new Color(65, 244, 130);
	
	public static Color livesColor = Color.black;
	
	private static final Font body = new Font("Verdana", Font.PLAIN, 30);
	private static final Font small = new Font("Lucida Sans", Font.PLAIN, 26);
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		
		//Update items in the canvas' list of entities. player will always be 0.
		getEnts(Game.entList);
		
		for(int i = 0; i < entList.size(); i++) {
			Entity tempEnt = entList.get(i);
			switch(tempEnt.getID()) {
			
			//-- Paddle and ball drawing.
				case Player:
					g.setColor(Color.black);
					g.fillRoundRect((int) tempEnt.getPosX(), (int) tempEnt.getPosY(), tempEnt.getWidth(), tempEnt.getHeight(), 8, 8);
					break;
					
				case Ball:
					g.setColor(ballColor);
					g.fillOval((int) tempEnt.getPosX(), (int) tempEnt.getPosY(), tempEnt.getWidth(), tempEnt.getHeight());
					break;
					
			//-- Enemies
				case BasicUFO:
					g.setColor(Color.orange);
					if (BasicUFO.img != null)
						// If image was successfully loaded, draw it.
						g.drawImage(BasicUFO.img, (int) tempEnt.getPosX(), (int) tempEnt.getPosY(), null);
					else
						//Otherwise, draw a rectangle with its dimensions.
						g.fillRect((int) tempEnt.getPosX(), (int) tempEnt.getPosY(), 149, 56);
					break;
					
				case BasicUFOBullet:
					g.setColor(purple);
					if (BasicUFOBullet.img != null)
						//-- if image was successfully loaded, draw it.
						g.drawImage(BasicUFOBullet.img, (int) tempEnt.getPosX(), (int) tempEnt.getPosY(), null);
					else
						//-- otherwise, draw a filled circle.
						g.fillOval((int) tempEnt.getPosX(), (int) tempEnt.getPosY(), tempEnt.getWidth(), tempEnt.getHeight());
					break;
					
			//-- Health packs
				case HealthPack:
					g.setColor(Color.green);
					if(HealthPack.img != null)
						g.drawImage(HealthPack.img, (int) tempEnt.getPosX(), (int) tempEnt.getPosY(), null);
					else
						g.fillRect((int) tempEnt.getPosX(), (int) tempEnt.getPosY(), tempEnt.getWidth(), tempEnt.getHeight());
					break;
					
				case BonusStar:
					g.setColor(Color.yellow);
					if(BonusStar.img != null)
						g.drawImage(BonusStar.img, (int) tempEnt.getPosX(), (int) tempEnt.getPosY(), null);
					else
						g.fillRect((int) tempEnt.getPosX(), (int) tempEnt.getPosY(), tempEnt.getWidth(), tempEnt.getHeight());
					break;
					
				default:
					//-- Catch unidentified entities
					System.err.println("Undefined or unassigned ent. ID on entity w/ entlist index: " + i + ".");
					System.err.flush();
					break;
			}
			
			//-- Painting the timer, score, health, etc...
			//-- This is done after entities so you can always see them.
			
			g.setFont(body);
			g.setColor(Color.black);
			g.drawString("Score: " + Game.score, 15, 35);
			g.setColor(livesColor);
			g.drawString("Lives: " + Game.lives, 15, 70);
			
			String currentLevel = "Level " + Game.currentLevel;
			g.setFont(body);
			g.drawString(currentLevel, Game.WIDTH - (g.getFontMetrics().stringWidth(currentLevel) + 25), 45);
			
			//-- current time left in level:
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect((Game.WIDTH/2) - 150, 25, 300, 42);
			g.setColor(Color.blue);
			g.setColor(timerColor);
			g.fillRect((Game.WIDTH/2) - 149, 26, (300/Game.levelLength) * Game.levelTimer, 40);
			g.setColor(Color.black);
			g.setFont(small);
			g.drawString("Time left in level: " + Game.levelTimer + "s", (Game.WIDTH/2) - 140, 55);
			
			if (Game.lives == 0) {
				Game.gameOver = true;
				Game.timer.stop();
				String go = "game over!";
				g.setFont(new Font("Lucida Sans", Font.PLAIN, 60));
				g.drawString(go, (Game.WIDTH/2)-(g.getFontMetrics().stringWidth(go)/2), (Game.HEIGHT/2)-(g.getFontMetrics().getHeight()/2));
				g.setFont(body);
				String fs = "final score: " + (Game.score * Game.currentLevel);
				g.drawString(fs, (Game.WIDTH/2)-(g.getFontMetrics().stringWidth(fs)/2), 500);
			}
		}
	}
	
	
	//-- method for getting the entire list of entities in the game.
	public void getEnts(LinkedList<Entity> list) { this.entList = list; }
	
	
}
