package SERVER;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ReciveFile extends SimpleFileServer{

	
	public void Reciving() throws IOException{
	  // receive file
	  byte [] mybytearray  = new byte [FILE_SIZE];
	  InputStream is = sock.getInputStream();
	  fos = new FileOutputStream(FILE_TO_RECEIVED);
	  bos = new BufferedOutputStream(fos);
	  bytesRead = is.read(mybytearray,0,mybytearray.length);
	  current = bytesRead;
	
	  do {
	     bytesRead =
	        is.read(mybytearray, current, (mybytearray.length-current));
	     if(bytesRead >= 0) current += bytesRead;
	  } while(bytesRead > -1);
	
	  bos.write(mybytearray, 0 , current);
	  bos.flush();
	  System.out.println("File " + FILE_TO_RECEIVED
	      + " downloaded (" + current + " bytes read)");
	}
	
}
