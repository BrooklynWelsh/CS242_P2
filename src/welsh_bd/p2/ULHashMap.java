package welsh_bd.p2;

import java.util.Iterator;

public class ULHashMap<K,V> implements Cloneable, Iterable{
	
	public ULHashMap() {
		
	}
	
	public ULHashMap(int expectedSize) {
		size = expectedSize;
	}
	
	 public static class Mapping{
			
	 }

	@Override
	public Iterator iterator() {
		// TODO Auto-generated method stub
		return null;
	}
	 
	int size;
	ULHashMap<Character, Integer> charactersToCounts;
}
