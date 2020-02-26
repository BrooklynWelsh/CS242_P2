package anagrams;

import java.util.*;

public class AlphaCount {
	
	HashMap<Character, Integer> charCounts;
	String inputString;
	int size;
	
	
	AlphaCount(){
		inputString = null;
		size = 0;
	}
	
	AlphaCount(String input){
		inputString = input;
		size = 0;
		
		for(Character c : inputString.toCharArray()) {
			if(Character.isLetter(c)) {
				Integer count = charCounts.get(c);
				if(count == null) {
					charCounts.put(c, 0);
				}else {
					charCounts.put(c, count++);
				}
			}
			size++;
		}
	}
	
	public AlphaCount add(AlphaCount other) {
		String newString = other.toString() + this.toString();
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

