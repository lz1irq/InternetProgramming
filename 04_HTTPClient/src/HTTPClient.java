import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class HTTPClient {
	
	private final static int HTTP_PORT = 80;
	private final static String PROTOCOL_VERSION = "HTTP/1.1";
	
	private final static int STATUS_OK = 200;
	private final static int STATUS_MOVED_PERM = 301;
	private final static int STATUS_MOVED_TEMP = 302;
	
	private final static String HEADER_LOCATION = "Location";
	
	private  Socket serverSocket;
	private  BufferedReader in;
	private  PrintWriter out;
	
	private  String server;
	private  String host;
	
	public HTTPClient(String serverAddress) throws UnknownHostException, IOException {
		serverSocket = new Socket(serverAddress,HTTP_PORT);
		in = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
		out = new PrintWriter(serverSocket.getOutputStream());
		server = serverAddress;
		host = server;
	}
	
	//when we want to access a specific virtual host
	public HTTPClient(String serverAddress, String virtualHost) throws UnknownHostException, IOException {
		serverSocket = new Socket(serverAddress,HTTP_PORT);
		in = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
		out = new PrintWriter(serverSocket.getOutputStream());
		server = serverAddress;
		host = virtualHost;
	}
	
	public HTTPResponse request(String method, String path) throws IOException {
		sendRequest(method, path);
		HTTPResponse response = getReply();
		
		if(response.getStatus() == STATUS_MOVED_PERM || response.getStatus() == STATUS_MOVED_TEMP) {
			String newPath = response.getHeaderValue(HEADER_LOCATION);
			response = request(method, newPath);
			System.out.println("Path '" + path + "' has been moved to '" + newPath +"'\n");
		}
		
		return response;
	}
	
	private HTTPResponse getReply() throws IOException {
		final HTTPResponse response = new HTTPResponse();
		String statusCode = in.readLine().split(" ", 3)[1];
		response.setStatus(Integer.parseInt(statusCode));
		
		String next;
		while(!(next = in.readLine()).equals("")) {
			String[] header = next.split("(: )|(:)", 2);
			response.getHeaders().add(new HTTPHeader(header[0], header[1]));
		}
		
		if(response.getStatus() != STATUS_MOVED_PERM || response.getStatus() != STATUS_MOVED_TEMP) {
			//getting the body only works for fixed content-length websites - chunk transfer is not supported yet!
			if(response.getBody() != null) { 
				in.read(response.getBody());
			}
		}
		
		return response;
	}	
	
	public void disconnect() throws IOException {
		serverSocket.close();
	}
	
	private void sendRequest(String method, String path) {
		out.printf("%s %s %s\n", method, path, PROTOCOL_VERSION);
		out.printf("Host: %s\n", host);
		out.printf("\n");
		out.flush();
	}
}
