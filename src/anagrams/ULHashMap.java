package anagrams;

import java.util.Iterator;
import java.util.LinkedList;

public class ULHashMap<K, V> implements Cloneable, Iterable<ULHashMap.Mapping<K, V>> {

	private int size;
	private int tableSize;
	private LinkedList<Mapping<K, V>> buckets[];

	public ULHashMap() {
		// Table size must be prime
		tableSize = 7; // Just set it to a small prime
		size = 0;
	}

	public ULHashMap(int expectedSize) {
		// First set the size (number of key value mappings) equal to number of chars in
		// string
		size = expectedSize;

		// Next, make sure we set tableSize to a prime number
		if (isPrime(expectedSize))
			tableSize = expectedSize;
		else {
			while (!isPrime(expectedSize)) {
				expectedSize++;
			}
		}

		tableSize = expectedSize;
	}

	public void clear() {
		for (LinkedList<Mapping<K, V>> list : buckets) {
			list.clear();
		}
	}

	public ULHashMap<K, V> clone() {

	}

	public boolean containsKey(K key) {
		boolean retVal = false;
		for (LinkedList<Mapping<K, V>> bucket : buckets) {
			for (Mapping<K, V> mapping : bucket) {
				if (mapping.getKey().equals(key))
					retVal = true;
			}
		}

		return retVal;
	}

	public boolean empty() {
		return size == 0;
	}

	public boolean equals(Object otherObject) {
		return this.equals(otherObject);
	}

	public void erase(K key) {
		int bucketIndex = Math.abs(key.hashCode()) % tableSize;

		for (Mapping<K, V> mapping : buckets[bucketIndex]) {
			if (mapping.key.equals(key)) {
				buckets[bucketIndex].remove(mapping);
				size--;
			}
		}
	}

	public void insert(K key, V value) {
		int bucketIndex = Math.abs(key.hashCode()) % tableSize;

		// If bucket is empty just add new mapping to the first space in bucket
		if (buckets[bucketIndex].size() == 0) {
			buckets[bucketIndex].addFirst(new Mapping<K, V>(key, value));
			size++;
		}

		// Else, iterate through bucket to see if K is already there
		else {
			for (Mapping<K, V> mapping : buckets[bucketIndex]) {
				if (mapping.key.equals(key))
					throw new DuplicateKeyException();
			}
			// If we didn't throw, insert key at end of bucket
			buckets[bucketIndex].addLast(new Mapping<K, V>(key, value));
			size++;

			// Now check that we haven't filled our list. If so, find next prime number
			if (size == tableSize) {
				int newTableSize = tableSize + 1;
				while (!isPrime(newTableSize))
					++newTableSize;
				tableSize = newTableSize;
			}
		}
	}

	public boolean isPrime(int possibleSize) {
		boolean retVal = true;
		int possibleFactor = 1;

		// While we have more numbers to check AND we haven't already found that
		// possibleSize is not prime...
		while (possibleFactor < possibleSize && retVal == true) {
			// Check if possibleFactor is actually a factor and if not increment by one
			if (possibleSize % possibleFactor == 0)
				retVal = false;
			possibleFactor++;
		}

		return retVal;
	}

	@Override
	public Iterator<ULHashMap.Mapping<K, V>> iterator() {
		return new Iterator<ULHashMap.Mapping<K, V>>() {

			int bucket = 0;
			int position = 0;

			@Override
			public boolean hasNext() {
				return index < size;
			}

			@Override
			public Mapping<K, V> next() {
				Mapping<K, V> mapping = null;
				if (!hasNext())
					throw new java.util.NoSuchElementException();
				else {
					mapping = buckets[bucket].get(position);
				}

				// Mapping<K,V> iter = buckets[0].getFirst(); // Start at very first element of
				// first bucket

			}

			@Override
			public void remove() {
				throw new java.lang.UnsupportedOperationException();
			}
		};
	}

	public V lookup(K key) {
		int bucketIndex = Math.abs(key.hashCode()) % tableSize;
		V retVal = null;
		// If there's only one item it has to be it
		if (buckets[bucketIndex].size() == 1)
			retVal = buckets[bucketIndex].getFirst().getValue();
		else {
			for (Mapping<K, V> mapping : buckets[bucketIndex]) {
				if (mapping.getKey().equals(key))
					retVal = mapping.getValue();
			}
		}

		return retVal;
	}

	public void put(K key, V value) {
		int bucketIndex = Math.abs(key.hashCode()) % tableSize;

		// If bucket is empty just add new mapping to the first space in bucket
		if (buckets[bucketIndex].size() == 0) {
			buckets[bucketIndex].addFirst(new Mapping<K, V>(key, value));
			size++;
		}

		// Else, iterate through bucket to see if K is already there. If so, overwrite
		else {
			boolean wasInserted = false;
			for (Mapping<K, V> mapping : buckets[bucketIndex]) {
				if (mapping.key.equals(key)) {
					mapping.key = key;
					mapping.value = value;
					wasInserted = true;
				}

				// If not insert key at end of bucket
				if (!wasInserted) {
					buckets[bucketIndex].addLast(new Mapping<K, V>(key, value));
					size++;
				}
			}

			// Now check that we haven't filled our list. If so, find next prime number
			if (size == tableSize) {
				int newTableSize = tableSize + 1;
				while (!isPrime(newTableSize))
					++newTableSize;
				tableSize = newTableSize;
			}
		}
	}

	public int size() {
		return size;
	}

	public int tableSize() {
		return tableSize;
	}

	public static class Mapping<K, V> {

		public Mapping() {
		}

		public Mapping(K Key, V Value) {
			this.key = Key;
			this.value = Value;
		}

		public K getKey() {
			return key;
		}

		public V getValue() {
			return value;
		}

		public K key;
		public V value;
	}

	public class ULIterator<ULHashMap.Mapping<K, V>> implements Iterable {

		private int bucket = 0;
		private int position = 0;
		private int index = 0;

		public boolean hasNext() {
			return index < size;
		}

		public Mapping<K,V> next(){
			Mapping<K,V> mapping = null;
			if(buckets[bucket].size() > position) {
				mapping = buckets[bucket].get(position);
				position++;
				index++;
			}else if(bucket + 1 < tableSize) {
				mapping = buckets[bucket].get(0);
				bucket++;
				position = 1;
				index++;
			}else {
				throw new java.util.NoSuchElementException();
			}
			return mapping;
		}
		
		public void remove() {
			throw new java.lang.UnsupportedOperationException();
		}

		

	}

}
