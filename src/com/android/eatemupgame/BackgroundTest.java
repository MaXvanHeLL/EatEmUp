package com.android.eatemupgame;

import junit.framework.TestCase;

public class BackgroundTest extends TestCase {

	public void testBackground() {
		Background test = new Background(0, 0);
		assertNotNull(test);
	}

	public void testGetPosX() {
		Background test = new Background(1, 0);
		assertEquals(1, test.getPosX());
	}

	public void testGetPosY() {
		Background test = new Background(0, 2);
		assertEquals(2, test.getPosY());
	}

	public void testSetPosX() {
		Background test = new Background(0, 0);
		test.setPosX(3);
		assertEquals(3, test.getPosX());
	}

	public void testSetPosY() {
		Background test = new Background(0, 0);
		test.setPosY(4);
		assertEquals(4, test.getPosY());
	}

}
