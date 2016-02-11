import java.io.*; 
import java.net.*; 

public class CommunicationModule {
	

    String username = "Usman", ip = "52.192.85.179";		// 52.69.91.15 
    String levelInstructions;
    Socket clientSocket;
    DataOutputStream outToServer;
    DataInputStream inFromServer;
    InputStream sin;
    
    
    public CommunicationModule() {

  	        try{     
  	        
  	        clientSocket = new Socket(ip,1234);

  	        outToServer = 
  	          new DataOutputStream(clientSocket.getOutputStream()); 
  	        
  	       sin = clientSocket.getInputStream();

  	       inFromServer = new DataInputStream(sin);

	} catch(IOException e)
  	  {
    	   
  	  }	
    }

    public String getLevelInstructions(String username)
    {
    // this method will be called from BreakoutGame class by giving username of user as argument.
    // Just for testing, username is being 	
    	
    System.out.print("Enter message: ");
//    try{
//    	
//    //	this.username = username; 
//
//    	System.out.println(outToServer);
//    	
//    	
//    	outToServer.writeBytes(username);
//    	
//    	System.out.println("Bytes Sent!!!"+username);
//
//    	    	
//        levelInstructions = inFromServer.readUTF();
//
//        System.out.println("FROM SERVER: " + levelInstructions); 
//      
//        clientSocket.close();
//
//    } catch(IOException e)
//  	  {
//    	   
//  	  }	
    
    try{


    	       System.out.print("Enter message: ");
    	       username = "usman"; 

    	        outToServer.writeBytes(username + '\n'); 

    	        levelInstructions = inFromServer.readUTF();

    	        System.out.println("FROM SERVER: " + levelInstructions); 
    	      
    	        clientSocket.close();
    	        
    	  } catch(IOException e)
    	  {
    		  
    	  } 
    	       
    
    	        
    return levelInstructions;
    
    } 
        

}

