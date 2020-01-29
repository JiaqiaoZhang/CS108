// Test cases for CharGrid -- a few basic tests are provided.
package assign1;

import static org.junit.Assert.*;
import org.junit.Test;

public class CharGridTest {
	
	@Test
	public void testCharArea1() {
		char[][] grid = new char[][] {
				{'a', 'y', ' '},
				{'x', 'a', 'z'},
			};
		
		
		CharGrid cg = new CharGrid(grid);
				
		assertEquals(4, cg.charArea('a'));
		assertEquals(1, cg.charArea('z'));
	}
	
	
	@Test
	public void testCharArea2() {
		char[][] grid = new char[][] {
				{'c', 'a', ' '},
				{'b', ' ', 'b'},
				{' ', ' ', 'a'}
			};
		
		CharGrid cg = new CharGrid(grid);
		
		assertEquals(6, cg.charArea('a'));
		assertEquals(3, cg.charArea('b'));
		assertEquals(1, cg.charArea('c'));
	}
	
	// TODO Add more tests
	@Test
	public void testCountPlus1() {
		char[][] grid = new char[][] {
				{'b', 'a', ' ', 'd'},
				{'a', 'a', 'a', 'd'},
				{' ', 'a', 'z', 'd'},
				{'c', 'z', 'z', 'z'},
				{'c', 'd', 'd', 'd'}
			};
		
		CharGrid cg = new CharGrid(grid);
		assertEquals(1, cg.countPlus());

	}
	
	@Test
	public void testCountPlus2() {
		char[][] grid = new char[][] {
				{'b', 'a', ' ', 'd'},
				{'a', 'a', 'a', 'd'},
				{' ', 'a', 'z', 'd'},
				{'c', 'z', 'z', 'z'},
				{'c', 'd', 'z', 'd'}
			};
		
		CharGrid cg = new CharGrid(grid);
		assertEquals(2, cg.countPlus());

	}
	
	@Test
	public void testCountPlus3() {
		char[][] grid = new char[][] {
				{'b', 'a', ' ', 'd'},
				{'a', 'a', 'z', 'd'},
				{' ', 'a', 'z', 'd'},
				{'c', 'z', 'z', 'z'},
				{'c', 'd', 'z', 'd'}
			};
		
		CharGrid cg = new CharGrid(grid);
		assertEquals(0, cg.countPlus());

	}
	
	@Test
	public void testCountPlus4() {
		// non letters
		char[][] grid = new char[][] {
				{'b', '+', ' ', 'd'},
				{'+', '+', '+', 'd'},
				{' ', '+', 'z', 'd'},
				{'c', 'z', 'z', 'z'},
				{'c', 'd', 'z', 'd'}
			};
		
		CharGrid cg = new CharGrid(grid);
		assertEquals(2, cg.countPlus());

	}
	
	@Test
	public void testCountPlus5() {
		// digits
		char[][] grid = new char[][] {
				{'b', '1', ' ', 'd'},
				{'1', '1', '1', '1'},
				{' ', '1', 'z', 'd'},
				{'c', 'z', 'z', 'z'},
				{'c', 'd', 'z', 'd'}
			};
		
		CharGrid cg = new CharGrid(grid);
		assertEquals(1, cg.countPlus());

	}
	
	
}
