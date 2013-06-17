/*package com.android.eatemupgame;

public class Input implements 
		Constants {
	
};

package com.android.eatemupgame;

public class Input implements 
		Constants {
	
};

/*package com.android.eatemupgame;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Input implements MouseListener, MouseMotionListener,
		Constants {
//	private int coordinationX = SCREEN_RESOLUTION_X / 2;
//	private int coordinationY = SCREEN_RESOLUTION_Y / 2;
	private boolean pressed = false;
	private Dino monty;
	
	// Constructor
	public Input(Dino Monty) {
		monty = Monty;
	}

	// MouseListener
	@Override
	public void mouseClicked(MouseEvent e) {
		pressed = false;
		System.out.println("clicked");
	}

	@Override
	public void mousePressed(MouseEvent e) {
		monty.setMoveToX(e.getX() - SCREEN_RESOLUTION_X / 2);
		monty.setMoveToY(e.getY() - SCREEN_RESOLUTION_Y / 2);		
		pressed = true;
		monty.setStatus(STATUS.WALK);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		pressed = false;
		monty.setStatus(STATUS.IDLE);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	// MouseMotionListener
	@Override
	public void mouseDragged(MouseEvent e) {
		monty.setMoveToX(e.getX() - SCREEN_RESOLUTION_X / 2);
		monty.setMoveToY(e.getY() - SCREEN_RESOLUTION_Y / 2);	
		pressed = true;
		monty.setStatus(STATUS.WALK);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

	public boolean isPressed() {
		return pressed;
	}

}
*/