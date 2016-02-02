package ui;


import java.util.Scanner;
import mazegame.MazeGame;

public class TextUI implements UI {
	
    /** 
     * Initializes a GUI for the given MazeGame.
     * @param game The MazeGame of this TextUI 
     */
	private MazeGame game;
	
    /** 
     * Returns the MazeGame of this TextUI
     * @return the MazeGame of this TextUI
     */
	public TextUI(MazeGame game) {
		this.game = game;
	}
	
	@Override
	public void launchGame() {
		if (!game.isBlocked() & game.hasWon() == 0) {
			System.out.println("Please enter your next move: ");
			Scanner input = new Scanner(System.in);
			while (input.hasNext()) {
				String next = input.next();
				char character = next.charAt(0);
				game.move(character);
				System.out.println(game.getMaze());
				if (game.isBlocked() || game.hasWon() != 0) {
					break;
				}
				System.out.println("Please enter your next move: ");
			}
		}
	}

    @Override
    public void displayWinner() {
        
        int won = game.hasWon();        
        String message;
        
        if (game.isBlocked()) { // no winners
            message = "Game over! Both players are stuck.";
        } else {
            if (won == 0) { // game is still on
                return;
            } else if (won == 1) {
                message = "Congratulations Player 1! You won the maze in " + 
                          game.getPlayerOne().getNumMoves() + " moves.";
            } else if (won == 2) { 
                message = "Congratulations Player 2! You won the maze in " + 
                          game.getPlayerTwo().getNumMoves() + " moves.";
            } else { // it's a tie
                message = "It's a tie!";
            }
        }     
        System.out.println(message);
    }
}
