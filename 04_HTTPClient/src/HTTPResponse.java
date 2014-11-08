import java.util.ArrayList;
import java.util.List;


public class HTTPResponse {
	
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 10;
	private static final String HEADER_CONTENT_LENGTH = "content-length";
	
	private List<HTTPHeader> headers = new ArrayList<HTTPHeader>();;
	private int status;
	private char[] body;
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int newStatus) {
		this.status = newStatus;
	}
	
	public char[] getBody() {
		try {
			int bodySize = getContentSize();
			body = new char[bodySize];
		}
		catch(IllegalArgumentException exc) {
			body = null;
		}
		
		return body;
	}
	private int getContentSize() {
		return Integer.parseInt(getHeaderValue(HEADER_CONTENT_LENGTH));
	}

	public void setBody(char[] body) {
		this.body = body;
	}
	public List<HTTPHeader> getHeaders() {
		return headers;
	}
	
	public String getHeaderValue(String headerName) {
		for(HTTPHeader header: headers) {
			if(header.getName().toLowerCase().equals(headerName.toLowerCase())) {
				return header.getValue();
			}
		}
		throw new IllegalArgumentException("Header with name '" + headerName + "' not found!");
	}
	
}
