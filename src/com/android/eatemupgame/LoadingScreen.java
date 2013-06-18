package com.android.eatemupgame;

import com.android.framework.Game;
import com.android.framework.Graphics;
import com.android.framework.Graphics.ImageFormat;
import com.android.framework.Screen;
import com.android.eatemupgame.Assets;

public class LoadingScreen extends Screen {
	public LoadingScreen(Game game) {
		
		super(game);
	}

	@Override
	public void update(float deltaTime) {
		Graphics g = game.getGraphics();
		
		//This is how you would load a sound if you had one.
		//Assets.click = game.getAudio().createSound("explode.ogg");
	
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
		
		Assets.button1 = g.newImage("button1.png", ImageFormat.RGB565);
		Assets.button2 = g.newImage("button2.png", ImageFormat.RGB565);
		Assets.button3 = g.newImage("button3.png", ImageFormat.RGB565);
		
		Assets.breathingOrange1 = g.newImage("breathingOrange1.png", ImageFormat.RGB565);
		Assets.breathingOrange2= g.newImage("breathingOrange2.png", ImageFormat.RGB565);
		
		Assets.walkingOrange1 = g.newImage("walkingOrange1.png", ImageFormat.RGB565);
		Assets.walkingOrange2 = g.newImage("walkingOrange2.png", ImageFormat.RGB565);
		Assets.walkingGreen1 = g.newImage("walkingGreen1.png", ImageFormat.RGB565);
		Assets.walkingGreen2 = g.newImage("walkingGreen2.png", ImageFormat.RGB565);
		
		Assets.attackingOrange1 = g.newImage("attackingOrange1.png", ImageFormat.RGB565);
		Assets.attackingOrange2= g.newImage("attackingOrange2.png", ImageFormat.RGB565);
		
		Assets.dyingOrange1 = g.newImage("dyingOrange1.png", ImageFormat.RGB565);
		Assets.dyingOrange2 = g.newImage("dyingOrange2.png", ImageFormat.RGB565);
		Assets.dyingGreen1 = g.newImage("dyingGreen1.png", ImageFormat.RGB565);

		
		Assets.musicButton = g.newImage("musicButton.png", ImageFormat.RGB565);
		
		
		/*Assets.character = g.newImage("character.png", ImageFormat.ARGB4444);
		Assets.character2 = g.newImage("character2.png", ImageFormat.ARGB4444);
		Assets.character3  = g.newImage("character3.png", ImageFormat.ARGB4444);
		Assets.characterJump = g.newImage("jumped.png", ImageFormat.ARGB4444);
		Assets.characterDown = g.newImage("down.png", ImageFormat.ARGB4444);
		Assets.heliboy = g.newImage("heliboy.png", ImageFormat.ARGB4444);
		Assets.heliboy2 = g.newImage("heliboy2.png", ImageFormat.ARGB4444);
		Assets.heliboy3  = g.newImage("heliboy3.png", ImageFormat.ARGB4444);
		Assets.heliboy4  = g.newImage("heliboy4.png", ImageFormat.ARGB4444);
		Assets.heliboy5  = g.newImage("heliboy5.png", ImageFormat.ARGB4444);

		Assets.tiledirt = g.newImage("tiledirt.png", ImageFormat.RGB565);
		Assets.tilegrassTop = g.newImage("tilegrasstop.png", ImageFormat.RGB565);
		Assets.tilegrassBot = g.newImage("tilegrassbot.png", ImageFormat.RGB565);
		Assets.tilegrassLeft = g.newImage("tilegrassleft.png", ImageFormat.RGB565);
		Assets.tilegrassRight = g.newImage("tilegrassright.png", ImageFormat.RGB565);
		
		Assets.button = g.newImage("button.jpg", ImageFormat.RGB565);
		*/
		
		//Assets.background = g.newImage("background.png", ImageFormat.RGB565);
		game.setScreen(new MainMenuScreen(game));

	}

	@Override
	public void paint(float deltaTime) {
		Graphics g = game.getGraphics();
		g.drawImage(Assets.splash, 0, 0);
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