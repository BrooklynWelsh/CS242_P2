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
		buckets = (LinkedList<Mapping<K, V>>[]) new LinkedList[tableSize];
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
		buckets = (LinkedList<Mapping<K, V>>[]) new LinkedList[tableSize];
	}

	public void clear() {
		for (LinkedList<Mapping<K, V>> list : buckets) {
			list.clear();
		}
	}

	public ULHashMap<K, V> clone() throws CloneNotSupportedException {
		ULHashMap<K, V> newHashMap = null;
		newHashMap = (ULHashMap<K, V>) super.clone();
		newHashMap.buckets = this.buckets.clone();
		return newHashMap;
	}

	public boolean containsKey(K key) {
		boolean retVal = false;
		for (int i = 0; i < tableSize; i++) {
			if (buckets[i] != null) {
				for (Mapping<K, V> mapping : buckets[i]) {
					if (mapping.getKey().equals(key))
						retVal = true;
				}
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

	public Iterator<ULHashMap.Mapping<K, V>> iterator() {
		return new Iterator<ULHashMap.Mapping<K, V>>() {

			private int bucket = 0;
			private int position = 0;
			private int index = 0;

			public boolean hasNext() {
				return index < size;
			}

			public Mapping<K, V> next() {
				Mapping<K, V> mapping = null;
				if (buckets[bucket] != null && buckets[bucket].size() > position) {
					mapping = buckets[bucket].get(position);
					position++;
					index++;
				} else if (bucket + 1 < tableSize) {
					mapping = buckets[bucket].get(0);
					bucket++;
					position = 1;
					index++;
				} else {
					throw new java.util.NoSuchElementException();
				}
				return mapping;
			}

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
		if (buckets[bucketIndex] == null) {
			buckets[bucketIndex] = new LinkedList<Mapping<K, V>>();
			buckets[bucketIndex].addFirst(new Mapping<K, V>(key, value));
			size++;
		}

		// Else, iterate through bucket to see if K is already there. If so, overwrite
		else {
			boolean isPresent = false;
			Mapping<K, V> currentMapping = null;
			for (Mapping<K, V> mapping : buckets[bucketIndex]) {
				if (mapping.key.equals(key)) {
					isPresent = true;
					currentMapping = mapping;
				}
			}

			if (isPresent) {
				buckets[bucketIndex].remove(currentMapping);
				size--;
			}
			// If not insert key at end of bucket
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

}
