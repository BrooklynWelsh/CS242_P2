package anagrams;

import java.util.*;

import anagrams.ULHashMap.Mapping;

public class AlphaCount {
	
	private ULHashMap<Character, Integer> charCounts;
	private String inputString;
	private int size;
	
	
	AlphaCount(){
		inputString = null;
		size = 0;
		charCounts = new ULHashMap<Character, Integer>();
	}
	
	public AlphaCount(String input){
		inputString = input;
		size = 0;
		charCounts = new ULHashMap<Character, Integer>();
		for(Character c : inputString.toCharArray()) {
			if(Character.isLetter(c)) {
				if(charCounts.containsKey(c)) {
					Integer count = charCounts.lookup(c);
					charCounts.put(c, ++count);
				}
				else {
					charCounts.put(c, 1);	// Initial value is 1
				}
			}
			size++;
		}
	}
	
	public AlphaCount add(AlphaCount other) {
		String newString = other.inputString + this.inputString;
		return new AlphaCount(newString);
	}
	
	public boolean equals(Object otherObject) {
		boolean isEqual = false;
		if(this == otherObject) isEqual = true;
		
		if(otherObject != null && this.getClass() == otherObject.getClass()) {
			AlphaCount newAlpha = (AlphaCount) otherObject;
			isEqual = this.charCounts.equals(newAlpha.charCounts);
		}
		
		return isEqual;
	}
	
	public int getLetter(char letter) {
		Integer charCount = charCounts.lookup(letter);
		if(charCount == null) {
			charCount = 0;
		}
		return charCount;
	}
	
	public int hashCode() {
		int hashcode = 0;
		for(Mapping<Character, Integer>  entry: charCounts) {
			hashcode += entry.hashCode();
		}
		return hashcode;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public boolean isSubset(AlphaCount other) {
		boolean isSubset = true;
		for(Mapping<Character, Integer> entry : other.charCounts) {
			if(entry.getValue() > this.getLetter(entry.getKey())) {
				isSubset = false;
			}
		}
		
		return isSubset;
	} 
	
	public int size() {
		return size;
	}
	
	public AlphaCount subtract(AlphaCount other) {
		String newString = "";
		for(Mapping<Character, Integer> entry : this.charCounts) {
			Character character = entry.getKey();
			int oldCharCount = entry.getValue();
			int otherCharCount = other.getLetter(character);
			int newCharCount = oldCharCount - otherCharCount;
			
			if(newCharCount > 0) {
				for(int i = 0; i < newCharCount; i++) {
					newString = newString + character;
				}
			}
		}

		return new AlphaCount(newString);
	}
	
	
	public String toString() {
		String returnString = "";
		for(Mapping<Character, Integer>  entry: charCounts) {
			returnString = returnString + entry.toString() + " ";
		}
		return returnString;
	}
}

