package com.android.eatemupgame;

public class Dino implements Constants {
	private int posX;
	private int posY;
	private int moveToX;
	private int moveToY;
	private double angle;
	private double maxAngle;
	private String color;
	private STATUS status;
	private int speed;
	private int despawn_time;
	private int imageCounter;
	
	Dino(int PosX, int PosY, int MoveToX, int MoveToY, String Color, int Speed) {
		posX = PosX;
		posY = PosY;
		moveToX = MoveToX;
		moveToY = MoveToY;
		color = Color;
		angle = 0;
		maxAngle = MAX_ANGLE;
		status = STATUS.IDLE;
		speed = Speed;
		despawn_time = DESPAWN_TIME;
		imageCounter = 0;
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

	public int getMoveToX() {
		return moveToX;
	}

	public int getMoveToY() {
		return moveToY;
	}

	public double getAngle() {
		return angle;
	}

	public String getColor() {
		return color;
	}

	public STATUS getStatus() {
		return status;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public void setMoveToX(int moveToX) {
		this.moveToX = moveToX;
	}

	public void setMoveToY(int moveToY) {
		this.moveToY = moveToY;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}

	public void setStatus(STATUS status) {
		this.status = status;
	}

	public int getSpeed() {
		return speed;
	}

	public double getMaxAngle() {
		return maxAngle;
	}

	public int getDespawn_time() {
		return despawn_time;
	}

	public void setDespawn_time(int despawn_time) {
		this.despawn_time = despawn_time;
	}

	public int getImageCounter() {
		return imageCounter;
	}

	public void setImageCounter(int imageCounter) {
		this.imageCounter = imageCounter;
	}

}
