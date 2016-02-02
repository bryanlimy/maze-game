package mazegame;

public class VisitedHallway extends Sprite {

	/**
	 * Create a visited hallway with symbol ".", at the give row and column
	 * position.
	 * @param symbol the symbol of visited hallway
	 * @param row row position
	 * @param col column position
	 */
	public VisitedHallway(char symbol, int row, int col) {
		super(symbol, row, col);
	}
}
