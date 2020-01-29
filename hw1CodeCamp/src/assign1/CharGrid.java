// HW1 2-d array Problems
// CharGrid encapsulates a 2-d grid of chars and supports
// a few operations on the grid.

package assign1;

public class CharGrid {
	private char[][] grid;

	/**
	 * Constructs a new CharGrid with the given grid.
	 * Does not make a copy.
	 * @param grid
	 */
	public CharGrid(char[][] grid) {
		this.grid = grid;
	}
	
	/**
	 * Returns the area for the given char in the grid. (see handout).
	 * @param ch char to look for
	 * @return area for given char
	 */
	public int charArea(char ch) {
		int minRow = Integer.MAX_VALUE, minCol = Integer.MAX_VALUE, maxRow = 0, maxCol = 0;
		boolean appear = false;
		for(int r = 0; r < this.grid.length; r++) {
			for(int c = 0; c < this.grid[0].length; c++) {
				if(this.grid[r][c] == ch) {
					appear = true;
					if(r < minRow) {minRow = r;}
					if(c < minCol) {minCol = c;}
					if(r > maxRow) {maxRow = r;}
					if(c > maxCol) {maxCol = c;}
				}
			}
		}
		if(!appear) {return 0;}
		int height = maxRow - minRow + 1;
		int width = maxCol - minCol + 1;
		return height * width; // TODO ADD YOUR CODE HERE
	}
	
	/**
	 * Returns the count of '+' figures in the grid (see handout).
	 * @return number of + in grid
	 */
	public int countPlus() {
		int count = 0;
		if(this.grid.length < 3 || this.grid[0].length < 3) {return 0;}
		for(int row = 1; row < this.grid.length - 1; row++) {
			for(int col = 1; col < this.grid[0].length - 1; col++) {
				if(isPlus(row, col)) {count++;}
			}
		}
		return count; // TODO ADD YOUR CODE HERE
	}
	
	private boolean isPlus(int row, int col) {
		char central = this.grid[row][col];
		int arm =1;
		int maxRow = this.grid.length;
		int maxCol = this.grid[0].length;
		while(this.grid[row-arm][col] == central && this.grid[row+arm][col] == central && 
				this.grid[row][col-arm] == central && this.grid[row][col+arm] == central) {
			arm++;

			if(row+arm >= maxRow || row - arm < 0 || col-arm < 0 || col + arm >= maxCol) {
				break;
			}
		}
		if(row+arm < maxRow && this.grid[row+arm][col] == central) {return false;}
		if(row-arm >= 0 && this.grid[row-arm][col] == central) {return false;}
		if(col+arm < maxCol && this.grid[row][col+arm] == central) {return false;}
		if(col-arm >= 0 && this.grid[row][col-arm] == central) {return false;}
		if(arm == 1) {return false;}
		return true;
	}
	
}
