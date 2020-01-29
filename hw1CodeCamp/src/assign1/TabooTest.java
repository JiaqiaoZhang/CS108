// TabooTest.java
// Taboo class tests -- nothing provided.
package assign1;

import static org.junit.Assert.*;
import java.util.*;

import org.junit.Test;

public class TabooTest {

	// TODO ADD TESTS
	@Test
	public void noFollow1() {
		// basic cases
		List<Character> rules = Arrays. asList('a', 'c', 'a', 'b','c','a');
		
		Taboo<Character> test = new Taboo<Character>(rules);
		Set<Character> res1 = new HashSet<Character>();
		Set<Character> res2 = new HashSet<Character>();
		Set<Character> res3 = new HashSet<Character>();
		res1.add('c');
		res1.add('b');
		res2.add('a');
		res3.add('c');
		assertEquals(res1, test.noFollow('a'));
		assertEquals(res2, test.noFollow('c'));
		assertEquals(res3, test.noFollow('b'));

	}
	
	@Test
	public void noFollow2() {
		// basic cases
		List<Character> rules = Arrays. asList('a', 'b', null, 'a','d','c');
		
		Taboo<Character> test = new Taboo<Character>(rules);
		Set<Character> res1 = new HashSet<Character>();
		Set<Character> res2 = new HashSet<Character>();
		res1.add('b');
		res1.add('d');
		res2.add('c');
		assertEquals(res1, test.noFollow('a'));
		assertEquals(res2, test.noFollow('d'));
		assertEquals(null, test.noFollow('b'));

	}
	
	@Test
	public void reduce1() {
		// basic cases
		List<Character> rules = Arrays. asList('a', 'c', 'a', 'b');
		Taboo<Character> test = new Taboo<Character>(rules);
		ArrayList<Character> before1 = new ArrayList<>(Arrays. asList('a', 'c', 'b', 'x','c','a'));
		ArrayList<Character> after1 = new ArrayList<>(Arrays. asList('a', 'x', 'c'));
		test.reduce(before1);
		assertTrue(after1.equals(before1));
		
		ArrayList<Character> before2 = new ArrayList<>(Arrays. asList('a', 'c', 'b', null ,'c','a'));
		ArrayList<Character> after2= new ArrayList<>(Arrays. asList('a', null, 'c'));
		test.reduce(before2);
		assertTrue(after2.equals(before2));

	}
	@Test
	public void reduce2() {
		// basic cases
		List<Character> rules = Arrays. asList('a', 'b', null, 'a','d','c');
		Taboo<Character> test = new Taboo<Character>(rules);
		ArrayList<Character> before1 = new ArrayList<>(Arrays. asList('a', 'c', 'b', 'a','d','c'));
		ArrayList<Character> after1 = new ArrayList<>(Arrays. asList('a', 'c', 'b', 'a', 'c'));
		test.reduce(before1);
		assertTrue(after1.equals(before1));
		
		ArrayList<Character> before2 = new ArrayList<>(Arrays. asList('a', 'b', 'a', null ,'d','c'));
		ArrayList<Character> after2= new ArrayList<>(Arrays. asList('a','a', null, 'd'));
		test.reduce(before2);
		assertTrue(after2.equals(before2));
		
		ArrayList<Character> before3 = new ArrayList<>();
		ArrayList<Character> after3= new ArrayList<>();
		test.reduce(before3);
		assertTrue(after3.equals(before3));

	}
	
	
}
