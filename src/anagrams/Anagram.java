package anagrams;
import java.util.*;

public class Anagram {
	
	private int size = 0;
	private ArrayList<String> words;
	
	Anagram(){
		words = new ArrayList<String>();
	}
	
	Anagram(List<String> words){
		this.words = new ArrayList<String>(words);
		size = words.size();
	}
	
	public Anagram addAnagram(Anagram other) {
		@SuppressWarnings("unchecked")
		ArrayList<String> sum = (ArrayList<String>) words.clone();
		
		sum.addAll(other.words);
		size = size + sum.size();
		Anagram anagramSum = new Anagram(sum);
		return anagramSum;
	}
	
	public Anagram addWord(String word) {
		@SuppressWarnings("unchecked")
		ArrayList<String> newList = (ArrayList<String>) words.clone();		// ERROR: Gives NULL pointer exception
		newList.add(word);
		size++;
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
		return words.toString();
	}
}
