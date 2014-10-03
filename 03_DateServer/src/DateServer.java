import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class DateServer extends Thread {
	
	private final static int SERVER_PORT = 1337;
	private final ServerSocket server;
	
	public DateServer() throws IOException {
		server = new ServerSocket(SERVER_PORT);
		System.out.println("Date Server is now running on port " + SERVER_PORT);
	}
	
	public void run() {
		boolean running = true;
		
		while(running) {
			try {
				final Socket client = server.accept();
				new ClientManager(client).run();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
