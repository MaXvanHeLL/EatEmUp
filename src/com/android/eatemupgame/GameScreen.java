package com.android.eatemupgame;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Vector;

import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import com.android.framework.Game;
import com.android.framework.Graphics;
import com.android.framework.Image;
import com.android.framework.Input.TouchEvent;
import com.android.framework.Screen;
import com.android.eatemupgame.Assets;
import com.android.eatemupgame.GameScreen.GameState;

public class GameScreen extends Screen implements Constants {
	enum GameState {
		Ready, Running, Paused, GameOver
	}

	GameState state = GameState.Ready;

	// Variable Setup
	Paint paint, paint2;
	Random random;
	Dino monty;

	// adding the color Map just temporary
	Map<Integer, String> randomColorMap;

	Vector<Dino> enemies;
	Background background;
	// Input input;
	Draw drawInstance;
	Calculation calculation;
	Draw draw;
	private float game_over_counter;

	private Image backgroundImage, image, dinoImageBreathing1,
			dinoImageBreathing2, dinoImageBreathing3, dinoImageWalking1,
			dinoImageWalking2, dinoImageWalking3, Breathing1, Breathing2,
			Breathing3, Breathing4, Breathing5;
	private URL base; // for loading the images
	private Graphics second;
	private int score;

	public GameScreen(Game game) {
		super(game);

		// Initialize game objects here

		random = new Random();
		monty = new Dino(SCREEN_RESOLUTION_X / 2, SCREEN_RESOLUTION_Y / 2,
				SCREEN_RESOLUTION_X / 2, SCREEN_RESOLUTION_Y / 2, "ORANGE",
				DINO_SPEED); // monty shall not move at the beginning!

		// adding the color Map just temporary
		randomColorMap = new HashMap<Integer, String>();
		{
			randomColorMap.put(0, "ORANGE");
			randomColorMap.put(1, "BROWN");
			randomColorMap.put(2, "GREEN");
			randomColorMap.put(3, "BLUE");
			randomColorMap.put(4, "RED");
		}
		enemies = new Vector<Dino>();
		background = new Background(BACKGROUND_LEFT, BACKGROUND_TOP);
		calculation = new Calculation(monty, enemies, background);

		game_over_counter = GAME_OVER_COUNTER_MAX;
		score = 0;

		backgroundImage = Assets.background;

		paint = new Paint();
		paint.setTextSize(30);
		paint.setTextAlign(Paint.Align.CENTER);
		paint.setAntiAlias(true);
		paint.setColor(Color.WHITE);

		paint2 = new Paint();
		paint2.setTextSize(100);
		paint2.setTextAlign(Paint.Align.CENTER);
		paint2.setAntiAlias(true);
		paint2.setColor(Color.WHITE);
	}

	@Override
	public void update(float deltaTime) {
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();

		// We have four separate update methods in this example.
		// Depending on the state of the game, we call different update methods.
		// Refer to Unit 3's code. We did a similar thing without separating the
		// update methods.

		if (state == GameState.Ready)
			updateReady(touchEvents);
		if (state == GameState.Running)
			updateRunning(touchEvents, deltaTime);
		if (state == GameState.Paused)
			updatePaused(touchEvents);
		if (state == GameState.GameOver)
			updateGameOver(touchEvents);
	}

	private void updateReady(List<TouchEvent> touchEvents) {

		// This example starts with a "Ready" screen.
		// When the user touches the screen, the game begins.
		// state now becomes GameState.Running.
		// Now the updateRunning() method will be called!

		if (touchEvents.size() > 0)
			state = GameState.Running;
	}

