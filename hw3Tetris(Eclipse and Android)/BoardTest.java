package edu.stanford.cs108.tetris;

import static org.junit.Assert.*;

import org.junit.*;


public class BoardTest {
	private Board board;
	private Piece pyr1, pyr2, pyr3, pyr4, s, sRotated, stick;
	
	@Before
	public void setUp() throws Exception{
		board = new Board(3, 6);
		
		pyr1 = new Piece(Piece.PYRAMID_STR);
		pyr2 = pyr1.computeNextRotation();
		pyr3 = pyr2.computeNextRotation();
		pyr4 = pyr3.computeNextRotation();
		
		s = new Piece(Piece.S1_STR);
		sRotated = s.computeNextRotation();
		
		stick = new Piece(Piece.STICK_STR);
		board.place(pyr1, 0, 0);

	}
	
	// check the maxHeight, widths, heights after one placement
	@Test
	public void testBasic() {
		assertEquals(1, board.getColumnHeight(0));
		assertEquals(2, board.getColumnHeight(1));
		assertEquals(1, board.getColumnHeight(2));
		assertEquals(2, board.getMaxHeight());
		assertEquals(3, board.getRowWidth(0));
		assertEquals(1, board.getRowWidth(1));
		assertEquals(0, board.getRowWidth(2));
		assertEquals(0, board.getRowWidth(3));
	}
	
	// check maxHeight, widths, heights after another placement
	@Test
	public void testBasic1() {
		board.commit();
		int placeStatus = board.place(sRotated, 1, 1);
		assertEquals(Board.PLACE_OK, placeStatus);
		assertEquals(1, board.getColumnHeight(0));
		assertEquals(4, board.getColumnHeight(1));
		assertEquals(3, board.getColumnHeight(2));
		assertEquals(4, board.getMaxHeight());
		assertEquals(3, board.getRowWidth(0));
		assertEquals(2, board.getRowWidth(1));
		assertEquals(2, board.getRowWidth(2));
		assertEquals(1, board.getRowWidth(3));
		
	}
	
	@Test
	public void testClearRows() {
		
		
		int rowCleared = board.clearRows();
		
		assertEquals(1, rowCleared);
        assertEquals(0, board.getColumnHeight(0));
		assertEquals(1, board.getColumnHeight(1));
		assertEquals(1, board.getMaxHeight());
		assertEquals(0, board.getColumnHeight(2));
        assertEquals(1, board.getRowWidth(0));
        assertEquals(0, board.getRowWidth(1));
		board.commit();
		
		board.place(sRotated, 1, 0);
		int rowCleared2 = board.clearRows();
		assertEquals(0, rowCleared2);
		
		board.commit();
		int result = board.place(stick, 0, 0);
		assertEquals(Board.PLACE_OK,result);
		
		assertEquals(4, board.getMaxHeight());
		
//		System.out.println(board.toString());
		int rowCleared3 = board.clearRows();
		assertEquals(2, rowCleared3);
		
		assertEquals(0, board.getColumnHeight(2));
        assertEquals(2, board.getRowWidth(0));
        assertEquals(2, board.getMaxHeight());
	}
	
	@Test
	public void testDropHeight() {
		int res1 = board.dropHeight(pyr1, 0);
		int res2 = board.dropHeight(pyr2, 1);
		int res3 = board.dropHeight(pyr3, 0);
		int res4 = board.dropHeight(pyr4, 0);
		int res5 = board.dropHeight(s, 0);
		
		assertEquals(2, res1);
		assertEquals(1, res2);
		assertEquals(2, res3);
		assertEquals(1, res4);
		assertEquals(2, res5);
		
	}
	
	@Test
	public void testUndo() {
        board.undo();
        assertEquals(0, board.getColumnHeight(0));
        assertEquals(0, board.getMaxHeight());
        assertEquals(0, board.getRowWidth(0));

        
        board.place(pyr1, 0, 0);
        board.commit();

        board.place(pyr2, 1, board.dropHeight(pyr2, 1));
        board.clearRows();
        board.undo();

        assertEquals(1, board.getColumnHeight(0));
        assertEquals(2, board.getMaxHeight());
        assertEquals(3, board.getRowWidth(0));
        
	}

}
