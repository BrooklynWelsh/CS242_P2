
package anagrams;

public class DuplicateKeyException extends RuntimeException {

	public DuplicateKeyException(){}
	
	public DuplicateKeyException(String message){
		System.out.println(message);
	}
}

