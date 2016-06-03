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

  public final static int SOCKET_PORT_SEND = 13266;  // you may change this
  public final static int SOCKET_PORT_RECIVE = 13267;  // you may change this
  public final static String FILE_TO_SEND = "Calculator_Server/Program.txt";  // you may change this
  public final static String FILE_TO_RECEIVED = "Calculator_Server/Program_receptionat_Delacilent.txt";
  public final static int FILE_SIZE = 6022386;  // file size temporary hard coded
  												// should bigger than the file to be downloaded
  
  ///***Variable fot SEND ***\\\
  protected FileInputStream fis = null;
  protected static BufferedInputStream bis = null;
  protected static OutputStream os = null;
  protected static ServerSocket servsock_send = null;
  protected static Socket sock_send = null;
 
  static SendFile send = new SendFile();
 
  ///***Variable fot RECIVE ***\\\
  protected int bytesRead;
  protected int current = 0;
  protected static FileOutputStream fos = null;
  protected static BufferedOutputStream bos = null;
  protected static ServerSocket servsock_recive = null;
  protected static Socket sock_recive = null;

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
      if (servsock_send != null) servsock_send.close();
      if (servsock_recive != null) servsock_recive.close();
    }
  }
  

		///*** SendFile***\\\
	  public  void SendFile() throws IOException{
		  servsock_send = new ServerSocket(SOCKET_PORT_SEND);
	      while (true) { 
	    	
	        System.out.println("Waiting to send...");
	        try {
	          sock_send = servsock_send.accept();
	          System.out.println("Accepted connection : " + sock_send);
	          
	          send.Sending();
	         
	        }
	        finally {
	          if (bis != null) bis.close();
	          if (os != null) os.close();
	          if (sock_send!=null) sock_send.close();
	        }
	      }
	  }
	      
      ///*** ReciveFiel***\\\
      public  void ReciveFile() throws IOException{
    	  servsock_recive = new ServerSocket(SOCKET_PORT_RECIVE);
	      while (true) { 
	    	  
	          System.out.println("Waiting to recive...");
	          try {
	            sock_recive = servsock_recive.accept();
	            System.out.println("Accepted connection : " + sock_recive);
	          
	            recive.Reciving();
	             
	          }
	          finally {
	            if (bis != null) bis.close();
	            if (os != null) os.close();
	            if (sock_recive!=null) sock_recive.close();
	          }
	      }
      }
  
  
  
}