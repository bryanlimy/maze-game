package mazegame;

public abstract class Sprite {

	/** Symbol of the object.*/
	protected char symbol;
	
	/** Row position of the object.*/
	protected int row;
	
	/** Column position of the object.*/
	protected int column;
	
	/**
	 * Create an object by its given symbol, row and column position.
	 * "1" for player 1, "2" for player 2, "B" for banana,
	 * "M" for mobile banana, "W" for wall, "." for visited hallway and
	 * " " for unvisited hallway.
	 * @param symbol the symbol of the given object
	 * @param row row position
	 * @param col column position
	 */
	public Sprite(char symbol, int row, int col) {
		this.symbol = symbol;
		this.row = row;
		this.column = col;
	}

	/**
	 * Return the symbol of the object.
	 * @return the symbol of the object.
	 */
	public char getSymbol() {
		return symbol;
	}

	/**
	 * Return the row position of the object.
	 * @return row position of the object.
	 */
	public int getRow() {
		return row;
	}
	
	/**
	 * Return the column position of the object.
	 * @return column position of the object.
	 */
	public int getColumn() {
		return column;
	}

	@Override
	public String toString() {
		return symbol + "";
	}
}
