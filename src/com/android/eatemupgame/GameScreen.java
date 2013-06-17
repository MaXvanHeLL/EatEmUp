package com.android.eatemupgame;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Vector;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.util.Log;

import com.android.framework.Game;
import com.android.framework.Graphics;
import com.android.framework.Image;
import com.android.framework.Input.TouchEvent;
import com.android.framework.Screen;

public class GameScreen extends Screen implements Constants {
	enum GameState {
		Ready, Running, Paused, GameOver
	}

	GameState state = GameState.Ready;

	// Variable Setup

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
	private double game_over_counter;

	private Image backgroundImage, image, dinoImageBreathing1,
			dinoImageBreathing2, dinoImageBreathing3, dinoImageWalking1,
			dinoImageWalking2, dinoImageWalking3, Breathing1, Breathing2,
			Breathing3, Breathing4, Breathing5;
	private URL base; // for loading the images
	private Graphics second;
	private int imageCounter;

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
		enemies = new Vector<Dino>(); // enemies.add(new Dino());
		background = new Background(BACKGROUND_LEFT, BACKGROUND_TOP);
		// input = new Input(monty);

		drawInstance = new Draw();

		calculation = new Calculation(monty, enemies, background// , input
		);

		game_over_counter = GAME_OVER_COUNTER;

