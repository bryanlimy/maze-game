package mazegame;

public class MobileBanana extends Banana implements Moveable {

	/**
	 * Create mobile banana with symbol "M", its row and column position
	 * and its value 
	 * @param symbol the symbol of mobile banana
	 * @param row the row position of this mobile banana
	 * @param col the column position of this mobile banana
	 * @param value the value of mobile banana
	 */
	public MobileBanana(char symbol, int row, int col, int value) {
		super(symbol, row, col, value);
	}

	@Override
	public void move(int row, int col) {
		this.row = row;
		this.column = col;
        }
}