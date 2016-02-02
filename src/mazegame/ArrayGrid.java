package mazegame;

public class ArrayGrid<T> implements Grid<T> {
	
	/** the number of rows in the maze. */
	private int numRows;
	
	/** the number of columns in the maze. */
	private int numCols;
		
	/** create a 2D array that store the Grid's size. */
	private T [][] grid;
	
	/**
	 * Create an array that is defined by the number of rows and columns
	 * @param numRows the number of rows
	 * @param numCols the number of columns
	 */
	public ArrayGrid(int numRows, int numCols) {
		this.numRows = numRows;
		this.numCols = numCols;
		grid = ((T[][]) new Object[numRows][numCols]);
	}

	/**
	 * Return the number of rows.
	 */
	public int getNumRows() {
		return this.numRows;
	}

	/**
	 * Return the number of columns.
	 */
	public int getNumCols() {
		return this.numCols;
	}

	/**
	 * Return the item at position row and column in the maze.
	 * @param row the row of the item
	 * @param col the column of the item
	 * @return the item at (row, column)
	 */
	public T getCell(int row, int col) {
		return grid[row][col];
	}
	
	@Override
	public void setCell(int row, int col, T item) {
		grid[row][col] = item;
	}

	/**
	 * Compare two mazes, return true if they are equal.
	 * Two mazes are equal when each row and column position are equal.
	 * @return true when two mazes are equal, false otherwise
	 */
	public boolean equals(Grid<T> other) {
		for (int i = 0; i < this.numRows; i++) {
			for (int j = 0; j < this.numCols; j++) {
				if (this.getCell(i, j) != other.getCell(i, j)) {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * Print out the maze in 2D.
	 */
	public String toString() {
		String result = "";
		for (int i = 0; i < this.numRows; i++) {
			for (int j = 0; j < this.numCols; j++) {
				if ( j + 1 >= this.numCols) {
					result += this.getCell(i, j) + "\n";
				} else {
					result += this.getCell(i, j);
				}
			}
		}
		return result;
	}
}

