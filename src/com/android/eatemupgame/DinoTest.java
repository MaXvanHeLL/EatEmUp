package com.android.eatemupgame;

import com.android.eatemupgame.Constants.STATUS;

import junit.framework.TestCase;

public class DinoTest extends TestCase {

	public void testDino() {
		Dino test = new Dino(0, 0, 0, 0, "", 0);
		assertNotNull(test);
	}

	public void testGetPosX() {
		Dino test = new Dino(1, 0, 0, 0, "", 0);
		assertEquals(1, test.getPosX());
	}

	public void testGetPosY() {
		Dino test = new Dino(0, 2, 0, 0, "", 0);
		assertEquals(2, test.getPosY());
	}

	public void testGetMoveToX() {
		Dino test = new Dino(0, 0, 3, 0, "", 0);
		assertEquals(3, test.getMoveToX());
	}

	public void testGetMoveToY() {
		Dino test = new Dino(0, 0, 0, 4, "", 0);
		assertEquals(4, test.getMoveToY());
	}

	public void testGetAngle() {
		Dino test = new Dino(0, 0, 0, 0, "", 0);
		assertEquals(0.0, test.getAngle());
	}

	public void testGetColor() {
		Dino test = new Dino(0, 0, 0, 0, "blue", 0);
		assertEquals("blue", test.getColor());
	}

	public void testGetStatus() {
		Dino test = new Dino(0, 0, 0, 0, "", 0);
		assertEquals(STATUS.IDLE, test.getStatus());
	}

	public void testSetPosX() {
		Dino test = new Dino(0, 0, 0, 0, "", 0);
		test.setPosX(5);
		assertEquals(5, test.getPosX());
	}

	public void testSetPosY() {
		Dino test = new Dino(0, 0, 0, 0, "", 0);
		test.setPosY(6);
		assertEquals(6, test.getPosY());
	}

	public void testSetMoveToX() {
		Dino test = new Dino(0, 0, 0, 0, "", 0);
		test.setMoveToX(7);
		assertEquals(7, test.getMoveToX());
	}

	public void testSetMoveToY() {
		Dino test = new Dino(0, 0, 0, 0, "", 0);
		test.setMoveToY(8);
		assertEquals(8, test.getMoveToY());
	}

	public void testSetAngle() {
		Dino test = new Dino(0, 0, 0, 0, "", 0);
		test.setAngle(1.1);
		assertEquals(1.1, test.getAngle());
	}

	public void testSetStatus() {
		Dino test = new Dino(0, 0, 0, 0, "", 0);
		test.setStatus(STATUS.WALK);
		assertEquals(STATUS.WALK, test.getStatus());
	}

	public void testGetSpeed() {
		Dino test = new Dino(0, 0, 0, 0, "", 9);
		assertEquals(9, test.getSpeed());
	}

	public void testGetMaxAngle() {
		Dino test = new Dino(0, 0, 0, 0, "", 0);
		assertEquals(0.06, test.getMaxAngle());
	}

	public void testGetDespawn_time() {
		Dino test = new Dino(0, 0, 0, 0, "", 0);
		assertEquals(20, test.getDespawn_time());
	}

	public void testSetDespawn_time() {
		Dino test = new Dino(0, 0, 0, 0, "", 0);
		test.setDespawn_time(10);
		assertEquals(10, test.getDespawn_time());
	}

	public void testGetImageCounter() {
		Dino test = new Dino(0, 0, 0, 0, "", 0);
		assertEquals(0, test.getImageCounter());
	}

	public void testSetImageCounter() {
		Dino test = new Dino(0, 0, 0, 0, "", 0);
		test.setImageCounter(11);
		assertEquals(11, test.getImageCounter());
	}

}
