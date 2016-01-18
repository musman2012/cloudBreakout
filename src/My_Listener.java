import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;



public class My_Listener extends KeyAdapter implements KeyListener {
	
	public My_Listener() {
		// TODO Auto-generated constructor stub
	}

	public void keyPressed(KeyEvent e)
	{
		System.out.println("Pressed!!!");
		// call the method of slider here
	}
	
	public void keyReleased(KeyEvent e) 
	{
		
    }

}
