package com.android.eatemupgame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.android.framework.Screen;
import com.android.framework.implementation.AndroidGame;

public class SampleGame extends AndroidGame {

	public static String map;
	boolean firstTimeCreate = true;

	@Override
	public Screen getInitScreen() {

		if (firstTimeCreate) {
			Assets.load(this);
			firstTimeCreate = false;
		}

		InputStream is = getResources().openRawResource(R.raw.map2);
		map = convertStreamToString(is);

		return new SplashLoadingScreen(this);

	}

	@Override
	public void onBackPressed() {
		getCurrentScreen().backButton();
	}

	private static String convertStreamToString(InputStream is) {

		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append((line + "\n"));
			}
		} catch (IOException e) {

		} finally {
			try {
				is.close();
			} catch (IOException e) {

			}
		}
		return sb.toString();
	}

	@Override
	public void onResume() {
		super.onResume();

		Assets.theme.play();

	}

	@Override
	public void onPause() {
		super.onPause();
		Assets.theme.pause();

	}

}