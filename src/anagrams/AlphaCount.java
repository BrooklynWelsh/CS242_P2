package anagrams;

public class AlphaCount {
	
	ULHashMap<Character, Integer> charactersToCounts;
	String inputString;
	int size;
	
	
	public AlphaCount(){
		inputString = null;
		size = 0;
	}
	
	public AlphaCount(String input) {
		inputString = input.replaceAll("[0-9] ", "").toLowerCase();	// This program only keeps characters, not numbers (UPDATE to also remove non letter chars)
		charactersToCounts = new ULHashMap<Character, Integer>(inputString.length());
	}
	
	public AlphaCount add(AlphaCount other) {
		return null;
		
	}
	
	public boolean equals(Object otherObject) {
		return false;
		
	}
	
	public int getLetter(char letter) {
		return 0;
	}
	
	public int hashCode() {
		return 0;
	}
	
	public boolean isEmpty() {
		return false;
	}
	
	public boolean isSubset(AlphaCount other) {
		return false;
	}
	
	public int size() {
		return 0;
	}
	
	public AlphaCount subtract(AlphaCount other) {
		return null;
	}
	public String toString() {
		return null;
	}
}

