package mazegame;

public class Monkey extends Sprite implements Moveable {

	/** Score of the player, initially set at 0. */
	private int score = 0;
	
	/** Number of moves of a monkey made. */
	private int numMoves;
	
	/**
	 * Create a monkey with symbol "1" for player1, and symbol "2" for player2
	 * at the give row and column position.
	 * @param symbol the symbol of unvisited hallway
	 * @param row row position
	 * @param col column position
	 */
	public Monkey(char symbol, int row, int col) {
		super(symbol, row, col);
	}
	
	/**
	 * Score adds banana's value if a player ate an banana.
	 * @param score the score this player has
	 */
	public void eatBanana(int score) {
		this.score += score;
	}

	/**
	 * Return the score of this player.
	 * @return the score of this player.
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Return the number of moves by this player.
	 * @return the number of moves by this player
	 */
	public int getNumMoves() {
		return numMoves;
	}

	/**
	 * Move this player to the given row and column position.
	 * Add one to this play's number of moves.
	 */
	public void move(int row, int col) {
		this.row = row;
		this.column = col;
		numMoves += 1;
	}
}
