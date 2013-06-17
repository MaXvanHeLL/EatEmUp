package com.android.eatemupgame;



//import java.awt.Graphics;
//import java.awt.Image;

import com.android.framework.Image;
import com.android.framework.Graphics;

import java.net.URL;
import java.util.Vector;

public class Draw implements Constants {
	private Dino monty;
	private Vector enemies;
	private Background background;
	
	private Image image, montyImage, backgroundImage;
	private URL base; // for loading the images
	private Graphics second;
	
	public static int drawImageCounter = 0;
	
	Draw(Dino Monty, Vector Enemies, Background Background)
	{
		monty = Monty;
		enemies = Enemies;
		background = Background;
		
		montyImage = getImage(base, "data/dino_breathing.png"); // maybe change
		backgroundImage = getImage(base, "data/background.png");
		
		
		
	}

	public Draw() {
		// TODO Auto-generated constructor stub
	}

	private Image getImage(URL base2, String string) {
		// TODO Auto-generated method stub
		return null;
	}
/*
	public void draw(){
		
		
		g.drawImage(backgroundImage, monty.getScrollBackgroundX(),
				monty.getScrollBackgroundY(), this);


		g.drawImage(montyImage, monty.getCenterX() - DINO_RESOLUTION_X / 2,
				monty.getCenterY() - DINO_RESOLUTION_Y / 2, this);
		
	}
	

	@Override
	public void update(Graphics g) {
		if (image == null) {
			image = createImage(this.getWidth(), this.getHeight());
			second = image.getGraphics();
		}

		second.setColor(getBackground());
		second.fillRect(0, 0, getWidth(), getHeight());
		second.setColor(getForeground());
		paint(second);

		g.drawImage(image, 0, 0, this);
	}

*/

	public int getDrawImageCounter() {
		return drawImageCounter;
	}

	public void setDrawImageCounter(int drawImageCounter) {
		Draw.drawImageCounter = drawImageCounter;
	}
	

}
