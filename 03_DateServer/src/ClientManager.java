import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class ClientManager extends Thread {
	private final static long MILISECONDS_PER_DAY = 86400000L;
	private static final SimpleDateFormat isoDate = new SimpleDateFormat("yyyy-MM-dd");
	private final Socket client;
	
	public ClientManager(Socket client) {
		this.client = client;
	}
	
	public void run() {
		try {
			handleClient();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	private void handleClient() throws IOException, ParseException {
			final BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			final PrintWriter out = new PrintWriter(client.getOutputStream());
			out.println("Please enter date in ISO (yyyy-MM-dd) format: ");
			out.flush();
			String userDate = in.readLine();
			out.println("\n"+calculateDateDifference(userDate));
			out.flush();
			client.close();
	}
	
	private String calculateDateDifference(String userDate) throws ParseException {
		Date futureDate = isoDate.parse(userDate);
		Date today = new Date();
		
		long days = unixToDays(futureDate.getTime() - today.getTime());
		System.out.println(futureDate.getTime());
		System.out.println(days);
		return Long.toString(days);
	}
	
	private long unixToDays(long unixtime) {
		return unixtime/MILISECONDS_PER_DAY;
	}
	
}
