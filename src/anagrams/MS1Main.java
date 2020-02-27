package anagrams;

import java.util.Scanner;

public class MS1Main {

	public static void main(String[] args) {

		// Read a string from the console
//		if(args.length != 1) {
//			System.out.println("ERROR: Need one string argument. USAGE: MS1Main <string>");
//			System.exit(-1);
//		}
//		String theString = args[0];
//		System.out.println("The string given is : " + theString);

		Scanner scanner = new Scanner(System.in);
		String testString = null;
		
		System.out.print("Please provide a new test string (0 to exit): ");
		testString = scanner.nextLine();
		
		while (!testString.equals("0")) {

			

			// Convert it into an alphaCount
			AlphaCount alphaCount = new AlphaCount(testString);

			// Print out the counts
			System.out.println("Now printing the count : letter mappings for alphaCount...");
			System.out.println(alphaCount.toString());

			// Create and print the hashCode for alphaCount
			int theHash = alphaCount.hashCode();
			System.out.println("alphaCount hashCode : " + theHash);
			
			System.out.print("Please provide a new test string (0 to exit): ");
			testString = scanner.nextLine();
		}
	}

}
