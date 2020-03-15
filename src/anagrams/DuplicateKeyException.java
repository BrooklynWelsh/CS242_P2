package anagrams;

public class DuplicateKeyException extends RuntimeException{

 DuplicateKeyException(){}
 
 public DuplicateKeyException(String message) {
	 System.out.println(message);
 }
}
