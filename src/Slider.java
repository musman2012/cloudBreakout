import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JComponent;

class Slider{
	
	/**
	 * This class will draw one Slider in the game at given x and y co-ordinates.
	 */
	private int x, y;							// x and y component of slider location
	private final int WIDTH_OF_SLIDER = 60;		// Slider's Width in pixels
	private final int HEIGHT_OF_SLIDER = 10;	// Slider's Height in pixels
	
	Slider(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	
	public Rectangle getRectangleToDraw()
	{
	//	System.out.println(x+"            X ");
		return new Rectangle(x, y, WIDTH_OF_SLIDER, HEIGHT_OF_SLIDER);
	}
	

	public void move(KeyEvent e){
		int event = e.getKeyCode();
		
		System.out.println(event);
		
		if(event == 37)
			this.x -= 5;
		else if(event == 39)
			this.x += 5;
		
		if (x > 600 - WIDTH_OF_SLIDER)
			x = 600 - WIDTH_OF_SLIDER;
		else if(x < 0)
			x = 0;
		
		
		// RightKey == 39, LK = 37
	}
	
	
  
}
