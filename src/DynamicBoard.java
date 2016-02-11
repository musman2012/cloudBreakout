import java.awt.Color;
import java.awt.Rectangle;
import java.util.StringTokenizer;


public class DynamicBoard {
	
	// Instance of this class would be in Game Board and 
	// this will be containing Array of bricks itself
	
	
	String levelInstructions;
	
	int numberOfBricks, brickPatternType, noOfRows, slowness;
	
	int [] colors;
	
	Brick [] bricks;
	
	
	DynamicBoard(String instructions)
	{
	//	numberOfBricks = bricks;
		levelInstructions = instructions;
		parseIntructions(instructions);
		this.bricks = new Brick [numberOfBricks];
		
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
	
	public int [] getRowColors()
	{
		return colors;
	}
	
	private void parseIntructions(String instructions)
	{

		String delimiter = "|";
		
		StringTokenizer st = new StringTokenizer(instructions, delimiter);
		
		String str_bricksPattern = st.nextToken();
		
		String str_slowness = st.nextToken();		// Slowness
		
		String str_bricks =  st.nextToken();
		
		String str_rows =  st.nextToken();
		
		String str_colors = st.nextToken();
		
		brickPatternType = Integer.parseInt(str_bricksPattern);
		
		slowness = Integer.parseInt(str_slowness);
		
		numberOfBricks = Integer.parseInt(str_bricks);
		
		noOfRows = Integer.parseInt(str_rows);
		
		initializeColors(str_colors);
		
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
			int index = 0;
			
			for(int i=0; i<tempColor.length(); i++)
			{
				if(tempColor.charAt(i) < 65)
					index = Integer.parseInt(tempColor.charAt(i)+"");
				switch (tempColor.charAt(i))
				{
					case 'A':
						index = 10;
						break;
					case 'B':
						index = 11;
						break;
					case 'C':
						index = 12;
						break;	
				}	
				
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
		if(brickPatternType == 2)
		{
			typeTwoPattern();
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
			if(colors[i] == 1)				// RED color
				color = Color.RED;
			else if(colors[i] == 2)			// BLACK color
				color = Color.BLACK;
			else if(colors[i] == 3)			// BLUE color
				color = Color.BLUE;
			else if(colors[i] == 4)			// YELLOW color
				color = Color.YELLOW;
			
			for(int j = 0; j < cols; j++)
			{
				bricks[index] = new Brick((starting_x + (j * (widthOfBricks + 2))), (i * (heightOfBricks + 2)) + starting_y, color);
				System.out.println(starting_x + (j * (widthOfBricks + 2))+""+color.toString());
				index ++;
			}
		}
	}
	
	/**
	 * Instruction set for this is 2|10|108|12|1,2
	 * Here 1 and 2 are indicating number of squares and their colors
	 * ******** We need to destroy some of the bricks right from the start *********
	 */
	
	private Color decideColor(int squareNumber) // Outer most square is number 1.
	{
		switch (colors[squareNumber-1])
		{
		case 1:
			return Color.RED;
			
		case 2:
			return Color.BLACK;
			
		case 3:
			return Color.BLUE;
			
		case 4:
			return Color.YELLOW;
			
		}
		return Color.BLACK;
	}
	
	private void typeTwoPattern()
	{
		int cols = numberOfBricks/noOfRows, starting_x = 110, starting_y = 40, index = 0;
		
		Color color = Color.BLACK;
		
		int heightOfBricks = 20, widthOfBricks = 45;
		
		for(int i = 0; i < noOfRows; i++)
		{
			
			for(int j = 0; j < cols; j++)
			{
				if((i<2 || i>=noOfRows-2) || (j<2 || j>cols-2))  // outer square top and bottom
				{
					color = decideColor(1);
				}
				else
					color = decideColor(2);
				
				bricks[index] = new Brick((starting_x + (j * (widthOfBricks + 2))), (i * (heightOfBricks + 2)) + starting_y, color);
				System.out.println(starting_x + (j * (widthOfBricks + 2)));
				
				// Destroying third and third last rows
				if((i == 2 || i == noOfRows-3) && (j>0 && j<cols-1))
				{
					bricks[index].destroy();
				}
				
				if ((i>1 && i<noOfRows-2) && (j==1 || j==cols-2))
				{
					bricks[index].destroy();
				}
				index++;
			}
		}

	}

	
	public void getBricksToDraw(Rectangle [] brickObjects, Color [] colorArray)
	{
		for(int i = 0; i < numberOfBricks; i++)
		{
			brickObjects[i] = bricks[i].getRectangleToDraw();
			colorArray[i] = bricks[i].getColor();
		}
		
	}
	
	public int getNumberOfBricks()
	{
		return numberOfBricks;
	}
	
	public int getSlowness()
	{
		return slowness;
	}
	
	public boolean checkCollisionwithBricks(Ball ball)
	{
		// Check collision with all of the bricks
		for(int i = 0; i < bricks.length; i++)
		{
			if (ball.getCircleToDraw().intersects(bricks[i].getRectangleToDraw()))
			{
				bricks[i].destroy();
				if(ball.getX() > bricks[i].getX()+42 || ball.getX()+7 < bricks[i].getX()){
					ball.setXDir(ball.getXDir()*-1);
					System.out.println("Side tay lagi ooo!!!");
					ball.setYDir(ball.getYDir()*-1);
				}
					
				ball.setYDir(ball.getYDir()*-1);
				return true;
			}
		}
		return false;

	}

	

}
