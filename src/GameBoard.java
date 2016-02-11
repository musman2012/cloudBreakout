import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Time;

import javax.swing.JLabel;
import javax.swing.JPanel;
//import javax.swing.Timer;
import java.util.Timer;
import java.util.TimerTask;

public class GameBoard extends JPanel{
		
	//	Brick temp = new Brick(40, 40, Color.RED);
		
		Container t = new Container();
		
		Slider s;
		
		int slowness;
		
		int numberOfBricks;
		
	//	Brick [] bricks = new Brick [numberOfBricks];
		
		Thread thread = new Thread();
		
		Ball ball;
		
		DynamicBoard dynamicBoard;
		
		Integer score = 0;
		
		String levelInstruction;
		
		JLabel scoreLabel = new JLabel("Score : 0");
		
		int noOfActiveBricks;				// Would be useful in isWin Condition
		
		GameBoard(String instructions)
		{
			super();
			setBounds(30, 30, 600, 600);
			
			// "1|10|50|5|123,45?" ==> Type|Slowness | Total bricks|No of rows|Red rows|Green|Blue|Yellow
			
			levelInstruction = instructions;
			
	//		parseIntructions(instructions);
			
			dynamicBoard = new DynamicBoard(levelInstruction);
			
			numberOfBricks = dynamicBoard.getNumberOfBricks();
			
			slowness = dynamicBoard.getSlowness();
			
			initializeBoard();
			
			noOfActiveBricks = numberOfBricks;
			
			// Use a separate loop to get total number of bricks and etc
			
	//		ball.moveTheBall();
	//		thread.start();
			Timer timer = new Timer();
	//		startTheGame();
	//		scoreLabel.setText(score.toString());
			timer.scheduleAtFixedRate(new myTask(), 200, slowness);
			
			add(scoreLabel);
			
		}
		
//		private void parseIntructions(String instructions)
//		{
//			String str_slowness = instructions.substring(2,4);
//			
//			String str_bricks = instructions.substring(5,7);
//			
//			slowness = Integer.parseInt(str_slowness);
//			
//			numberOfBricks = Integer.parseInt(str_bricks);
//		}
		
		private class myTask extends TimerTask{

			@Override
			public void run() {
				ball.moveTheBall();
				checkBallCollsion();
				repaint();
				checkWinCondition();
			}
			
		}
		
		public void checkWinCondition()
		{
			if(noOfActiveBricks == 0)
			{
				System.out.println("Player have won the game!!!");
				System.exit(1);
			}
		}
		
		
		/**
		 * This method will just add the required elements (Bricks, slider and ball) to board. 
		 */
		public void initializeBoard()
		{
			setFocusable(true);
			addKeyListener(new MyListener());		// add listener
			
			int sliderInitalX = (int) ((Math.random() * 1000 + 100) % 500);
			int sliderInitalY = 540;
			
			s = new Slider(sliderInitalX, sliderInitalY);				// initialize slider
			dynamicBoard.addBricks();				// Initializing Bricks positions
			
			int ballInitalX = (int) ((Math.random() * 1000 + 100) % 500);
			ball = new Ball(sliderInitalX + 20, sliderInitalY - 10, 10);
			
			this.add(scoreLabel, 0, 0);
			
		}
		
	
		public void paintComponent(Graphics g) {
				
			   
				super.paintComponent(g);
			    Graphics2D graphics = (Graphics2D) g;
			    
			    drawSprites(graphics);
			    
			    
			    Toolkit.getDefaultToolkit().sync();
			    
			    
		}
		
		/**
		 * This function would draw the board,
		 * 
		 * It will call Ball, Paddle and Dynamic Board classes
		 * to get rectangles and circle, and will
		 * Draw them using Graphics2D object.
		 */
		
		public void drawSprites(Graphics2D g)
		{
			Rectangle brickObjects [] = new Rectangle[numberOfBricks];
			
		//	int [] colorArray = dynamicBoard.getRowColors();
			
			Color [] colorArray = new Color [numberOfBricks];
			
			int rows = colorArray.length, counter = 0;
			
			int cols = numberOfBricks/rows;
			
//			g.clearRect(130, 130, 200, 30);
			
			g.fill(s.getRectangleToDraw());
			
	//		g.fill(temp.getRectangleToDraw());
			
			g.fill(ball.getCircleToDraw());
			
			scoreLabel.setText("Score : "+score);
			
			g.drawString(score.toString(), 0, 0);
			
			dynamicBoard.getBricksToDraw(brickObjects, colorArray);
			
	//		dynamicBoard.
			
			for(int i = 0; i<rows; i++)
			{
//				if(colorArray[i] == 1)				// RED color
//					g.setColor(Color.RED);
//				else if(colorArray[i] == 2)			// BLACK color
//					g.setColor(Color.BLACK);
//				else if(colorArray[i] == 3)			// BLUE color
//					g.setColor(Color.BLUE);
//				else if(colorArray[i] == 4)			// YELLOW color
//					g.setColor(Color.YELLOW);
				g.setColor(colorArray[i]);
				
				for(int j = 0; j<cols; j++)
				{
					g.fill(brickObjects[counter]);
					counter++;
				}
			
			}
		}
		
		private class MyListener implements KeyListener {

			 @Override
			public void keyReleased(KeyEvent e)
			{
				System.out.println("Pressed!!!");
		//		s.move(e);
				
		//		repaint();
				// call the method of slider here
			}
			 @Override
			public void keyPressed(KeyEvent e) 
			{
				 System.out.println("Pressed!!!");
				 s.move(e);
		//		 ball.moveTheBall();
		//		 repaint();
		    }
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				 System.out.println("Pressed!!!");
			}

		}
		
		public void checkBallCollsion(){
			int starting_x = s.getRectangleToDraw().x;
			int starting_y = s.getRectangleToDraw().y;
			int ending_x = 60, ending_y = 10;
			
			System.out.println(starting_x +"                ====== X ");
			// Check collision with the slider
			if(ball.getCircleToDraw().intersects(starting_x, starting_y, ending_x, ending_y))
			{
				System.out.println("Intersected!");
			//	System.exit(1);
			//	ball.setXDir(ball.getXDir()*-1);
				ball.setYDir(ball.getYDir()*-1);
			}
			
			if(dynamicBoard.checkCollisionwithBricks(ball))
			{
				noOfActiveBricks --;
				score += 5;

			}
				
		}
		
		/**
		 * This function should be static as we may need it to be at class level for all the objects of the class.
		 * (Usefull when we need to convert it as a multi-player game)
		 */
		
		public static void gameLost()
		{
			System.out.println("Player have lost the game!!");
			System.exit(1);
		}

	
}
