package my.game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

public class KeyInput extends KeyAdapter {
	
	private LinkedList<Entity> entList;
	
	public KeyInput(LinkedList<Entity> list) {
		entList = list;
	}
	
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		
		
		if (keyCode == KeyEvent.VK_RIGHT) {
			entList.get(0).setVelX(18);
		}
		if (keyCode == KeyEvent.VK_LEFT) {
			entList.get(0).setVelX(-18);
		}
		
		
		
		if (keyCode == KeyEvent.VK_ESCAPE || keyCode == KeyEvent.VK_P) {
			if (!Game.gameOver) {
				if (Game.timer.isRunning()) {
					Game.timer.stop();
					Game.pauseScreen.setVisible(true);
				} else {
					Game.pauseScreen.setVisible(false);
					Game.timer.start();
				}
			}
		}
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_RIGHT) {
			entList.get(0).setVelX(0);
		}
		if (key == KeyEvent.VK_LEFT) {
			entList.get(0).setVelX(0);
		}
	}

}
