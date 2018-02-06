package my.game;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Game implements ActionListener {

	public static final int WIDTH = 1600, HEIGHT = 900;
	public static final int PADDLE_HEIGHT = HEIGHT - 140;
	public static Game game;
	public static LinkedList<Entity> entList;
	
	public static JFrame window;
	public static Canvas canvas;
	public static PauseScreen pauseScreen;
	private Spawner spawner;
	private Paddle player;
	private Ball ball;
	public static Timer timer;
	
	public static int lives = 5;                 //-- remaining lives in this game
	public static int score = 0;                 //-- Duh.
	public static int scoreCount = 0;            //-- Makes score increase every so-many ticks.
	public static int levelLength = 20;          //-- Length of the level (doesn't change).
	public static int levelTimer = levelLength;  //-- Time left in this level (changes as a countdown in seconds remaining)
	public static int currentLevel = 1;          //-- Duh.
	public static boolean gameOver = false;      //-- More duh.
	
	
	//-- These are just used for debugging. Ignore please :) --\\
	public static int GAME_COUNT = 0; //-- Ticks that the game has been running.
	public static int RUNTIME = 0;    //-- Seconds that the game has been running.
	public static int secondizer = 0; //-- Disposable int that is used to calculate the seconds.
	//-- --//
	public static boolean running = false;
	
	public Game() {
		window = new JFrame("Lob Pong 2017 -- Laurance Case");
		entList = new LinkedList<Entity>();
		canvas = new Canvas();
		pauseScreen = new PauseScreen();
		spawner = new Spawner();
		
		
		canvas.setBackground(new Color(163, 184, 241));
		canvas.setFocusable(true);
		canvas.requestFocus();
		canvas.addKeyListener(new KeyInput(entList));
		
		player = new Paddle();
		ball   = new Ball();
		
		
		entList.add(player);
		entList.add(ball);
		
		
		window.add(canvas);
		//window.add(pauseScreen);
		
		initWindow();
		
		timer = new Timer((1000/60), this); //-- for 60 fps framerate
		
		window.setVisible(true);
		
		timer.start();
	}
	
	public void actionPerformed(ActionEvent e) { //--Timer callback, essentially the whole-game tick method.
		// make all objects tick before rendering the scene.
		for(int i = 0; i < entList.size(); i++) {
			Entity temp = entList.get(i);
			
			temp.tick();
			//-- If over 300px offscreen in any direction, remove the entity from the list.
			
			if (i > 1) { //Exclude ball and paddle from off-screen deletion
				if (temp.getPosX() < -500 || temp.getPosX() > Game.WIDTH+500 || temp.getPosY() < -500 || temp.getPosY() > Game.HEIGHT+500)
					entList.remove(temp);
			}
		}
		
		spawner.tick();
		
		canvas.repaint();
		
		//-- Scoring the game
		scoreCount++;
		if (scoreCount == 10) {
			score++;
			scoreCount = 0;
		}
		
		if (GAME_COUNT % 60 == 0) levelTimer--; //Timer countdown every second.
		
		if (levelTimer == 0) {  //-- level timer reaches 0, reset timer and increment the level.
			levelTimer = levelLength;
			currentLevel++;
		}
		
		//-- Debugging. Ignore everything below this point in this method.
		GAME_COUNT++;
		secondizer++;
		if (secondizer >= 60) secondizer = 0;
		
		RUNTIME = GAME_COUNT/60;
		
		/*
		//for debugging: print loops thru game loop
		if (secondizer == 0) {
			System.out.println("Runtime: " + RUNTIME + " sec; Entities in existence: " + entList.size());
			System.out.println("Timer rem. in level: " + levelTimer);
		} */
		
	}
	
	public void initWindow() {
		window.setSize(WIDTH, HEIGHT);
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
	}
	
	
	public static void main(String[] args) {
		try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } 
	    catch (UnsupportedLookAndFeelException|ClassNotFoundException|InstantiationException|IllegalAccessException e) {}
		
		game = new Game();
	}

}
