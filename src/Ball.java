import java.awt.Rectangle;
import java.awt.geom.*;;

public class Ball {
	
	private int starting_x, starting_y, diameter, directionOfX = 1, directionOfY = -1;

	Ball()
	{
		starting_x = 100;
		starting_y = 100;
		diameter = 10;
	}
	Ball(int x, int y, int diameter)
	{
		starting_x = x;
		starting_y = y;
		this.diameter = diameter;
	}
	public Ellipse2D getCircleToDraw()
	{
		Ellipse2D.Double ball = new Ellipse2D.Double();
		ball.height = diameter;
		ball.width = diameter;
		ball.x = starting_x;
		ball.y = starting_y;
		
		return ball;
	}
	
	public int getXDir()
	{
		return directionOfX;
	}
	
	public int getYDir()
	{
		return directionOfY;
	}
	
	public void setXDir(int x)
	{
		directionOfX = x;
	}
	
	public void setYDir(int y)
	{
		directionOfY = y;
	}
	
	public int getX()
	{
		return starting_x;
	}
	
	public int getY()
	{
		return starting_y;
	}
	
	public void moveTheBall()
	{
		starting_x = starting_x + directionOfX;
		starting_y = starting_y + directionOfY;
				
	//	System.out.println(starting_x);
		
		if (starting_x == 600 - diameter)
			directionOfX = -1;
		else if(starting_x == -1)
			directionOfX = 1;
		if (starting_y == 565 - diameter)
			GameBoard.gameLost();
		else if(starting_y == -1)
			directionOfY = 1;
		
	}
	
	
//	public boolean checkCollision()
//	{
//		
//	}
	
}