		backgroundImage = Assets.background;
	}

	/*
	 * private void loadMap() { ArrayList lines = new ArrayList(); int width =
	 * 0; int height = 0;
	 * 
	 * Scanner scanner = new Scanner(SampleGame.map); while
	 * (scanner.hasNextLine()) { String line = scanner.nextLine();
	 * 
	 * // no more lines to read if (line == null) { break; }
	 * 
	 * if (!line.startsWith("!")) { lines.add(line); width = Math.max(width,
	 * line.length());
	 * 
	 * } } height = lines.size();
	 * 
	 * for (int j = 0; j < 12; j++) { String line = (String) lines.get(j); for
	 * (int i = 0; i < width; i++) {
	 * 
	 * if (i < line.length()) { char ch = line.charAt(i); Tile t = new Tile(i,
	 * j, Character.getNumericValue(ch)); tilearray.add(t); }
	 * 
	 * } }
	 * 
	 * }
	 */

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
		for (int i = 0; i < len; i++) {

			TouchEvent event = touchEvents.get(i);

			if (game.getInput().isTouchDown(i)) {
				Log.d("blalba", Integer.toString(event.x));

				// Log.d("blalba", Integer.toString(event.x));

				monty.setMoveToX(event.x - SCREEN_RESOLUTION_X / 2);
				monty.setMoveToY(event.y - SCREEN_RESOLUTION_Y / 2);

				if (monty.getStatus() != STATUS.ATTACK)
					monty.setStatus(STATUS.WALK);
			} else
				monty.setStatus(STATUS.IDLE);
		}

		// This is identical to the update() method from our Unit 2/3 game.

		if (enemies.size() < MAX_ENEMIES) // elements in the vector,
			// max amount of enemies

			if (random.nextInt(ENEMY_SPAWN_CHANCE) == 0) {
				addNewEnemy();
			}

		possibleDirChange();

		calculation.calculate();
		checkDeadDinos();

		/*
		 * // 1. All touch input is handled here: int len = touchEvents.size();
		 * for (int i = 0; i < len; i++) { TouchEvent event =
		 * touchEvents.get(i); if (event.type == TouchEvent.TOUCH_DOWN) {
		 * 
		 * if (inBounds(event, 0, 285, 65, 65)) { robot.jump(); currentSprite =
		 * anim.getImage(); robot.setDucked(false); }
		 * 
		 * else if (inBounds(event, 0, 350, 65, 65)) {
		 * 
		 * if (robot.isDucked() == false && robot.isJumped() == false &&
		 * robot.isReadyToFire()) { robot.shoot(); } }
		 * 
		 * else if (inBounds(event, 0, 415, 65, 65) && robot.isJumped() ==
		 * false) { currentSprite = Assets.characterDown; robot.setDucked(true);
		 * robot.setSpeedX(0);
		 * 
		 * }
		 * 
		 * if (event.x > 400) { // Move right. robot.moveRight();
		 * robot.setMovingRight(true);
		 * 
		 * }
		 * 
		 * }
		 * 
		 * if (event.type == TouchEvent.TOUCH_UP) {
		 * 
		 * if (inBounds(event, 0, 415, 65, 65)) { currentSprite =
		 * anim.getImage(); robot.setDucked(false);
		 * 
		 * }
		 * 
		 * if (inBounds(event, 0, 0, 35, 35)) { pause();
		 * 
		 * }
		 * 
		 * if (event.x > 400) { // Move right. robot.stopRight(); } }
		 * 
		 * }
		 * 
		 * // 2. Check miscellaneous events like death:
		 * 
		 * if (livesLeft == 0) { state = GameState.GameOver; }
		 * 
		 * // 3. Call individual update() methods here. // This is where all the
		 * game updates happen. // For example, robot.update(); robot.update();
		 * if (robot.isJumped()) { currentSprite = Assets.characterJump; } else
		 * if (robot.isJumped() == false && robot.isDucked() == false) {
		 * currentSprite = anim.getImage(); }
		 * 
		 * ArrayList projectiles = robot.getProjectiles(); for (int i = 0; i <
		 * projectiles.size(); i++) { Projectile p = (Projectile)
		 * projectiles.get(i); if (p.isVisible() == true) { p.update(); } else {
		 * projectiles.remove(i); } }
		 * 
		 * updateTiles(); hb.update(); hb2.update(); bg1.update(); bg2.update();
		 * animate();
		 * 
		 * if (robot.getCenterY() > 500) { state = GameState.GameOver; }
		 */
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
					// nullify();
					// goToMenu();
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

	/*
	 * private void updateTiles() {
	 * 
	 * for (int i = 0; i < tilearray.size(); i++) { Tile t = (Tile)
	 * tilearray.get(i); t.update(); }
	 * 
	 * }
	 */
	/*
	 * @Override public void paint(float deltaTime) { Graphics g =
	 * game.getGraphics();
	 * 
	 * g.drawImage(Assets.background, bg1.getBgX(), bg1.getBgY());
	 * g.drawImage(Assets.background, bg2.getBgX(), bg2.getBgY());
	 * paintTiles(g);
	 * 
	 * /* ArrayList projectiles = robot.getProjectiles(); for (int i = 0; i <
	 * projectiles.size(); i++) { Projectile p = (Projectile)
	 * projectiles.get(i); g.drawRect(p.getX(), p.getY(), 10, 5, Color.YELLOW);
	 * } // First draw the game elements.
	 * 
	 * g.drawImage(currentSprite, robot.getCenterX() - 61, robot.getCenterY() -
	 * 63); g.drawImage(hanim.getImage(), hb.getCenterX() - 48, hb.getCenterY()
	 * - 48); g.drawImage(hanim.getImage(), hb2.getCenterX() - 48,
	 * hb2.getCenterY() - 48);
	 * 
	 * // Example: // g.drawImage(Assets.background, 0, 0); //
	 * g.drawImage(Assets.character, characterX, characterY);
	 * 
	 * // Secondly, draw the UI above the game elements. if (state ==
	 * GameState.Ready) drawReadyUI(); if (state == GameState.Running)
	 * drawRunningUI(); if (state == GameState.Paused) drawPausedUI(); if (state
	 * == GameState.GameOver) drawGameOverUI();
	 * 
	 * }
	 * 
	 * private void paintTiles(Graphics g) { for (int i = 0; i <
	 * tilearray.size(); i++) { Tile t = (Tile) tilearray.get(i); if (t.type !=
	 * 0) { g.drawImage(t.getTileImage(), t.getTileX(), t.getTileY()); } } }
	 * 
	 * public void animate() { anim.update(10); hanim.update(50); }
	 * 
	 * private void nullify() {
	 * 
	 * // Set all variables to null. You will be recreating them in the //
	 * constructor. paint = null; bg1 = null; bg2 = null; robot = null; hb =
	 * null; hb2 = null; currentSprite = null; character = null; character2 =
	 * null; character3 = null; heliboy = null; heliboy2 = null; heliboy3 =
	 * null; heliboy4 = null; heliboy5 = null; anim = null; hanim = null;
	 * 
	 * // Call garbage collector to clean up memory. System.gc();
	 * 
	 * }
	 * 
	 * private void drawReadyUI() { Graphics g = game.getGraphics();
	 * 
	 * g.drawARGB(155, 0, 0, 0); g.drawString("Tap to Start.", 400, 240, paint);
	 * 
	 * }
	 * 
	 * private void drawRunningUI() { Graphics g = game.getGraphics();
	 * g.drawImage(Assets.button, 0, 285, 0, 0, 65, 65);
	 * g.drawImage(Assets.button, 0, 350, 0, 65, 65, 65);
	 * g.drawImage(Assets.button, 0, 415, 0, 130, 65, 65);
	 * g.drawImage(Assets.button, 0, 0, 0, 195, 35, 35);
	 * 
	 * }
	 * 
	 * private void drawPausedUI() { Graphics g = game.getGraphics(); // Darken
	 * the entire screen so you can display the Paused screen. g.drawARGB(155,
	 * 0, 0, 0); g.drawString("Resume", 400, 165, paint2); g.drawString("Menu",
	 * 400, 360, paint2);
	 * 
	 * }
	 * 
	 * private void drawGameOverUI() { Graphics g = game.getGraphics();
	 * g.drawRect(0, 0, 1281, 801, Color.BLACK); g.drawString("GAME OVER.", 400,
	 * 240, paint2); g.drawString("Tap to return.", 400, 290, paint);
	 * 
	 * }
	 * 
	 * @Override public void pause() { if (state == GameState.Running) state =
	 * GameState.Paused;
	 * 
	 * }
	 * 
	 * @Override public void resume() { if (state == GameState.Paused) state =
	 * GameState.Running; }
	 * 
	 * @Override public void dispose() {
	 * 
	 * }
	 * 
	 * @Override public void backButton() { pause(); }
	 * 
	 * private void goToMenu() { // TODO Auto-generated method stub
	 * game.setScreen(new MainMenuScreen(game));
	 * 
	 * }
	 * 
	 * public static Background getBg1() { // TODO Auto-generated method stub
	 * return bg1; }
	 * 
	 * public static Background getBg2() { // TODO Auto-generated method stub
	 * return bg2; }
	 * 
	 * public static Robot getRobot() { // TODO Auto-generated method stub
	 * return robot; }
	 */

	// return a new direction for enemys, either -100 to -51 or 51 to 100
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

		game_over_counter -= 0.017; // sleep timer

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
		int x = curEnemy.getPosX() - monty.getPosX();
		int y = curEnemy.getPosY() - monty.getPosY();
		int r = (int) Math.sqrt(x * x + y * y);
		if (r < EAT_RADIUS) {
			curEnemy.setStatus(STATUS.DIE);
			game_over_counter += GAME_OVER_COUNTER_BONUS;
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
		// rotate all, draw enemy and derotate all
		for (int vecIndex = 0; vecIndex < enemies.size(); vecIndex++) {
			Dino curEnemy = enemies.elementAt(vecIndex);
			if (curEnemy == null)
				continue;

			double angle = calcAngle(curEnemy.getAngle());

			g.drawRotatedImage(Assets.walkingOrange1, curEnemy.getPosX()
					+ background.getPosX(),
					curEnemy.getPosY() + background.getPosY(), (int) angle);

		}
	}

	public void drawPlayer(Graphics g) {
		double angle = calcAngle(monty.getAngle());
		Image drawImage = selectPlayerImage(g, monty);
		g.drawRotatedImage(drawImage, monty.getPosX(), monty.getPosY(),
				(int) angle);
	}

	private double calcAngle(double Angle) {
		return (195 + ((Angle + Math.PI) / (2 * Math.PI)) * 360) % 360;
	}

	public Image selectPlayerImage(Graphics g, Dino dino) {
		// imageCounter = drawInstance.getDrawImageCounter();
		int imageCounter = dino.getImageCounter();

		// set back the draw Counter for different pictures
		if (imageCounter == 99) {
			dino.setImageCounter(0);
		}

		imageCounter++;
		dino.setImageCounter(imageCounter);

		if (dino == monty) {

			// draw player idle
			if (dino.getStatus() == STATUS.IDLE) {
				if (imageCounter >= 0 && imageCounter <= 49)
					return Assets.breathingOrange1;

				if (imageCounter >= 50 && imageCounter < 100)
					return Assets.breathingOrange2;

			}
			
			// draw player walking
			if (dino.getStatus() == STATUS.WALK) {
				if (imageCounter >= 0 && imageCounter <= 49)
					return Assets.walkingOrange1;

				if (imageCounter >= 50 && imageCounter < 100)
					return Assets.walkingOrange2;
			}

			// draw player attacking
			if (dino.getStatus() == STATUS.ATTACK) {
				if (imageCounter >= 0 && imageCounter <= 49)
					return Assets.attackingOrange1;

				if (imageCounter >= 50 && imageCounter < 100)
					return Assets.attackingOrange2;
			}
			
			// draw player dying
			if (dino.getStatus() == STATUS.DIE) {
				if (imageCounter >= 0 && imageCounter <= 49)
					return Assets.dyingOrange1;

				if (imageCounter >= 50 && imageCounter < 100)
					return Assets.dyingOrange2;
			}
			
			
			
			
		}
		return Assets.breathingOrange1;

	}

	@Override
	public void paint(float deltaTime) {
		// TODO Auto-generated method stub

		Graphics g = game.getGraphics();

		drawBackground(g);
		drawEnemies(g);
		drawPlayer(g);

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void backButton() {
		// TODO Auto-generated method stub

	}

}