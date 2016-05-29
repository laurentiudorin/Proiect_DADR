package CLIENT;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class SendFile extends SimpleFileClient{

	public void Sending() throws IOException{
	
      // send file
      File myFile = new File (FILE_TO_SEND);
      byte [] mybytearray  = new byte [(int)myFile.length()];
      fis = new FileInputStream(myFile);
      bis = new BufferedInputStream(fis);
      bis.read(mybytearray,0,mybytearray.length);
      os = sock.getOutputStream();
      System.out.println("Sending " + FILE_TO_SEND + "(" + mybytearray.length + " bytes)");
      os.write(mybytearray,0,mybytearray.length);
      os.flush();
      System.out.println("Done.");
	}
}
