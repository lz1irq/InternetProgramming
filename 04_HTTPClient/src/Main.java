import java.io.IOException;
import java.net.URLEncoder;
import java.net.UnknownHostException;


public class Main {

	public static void main(String[] args) throws UnknownHostException, IOException {
		final String params = URLEncoder.encode("&a=b&aa=bb", "UTF-8");
		final HTTPClient http = new HTTPClient("posttestserver.com");
		final HTTPResponse response = http.request("POST", "/post.php?dump", "a=b&aa=bb");
		System.out.print(response.getStatusLine() + "\n");
		
		for(HTTPHeader header: response.getHeaders()) {
			System.out.println(header.getName() + ": " + header.getValue());
		}
		
		System.out.println(new String(response.getBody()));
	}
}
