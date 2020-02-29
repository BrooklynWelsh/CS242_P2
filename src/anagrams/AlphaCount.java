package anagrams;

import java.util.*;

public class AlphaCount {
	
	private HashMap<Character, Integer> charCounts;
	private String inputString;
	private int size;
	
	
	AlphaCount(){
		inputString = null;
		size = 0;
		charCounts = new HashMap<Character, Integer>();
	}
	
	public AlphaCount(String input){
		inputString = input;
		size = 0;
		charCounts = new HashMap<Character, Integer>();
		for(Character c : inputString.toCharArray()) {
			if(Character.isLetter(c)) {
				if(charCounts.containsKey(c)) {
					Integer count = charCounts.get(c);
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
		Integer charCount = charCounts.get(letter);
		if(charCount == null) {
			charCount = 0;
		}
		return charCount;
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
		boolean isSubset = true;
		for(Map.Entry<Character, Integer> entry : other.charCounts.entrySet()) {
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
		for(Map.Entry<Character, Integer> entry : other.charCounts.entrySet()) {
			Character character = entry.getKey();
			int otherCharCount = entry.getValue();
			int oldCharCount = this.getLetter(character);
			int newCharCount = oldCharCount - otherCharCount;
			
			if(newCharCount > 0) {
				for(int i = 0; i < newCharCount; i++) {
					newString = newString + character;
				}
			}
		}
		
		for(Map.Entry<Character, Integer> entry : this.charCounts.entrySet()) {
			if(other.getLetter(entry.getKey()) == 0) {
				for(int i = 0; i < entry.getValue(); i++) {
					newString = newString  + entry.getKey();
				}
			}
		}

		return new AlphaCount(newString);
	}
	
	
	public String toString() {
		String returnString = "";
		for(Map.Entry<Character, Integer>  entry: charCounts.entrySet()) {
			returnString = returnString + entry.toString() + " ";
		}
		return returnString;
	}
}

