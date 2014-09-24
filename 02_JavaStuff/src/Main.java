import java.util.*;
import java.io.*;

public class Main {
	static Map<String, Integer> countries = new HashMap<String, Integer>();
	static int MIN_POPULATION = 10000000;
	public static void main(String[] args) throws IOException {
		loadCountries();
		findCountries(MIN_POPULATION);
		capitalise();
	}
	
	private static void loadCountries() {
		countries.put("Bulgaria", 7000000);
		countries.put("China", 1339190000);
		countries.put("Haiti", 10200000);
		countries.put("Bangladesh", 164000000);
		countries.put("Poland", 39000000);
		countries.put("Slovenia", 2000000);
		countries.put("Iraq", 32000000);
		countries.put("Monaco", 33000);
		countries.put("Switzerland", 8000000);
		countries.put("Nigeria", 170000000);
		countries.put("Croatia", 4000000);
		countries.put("Ukraine", 45000000);
		
	}
	
	private static void findCountries(int min_population) {
		for(Map.Entry<String, Integer> country: countries.entrySet()) {
			if(country.getValue() > min_population) System.out.println(country.getKey());
		}
	}
	
	private static void capitalise() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Enter string to be capitalised: ");
		String input = br.readLine();
		System.out.println(input.toUpperCase());
	}

}
