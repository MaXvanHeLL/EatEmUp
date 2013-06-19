package com.android.eatemupgame;

import java.util.Vector;

public class Calculation implements Constants {
	private Dino monty;
	private Vector<Dino> enemies;
	private Background background;

	Calculation(Dino Monty, Vector<Dino> Enemies, Background Background) {
		monty = Monty;
		enemies = Enemies;
		background = Background;
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

		if (currentDino.getStatus() == STATUS.WALK
				|| currentDino.getStatus() == STATUS.ATTACK) {

			double angle = calcNewAngle(currentDino);

			referenceX = (int) (currentDino.getSpeed()
					* Math.sin(Math.PI / 2 - angle) / Math.sin(90));
			referenceY = (int) (currentDino.getSpeed() * Math.sin(angle) / Math
					.sin(90));

			if (currentDino == monty) {
				movePlayer(referenceX, referenceY);

			} else {
				moveEnemy(referenceX, referenceY, currentDino);
			}
		}
	}

	private double calcNewAngle(Dino currentDino) {
		double angle = Math.atan2(currentDino.getMoveToY(),
				currentDino.getMoveToX());

		double oldAngle = currentDino.getAngle();
		double direction = angle - oldAngle;

		if (direction < 0)
			direction += Math.PI * 2;

		angle = calcTurnDir(angle, oldAngle, direction, currentDino);

		if (angle > Math.PI)
			angle -= Math.PI * 2;

		currentDino.setAngle(angle);

		return angle;
	}

	double calcTurnDir(double angle, double oldAngle, double direction,
			Dino currentDino) {

		// turn right
		if (direction < Math.PI)
			angle = Math.min(direction, currentDino.getMaxAngle()) + oldAngle;

		// turn left
		else
			angle = Math
					.max(direction, Math.PI * 2 - currentDino.getMaxAngle())
					+ oldAngle;

		return angle;
	}

	void movePlayer(int referenceX, int referenceY) {
		background.setPosX(background.getPosX() - referenceX);
		background.setPosY(background.getPosY() - referenceY);
	}

	void moveEnemy(int referenceX, int referenceY, Dino currentDino) {
		currentDino.setPosX(currentDino.getPosX() + referenceX);
		currentDino.setPosY(currentDino.getPosY() + referenceY);
	}

	// checks, if a jump of the 9x9 fielded Background Image is necessary
	private void checkJumpBackground() {

		// Center of the Screen, monty; mirrored movement!
		int newCenterX = background.getPosX() + BACKGROUND_RESOLUTION_X / 2;
		int newCenterY = background.getPosY() + BACKGROUND_RESOLUTION_Y / 2;

		// change coords by the calculated value
		int changePosX = jumpScreenRight(newCenterX);
		if (changePosX == 0) {
			changePosX = jumpScreenLeft(newCenterX);
		}

		// change coords by the calculated value
		int changePosY = jumpScreenDown(newCenterY);
		if (changePosY == 0) {
			changePosY = jumpScreenUp(newCenterY);
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

	int jumpScreenRight(int newCenterX) {

		// jump only one screen of the 9x9 field
		int jumpTimesX = 1;

		// right jump, calculating from (0/0)
		if (newCenterX - SCREEN_RESOLUTION_X / 2 < -BACKGROUND_RESOLUTION_X
				+ BACKGROUND_LEFT) {
			if (SCREEN_RESOLUTION_X < BACKGROUND_RESOLUTION_X)
				jumpTimesX = 2;
			return BACKGROUND_RESOLUTION_X * jumpTimesX;
		}

		return 0;
	}

	int jumpScreenLeft(int newCenterX) {

		// jump only one screen of the 9x9 field
		int jumpTimesX = 1;

		// left jump, calculating from (0/0)
		if (newCenterX + SCREEN_RESOLUTION_X / 2 > BACKGROUND_RESOLUTION_X * 2
				+ BACKGROUND_LEFT) {
			if (SCREEN_RESOLUTION_X < BACKGROUND_RESOLUTION_X)
				jumpTimesX = 2;
			return BACKGROUND_RESOLUTION_X * jumpTimesX * -1;
		}

		return 0;
	}

	int jumpScreenDown(int newCenterY) {

		// jump only one screen of the 9x9 field
		int jumpTimesY = 1;

		// down jump, calculating from (0/0)
		if (newCenterY - SCREEN_RESOLUTION_Y / 2 < -BACKGROUND_RESOLUTION_Y
				+ BACKGROUND_TOP) {
			if (SCREEN_RESOLUTION_Y < BACKGROUND_RESOLUTION_Y)
				jumpTimesY = 2;
			return BACKGROUND_RESOLUTION_Y * jumpTimesY;
		}

		return 0;

	}

	int jumpScreenUp(int newCenterY) {

		// jump only one screen of the 9x9 field
		int jumpTimesY = 1;

		// up jump, calculating from (0/0)
		if (newCenterY + SCREEN_RESOLUTION_Y / 2 > BACKGROUND_RESOLUTION_Y * 2
				+ BACKGROUND_TOP) {
			if (SCREEN_RESOLUTION_Y < BACKGROUND_RESOLUTION_Y)
				jumpTimesY = 2;
			return BACKGROUND_RESOLUTION_Y * jumpTimesY * -1;
		}

		return 0;

	}

}
