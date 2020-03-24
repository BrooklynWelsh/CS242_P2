package anagrams;

import java.util.ArrayList;
import java.util.List;

public class AnagramGenerator {

	List<String> lexicon;
	boolean cache;
	ArrayList<AlphaCount> lexiconCount;
	
	AnagramGenerator(){
		
	}
	
	public AnagramGenerator(List<String> lexicon){
		this.lexicon = lexicon;
		this.lexiconCount = new ArrayList<AlphaCount>();
		
		for(String word : lexicon) {
			lexiconCount.add(new AlphaCount(word));
		}
	}
	
	public AnagramGenerator(List<String> lexicon, boolean cachingEnabled){
		if(cachingEnabled == true) throw new UnsupportedOperationException("Caching is not supported at this time");
		this.lexicon = lexicon;
		this.cache = cachingEnabled;
		this.lexiconCount = new ArrayList<AlphaCount>();
		
		for(String word : lexicon) {
			lexiconCount.add(new AlphaCount(word));
		}
	}
	
	public List<Anagram> generateAnagram(String input){
		AlphaCount baseCount = new AlphaCount(input);							// Create an AlphaCount from the input String
		List<Anagram> anagrams = new ArrayList<Anagram>();						// Create a blank Anagram List to return later
			
		for(String word : lexicon) {											// For each word in our lexicon list...
				Anagram wordAnagrams = new Anagram();							// Create a blank Anagram
				wordAnagrams = generateAnagram(baseCount, word, wordAnagrams);
				if(wordAnagrams != null)	anagrams.add(wordAnagrams);		// Recursively add valid anagrams
			}
		
		//converts the list of anagrams to a list of strings for easy sorting, then converts back
		ArrayList<String> sortedAnagramStrings = new ArrayList<String>();
		for(Anagram anagram: anagrams) {
			sortedAnagramStrings.add(anagram.toString());
		}
		//The null comparator uses the default comparator for the String class
		sortedAnagramStrings.sort(null);
		anagrams = new ArrayList<Anagram>();
		for(String string: sortedAnagramStrings) {
			Anagram newAnagram = new Anagram();
			//Hopefully adding the whole anagram back as a single string will work
			newAnagram.addWord(string);
			anagrams.add(newAnagram);
		}
		
		
		return anagrams;
	}
	
	private Anagram generateAnagram(AlphaCount input, String word, Anagram wordAnagrams){
		
		AlphaCount wordAlphaCount = new AlphaCount(word);			// Create an AlphaCount for our word that comes from the lexicon
		if(input.isSubset(wordAlphaCount)) {						// If the word is a subset of input...
			input = input.subtract(wordAlphaCount);					// Subtract its AlphaCount from our input AlphaCount
			wordAnagrams = wordAnagrams.addWord(word);				// Add the word String to our Anagram
			
			if(!input.isEmpty()) {
				for(String nextWord : lexicon) {					// For all other words in the lexicon...
					Anagram newAnagram = new Anagram();
					newAnagram = generateAnagram(input, nextWord, wordAnagrams);
					if(newAnagram != null && !newAnagram.equals(wordAnagrams)) {
						wordAnagrams = newAnagram;
						input = input.subtract(new AlphaCount(wordAnagrams.toString()));
					}
				}
			}
		}
		if(input.size() != 0) wordAnagrams = null;		// We couldn't subtract all of our AlphaCount, so its not a valid Anagram, set to null
		return wordAnagrams;
	}
}
