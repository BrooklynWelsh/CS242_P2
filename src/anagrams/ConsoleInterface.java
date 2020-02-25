package anagrams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import static java.lang.System.*;

/**
 * Main class that provides a simple console interface to anagram generation.  I have written this class for you.
 * DO NOT CHANGE IT (or ask me before you do).
 * @author Joe Meehean
 *
 */

public abstract class ConsoleInterface {
	
	private static final String RESOURCE_DIRECTORY = "resources";
	private static final String LARGE_LEX_FILE = "large.lex.txt";
	private static final String YES = "Y";
	private static final int REQUIRED_ARGS = 3;
	/**
	 * Command line usage information
	 */
	private static final String USAGE = "required command line arguments: <lexfile> <input string> <true|false>";
	
	private ConsoleInterface() {}
	
	/**
	 * Runs the program in the console.
	 * This program can be run in two modes: interactive and non-interactive.  
	 * In interactive mode the program gets information from the user through the standard input (System.in).  
	 * This mode asks the user which lexicon file they wish to use (and looks for that lexicon file in 
	 * the resources folder) and whether the user wants caching enabled.  
	 * This mode then asks for a phrase, prints the anagrams for that phrase, and then asks for another phrase.  
	 * This mode exists so you can play with and debug your program.
	 * 
	 * In non-interactive mode, the lexicon, caching preference, and user input phrase are specified
	 * as command line arguments.  The program does not ask the user for any input through standard in
	 *  and the only thing it prints out are the anagrams for the given word.  
	 *  In this mode, the lexicon file is not assumed to be in the resources folder.  
	 *  If you want to specify a lexicon in the resources folder, you must add the folder to the lexicon file name.  
	 *  This mode exists so that you and I can test your program.  
	 *  The program determines which mode to run in by examining the command line arguments.  
	 *  If any command arguments are given, it runs in non-interactive  mode.  
	 * @param args command line arguments
	 * @throws IOException if reading the lexicon file fails
	 */
	public static void main(String[] args) throws IOException{
		if( args.length == 0 ) {
		 interactiveMode();
		}
		else {
			nonInteractiveMode(args);
		}
	}
	
	/**
	 * Run the program by asking for user input through the console.
	 * @throws IOException if reading the lexicon file fails
	 */
	public static void interactiveMode() throws IOException {
		System.out.println("Welcome to the Anagram Generator.");
		System.out.println();

		// open a scanner to read console input
		Scanner console = new Scanner(System.in);
		
		// keep asking for a lexicon file until it gives us a valid one
		Path lexiconPath = null;
		do {
			// get the lexicon file name from the user
			lexiconPath = getLexFilePath(console);

			// if it doesn't exist, ask again
			if( !Files.exists(lexiconPath) ) {
				out.println("File: " + lexiconPath.toString() + " does not exist. Try again.");
			}

		}while(lexiconPath == null || !Files.exists(lexiconPath) );  // keep trying until we get a valid lexicon file
		
		// read the lexicon 
		List<String> lexiconList = readLexicon(lexiconPath);
		
		// see if the user wants caching
		boolean cacheBool = userWantsCaching(console);

		// make an anagram generator, if possible
		// caching may be requested, but unsupported
		try {
			List<String> immutableLexiconList = Collections.unmodifiableList(lexiconList);
			AnagramGenerator generator = new AnagramGenerator(immutableLexiconList, cacheBool);

			// start the read, generate, print loop
			while( askForInputGenerateAnagrams(console, generator) );

		}
		catch(UnsupportedOperationException e) {
			err.println("Caching is not supported in this Anagram Generator");
		}
		// close the console scanner
		console.close();

		out.println("Goodbye");
	}
	
	/**
	 * Reads the lexicon file and returns the strings in a list.
	 * @param lexFilePath
	 * @return see above
	 * @throws IOException if reading the lexicon file fails
	 */
	private static List<String> readLexicon(Path lexFilePath) throws IOException{
		// open the lexicon file
		Scanner input = new Scanner(lexFilePath);

		// read lexicon into a LinkedList
		LinkedList<String> lexiconList = new LinkedList<>();
		while (input.hasNextLine()) {
			lexiconList.add(input.nextLine());
		}

		// close the file scanner
		input.close();

		return lexiconList;
	}
	
	/**
	 * Asks the user for a file name.  Transforms the file name to reference the "resources" folder.
	 * @param console
	 * @return the user-specified file path, located in the resource folder
	 */
	private static Path getLexFilePath(Scanner console) {
		// get the file name from the user
		out.print("What is the name of the lexicon file (defaults to large.lex.txt)? ");
		String fileName = console.nextLine();

		// default to the large lex file
		if(fileName.length() == 0) {
			fileName = LARGE_LEX_FILE;
		}

		// add the resources folder to the path
		Path lexPath = Paths.get(RESOURCE_DIRECTORY, fileName);
		//String fullName = "resources" + File.separator + fileName;
		
		return lexPath;
	}
	
	/**
	 * Asks the user if the want caching, returns the result.
	 * @param console
	 * @return true if the user wants caching
	 */
	private static boolean userWantsCaching(Scanner console) {
		out.print("Cache results Y or N (defaults N)? ");
		String cacheStr = console.nextLine();
		return cacheStr.compareToIgnoreCase(YES) == 0;
	}
	
	/**
	 * Reads an input from the user, generates anagrams, and prints them 
	 * @param console
	 * @param generator
	 * @return true if the user provided a non-empty input
	 */
	private static boolean askForInputGenerateAnagrams(Scanner console, AnagramGenerator generator) {
		// ask the user for phrase
		out.println();
		out.print("phrase to scramble (return to quit)? ");
		String phrase = console.nextLine();
		
		// generate anagrams if a phrase was given
		if (phrase.length() != 0) {
			List<Anagram> anagrams = generator.generateAnagram(phrase);
			// print the results
			for(Anagram anagram : anagrams) {
				out.println(anagram);
			}
		}
		
		return phrase.length() != 0;
	}
	
	/**
	 * Run the program using only command-line arguments.  Once the program has started, it requires no input from the user.
	 * @param args
	 * @throws IOException if reading the lexicon file fails
	 */
	public static void nonInteractiveMode(String[] args) throws IOException {
		if( args.length != REQUIRED_ARGS ) {
			err.println("Insufficient arguments: " + USAGE);
		}
		else {
			// process the command line argruments
			//command line arguments: <lexfile> <input string> <true|false>
			Path lexFilePath = Paths.get(args[0]);
			String phrase = args[1];
			boolean caching = Boolean.parseBoolean(args[2]);
			
			try {
				// read the lex file
				List<String> lexList = readLexicon(lexFilePath);
				
				// generate the anagrams
				AnagramGenerator gen = new AnagramGenerator(lexList, caching);
				List<Anagram> anagrams = gen.generateAnagram(phrase);
				
				// print the results
				for(Anagram anagram : anagrams) {
					out.println(anagram);
				}
			}
			catch(UnsupportedOperationException e) {
				err.println("This AnagramGenerator does not support caching");
			}
			
		}
	}
}
