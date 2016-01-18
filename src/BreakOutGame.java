

import javax.swing.JFrame;


public class BreakOutGame extends JFrame{

	public static void main(String[] a) {

		BreakOutGame game = new BreakOutGame();
		
		game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    
		game.setBounds(30, 30, 600, 600);			// (starting x, starting y, increase in x, y)
	    
		// Color values are being injected correctly but Graphics are rendering only BLACK color!!
	    
		game.add(new GameBoard("1|10|50|5|123,45"));
		game.setVisible(true);
		
	    
	  }


}