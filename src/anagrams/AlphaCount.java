package anagrams;

import java.util.*;

public class AlphaCount {
	
	HashMap<Character, Integer> charCounts;
	String inputString;
	int size;
	
	
	public AlphaCount(){
		inputString = null;
		size = 0;
	}
	
	public AlphaCount(String input) {
		inputString = input.replaceAll("[0-9] ", "").toLowerCase();	// This program only keeps characters, not numbers (UPDATE to also remove non letter chars)
		charCounts = new HashMap<Character, Integer>(inputString.length());
	}
	
	public AlphaCount add(AlphaCount other) {
		String newString = other.toString() + this.toString();
		return new AlphaCount(newString);
	}
	
	public boolean equals(Object otherObject) {
		return false;
		
	}
	
	public int getLetter(char letter) {
		return charCounts.get(letter);
	}
	
	public int hashCode() {
		int hashcode = 0;
		for(Map.Entry<Character, Integer>  entry: charCounts.entrySet()) {
			hashcode += entry.hashCode();
		}
		return hashcode;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public boolean isSubset(AlphaCount other) {
		return false;
	}
	
	public int size() {
		return size;
	}
	
	public AlphaCount subtract(AlphaCount other) {
		return null;
	}
	public String toString() {
		String returnString = "";
		for(Map.Entry<Character, Integer>  entry: charCounts.entrySet()) {
			returnString = returnString + entry.toString();
		}
		return returnString;
	}
}

