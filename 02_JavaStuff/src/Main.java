import java.util.*;
import java.io.*;

public class Main {
	static Map<String, Integer> population = new HashMap<String, Integer>();
	static int POPULATION_CRITERIA = 10000000;
	public static void main(String[] args) throws IOException {
		loadCountries();
		findCountries(POPULATION_CRITERIA);
		capitalise();
	}
	
	private static void loadCountries() {
		population.put("Bulgaria", 7000000);
		population.put("China", 1339190000);
		population.put("Haiti", 10200000);
		population.put("Bangladesh", 164000000);
		population.put("Poland", 39000000);
		population.put("Slovenia", 2000000);
		population.put("Iraq", 32000000);
		population.put("Monaco", 33000);
		population.put("Switzerland", 8000000);
		population.put("Nigeria", 170000000);
		population.put("Croatia", 4000000);
		population.put("Ukraine", 45000000);
		
	}
	
	private static void findCountries(int min_population) {
		for(Map.Entry<String, Integer> country: population.entrySet()) {
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
