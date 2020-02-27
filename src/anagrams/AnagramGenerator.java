package anagrams;

import java.util.List;

public class AnagramGenerator {

	List<String> lexicon;
	boolean cache;
	
	AnagramGenerator(){
		
	}
	
	AnagramGenerator(List<String> lexicon){
		this.lexicon = lexicon;
	}
	
	AnagramGenerator(List<String> lexicon, boolean cachingEnabled){
		if(cachingEnabled == true) throw new UnsupportedOperationException("Caching is not supported at this time");
		this.lexicon = lexicon;
		this.cache = cachingEnabled;
	}
	
	List<Anagram> generateAnagram(String input){
		return null;
	}
}
