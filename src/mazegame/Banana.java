package mazegame;

public class Banana extends Sprite {

	/** the value of the banana. */
	protected int value;
	
	/**
	 * Create a new banana with the given symbol, row position, column position
	 * and it's value.
	 * Banana has symbol "B" and value of 1.
	 * Mobile banana has symbol "M" and value of 2.
	 * @param symbol the symbol of the banana
	 * @param row the row position of the banana
	 * @param col the column position of the banana
	 * @param value the value of the banana
	 */
	public Banana(char symbol, int row, int col, int value) {
		super(symbol, row, col);
		// TODO Auto-generated constructor stub
		this.value = value;
	}
	
	/**
	 * Return the value of the give banana.
	 * @return the value of the given banana
	 */
	public int getValue() {
		return value;
	}
}
