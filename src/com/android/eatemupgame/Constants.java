package com.android.eatemupgame;

public interface Constants {

	public static final int SCREEN_RESOLUTION_X = 799; // load Resolution somehow??
	public static final int SCREEN_RESOLUTION_Y = 479;
	
	public static final int BACKGROUND_RESOLUTION_X = 999; // 1200 -> from 0 to _1199_!!!
	public static final int BACKGROUND_RESOLUTION_Y = 699;
	
	// Needed, that the Top Left Corner of the Background_Image is interpreted as (0/0)
	public static final int BACKGROUND_LEFT = -(BACKGROUND_RESOLUTION_X - SCREEN_RESOLUTION_X) / 2;
	public static final int BACKGROUND_TOP = -(BACKGROUND_RESOLUTION_Y - SCREEN_RESOLUTION_Y) / 2;
			
	public static final int MONTY_RESOLUTION_X = 149;
	public static final int MONTY_RESOLUTION_Y = 79;
	
	public static final int ENEMY_RESOLUTION_X = 99;
	public static final int ENEMY_RESOLUTION_Y = 53;
	
	public static final int DINO_SPEED = 7;
	public static final int MIN_ENEMY_SPEED = 4;
	
	public static final double MAX_ANGLE = 0.06;
	public static final int RANDOM_COLOR = 5;
	public static final int EAT_RADIUS = 100;
	public static final int DESPAWN_TIME = 20; // in fps!
	
	public static final int ENEMY_SPAWN_DISTANCE_FROM_SCREEN = 200;
	public static final int ENEMY_SPAWN_DISTANCE_AREA = 200;
	public static final int ENEMY_DESPAWN_DISTANCE_FROM_SCREEN = 600;
	
	public static final float GAME_OVER_COUNTER_MAX = 100;
	public static final float GAME_OVER_COUNTER_BONUS = 15;
	
	public static final int MAX_ENEMIES = 10;
	public static final int MIN_ENEMIES = 3;
	public static final int ENEMY_SPAWN_CHANCE = 30; // fps
	public static final int ENEMY_SPAWN_CHANCE_MIN = 8;
	public static final int ENEMY_CHANGE_DIRECTION_CHANCE = 80;
	
	public static final int ANIMATION_PIC_DURATION = 50;
	
	public static int MENU_DRAW_COUNTER = 140;

	public static enum STATUS {
		IDLE, WALK, ATTACK, DIE
	}
}
