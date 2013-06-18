package com.android.eatemupgame;

import com.android.framework.Image;
import com.android.framework.Music;
import com.android.framework.Sound;

public class Assets {

	public static Image menu1, menu2, menu3, menu4, menu5, menu6, menu7, menu8, menu9, menu10, menu11, menu12, menu13, menu14;
	public static Image button1, button2, button3, background,  splash, musicButton;
	
	public static Image breathingOrange1, breathingOrange2;
	public static Image walkingOrange1, walkingOrange2;
	public static Image attackingOrange1, attackingOrange2, attackingOrange3;
	public static Image dyingOrange1, dyingOrange2, dyingOrange3, dyingOrange4;
	
	public static Image walkingGreen1, walkingGreen2;
	public static Image walkingBlue1, walkingBlue2;
	public static Image walkingRed1, walkingRed2;
	public static Image walkingYellow1, walkingYellow2;
	
	public static Image dyingGreen1, dyingGreen2, dyingGreen3;
	public static Image dyingBlue1, dyingBlue2, dyingBlue3;
	public static Image dyingRed1, dyingRed2, dyingRed3;
	public static Image dyingYellow1, dyingYellow2, dyingYellow3;
	public static Image dyingEnemy4;
	
	public static Image dummyPic;
	
	public static Sound click;
	public static Music theme;
	
	public static void load(SampleGame sampleGame) {

		theme = sampleGame.getAudio().createMusic("menutheme.mp3");
		theme.setLooping(true);
		theme.setVolume(0.85f);
		theme.play();
	}
	
}