	private void updateRunning(List<TouchEvent> touchEvents, float deltaTime) {

		int len = touchEvents.size();

		Log.d("touch events", Integer.toString(len));

		for (int i = 0; i < len; i++) {

			TouchEvent event = touchEvents.get(i);

			// game.getInput().isTouchDown(i)
			if (game.getInput().isTouchDown(i) || len > 0) {
				// Log.d("blalba", "true");

				monty.setMoveToX(event.x - SCREEN_RESOLUTION_X / 2);
				monty.setMoveToY(event.y - SCREEN_RESOLUTION_Y / 2);

				if (monty.getStatus() == STATUS.IDLE)
					monty.setStatus(STATUS.WALK);
			} else if (monty.getStatus() == STATUS.WALK)
				monty.setStatus(STATUS.IDLE);
			if (event.type == TouchEvent.TOUCH_UP) {
				if (inBounds(event, 700, 0, 800, 100)
						&& Assets.theme.isPlaying()) {
					Log.d("YYYYYY", "OOOOOOOFFFFFF");
					Assets.theme.stop();
				}

				else if (inBounds(event, 700, 0, 800, 100)
						&& Assets.theme.isStopped()) {
					Log.d("YYYYYY", "OOOOOOOONNNNNNN");
					Assets.theme.play();
				}
			}
		}

		if (enemies.size() < MAX_ENEMIES) // max amount of enemies
		{

			if (enemies.size() < 1)
				addNewEnemy();

			if (enemies.size() < MIN_ENEMIES)
				if (random.nextInt(ENEMY_SPAWN_CHANCE_MIN) == 0)
					addNewEnemy();

			if (random.nextInt(ENEMY_SPAWN_CHANCE) == 0)
				addNewEnemy();

		}
		possibleDirChange();
		calculation.calculate();
		checkDeadDinos();
	}

	private boolean inBounds(TouchEvent event, int x, int y, int width,
			int height) {
		if (event.x > x && event.x < x + width - 1 && event.y > y
				&& event.y < y + height - 1)
			return true;
		else
			return false;
	}

