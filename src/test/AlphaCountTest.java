package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import anagrams.AlphaCount;

class AlphaCountTest {

	@Test
	void testIsSubset() {
		AlphaCount alpha1 = new AlphaCount("aab");
		AlphaCount alpha2 = new AlphaCount("aabcdef");
		assertFalse(alpha1.isSubset(alpha2));
		assertTrue(alpha2.isSubset(alpha1));
		
		AlphaCount alpha3 = new AlphaCount("aabbcdef");
		assertFalse(alpha1.isSubset(alpha3));
	}

}
