

/*

package com.android.eatemupgame;


//import java.awt.Frame;
//import java.awt.Graphics;
//import java.awt.Graphics2D;
//import java.awt.Image;

import com.android.framework.Image;
import com.android.framework.Graphics;

import com.android.framework.Game;
import com.android.framework.Input.TouchEvent;
import com.android.framework.Screen;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Vector;

public class Game extends Screen implements Runnable, Constants {
	Random random = new Random();
	Dino monty = new Dino(SCREEN_RESOLUTION_X / 2, SCREEN_RESOLUTION_Y / 2,
			SCREEN_RESOLUTION_X / 2, SCREEN_RESOLUTION_Y / 2, "ORANGE",
			DINO_SPEED); // monty shall not move at the beginning!

	// adding the color Map just temporary
	Map<Integer, String> randomColorMap = new HashMap<Integer, String>();
	{
		randomColorMap.put(0, "ORANGE");
		randomColorMap.put(1, "BROWN");
		randomColorMap.put(2, "GREEN");
		randomColorMap.put(3, "BLUE");
		randomColorMap.put(4, "RED");
	}
	Vector<Dino> enemies = new Vector<Dino>(); // enemies.add(new Dino());
	Background background = new Background(BACKGROUND_LEFT, BACKGROUND_TOP);
	Input input = new Input(monty);

	Draw drawInstance = new Draw();
	Calculation calculation = new Calculation(monty, enemies, background, input);
	Draw draw;

	private double game_over_counter = GAME_OVER_COUNTER;

	private Image image, dinoImageBreathing1, dinoImageBreathing2,
			dinoImageBreathing3, dinoImageWalking1, dinoImageWalking2,
			dinoImageWalking3, backgroundImage, Breathing1, Breathing2, Breathing3, Breathing4, Breathing5;
	private URL base; // for loading the images
	private Graphics second;
	private int imageCounter;

	//@Override
	public void start() {
		Thread thread = new Thread(this);
		thread.start();
	}

	//@Override
	public void init() {
		//setSize(SCREEN_RESOLUTION_X, SCREEN_RESOLUTION_Y);
		//setFocusable(true);
		//Frame frame = (Frame) this.getParent().getParent();
		//frame.setTitle("Eat Em Up");
		draw = new Draw(monty, enemies, background);

		addMouseListener(input);
		addMouseMotionListener(input);

		base = getDocumentBase();
		
		
		Breathing1 = getImage(base, "data/atmen 1.png");
		Breathing2 = getImage(base, "data/atmen 2.png");
		Breathing3 = getImage(base, "data/atmen 3.png");
		Breathing4 = getImage(base, "data/atmen 4.png");
		Breathing5 = getImage(base, "data/atmen 5.png");

		dinoImageBreathing1 = getImage(base, "data/dino_breathing.png");
		dinoImageBreathing2 = getImage(base, "data/dino_breathing2.png");
		dinoImageBreathing3 = getImage(base, "data/dino_breathing3.png");

		dinoImageWalking1 = getImage(base, "data/dino_walking1.png");
		dinoImageWalking2 = getImage(base, "data/dino_walking2.png");
		dinoImageWalking3 = getImage(base, "data/dino_walking3.png");

		backgroundImage = getImage(base, "data/test_background.png");

	}

	//@Override
	public void stop() {
		// super.stop();
	}

	//@Override
	public void destroy() {
		// super.destroy();
	}

	@Override
	public void run() {

		while (true) {

			if (enemies.size() < MAX_ENEMIES) // elements in the vector,
				// max amount of enemies

				if (random.nextInt(ENEMY_SPAWN_CHANCE) == 0) {
					addNewEnemy();
				}

			possibleDirChange();

			calculation.calculate();
			checkDeadDinos();
			repaint();

			try {
				Thread.sleep(17);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	//@Override
	public void update(Graphics g) {
		if (image == null) {
			image = createImage(this.getWidth(), this.getHeight());
			second = image.getGraphics();
		}

		second.setColor(getBackground());
		second.fillRect(0, 0, getWidth(), getHeight());
		second.setColor(getForeground());
		paint(second);

		g.drawImage(image, 0, 0, this);
	}

	//@Override
	public void paint(Graphics g) {
		drawBackground(g);
		drawEnemies(g);
		drawPlayer(g);
	}



*/