	private void updatePaused(List<TouchEvent> touchEvents) {
		int len = touchEvents.size();

		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_UP) {
				if (inBounds(event, 0, 0, 800, 240)) {

					if (!inBounds(event, 0, 0, 35, 35)) {
						resume();
					}
				}

				if (inBounds(event, 0, 240, 800, 240)) {
					nullify();
					goToMenu();
				}
			}
		}
	}

	private void updateGameOver(List<TouchEvent> touchEvents) {
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_DOWN) {
				if (inBounds(event, 0, 0, 800, 480)) {
					// nullify();
					game.setScreen(new MainMenuScreen(game));
					return;
				}
			}
		}

	}

	public int changeDirection() {
		int direction = random.nextInt(100) - 50; // -50 - 49
		if (direction < 0)
			return direction - 50;
		else
			return direction + 51; // off by one!
	}

	public int newSpawnPosX() {
		int newPositionX = random.nextInt(SCREEN_RESOLUTION_X
				+ ENEMY_SPAWN_DISTANCE_FROM_SCREEN * 2
				+ ENEMY_SPAWN_DISTANCE_AREA * 2);

		newPositionX = newPositionX - ENEMY_SPAWN_DISTANCE_FROM_SCREEN
				- ENEMY_SPAWN_DISTANCE_AREA - background.getPosX();

		return newPositionX;
	}

	public int newSpawnPosY() {
		int newPositionY = random.nextInt(SCREEN_RESOLUTION_Y
				+ ENEMY_SPAWN_DISTANCE_FROM_SCREEN * 2
				+ ENEMY_SPAWN_DISTANCE_AREA * 2);
		newPositionY = newPositionY - ENEMY_SPAWN_DISTANCE_FROM_SCREEN
				- ENEMY_SPAWN_DISTANCE_AREA - background.getPosY();

		return newPositionY;
	}

	public boolean checkPosition(int newPositionX, int newPositionY) {
		return (newPositionX < -ENEMY_SPAWN_DISTANCE_FROM_SCREEN
				|| newPositionX > SCREEN_RESOLUTION_X
						+ ENEMY_SPAWN_DISTANCE_FROM_SCREEN
				|| newPositionY < -ENEMY_SPAWN_DISTANCE_FROM_SCREEN || newPositionY > SCREEN_RESOLUTION_Y
				+ ENEMY_SPAWN_DISTANCE_FROM_SCREEN);
	}

	public int changeMovingDirX(int newPositionX, int newMoveToX) {
		if (newPositionX < background.getPosX() && newMoveToX < 0)
			newMoveToX *= -1;
		if (newPositionX > background.getPosX() && newMoveToX > 0)
			newMoveToX *= -1;

		return newMoveToX;
	}

	public int changeMovingDirY(int newPositionY, int newMoveToY) {
		if (newPositionY < background.getPosY() && newMoveToY < 0)
			newMoveToY *= -1;
		if (newPositionY > background.getPosY() && newMoveToY > 0)
			newMoveToY *= -1;

		return newMoveToY;
	}

	public void addNewEnemy() {
		// calculating valid enemy spawn positions
		int newPositionX = 0;
		int newPositionY = 0;

		boolean posOk = false;
		while (!posOk) {

			newPositionX = newSpawnPosX();
			newPositionY = newSpawnPosY();

			if (checkPosition(newPositionX, newPositionY))
				posOk = true;
		}

		newPositionX -= background.getPosX();
		newPositionY -= background.getPosY();

		int newMoveToX = changeDirection();
		int newMoveToY = changeDirection();

		newMoveToX = changeMovingDirX(newPositionX, newMoveToX);
		newMoveToY = changeMovingDirY(newPositionY, newMoveToY);

		enemies.add(new Dino(newPositionX, newPositionY, newMoveToX,
				newMoveToY, randomColorMap.get(random.nextInt(4)), random
						.nextInt(DINO_SPEED - MIN_ENEMY_SPEED - 1)
						+ MIN_ENEMY_SPEED));
	}

	public void possibleDirChange() {
		for (int vecIndex = 0; vecIndex < enemies.size(); vecIndex++) {
			Dino curEnemy = enemies.elementAt(vecIndex);
			if (curEnemy == null)
				continue;

			// chance to change direction randomly
			if (random.nextInt(ENEMY_CHANGE_DIRECTION_CHANCE) == 0) {
				curEnemy.setMoveToX(curEnemy.getPosX() + changeDirection());
				curEnemy.setMoveToY(curEnemy.getPosY() + changeDirection());
			}
		}
	}

	public void checkDeadDinos() {
		checkEnemyDespawn();

		// Log.d("AAAAAAAAAAAAAAAAAAAAAAAAA",
		// (Float.toString(game_over_counter)));

		game_over_counter -= 0.1;

		if (game_over_counter <= 0) {
			game_over_counter = 0;
			monty.setStatus(STATUS.DIE);
		}
	}

	public void checkEnemyDespawn() {
		for (int vecIndex = 0; vecIndex < enemies.size(); vecIndex++) {
			Dino curEnemy = enemies.elementAt(vecIndex);

			checkEnemyEaten(curEnemy);
			despawnEnemyDistance(curEnemy);
		}
	}

	public void checkEnemyEaten(Dino curEnemy) {
		int x = curEnemy.getPosX() - monty.getPosX() + background.getPosX();
		int y = curEnemy.getPosY() - monty.getPosY() + background.getPosY();
		int r = (int) Math.sqrt(x * x + y * y);
		if (r < EAT_RADIUS && curEnemy.getStatus() != STATUS.DIE) {
			score++;
			curEnemy.setStatus(STATUS.DIE);
			game_over_counter += GAME_OVER_COUNTER_BONUS;
			if (game_over_counter > GAME_OVER_COUNTER_MAX)
				game_over_counter = GAME_OVER_COUNTER_MAX;
		}
	}

	public void despawnEnemyDistance(Dino curEnemy) {
		// despawn enemies
		if (curEnemy.getPosX() < -ENEMY_DESPAWN_DISTANCE_FROM_SCREEN
				- background.getPosX()
				|| curEnemy.getPosX() > ENEMY_DESPAWN_DISTANCE_FROM_SCREEN
						+ SCREEN_RESOLUTION_X - background.getPosX()
				|| curEnemy.getPosY() < -ENEMY_DESPAWN_DISTANCE_FROM_SCREEN
						- background.getPosY()
				|| curEnemy.getPosY() > ENEMY_DESPAWN_DISTANCE_FROM_SCREEN
						+ SCREEN_RESOLUTION_Y - background.getPosY()) {
			enemies.removeElement(curEnemy);
		}
	}

	public void drawBackground(Graphics g) {

		int bgCoordX = background.getPosX();
		int bgCoordY = background.getPosY();
		int counterX;
		int counterY;

		// draw 9x9 background field

		for (counterX = -1; counterX <= 1; counterX++)
			for (counterY = -1; counterY <= 1; counterY++)
				g.drawImage(backgroundImage, bgCoordX
						+ (BACKGROUND_RESOLUTION_X + 1) * counterX, bgCoordY
						+ (BACKGROUND_RESOLUTION_Y + 1) * counterY);
	}

	public void drawEnemies(Graphics g) {

		for (int vecIndex = 0; vecIndex < enemies.size(); vecIndex++) {
			Dino curEnemy = enemies.elementAt(vecIndex);
			if (curEnemy == null)
				continue;

			double angle = calcAngle(curEnemy.getAngle());

			Image drawImage = selectPlayerImage(curEnemy);
			g.drawRotatedImage(drawImage,
					curEnemy.getPosX() + background.getPosX(),
					curEnemy.getPosY() + background.getPosY(), (int) angle);
		}
	}

	public void drawPlayer(Graphics g) {
		double angle = calcAngle(monty.getAngle());

		Image drawImage = selectPlayerImage(monty);
		g.drawRotatedImage(drawImage, monty.getPosX(), monty.getPosY(),
				(int) angle);
	}

	private double calcAngle(double Angle) {
		return (195 + ((Angle + Math.PI) / (2 * Math.PI)) * 360) % 360;
	}

	public Image selectPlayerImage(Dino dino) {
		// imageCounter = drawInstance.getDrawImageCounter();
		int imageCounter = dino.getImageCounter();

		// set back the draw Counter for different pictures
		if (imageCounter == 100) {
			if (dino == monty && dino.getStatus() == STATUS.DIE) {
				state = GameState.GameOver;
				return Assets.dyingOrange2;
			}

			if (dino != monty && dino.getStatus() == STATUS.DIE) {
				enemies.removeElement(dino);
				return Assets.dyingOrange2;
			}

			if (dino == monty && dino.getStatus() == STATUS.ATTACK)
				dino.setStatus(STATUS.IDLE);

			dino.setImageCounter(0);
		}

		imageCounter = dino.getImageCounter();
		imageCounter++;
		dino.setImageCounter(imageCounter);

		if (dino == monty) {

			// draw player idle
			if (dino.getStatus() == STATUS.IDLE) {
				if (imageCounter >= 0 && imageCounter <= 49)
					return Assets.breathingOrange1;

				if (imageCounter >= 50 && imageCounter <= 100)
					return Assets.breathingOrange2;

			}

			// draw player walking
			if (dino.getStatus() == STATUS.WALK) {
				if (imageCounter >= 0 && imageCounter <= 49)
					return Assets.walkingOrange1;

				if (imageCounter >= 50 && imageCounter <= 100)
					return Assets.walkingOrange2;
			}

			// draw player attacking
			if (dino.getStatus() == STATUS.ATTACK) {
				if (imageCounter >= 0 && imageCounter <= 49)
					return Assets.attackingOrange1;

				if (imageCounter >= 50 && imageCounter <= 100)
					return Assets.attackingOrange2;
			}

			// draw player dying
			if (dino.getStatus() == STATUS.DIE) {
				if (imageCounter >= 0 && imageCounter <= 49)
					return Assets.dyingOrange1;

				if (imageCounter >= 50 && imageCounter <= 100)
					return Assets.dyingOrange2;
			}

		}

		if (dino != monty) {

			if (dino.getStatus() == STATUS.IDLE)
				dino.setStatus(STATUS.WALK);

			// draw player walking
			if (dino.getStatus() == STATUS.WALK) {
				if (imageCounter >= 0 && imageCounter <= 49)
					return Assets.walkingGreen1;

				if (imageCounter >= 50 && imageCounter <= 100)
					return Assets.walkingGreen2;
			}

			// draw player dying
			if (dino.getStatus() == STATUS.DIE) {
				if (imageCounter >= 0 && imageCounter <= 49)
					return Assets.dyingGreen1;

				if (imageCounter >= 50 && imageCounter <= 100)
					return Assets.dyingGreen1;
			}
		}

		return Assets.dyingOrange2;

	}

	private void drawReadyUI() {
		Graphics g = game.getGraphics();

		g.drawARGB(155, 0, 0, 0);
		g.drawString("Tap to Start Monty`s Adventure!.", 400, 240, paint);

	}

	private void drawRunningUI() {
		Graphics g = game.getGraphics();

		float hunger = game_over_counter;

		g.drawRect(30, 30, ((int) hunger) * 2, 15, -16711936);
		g.drawLine(28, 28, (int) GAME_OVER_COUNTER_MAX * 2 + 2 + 30, 28,
				-16777216);
		g.drawLine(28, 45, (int) GAME_OVER_COUNTER_MAX * 2 + 2 + 30, 45,
				-16777216);
		g.drawLine(28, 28, 28, 45, -16777216);
		g.drawLine((int) GAME_OVER_COUNTER_MAX * 2 + 2 + 30, 28,
				(int) GAME_OVER_COUNTER_MAX * 2 + 2 + 30, 45, -16777216);

	}

	private void drawPausedUI() {
		Graphics g = game.getGraphics();
		// Darken the entire screen so you can display the Paused screen.
		g.drawARGB(155, 0, 0, 0);
		g.drawString("Resume", 400, 165, paint2);
		g.drawString("Menu", 400, 360, paint2);

	}

	private void drawGameOverUI() {
		Graphics g = game.getGraphics();
		g.drawRect(0, 0, 1281, 801, Color.BLACK);
		g.drawString("GAME OVER.", 400, 240, paint2);
		g.drawString("Tap to return.", 400, 290, paint);

	}

	@Override
	public void paint(float deltaTime) {

		Graphics g = game.getGraphics();

		drawBackground(g);
		drawEnemies(g);
		drawPlayer(g);
		g.drawImage(Assets.musicButton, 730, 20);

		if (state == GameState.Ready)
			drawReadyUI();
		if (state == GameState.Running)
			drawRunningUI();
		if (state == GameState.Paused)
			drawPausedUI();
		if (state == GameState.GameOver)
			drawGameOverUI();

	}

	@Override
	public void pause() {
		if (state == GameState.Running)
			state = GameState.Paused;

	}

	@Override
	public void resume() {
		if (state == GameState.Paused)
			state = GameState.Running;
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void backButton() {
		pause();
	}

	private void nullify() {

		// Set all variables to null. You will be recreating them in the
		// constructor.
		/*
		 * paint = null; bg1 = null; bg2 = null; robot = null; hb = null; hb2 =
		 * null; currentSprite = null; character = null; character2 = null;
		 * character3 = null; heliboy = null; heliboy2 = null; heliboy3 = null;
		 * heliboy4 = null; heliboy5 = null; anim = null; hanim = null;
		 */

		// Call garbage collector to clean up memory.
		System.gc();
	}

	private void goToMenu() {
		// TODO Auto-generated method stub
		game.setScreen(new MainMenuScreen(game));

	}

}