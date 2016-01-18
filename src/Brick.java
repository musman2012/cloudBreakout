import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JComponent;

public class Brick {
	Color color;
	int x, y;
	int WIDTH_OF_BRICK = 45;
	int HEIGHT_OF_BRICK = 20;
	
	Brick(int x, int y, Color color){
		this.color = color;
		this.x = x;
		this.y = y;
	}
	
	public Rectangle getRectangleToDraw()
	{
		return new Rectangle(x,y,WIDTH_OF_BRICK,HEIGHT_OF_BRICK);
		
	}
	
	public void destroy()
	{
		WIDTH_OF_BRICK = 0;
		HEIGHT_OF_BRICK = 0;
	}
	
	
	
	
}
