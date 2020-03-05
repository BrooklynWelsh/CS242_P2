package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import anagrams.Anagram;
import anagrams.AnagramGenerator;

class AnagramGeneratorTest {

	@Test
	void test() throws IOException {
		String filename = "resources/small.lex.txt";
		Scanner scanner = new Scanner(Paths.get(filename));
		List<String> lexiconList = new ArrayList<String>();
		
		while(scanner.hasNext()) {
			lexiconList.add(scanner.nextLine());
		}
		
		scanner.close();
		AnagramGenerator generator = new AnagramGenerator(lexiconList);
		
		String input = "west";
		
		List<Anagram> anagramList = generator.generateAnagram(input);
		
		System.out.println("Anagram list for " + input + " using " + filename);
		for(Anagram anagram : anagramList) {
			System.out.println(anagram.toString());
		}
		
		String filename2 = "resources/large.lex.txt";
		Scanner scanner2 = new Scanner(Paths.get(filename2));
		List<String> lexiconList2 = new ArrayList<String>();
		
		while(scanner2.hasNext()) {
			lexiconList2.add(scanner2.nextLine());
		}
		
		scanner2.close();
		
		AnagramGenerator generator2 = new AnagramGenerator(lexiconList2);
		String input2 = "absentee";
		
		List<Anagram> anagramList2 = generator2.generateAnagram(input2);
		
		System.out.println("Anagram list for " + input2 + " using " + filename2);
		
		for(Anagram anagram : anagramList2) {
			System.out.println(anagram.toString());
		}

	}

}
