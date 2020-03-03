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
		String filename = "..\\resources\\small.lex";
		Scanner scanner = new Scanner(Paths.get(filename));
		List<String> lexiconList = new ArrayList<String>();
		
		while(scanner.hasNext()) {
			lexiconList.add(scanner.nextLine());
		}
		
		scanner.close();
		AnagramGenerator generator = new AnagramGenerator(lexiconList);
		
		String input = "twowestrowwetoverstew";
		
		List<Anagram> anagramList = generator.generateAnagram(input);
		
		for(Anagram anagram : anagramList) {
			System.out.println(anagram.toString());
		}
	}

}
