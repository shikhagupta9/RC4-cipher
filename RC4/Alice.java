import java.net.*;
import java.nio.charset.StandardCharsets;
import java.io.*; 
public class Alice {

	private Socket socket            = null; 
    private DataInputStream  input   = null; 
    private DataOutputStream o   = null; 
    private rc4 rc;
   
    // constructor to put ip address and port 
    public Alice(String address, int port) throws IOException 
    { 
        // establish a connection 
        try
        { 
            socket = new Socket(address, port); 
            System.out.println("Connected"); 
            
          

            input  = new DataInputStream(System.in); 
            
  
            // sends output to the socket 
            o    = new DataOutputStream(socket.getOutputStream()); 
        } 
        catch(UnknownHostException u) 
        { 
            System.out.println(u); 
        } 
        catch(IOException i) 
        { 
            System.out.println(i); 
        } 
  
        // string to read message from input 
        String line = ""; 
        
        String s=new String(rc4.secretkey);
         rc = new rc4(s);
        
    	
    	 
        // keep reading until "Over" is input 
        while (!line.equals("Over")) 
        { 
        	System.out.println("ENTER  PLAINTEXT :");
        	try {
				line = input.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
        	
        		
		        		System.out.println("INPUT PT is " + line);
		              
		                byte[] enText = rc.encrypt(line.getBytes());
		                String encrypted = new String(enText);
		                System.out.println("\n *****************      ENCRYPTING..............            *******\n");
		                System.out.println("Encrypted PT : " + encrypted );
		                rc4.displayinHex(enText);
		                o.writeUTF(encrypted);
		            
        	 
        }
  
        // close the connection 
        try
        { 
            input.close(); 
            o.close(); 
            socket.close(); 
        } 
        catch(IOException i) 
        { 
            System.out.println(i); 
        } 
    } 
  
    public static void main(String args[]) throws IOException 
    { 
        Alice client = new Alice("127.0.0.1", 5000); 
    } 
}
