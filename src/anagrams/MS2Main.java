package anagrams;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MS2Main {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner inputScanner = new Scanner(System.in);
		Scanner lexScanner = null;
		File file = null;
		System.out.println("Please select which lexicon file you would like to use: \n (1) for the small lexicon \n (2) for the large lexicon \n");
		int lexChoice = inputScanner.nextInt();
		if(lexChoice == 1) {
			file = new File("resources/small.lex.txt");
		} else if(lexChoice == 2) {
			file = new File("resources/large.lex.txt");
		}else {
			System.exit(-1);
		}
		
		lexScanner = new Scanner(file);
		
		ArrayList <String> lexList = new ArrayList<String>();
		
		while(lexScanner.hasNext()) {
			lexList.add(lexScanner.next());
		}
		
		System.out.println("Please enter a phrase to generate anagrams (0 to exit): ");
		String inputPhrase = inputScanner.next();
		AnagramGenerator generator = new AnagramGenerator(lexList);
		
		while(inputPhrase != "0") {
			List<Anagram> anagramList;
			anagramList = generator.generateAnagram(inputPhrase);
			for(Anagram anagram : anagramList) {
				System.out.println(anagram.toString());
			}
			System.out.println("Please enter A phrase to generate anagrams (0 to exit): ");
			inputPhrase = inputScanner.next();
		}
		
		
		
		
		
		
	}

}
