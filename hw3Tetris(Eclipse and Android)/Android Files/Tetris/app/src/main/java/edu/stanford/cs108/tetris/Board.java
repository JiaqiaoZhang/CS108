// Board.java
package edu.stanford.cs108.tetris;

import java.util.Arrays;
import java.util.function.IntPredicate;

/**
 CS108 Tetris Board.
 Represents a Tetris board -- essentially a 2-d grid
 of booleans. Supports tetris pieces and row clearing.
 Has an "undo" feature that allows clients to add and remove pieces efficiently.
 Does not do any drawing or have any idea of pixels. Instead,
 just represents the abstract 2-d board.
*/
public class Board	{
	// Some ivars are stubbed out for you:
	private int width;
	private int height;
	private boolean[][] grid;
	private boolean DEBUG = true;
	boolean committed;
	
	private int maxHeight;
	private int[] widths;
	private int[] heights;
	// backup data structure
	private boolean[][] xGrid;
	private int[] xWidths;
	private int[] xHeights;
	private int xMaxHeight;
	// Here a few trivial methods are provided:
	/**
	 Creates an empty board of the given width and height
	 measured in blocks.
	*/																			
	public Board(int width, int height) {
		this.width = width;
		this.height = height;
		grid = new boolean[width][height];
		committed = true;
		
		maxHeight = 0;
		widths = new int[height];
		heights = new int[width];
		
		xGrid = new boolean[width][height];
		xMaxHeight = 0;
		xWidths = new int[height];
		xHeights = new int[width];
	}
	
	
	/**
	 Returns the width of the board in blocks.
	*/
	public int getWidth() {
		return width;
	}
	
	
	/**
	 Returns the height of the board in blocks.
	*/
	public int getHeight() {
		return height;
	}
	
	
	/**
	 Returns the max column height present in the board.
	 For an empty board this is 0.
	*/
	public int getMaxHeight() {	 
		return maxHeight; 
	}
	
	
	/**
	 Checks the board for internal consistency -- used
	 for debugging.
	*/
	public void sanityCheck() {
		if (DEBUG) {
			int trueMaxHeight = 0;
			int[] trueWidths = new int[height];

			for(int i = 0; i < width; i++) {
				int currHeight = 0;
				for(int j = 0; j < height; j++) {
					if(grid[i][j]) {
						currHeight = j + 1;
						trueWidths[j]++;
					}
				}
				if(currHeight > trueMaxHeight) {trueMaxHeight = currHeight;}
				if(heights[i] != currHeight) {
					throw new RuntimeException("the hegihts array is not correct");
				}
			}
			if(!Arrays.equals(widths, trueWidths)) {
				throw new RuntimeException("the widths array is not correct");
			}

			if(trueMaxHeight != maxHeight) {
				throw new RuntimeException("the maxHeight is not correct");
			}
			
		}
	}
	
	/**
	 Given a piece and an x, returns the y
	 value where the piece would come to rest
	 if it were dropped straight down at that x.
	 
	 <p>
	 Implementation: use the skirt and the col heights
	 to compute this fast -- O(skirt length).
	*/
	public int dropHeight(Piece piece, int x) {
		int[] skirt = piece.getSkirt();
		int y = 0;
		
		for(int i = 0; i < skirt.length; i ++) {
			int currY = getColumnHeight(x + i) - skirt[i];
			if(currY > 0 && currY > y) {
				y = currY;
			}
		}
		return y;
	}
	
	
	/**
	 Returns the height of the given column --
	 i.e. the y value of the highest block + 1.
	 The height is 0 if the column contains no blocks.
	*/
	public int getColumnHeight(int x) {
		
		return heights[x]; 
	}
	
	
	/**
	 Returns the number of filled blocks in
	 the given row.
	*/
	public int getRowWidth(int y) {
		 return widths[y]; 
	}
	
	
	/**
	 Returns true if the given block is filled in the board.
	 Blocks outside of the valid width/height area
	 always return true.
	*/
	public boolean getGrid(int x, int y) {
		if(x >= width || y >= height || x < 0 || y < 0) {return true;}
		return grid[x][y]; 
	}
	
	
	public static final int PLACE_OK = 0;
	public static final int PLACE_ROW_FILLED = 1;
	public static final int PLACE_OUT_BOUNDS = 2;
	public static final int PLACE_BAD = 3;
	
