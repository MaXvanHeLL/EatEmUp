package com.android.eatemupgame;

import java.util.Vector;

public class Calculation implements Constants {
	private Dino monty;
	private Vector<Dino> enemies;
	private Background background;
//	private Input input;

	Calculation(Dino Monty, Vector<Dino> Enemies, Background Background
			//,Input Input
			) {
		monty = Monty;
		enemies = Enemies;
		background = Background;
		//input = Input;
	}

	public void calculate() {

		calcMovement(monty);

		for (int vecIndex = 0; vecIndex < enemies.size(); vecIndex++) {
			if (enemies.elementAt(vecIndex) == null)
				continue;
			calcMovement(enemies.elementAt(vecIndex));
		}

		checkJumpBackground();
	}

	// calculates the background movement (illusion for the player movement)
	private void calcMovement(Dino currentDino) {
		int referenceX = 0;
		int referenceY = 0;

		if (currentDino.getStatus() == STATUS.WALK ||
				currentDino.getStatus() == STATUS.ATTACK) {
			double angle = Math.atan2(currentDino.getMoveToY(),
					currentDino.getMoveToX());

			double oldAngle = currentDino.getAngle();
			double direction = angle - oldAngle;

			if (direction < 0)
				direction += Math.PI * 2;

			// turn right
			if (direction < Math.PI)
				angle = Math.min(direction, currentDino.getMaxAngle())
						+ oldAngle;

			// turn left
			else
				angle = Math.max(direction,
						Math.PI * 2 - currentDino.getMaxAngle())
						+ oldAngle;

			if (angle > Math.PI)
				angle -= Math.PI * 2;

			currentDino.setAngle(angle);

			referenceX = (int) (currentDino.getSpeed()
					* Math.sin(Math.PI / 2 - angle) / Math.sin(90));
			referenceY = (int) (currentDino.getSpeed() * Math.sin(angle) / Math
					.sin(90));

			// move background
			if (currentDino == monty) {
				background.setPosX(background.getPosX() - referenceX);
				background.setPosY(background.getPosY() - referenceY);
				// move enemy
			} else {
				currentDino.setPosX(currentDino.getPosX() + referenceX);
				currentDino.setPosY(currentDino.getPosY() + referenceY);
			}
		}
	}

	// checks, if a jump of the 9x9 fielded Background Image is necessary
	private void checkJumpBackground() {

		// Center of the Screen, monty; mirrored movement!
		int newCenterX = background.getPosX() + BACKGROUND_RESOLUTION_X / 2;
		int newCenterY = background.getPosY() + BACKGROUND_RESOLUTION_Y / 2;

		// jump only one screen of the 9x9 field
		int jumpTimesX = 1;
		int jumpTimesY = 1;

		// change coords by the calculated value
		int changePosX = 0;
		int changePosY = 0;

		// right jump, calculating from (0/0)
		if (newCenterX - SCREEN_RESOLUTION_X / 2 < -BACKGROUND_RESOLUTION_X
				+ BACKGROUND_LEFT) {
			if (SCREEN_RESOLUTION_X < BACKGROUND_RESOLUTION_X)
				jumpTimesX = 2;
			changePosX = BACKGROUND_RESOLUTION_X * jumpTimesX;
		}

		// down jump, calculating from (0/0)
		if (newCenterY - SCREEN_RESOLUTION_Y / 2 < -BACKGROUND_RESOLUTION_Y
				+ BACKGROUND_TOP) {
			if (SCREEN_RESOLUTION_Y < BACKGROUND_RESOLUTION_Y)
				jumpTimesY = 2;
			changePosY = BACKGROUND_RESOLUTION_Y * jumpTimesY;
		}

		// left jump, calculating from (0/0)
		if (newCenterX + SCREEN_RESOLUTION_X / 2 > BACKGROUND_RESOLUTION_X * 2
				+ BACKGROUND_LEFT) {
			if (SCREEN_RESOLUTION_X < BACKGROUND_RESOLUTION_X)
				jumpTimesX = 2;
			changePosX = BACKGROUND_RESOLUTION_X * jumpTimesX * -1;
		}

		// up jump, calculating from (0/0)
		if (newCenterY + SCREEN_RESOLUTION_Y / 2 > BACKGROUND_RESOLUTION_Y * 2
				+ BACKGROUND_TOP) {
			if (SCREEN_RESOLUTION_Y < BACKGROUND_RESOLUTION_Y)
				jumpTimesY = 2;
			changePosY = BACKGROUND_RESOLUTION_Y * jumpTimesY * -1;
		}

		background.setPosX(background.getPosX() + changePosX);
		background.setPosY(background.getPosY() + changePosY);

		for (int vecIndex = 0; vecIndex < enemies.size(); vecIndex++) {
			if (enemies.elementAt(vecIndex) == null)
				continue;
			Dino curEnemy = enemies.elementAt(vecIndex);
			curEnemy.setPosX(curEnemy.getPosX() - changePosX);
			curEnemy.setPosY(curEnemy.getPosY() - changePosY);
		}
	}
}
