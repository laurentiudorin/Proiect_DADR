package CLIENT;

import java.io.IOException;

public class ApplicationClient {
	public static void main(String[] args) throws IOException {
	
		SimpleFileClient client = new SimpleFileClient();
		
		while (true){
		client.ConnectToServer();
		}
		
		
	}
}
