import java.awt.Color;
import java.awt.Rectangle;
import java.util.StringTokenizer;


public class DynamicBoard {
	
	// Instance of this class would be in Game Board and 
	// this will be containing Array of bricks itself
	
	
	String levelInstructions;
	
	int numberOfBricks, brickPatternType, noOfRows, redRows, greenRows, yellowRows;
	
	int [] colors;
	
	Brick [] bricks;
	
	
	DynamicBoard(int bricks, String instructions)
	{
		numberOfBricks = bricks;
		levelInstructions = instructions;
		this.bricks = new Brick [numberOfBricks];
		parseIntructions(instructions);
					// This will hold the color of each row
	}
	
	/**
	 * 
	 * @param instructions
	 * 
	 * Method will take instruction string as parameter and will extract
	 * the required information for rendering the level background.
	 * 
	 */
	
	private void parseIntructions(String instructions)
	{

		String delimiter = "|";
		
		StringTokenizer st = new StringTokenizer(instructions, delimiter);
		
		String str_bricksPattern = st.nextToken();
		
		st.nextToken();		// Slowness
		
		String str_bricks =  st.nextToken();
		
		String str_rows =  st.nextToken();
		
		String str_colors = st.nextToken();
		
		brickPatternType = Integer.parseInt(str_bricksPattern);
				
		numberOfBricks = Integer.parseInt(str_bricks);
		
		noOfRows = Integer.parseInt(str_rows);
		
		initializeColors(str_colors);
		
//		while(str_colors.charAt(counter) != '?')
//		{
//			counter ++;
//			colors [counter]	
//				
//		}
	}
	
	private void initializeColors(String str_colors)
	{
		colors = new int [noOfRows];
		
		int colorIndicator = 1;
		
		for(int i = 0;i < noOfRows; i++)
			colors [i] = 0;		
		
		StringTokenizer st = new StringTokenizer(str_colors, ",");
		
		while(st.hasMoreElements())
		{
			String tempColor = st.nextToken();
			
			for(int i=0; i<tempColor.length(); i++)
			{
				int index = Integer.parseInt(tempColor.charAt(i)+"");
				index--;
				colors[index] = colorIndicator;
			}
			colorIndicator++;
		}
	}
	
	/**
	 * This method is placing the bricks on their position and giving them color values
	 * It must use color information of instructions, Pattern type, no. of rows
	 *  
	 */
	
	public void addBricks()
	{
		if(brickPatternType == 1)
		{
			typeOnePattern();
			return;
		}
	}
	
	private void typeOnePattern()
	{
		int cols = numberOfBricks/noOfRows, starting_x = 50, starting_y = 40, index = 0;
		
		Color color = Color.yellow;
		
		int heightOfBricks = 20, widthOfBricks = 45;
		
		for(int i = 0; i < noOfRows; i++)
		{
			if(colors[i] == 1)
				color = Color.red;
			else if(colors[i] == 2)
				color = Color.green;
			
			for(int j = 0; j < cols; j++)
			{
				bricks[index] = new Brick((starting_x + (j * (widthOfBricks + 2))), (i * (heightOfBricks + 2)) + starting_y, color);
				System.out.println(starting_x + (j * (widthOfBricks + 2))+""+color.toString());
				index ++;
			}
		}

	}
	
	private void typeTwoPattern()
	{
		int row = 0, starting_x = 110, starting_y = 40, index = 0;
		
		int heightOfBricks = 20, widthOfBricks = 45;
		
		for(int i = 0; i < noOfRows; i++)
		{
			for(int j = 0; j < 8; j++)
			{

				bricks[index] = new Brick((starting_x + (j * (widthOfBricks + 2))), (i * (heightOfBricks + 2)) + starting_y, Color.RED);
				System.out.println(starting_x + (j * (widthOfBricks + 2)));
				index ++;
			}
		}

	}

	
	public void getBricksToDraw(Rectangle [] brickObjects)
	{
		for(int i = 0; i < numberOfBricks; i++)
		{
			brickObjects[i] = bricks[i].getRectangleToDraw();
		}
		
	}
	
	public boolean checkCollisionwithBricks(Ball ball)
	{
		// Check collision with all of the bricks
		for(int i = 0; i < bricks.length; i++)
		{
			if (ball.getCircleToDraw().intersects(bricks[i].getRectangleToDraw()))
			{
				bricks[i].destroy();
				ball.setYDir(ball.getYDir()*-1);
				return true;
			}
		}
		return false;

	}

	

}
