package CLIENT;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleFileClient {

	  public final static int SOCKET_PORT_SEND = 13267;      // you may change this
	  public final static int SOCKET_PORT_RECIVE = 13266;
	  public final static String SERVER = "127.0.0.1";  // localhost
	  public final static String FILE_TO_RECEIVED = "Calculator_Client/Program_receptionat.txt";  // you may change this, I give a
	                                                                                              // different name because i don't want to
	                                                                                              // overwrite the one used by server...
	  public final static String FILE_TO_SEND = "Calculator_Client/Program_receptionat.txt";  // you may change this
	  
	  public final static int FILE_SIZE = 6022386; // file size temporary hard coded
	                                                // should bigger than the file to be downloaded
	
	 
	///***Variable fot RECIVE ***\\\
	  protected int bytesRead;
	  protected int current = 0;
	  protected static FileOutputStream fos = null;
	  protected static BufferedOutputStream bos = null;
	  protected static Socket sock = null;
	  
	  static ReciveFile recive = new ReciveFile();
	  
	  ///***Variable fot SEND ***\\\
	  protected FileInputStream fis = null;
	  protected static BufferedInputStream bis = null;
	  protected static OutputStream os = null;
	  protected static ServerSocket servsock = null;
	  
	  static SendFile send = new SendFile();
	  
	  
	  
	  
	  
	  public void ConnectToServer() throws IOException {
	    
		  Thread reciveThread = new Thread(new Runnable() {
			public void run() {
	
				try {
					ReciveFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		  
	   
	    Thread sendThread = new Thread(new Runnable() {
			public void run() {
				
				try {
					SendFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	    while (true){
	    reciveThread.start();
	    sendThread.start();
	    
	    if (sock != null) sock.close();
	    
	    
	    }
	  }
	  
	  
	  private void ReciveFile() throws IOException, InterruptedException{
		  while (true){
		///***Conect to teh server to recive the Virus***\\\
		    try {	
		    	sock = new Socket(SERVER, SOCKET_PORT_RECIVE);
		    	System.out.println("Connecting...");
		
		    	recive.Reciving();
		      
		      
		    }
		    finally {
		    	if (fos != null) fos.close();
		    	if (bos != null) bos.close();
		    	if (sock != null) sock.close();
		    }
		    Thread.sleep(1000);
		  }
		  
	  }
	  
	  private void SendFile()throws IOException, InterruptedException {
		while(true){
		Thread.sleep(1000);
		///***Conect to teh server to Send the Data***\\\
			try {	
				sock = new Socket(SERVER, SOCKET_PORT_SEND);
				System.out.println("Connecting...");
		
				send.Sending();
		      
		    }
		    finally {
		    	if (fos != null) fos.close();
		    	if (bos != null) bos.close();
		    	if (sock != null) sock.close();
		    }
		  }
	  }
	  
}