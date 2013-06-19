package com.android.eatemupgame;

import java.util.List;

import com.android.framework.Game;
import com.android.framework.Graphics;
import com.android.framework.Screen;
import com.android.framework.Graphics.ImageFormat;
import com.android.framework.Input.TouchEvent;

public class MainMenuScreen extends Screen implements Constants {
	public MainMenuScreen(Game game) {
		super(game);
	}

	private int menu_draw_counter = MENU_DRAW_COUNTER;

	@Override
	public void update(float deltaTime) {
		Graphics g = game.getGraphics();
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();

		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);

			if (event.type == TouchEvent.TOUCH_UP) {

				if (inBounds(event, 114, 232, 100, 100)) {
					Assets.background = g.newImage("background1.png",
							ImageFormat.RGB565);
					game.setScreen(new GameScreen(game));
				}
				if (inBounds(event, 314, 232, 100, 100)) {
					Assets.background = g.newImage("background2.png",
							ImageFormat.RGB565);
					//
					game.setScreen(new GameScreen(game));
				}
				if (inBounds(event, 514, 232, 100, 100)) {
					Assets.background = g.newImage("background3.png",
							ImageFormat.RGB565);
					game.setScreen(new GameScreen(game));
				}
				if (inBounds(event, 700, 0, 800, 100)
						&& Assets.theme.isPlaying()) {
					Assets.theme.stop();
					Assets.theme.setMute(true);
				}

				else if (inBounds(event, 700, 0, 800, 100)
						&& Assets.theme.isStopped()) {
					Assets.theme.play();
					Assets.theme.setMute(false);
				}
			}
		}
	}

	private boolean inBounds(TouchEvent event, int x, int y, int width,
			int height) {
		if (event.x > x && event.x < x + width - 1 && event.y > y
				&& event.y < y + height - 1)
			return true;
		else
			return false;
	}

	@Override
	public void paint(float deltaTime) {
		Graphics g = game.getGraphics();
		if (menu_draw_counter <= 140 && menu_draw_counter > 130)
			g.drawImage(Assets.menu1, 0, 0);
		if (menu_draw_counter <= 130 && menu_draw_counter > 120)
			g.drawImage(Assets.menu2, 0, 0);
		if (menu_draw_counter <= 120 && menu_draw_counter > 110)
			g.drawImage(Assets.menu3, 0, 0);
		if (menu_draw_counter <= 110 && menu_draw_counter > 100)
			g.drawImage(Assets.menu4, 0, 0);
		if (menu_draw_counter <= 100 && menu_draw_counter > 90)
			g.drawImage(Assets.menu5, 0, 0);
		if (menu_draw_counter <= 90 && menu_draw_counter > 80)
			g.drawImage(Assets.menu6, 0, 0);
		if (menu_draw_counter <= 80 && menu_draw_counter > 70)
			g.drawImage(Assets.menu7, 0, 0);
		if (menu_draw_counter <= 70 && menu_draw_counter > 60)
			g.drawImage(Assets.menu8, 0, 0);
		if (menu_draw_counter <= 60 && menu_draw_counter > 50)
			g.drawImage(Assets.menu9, 0, 0);
		if (menu_draw_counter <= 50 && menu_draw_counter > 40)
			g.drawImage(Assets.menu10, 0, 0);
		if (menu_draw_counter <= 40 && menu_draw_counter > 30)
			g.drawImage(Assets.menu11, 0, 0);
		if (menu_draw_counter <= 30 && menu_draw_counter > 20)
			g.drawImage(Assets.menu12, 0, 0);
		if (menu_draw_counter <= 20 && menu_draw_counter > 10)
			g.drawImage(Assets.menu13, 0, 0);
		if (menu_draw_counter <= 10 && menu_draw_counter > 0)
			g.drawImage(Assets.menu14, 0, 0);

		g.drawImage(Assets.buttonUnpressed1, 114, 232);
		g.drawImage(Assets.buttonUnpressed2, 314, 232);
		g.drawImage(Assets.buttonUnpressed3, 514, 232);

		if (Assets.theme.isPlaying())
			g.drawImage(Assets.musicON, 730, 20);
		else
			g.drawImage(Assets.musicOFF, 730, 20);


		menu_draw_counter--;
		if (menu_draw_counter == 0)
			menu_draw_counter = MENU_DRAW_COUNTER;

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
		android.os.Process.killProcess(android.os.Process.myPid());

	}
}
