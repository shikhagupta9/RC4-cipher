import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;



import java.io.*; 
public class Bob {

	 	private Socket          socket   = null; 
	    private ServerSocket    server   = null; 
	    private DataInputStream in       =  null; 
	  
	    // constructor with port 
	    public Bob(int port) 
	    { 
            
            String line="";
            
            
            String s=new String(rc4.secretkey);
            rc4 rc =new rc4(s);
            line="";
	        // starts server and waits for a connection 
	        try
	        {
	        	
	            server = new ServerSocket(port); 
	            System.out.println("Server started"); 
	  

	            System.out.println("Waiting for a client ..."); 
	            socket = server.accept(); 
	            System.out.println("Client accepted");
	            // takes input from the client socket 
	            in = new DataInputStream( 
	                new BufferedInputStream(socket.getInputStream())); 
	  
	           
	            String pt="";
	            // reads message from client until "Over" is sent 
	            while (!pt.equals("Over")) 
	            { 
	                try
	                { 
	                	
	                    line=in.readUTF();
	                    System.out.println("Received CT " + line);
	                    byte[] ct=line.getBytes();
	                    rc4.displayinHex(ct);
	                    System.out.println("\n **********        DECRYPTING...........    ************\n");
	                    byte[] deText = rc.decrypt(line.getBytes());
	                    
	    	            pt=new String(deText );
	    	            System.out.println("Decrypted CT " + pt);
	    	            rc4.displayinHex(deText);

	                    
	  
	                } 
	                catch(IOException i) 
	                { 
	                    System.out.println(i); 
	                } 
	            } 
	            System.out.println("Closing connection"); 
	  
	            // close connection 
	            socket.close(); 
	            in.close(); 
	        } 
	        catch(IOException i) 
	        { 
	            System.out.println(i); 
	        } 
	    } 
	  
	    public static void main(String args[]) 
	    { 
	        Bob server = new Bob(5000); 
	    } 

}
