package edu.stanford.cs108.tetris;

import static org.junit.Assert.*;
import java.util.*;

import org.junit.*;

/*
  Unit test for Piece class -- starter shell.
 */
public class PieceTest {
	// You can create data to be used in the your
	// test cases like this. For each run of a test method,
	// a new PieceTest object is created and setUp() is called
	// automatically by JUnit.
	// For example, the code below sets up some
	// pyramid and s pieces in instance variables
	// that can be used in tests.
	private Piece pyr1, pyr2, pyr3, pyr4, pyr5;
	private Piece s, sRotated, sInitial;
	private Piece square, squareRotate; 
	private Piece stick, stickRotated;
	private Piece l2, l2Rotated, l2Rotated2;
	
	@Before
	public void setUp() throws Exception {
		
		pyr1 = new Piece(Piece.PYRAMID_STR);
		pyr2 = pyr1.computeNextRotation();
		pyr3 = pyr2.computeNextRotation();
		pyr4 = pyr3.computeNextRotation();
		pyr5 = pyr4.computeNextRotation();
		
		s = new Piece(Piece.S1_STR);
		sRotated = s.computeNextRotation();
		sInitial = sRotated.computeNextRotation();
		
		square = new Piece(Piece.SQUARE_STR);
		squareRotate = square.computeNextRotation();
		
        stick = new Piece(Piece.STICK_STR);
        stickRotated = stick.computeNextRotation();
        
        l2 = new Piece(Piece.L2_STR);
        l2Rotated = l2.computeNextRotation();
        l2Rotated2 = l2Rotated.computeNextRotation();

	}
	
	// Here are some sample tests to get you started
	
	@Test
	public void testSampleSize() {
		// Check size of pyr piece
		assertEquals(3, pyr1.getWidth());
		assertEquals(2, pyr1.getHeight());
		
		// Now try after rotation
		// Effectively we're testing size and rotation code here
		assertEquals(2, pyr2.getWidth());
		assertEquals(3, pyr2.getHeight());
		
		// Now try with some other piece, made a different way
		Piece l = new Piece(Piece.STICK_STR);
		assertEquals(1, l.getWidth());
		assertEquals(4, l.getHeight());
		
        assertEquals(4, stickRotated.getWidth());
        assertEquals(1, stickRotated.getHeight());
        
        assertEquals(2, square.getWidth());
        assertEquals(2, square.getHeight());
        
        assertEquals(2, sRotated.getWidth());
        assertEquals(3, sRotated.getHeight());
	}
	
	
	// Test the skirt returned by a few pieces
	@Test
	public void testSampleSkirt() {
		// Note must use assertTrue(Arrays.equals(... as plain .equals does not work
		// right for arrays.
		assertTrue(Arrays.equals(new int[] {0, 0, 0}, pyr1.getSkirt()));

		assertTrue(Arrays.equals(new int[] {1, 0, 1}, pyr3.getSkirt()));
		
		assertTrue(Arrays.equals(new int[] {0, 0, 1}, s.getSkirt()));
		
		assertTrue(Arrays.equals(new int[] {1, 0}, sRotated.getSkirt()));
		
		assertTrue(Arrays.equals(new int[] {0, 2}, l2Rotated2.getSkirt()));
	}
	
	@Test
	public void testSampleEquals() {
		// Note must use assertTrue(Arrays.equals(... as plain .equals does not work
		// right for arrays.

		assertTrue(square.equals(squareRotate));
		
		assertTrue(pyr1.equals(pyr5));
		
		assertTrue(s.equals(sInitial));
		assertFalse(stick.equals(stickRotated));
		assertFalse(s.equals(sRotated));


	}
	
	@Test
	public void testGetPieces() {
		// Note must use assertTrue(Arrays.equals(... as plain .equals does not work
		// right for arrays.
		Piece[] pieces =  Piece.getPieces();

        assertTrue(pieces[Piece.S1].equals(s));
        assertTrue(pieces[Piece.L2].equals(l2));
        assertTrue(pieces[Piece.SQUARE].equals(square));
        assertTrue(pieces[Piece.PYRAMID].equals(pyr1));
        
        assertTrue(pieces[Piece.S1].fastRotation().equals(sRotated));
        assertFalse(pieces[Piece.S1].fastRotation().equals(s));
        
        assertTrue(pieces[Piece.L2].fastRotation().equals(l2Rotated));
        assertTrue(pieces[Piece.PYRAMID].fastRotation().equals(pyr2));
        
	}
	
	
}
