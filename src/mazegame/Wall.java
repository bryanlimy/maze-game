package mazegame;

public class Wall extends Sprite {

	/**
	 * Create a wall with symbol "X", at the give row and column
	 * position.
	 * @param symbol the symbol of wall
	 * @param row row position
	 * @param col column position
	 */
	public Wall(char symbol, int row, int col) {
		super(symbol, row, col);
	}
}
