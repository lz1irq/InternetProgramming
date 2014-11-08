import java.io.IOException;
import java.net.UnknownHostException;


public class Main {

	public static void main(String[] args) throws UnknownHostException, IOException {
		final HTTPClient http = new HTTPClient("abv.bg");
		final HTTPResponse response = http.request("GET", "/index.html");
		System.out.println(response.getStatus() + "\n");
		
		for(HTTPHeader header: response.getHeaders()) {
			System.out.println(header.getName() + ": " + header.getValue());
		}
		
		
		
		http.disconnect();
	}

}