	/**
	 Attempts to add the body of a piece to the board.
	 Copies the piece blocks into the board grid.
	 Returns PLACE_OK for a regular placement, or PLACE_ROW_FILLED
	 for a regular placement that causes at least one row to be filled.
	 
	 <p>Error cases:
	 A placement may fail in two ways. First, if part of the piece may falls out
	 of bounds of the board, PLACE_OUT_BOUNDS is returned.
	 Or the placement may collide with existing blocks in the grid
	 in which case PLACE_BAD is returned.
	 In both error cases, the board may be left in an invalid
	 state. The client can use undo(), to recover the valid, pre-place state.
	*/
	public int place(Piece piece, int x, int y) {
		// flag !committed problem
		if (!committed) throw new RuntimeException("place commit problem");
		committed = false;
		backUp();
		int result = PLACE_OK;
		
		
		if(x + piece.getWidth() > width || y + piece.getHeight() > height || x  < 0 || y < 0) {
			return PLACE_OUT_BOUNDS;
		}
		for(TPoint p : piece.getBody()) {
			int currX = x + p.x;
			int currY = y + p.y;
			if(grid[currX][currY]) {
				return PLACE_BAD;
			}
			
			grid[currX][currY] = true;
			
			if(heights[currX] < currY + 1) {
				heights[currX] = currY + 1;

				if(heights[currX] > maxHeight) {maxHeight = heights[currX];}
			}
			
			if(widths[currY] == width) {
				return PLACE_ROW_FILLED;
			}
			widths[currY]++;
			
		}
		sanityCheck();
		return result;
	}
	
	
	/**
	 Deletes rows that are filled all the way across, moving
	 things above down. Returns the number of rows cleared.
	*/
	public int clearRows() {
		int rowsCleared = 0;
		committed = false;
		
		int targetRow = 0;
		for(int currRow = 0; currRow < maxHeight; currRow ++) {
			if(widths[currRow] == width) {
				rowsCleared++;
			}else if(rowsCleared > 0) {
				moveRow(currRow, targetRow);
				targetRow ++;
			}else {
				targetRow ++;
			}
		}

		int originalHeight = maxHeight;
		while(targetRow < originalHeight) {
			widths[targetRow] = 0;
			for(int i = 0; i < width; i++) {
				grid[i][targetRow] = false;
				heights[i] --;
			}

			targetRow ++;
			maxHeight --;
		}

		for(int i = 0; i < width; i ++) {
			while(heights[i] > 0 && !grid[i][heights[i]-1]){
				heights[i]--;
			}
		}


		sanityCheck();
		return rowsCleared;
	}
	
	private void moveRow(int from, int to) {
		widths[to] = widths[from];
		for(int i = 0; i < width; i ++) {
			grid[i][to] = grid[i][from];	
		}
	}


	/**
	 Reverts the board to its state before up to one place
	 and one clearRows();
	 If the conditions for undo() are not met, such as
	 calling undo() twice in a row, then the second undo() does nothing.
	 See the overview docs.
	*/
	public void undo() {
		if(committed) {return;}
		committed = true;
        boolean[][] temp = grid;
        grid = xGrid;
        xGrid = temp;

        int[] tempX = heights;
        heights = xHeights;
        xHeights = tempX;

        int[] tempY = widths;
        widths = xWidths;
        xWidths = tempY;

		maxHeight = xMaxHeight;

		sanityCheck();
	}
	
	private void backUp() {
		System.arraycopy(heights, 0, xHeights, 0, width);
		System.arraycopy(widths, 0, xWidths, 0, height);
		for(int i = 0;i < width;i++) {
			System.arraycopy(grid[i], 0, xGrid[i], 0, height);
		}
		xMaxHeight = maxHeight;
	}
	
	
	/**
	 Puts the board in the committed state.
	*/
	public void commit() {
		committed = true;
	}


	
	/*
	 Renders the board state as a big String, suitable for printing.
	 This is the sort of print-obj-state utility that can help see complex
	 state change over time.
	 (provided debugging utility) 
	 */
	public String toString() {
		StringBuilder buff = new StringBuilder();
		for (int y = height-1; y>=0; y--) {
			buff.append('|');
			for (int x=0; x<width; x++) {
				if (getGrid(x,y)) buff.append('+');
				else buff.append(' ');
			}
			buff.append("|\n");
		}
		for (int x=0; x<width+2; x++) buff.append('-');
		return(buff.toString());
	}
}


