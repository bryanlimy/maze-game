package mazegame;

public interface Grid<T>  {
	
	/**
	 * Assign the item to the given row and column position.
	 * @param row the number of rows
	 * @param col the number of columns
	 * @param item the item to be assigned
	 */
	public void setCell(int row, int col, T item);
	
	/** Return the item at the given row and column position. */
	public T getCell(int row, int col);
	
	/** Return the number of rows. */
	public int getNumRows();
	
	/** Return the number of columns. */
	public int getNumCols();
	
	/**
	 * Check if two grids are equal, true if they are equal, false otherwise.
	 * @param other the second grid that is being compared
	 * @return true if two grid are equal
	 */
	public boolean equals(Grid<T> other);
	
	/**
	 * Print the Grid in 2D dimension.
	 * @return the grid in 2D dimension
	 */
	public String toString();

	
}
