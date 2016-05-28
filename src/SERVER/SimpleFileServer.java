package SERVER;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleFileServer {

  public final static int SOCKET_PORT = 13267;  // you may change this
  public final static String FILE_TO_SEND = "Calculator_Server/Program.txt";  // you may change this

  protected FileInputStream fis = null;
  protected static BufferedInputStream bis = null;
  protected static OutputStream os = null;
  protected static ServerSocket servsock = null;
  protected static Socket sock = null;
  
  static SendFile send = new SendFile();
  
  public void  WaitConnect() throws IOException {
    
    try {
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
    finally {
      if (servsock != null) servsock.close();
    }
  }
}