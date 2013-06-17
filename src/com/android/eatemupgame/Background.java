package com.android.eatemupgame;

public class Background {
	private int posX;
	private int posY;

	Background(int PosX, int PosY) {
		posX = PosX;
		posY = PosY;
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}
}