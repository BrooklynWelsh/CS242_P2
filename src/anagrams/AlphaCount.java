package anagrams;

public class AlphaCount {
	public AlphaCount(){
		inputString = "";
	}
	
	public AlphaCount(String input) {
		inputString = input.replaceAll("[0-9] ", "").toLowerCase();	// This program only keeps characters, not numbers (UPDATE to also remove non letter chars)
		charactersToCounts = new ULHashMap<Character, Integer>(inputString.length());
	}
	
	ULHashMap<Character, Integer> charactersToCounts;
	String inputString;
}
