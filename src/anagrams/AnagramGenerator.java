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
		AlphaCount baseCount = new AlphaCount(input);		// Create an AlphaCount from the input String
		List<Anagram> anagrams = new ArrayList<Anagram>();	// Create a blank Anagram List to return later
			
		for(String word : lexicon) {											// For each word in our lexicon list...
				Anagram wordAnagrams = new Anagram();							// Create a blank Anagram
				anagrams.add(generateAnagram(baseCount, word, wordAnagrams));	// Recursively add valid anagrams
			}
		
		return anagrams;
	}
	
	private Anagram generateAnagram(AlphaCount input, String word, Anagram wordAnagrams){
		
		AlphaCount wordAlphaCount = new AlphaCount(word);			// Create an AlphaCount for our word that comes from the lexicon
		if(input.isSubset(wordAlphaCount)) {						// If the word is a subset of input...
			input = input.subtract(wordAlphaCount);					// Subtract its AlphaCount from our input AlphaCount
			wordAnagrams.addWord(word);								// Add the word String to our Anagram
			
			for(String nextWord : lexicon) {						// For all other words in the lexicon...
				wordAnagrams = wordAnagrams.addAnagram(generateAnagram(input, nextWord, wordAnagrams));		// Add Anagram thats generated by recursive call to our Anagram
			}
		}
		if(input.size() != 0) wordAnagrams = null;		// We couldn't subtract all of our AlphaCount, so its not a valid Anagram, set to null
		return wordAnagrams;
	}
}
