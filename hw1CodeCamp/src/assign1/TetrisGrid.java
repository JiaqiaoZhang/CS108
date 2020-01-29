//
// TetrisGrid encapsulates a tetris board and has
// a clearRows() capability.
package assign1;

public class TetrisGrid {
	private boolean[][] grid;
	
	/**
	 * Constructs a new instance with the given grid.
	 * Does not make a copy.
	 * @param grid
	 */
	public TetrisGrid(boolean[][] grid) {
		this.grid = grid;
	}
	
	
	/**
	 * Does row-clearing on the grid (see handout).
	 */
	public void clearRows() {
		for(int c = 0; c < this.grid[0].length; c++) {
			for(int r = 0; r < this.grid.length - 1; r++) {
				if(this.grid[r][c] == false) {break;}
				if(this.grid[r][c] != this.grid[r+1][c]) {break;}
				if(r+1 == this.grid.length - 1) {
					changeRow(c);
				}
				c--;
			}
			
		}
		if(this.grid.length == 1) {
			for(int c = 0; c < this.grid[0].length; c++) {
				if(this.grid[0][c] == true) {
					changeRow(c);
				}
			}		
		}	
	}
	
	private void changeRow(int col) {
		

		while(col != this.grid[0].length - 1) {
			for(int r = 0; r < this.grid.length; r++) {
				this.grid[r][col] = this.grid[r][col+1];

			}
			col ++;
		}
		for(int r = 0; r < this.grid.length; r++) {
			this.grid[r][col] = false;

		}

	}
	
	/**
	 * Returns the internal 2d grid array.
	 * @return 2d grid array
	 */
	boolean[][] getGrid() {

		return this.grid; // TODO YOUR CODE HERE
	}
}
