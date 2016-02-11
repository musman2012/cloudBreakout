
public class Player {
	String username, name, password;
	
	CommunicationModule conn;
	
	Player()
	{
		username = "usman123";
		name = "Usman";
		password = "usman123";
		
		conn = new CommunicationModule();
	}
	
	public String getLevelInstructions()
	{
		return conn.getLevelInstructions(username);
	}
	
	
}
