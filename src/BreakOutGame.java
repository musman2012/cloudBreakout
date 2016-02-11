
import javax.swing.JFrame;

public class BreakOutGame extends JFrame{

	int SCREEN_WIDTH = 612, SCREEN_HEIGHT = 600;
	
	String instructions;
	
	public static void main(String[] a) {

		BreakOutGame game = new BreakOutGame();
		
		Player player = new Player();
		
		game.instructions = player.getLevelInstructions();
		
		System.out.println("Instructions received "+game.instructions);
		
		game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
		game.setBounds(30, 30, game.SCREEN_WIDTH, game.SCREEN_HEIGHT);			// (starting x, starting y, increase in x, y)
	    
		game.add(new GameBoard(game.instructions));
		
		// Second pattern color ==> Write square number on the Nth Index where N represents color
		// 1  ==> RED, 2 ==> BLACK, 3 ==> BLUE, 4 ==> YELLOW
		
		// Sample for type two "2|10|108|12|1,8,2,8"
		// Sample for type one "1|10|80|8|12,34,56,78"
		
		// Colors are not correct
		
		game.setVisible(true);
		
	    
	  }


}