package anagrams;
import java.util.*;

public class Anagram {
	
	private int size;
	private ArrayList<String> words;
	
	Anagram(){
		words = null;
	}
	
	Anagram(ArrayList<String> words){
		this.words = words;
	}
	
	public Anagram addAnagram(Anagram other) {
		@SuppressWarnings("unchecked")
		ArrayList<String> sum = (ArrayList<String>) words.clone();
		
		sum.addAll(other.words);
		Anagram anagramSum = new Anagram(sum);
		return anagramSum;
	}
	
	public Anagram addWord(String word) {
		@SuppressWarnings("unchecked")
		ArrayList<String> newList = (ArrayList<String>) words.clone();
		newList.add(word);
		Anagram newAnagram = new Anagram(newList);
		return newAnagram;
	}
	
	public boolean equals (Object otherObject) {
		boolean isEqual = false;
		if(this == otherObject) isEqual = true;
		
		if(otherObject != null && this.getClass() == otherObject.getClass()) {
			Anagram newAnagram = (Anagram) otherObject;
			isEqual = this.words.equals(newAnagram.words);
		}
		
		return isEqual;
	}
	
	public int size() {
		return size;
	}
	
	public String toString() {
		return null;
	}
}
