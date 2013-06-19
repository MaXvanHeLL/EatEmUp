package com.android.eatemupgame;

import com.android.framework.Game;
import com.android.framework.Graphics;
import com.android.framework.Graphics.ImageFormat;
import com.android.framework.Screen;
import com.android.eatemupgame.Assets;

public class LoadingScreen extends Screen implements Constants {
	public LoadingScreen(Game game) {
		
		super(game);
	}

	@Override
	public void update(float deltaTime) {
		Graphics g = game.getGraphics();
	
		Assets.menu1 = g.newImage("menu1.png", ImageFormat.RGB565);
		Assets.menu2 = g.newImage("menu2.png", ImageFormat.RGB565);
		Assets.menu3 = g.newImage("menu3.png", ImageFormat.RGB565);
		Assets.menu4 = g.newImage("menu4.png", ImageFormat.RGB565);
		Assets.menu5 = g.newImage("menu5.png", ImageFormat.RGB565);
		Assets.menu6 = g.newImage("menu6.png", ImageFormat.RGB565);
		Assets.menu7 = g.newImage("menu7.png", ImageFormat.RGB565);
		Assets.menu8 = g.newImage("menu8.png", ImageFormat.RGB565);
		Assets.menu9 = g.newImage("menu9.png", ImageFormat.RGB565);
		Assets.menu10 = g.newImage("menu10.png", ImageFormat.RGB565);
		Assets.menu11 = g.newImage("menu11.png", ImageFormat.RGB565);
		Assets.menu12 = g.newImage("menu12.png", ImageFormat.RGB565);
		Assets.menu13 = g.newImage("menu13.png", ImageFormat.RGB565);
		Assets.menu14 = g.newImage("menu14.png", ImageFormat.RGB565);
		
		Assets.buttonUnpressed1 = g.newImage("buttonUnpressed1.png", ImageFormat.RGB565);
		Assets.buttonUnpressed2 = g.newImage("buttonUnpressed2.png", ImageFormat.RGB565);
		Assets.buttonUnpressed3 = g.newImage("buttonUnpressed3.png", ImageFormat.RGB565);
		
		Assets.buttonPressed1 = g.newImage("buttonPressed1.png", ImageFormat.RGB565);
		Assets.buttonPressed2 = g.newImage("buttonPressed2.png", ImageFormat.RGB565);
		Assets.buttonPressed3 = g.newImage("buttonPressed3.png", ImageFormat.RGB565);
		
		//all monty images
		Assets.breathingOrange1 = g.newImage("breathingOrange1.png", ImageFormat.RGB565);
		Assets.breathingOrange2= g.newImage("breathingOrange2.png", ImageFormat.RGB565);
		Assets.walkingOrange1 = g.newImage("walkingOrange1.png", ImageFormat.RGB565);
		  Assets.walkingOrange21 = g.newImage("walkingOrange21.png", ImageFormat.RGB565);
		  Assets.walkingOrange22 = g.newImage("walkingOrange22.png", ImageFormat.RGB565);
		  Assets.walkingOrange31 = g.newImage("walkingOrange31.png", ImageFormat.RGB565);
		  Assets.walkingOrange32 = g.newImage("walkingOrange32.png", ImageFormat.RGB565);
		Assets.attackingOrange1 = g.newImage("attackingOrange1.png", ImageFormat.RGB565);
		Assets.attackingOrange2= g.newImage("attackingOrange2.png", ImageFormat.RGB565);
		Assets.attackingOrange3= g.newImage("attackingOrange3.png", ImageFormat.RGB565);
		Assets.dyingOrange1 = g.newImage("dyingOrange1.png", ImageFormat.RGB565);
		Assets.dyingOrange2 = g.newImage("dyingOrange2.png", ImageFormat.RGB565);
		Assets.dyingOrange3 = g.newImage("dyingOrange3.png", ImageFormat.RGB565);
		Assets.dyingOrange4 = g.newImage("dyingOrange4.png", ImageFormat.RGB565);

		//enemy walking images
		Assets.walkingGreen1 = g.newImage("walkingGreen1.png", ImageFormat.RGB565);
		Assets.walkingGreen2 = g.newImage("walkingGreen2.png", ImageFormat.RGB565);
		Assets.walkingBlue1 = g.newImage("walkingBlue1.png", ImageFormat.RGB565);
		Assets.walkingBlue2 = g.newImage("walkingBlue2.png", ImageFormat.RGB565);
		Assets.walkingRed1 = g.newImage("walkingRed1.png", ImageFormat.RGB565);
		Assets.walkingRed2 = g.newImage("walkingRed2.png", ImageFormat.RGB565);
		Assets.walkingYellow1 = g.newImage("walkingYellow1.png", ImageFormat.RGB565);
		Assets.walkingYellow2 = g.newImage("walkingYellow2.png", ImageFormat.RGB565);
		
		//enemy dying images
		Assets.dyingGreen1 = g.newImage("dyingGreen1.png", ImageFormat.RGB565);
		Assets.dyingGreen2 = g.newImage("dyingGreen2.png", ImageFormat.RGB565);
		Assets.dyingGreen3 = g.newImage("dyingGreen3.png", ImageFormat.RGB565);
		Assets.dyingBlue1 = g.newImage("dyingBlue1.png", ImageFormat.RGB565);
		Assets.dyingBlue2 = g.newImage("dyingBlue2.png", ImageFormat.RGB565);
		Assets.dyingBlue3 = g.newImage("dyingBlue3.png", ImageFormat.RGB565);
		Assets.dyingRed1 = g.newImage("dyingRed1.png", ImageFormat.RGB565);
		Assets.dyingRed2 = g.newImage("dyingRed2.png", ImageFormat.RGB565);
		Assets.dyingRed3 = g.newImage("dyingRed3.png", ImageFormat.RGB565);
		Assets.dyingYellow1 = g.newImage("dyingYellow1.png", ImageFormat.RGB565);
		Assets.dyingYellow2 = g.newImage("dyingYellow2.png", ImageFormat.RGB565);
		Assets.dyingYellow3 = g.newImage("dyingYellow3.png", ImageFormat.RGB565);
		Assets.dyingEnemy4 = g.newImage("dyingEnemy4.png", ImageFormat.RGB565);
		
		//dummy pic
		Assets.dummyPic = g.newImage("dummyPic.png", ImageFormat.RGB565);

		//enemy walking images
		Assets.walkingGreen1 = g.newImage("walkingGreen1.png", ImageFormat.RGB565);
		Assets.walkingGreen2 = g.newImage("walkingGreen2.png", ImageFormat.RGB565);
		Assets.walkingBlue1 = g.newImage("walkingBlue1.png", ImageFormat.RGB565);
		Assets.walkingBlue2 = g.newImage("walkingBlue2.png", ImageFormat.RGB565);
		Assets.walkingRed1 = g.newImage("walkingRed1.png", ImageFormat.RGB565);
		Assets.walkingRed2 = g.newImage("walkingRed2.png", ImageFormat.RGB565);
		Assets.walkingYellow1 = g.newImage("walkingYellow1.png", ImageFormat.RGB565);
		Assets.walkingYellow2 = g.newImage("walkingYellow2.png", ImageFormat.RGB565);
		
		//enemy dying images
		Assets.dyingGreen1 = g.newImage("dyingGreen1.png", ImageFormat.RGB565);
		Assets.dyingGreen2 = g.newImage("dyingGreen2.png", ImageFormat.RGB565);
		Assets.dyingGreen3 = g.newImage("dyingGreen3.png", ImageFormat.RGB565);
		Assets.dyingBlue1 = g.newImage("dyingBlue1.png", ImageFormat.RGB565);
		Assets.dyingBlue2 = g.newImage("dyingBlue2.png", ImageFormat.RGB565);
		Assets.dyingBlue3 = g.newImage("dyingBlue3.png", ImageFormat.RGB565);
		Assets.dyingRed1 = g.newImage("dyingRed1.png", ImageFormat.RGB565);
		Assets.dyingRed2 = g.newImage("dyingRed2.png", ImageFormat.RGB565);
		Assets.dyingRed3 = g.newImage("dyingRed3.png", ImageFormat.RGB565);
		Assets.dyingYellow1 = g.newImage("dyingYellow1.png", ImageFormat.RGB565);
		Assets.dyingYellow2 = g.newImage("dyingYellow2.png", ImageFormat.RGB565);
		Assets.dyingYellow3 = g.newImage("dyingYellow3.png", ImageFormat.RGB565);
		Assets.dyingEnemy4 = g.newImage("dyingEnemy4.png", ImageFormat.RGB565);
		
		//dummy pic
		Assets.dummyPic = g.newImage("dummyPic.png", ImageFormat.RGB565);
		
		Assets.musicON = g.newImage("musicON.png", ImageFormat.RGB565);
		Assets.musicOFF = g.newImage("musicOFF.png", ImageFormat.RGB565);
		
		game.setScreen(new MainMenuScreen(game));

	}

	@Override
	public void paint(float deltaTime) {
		Graphics g = game.getGraphics();
		g.drawImage(Assets.splash, SCREEN_RESOLUTION_X / 2 - 200 , SCREEN_RESOLUTION_Y / 2 - 80);
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {

	}

	@Override
	public void backButton() {

	}
}