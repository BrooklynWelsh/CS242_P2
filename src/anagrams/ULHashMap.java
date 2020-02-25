package anagrams;

import java.util.Iterator;
import java.util.LinkedList;

public class ULHashMap<K,V> implements Cloneable, Iterable{
	
	public ULHashMap() {
		// Table size must be prime
		tableSize = 7; // Just set it to a small prime
		size = 0;
	}
	
	public ULHashMap(int expectedSize) {
		// First set the size (number of key value mappings) equal to number of chars in string
		size = expectedSize;
		
		// Next, make sure we set tableSize to a prime number
		if(isPrime(expectedSize))	tableSize = expectedSize;
		else {
			while(!isPrime(expectedSize)) {
				expectedSize++;
			}
		}
		
		tableSize = expectedSize;
	}
	 
	public void clear() {
		for(LinkedList<V> list : buckets) {
			list.clear();
		}
	}
	
	public ULHashMap<K,V> clone(){
		
	}
	
	public boolean containsKey(K key) {
		boolean retVal = false;
		for(LinkedList<Mapping<K,V>> bucket : buckets) {
			for(Mapping<K,V> mapping : bucket) {
				if(mapping.getKey().equals(key))	retVal = true;
			}
		}
		
		return retVal;
	}
	
	public boolean empty() {
		return size == 0;
	}
	
	public boolean equals(Object otherObject) {
		
	}
	
	public void erase(K key) {
		
	}
	
	public void insert(K key, V value) {
		
	}
	
	public boolean isPrime(int possibleSize) {
		
	}
	
	public Iterator<ULHashMap.Mapping<K,V>> iterator() {
		
	}
	
	public V lookup(K key) {
		
	}
	
	public void put(K key, V value) {
		
	}
	
	public int size() {
		
	}
	
	public int tableSize() {
		
	}

	@Override
	public Iterator iterator() {
		// TODO Auto-generated method stub
		return null;
	}
	 
	int size;
	int tableSize;
	LinkedList<Mapping<K,V>> buckets[]; 
	
	public static class Mapping<K,V>{
		
		public K getKey() {
			return key;
		}
		
		public V getValue() {
			return value;
		}
		
		private K key;
		private V value;
	}
	
}
