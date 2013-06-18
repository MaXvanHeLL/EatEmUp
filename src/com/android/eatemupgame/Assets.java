package com.android.eatemupgame;

import com.android.framework.Image;
import com.android.framework.Music;
import com.android.framework.Sound;

public class Assets {
	
	//public static Image menu, splash, background, character, character2, character3, heliboy, heliboy2, heliboy3, heliboy4, heliboy5;
	//public static Image tiledirt, tilegrassTop, tilegrassBot, tilegrassLeft, tilegrassRight, characterJump, characterDown;
	//public static Image button;
	
	public static Image menu1, menu2, menu3, menu4, menu5, menu6, menu7, menu8, menu9, menu10, menu11, menu12, menu13, menu14;
	public static Image button1, button2, button3, background,  splash, musicButton;
	public static Image breathingOrange1, breathingOrange2;
	public static Image walkingOrange1, walkingOrange2;
	public static Image walkingGreen1, walkingGreen2;
	public static Image dyingGreen1;
	public static Image attackingOrange1, attackingOrange2;
	public static Image dyingOrange1, dyingOrange2;
	public static Sound click;
	public static Music theme;
	
	public static void load(SampleGame sampleGame) {
		// TODO Auto-generated method stub
		theme = sampleGame.getAudio().createMusic("menutheme.mp3");
		theme.setLooping(true);
		theme.setVolume(0.85f);
		theme.play();
	}
	
}
