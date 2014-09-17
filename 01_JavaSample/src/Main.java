
public class Main {

	public static void main(String[] args) {
		int bugsInCode = 99;
		int maxBugs = 127;
		for(int i=bugsInCode;i<=maxBugs;i+=2) {
			System.out.println(i+" bugs in the code. Take one down, patch it around");
		}
		
		Repeater uhf = new Repeater("LZ0UHF", 431.8, 439.4);
		Repeater uhf2 = new Repeater("LZ0UHF", 431.8, 439.4);
		Repeater sea = new Repeater("LZ0SEA", 431.5, 439.1);
		Repeater uhfLink = uhf;
		
		/* I've redefined the equals() method for the Repeater class to compare class values, 
		 not references (as is done in the larger part of Java's standard library, from what I've been told)*/
		if(uhf.equals(uhf2)) System.out.println("uhf and uhf2 have the same content");
		if(uhf == uhf2) System.out.println("uhf and uhf2 are the same object");
		if(uhf == uhfLink) System.out.println("uhfLink points to the same object as uhf");
		
	}

}
