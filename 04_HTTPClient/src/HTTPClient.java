import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
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
		server = serverAddress;
		host = server;
	}
	
	//when we want to access a specific virtual host
	public HTTPClient(String serverAddress, String virtualHost) throws UnknownHostException, IOException {
		server = serverAddress;
		host = virtualHost;
	}
	
	public HTTPResponse request(String method, String path, String body) throws IOException {
		serverSocket = new Socket(server,HTTP_PORT);
		in = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
		out = new PrintWriter(serverSocket.getOutputStream());
		
		sendRequest(method, path, body);
		HTTPResponse response = getReply();
		serverSocket.close();
		
		if(response.getStatusCode() == STATUS_MOVED_PERM || response.getStatusCode() == STATUS_MOVED_TEMP) {
			URL newLocation = new URL(response.getHeaderValue(HEADER_LOCATION));
			this.host = newLocation.getHost();
			response = request(method, newLocation.getPath(), body);
			System.out.println("'" + host + path + "' has been moved to '" + newLocation.toString() +"'\n");
		}
		return response;
	}
		
	private HTTPResponse getReply() throws IOException {
		final HTTPResponse response = new HTTPResponse();
		response.setStatusLine(in.readLine());
		
		String next;
		while(!(next = in.readLine()).equals("")) {
			String[] header = next.split("(: )|(:)", 2);
			response.getHeaders().add(new HTTPHeader(header[0], header[1]));
		}
		
		if(response.getStatusCode() != STATUS_MOVED_PERM || response.getStatusCode() != STATUS_MOVED_TEMP) {
			//getting the body only works for fixed content-length websites - chunk transfer is not supported yet!
			if(response.getBody() != null) { 
				char[] body = new char[response.getContentSize()];
				in.read(body);
				System.out.println(body);
			}
		}
		
		return response;
	}	
	
	private void sendRequest(String method, String path, String body) {
		out.printf("%s %s %s\n", method, path, PROTOCOL_VERSION);
		out.printf("Host: %s\n", host);
		if(body != null) {
			out.printf("Content-Length: %d\n", body.length());
			out.printf("Content-Type: %s\n", "application/x-www-form-urlencoded");
			out.printf("\n");
			out.println(body);
		}
		else {
			out.printf("\n");
		}
		out.flush();
	}
}
