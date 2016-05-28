package SERVER;

import java.io.IOException;

public class ApplicationServer {

	public static void main(String[] args) throws IOException {
	
		SimpleFileServer servere = new SimpleFileServer();
		
		servere.WaitConnect();
		
	}
}
