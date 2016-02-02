package mazegame;

import java.io.File;
import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.List;

/**
 * A class that represents the basic functionality of the maze game.
 * This class is responsible for performing the following operations:
 * 1. At creation, it initializes the instance variables used to store the
 *        current state of the game.
 * 2. When a move is specified, it checks if it is a legal move and makes the
 *        move if it is legal.
 * 3. It reports information about the current state of the game when asked.
 */
public class MazeGame {

	/** A random number generator to move the MobileBananas. */
    private Random random;
    
    /** The maze grid. */
    private Grid<Sprite> maze;
    
    /** The first player. */
    private Monkey player1;
    
    /** The second player. */
    private Monkey player2;

    /** The bananas to eat. */
    private List<Banana> bananas = new ArrayList<Banana>();

    
    /**
     * Creates a new MazeGame that corresponds to the maze in the file
     * named layoutFileName.
     * @param layoutFileName the path to the input maze file
     */
    public MazeGame(String layoutFileName) throws IOException {
        random = new Random();
        
        int[] dimensions = getDimensions(layoutFileName);
        maze = new ArrayGrid<Sprite>(dimensions[0], dimensions[1]);
               
        Scanner sc = new Scanner(new File(layoutFileName));

        /* INITIALIZE THE GRID HERE */
        
        int row = 0;
        String nextLine = sc.nextLine();
        
        while (sc.hasNext()) {
        	initiate(nextLine, row);
        	row ++;
        	nextLine = sc.nextLine();
    	}
        initiate(nextLine, row);
        sc.close();
    }
    
    /**
     * Initiate the grid by assigning symbols to the maze
     * @param line the line in the txt file
     * @param row the number of rows it is initiating
     */
    public void initiate(String line, int row) {
    	int col = 0;
    	for (int length = line.length(); col < length; col ++) {
    		char sign = line.charAt(col);
    		switch (sign) {
    		case MazeConstants.WALL:
    			maze.setCell(row, col, new Wall(sign, row, col));
    			break;
    		case MazeConstants.P1:
    			player1 = new Monkey(MazeConstants.P1, row, col);
    			maze.setCell(row, col, player1);
    			break;
    		case MazeConstants.P2:
    			player2 = new Monkey(MazeConstants.P2, row, col);
    			maze.setCell(row, col, player2);
    			break;
    		case MazeConstants.BANANA:
    			Banana new_banana = new Banana(MazeConstants.BANANA,
    					row, col,MazeConstants.BANANA_SCORE);
    			bananas.add(new_banana);
    			maze.setCell(row, col, bananas.get(bananas.size() - 1));
    			break;
    		case MazeConstants.MOBILE_BANANA:
    			MobileBanana new_mobile = new MobileBanana(MazeConstants.MOBILE_BANANA,
    					row, col, MazeConstants.MOBILE_BANANA_SCORE);
    			bananas.add(new_mobile);
    			maze.setCell(row, col, bananas.get(bananas.size() - 1));
    			break;
    		case MazeConstants.VACANT:
    			maze.setCell(row, col, new UnvisitedHallway(sign, row, col));
    			break;
    		}
    	}
    }
    
    /**
     * Returns the dimensions of the maze in the file named layoutFileName.
     * @param layoutFileName the path of the input maze file
     * @return an array [numRows, numCols], where numRows is the number
     * of rows and numCols is the number of columns in the maze that
     * corresponds to the given input maze file
     * @throws IOException
     */    
    private int[] getDimensions(String layoutFileName) throws IOException {       
        
        Scanner sc = new Scanner(new File(layoutFileName));

        // find the number of columns
        String nextLine = sc.nextLine();
        int numCols = nextLine.length();

        int numRows = 1;

        // find the number of rows
        while (sc.hasNext()) {
            numRows++;
            nextLine = sc.nextLine();
        }

        sc.close();
        return new int[]{numRows, numCols};
    }

    /**
     * Return the maze
     * @return the maze
     */
	public Grid<Sprite> getMaze() {
		return maze;
	}
	
	/**
	 * Return player1
	 * @return player1
	 */
	public Monkey getPlayerOne() {
		return player1;
	}
	
	/**
	 * Return player2.
	 * @return player2
	 */
	public Monkey getPlayerTwo() {
		return player2;
	}
    
	/**
	 * Return the number of rows in the maze.
	 * @return the number of rows in the maze
	 */
    public int getNumRows() {
    	return maze.getNumRows();
    }
    
    /**
     * Return the number of columns in the maze.
     * @return the number of columns in the maze
     */
    public int getNumCols() {
    	return maze.getNumCols();
    }
    
    /**
     * Return the item at row i and column j.
     * @param i the row in the maze
     * @param j the column in the maze
     * @return the item at row i and column j
     */
    public Sprite get(int i, int j) {
		return maze.getCell(i, j);
	} 
    
    /**
     * Move the player according to its input, if it is movable, else do nothing.
     * Add the symbol ".", after the player moves, and that spot cannot be entered again.
     * Player1 uses "w", "a", "s", "d" for up, left, down, right.
     * Player2 uses "i", "j","k","l" for up, left, down, right.
     * @param nextMove the direction of the monkey's next move
     */
    public void move(char nextMove) {
    	int player1_row = player1.getRow();
    	int player1_col = player1.getColumn();
    	int player2_row = player2.getRow();
    	int player2_col = player2.getColumn();	
    	
    	switch (nextMove) {
    	case MazeConstants.P1_LEFT: 
    		if (Movable(player1, player1_row, player1_col - 1)) {
    			player1_col -= 1;
    			}
    		break;
    	case MazeConstants.P1_DOWN: 
    		if (Movable(player1, player1_row + 1, player1_col)) {
    			player1_row += 1;
    			}
    		break;
    	case MazeConstants.P1_RIGHT:
    		if (Movable(player1, player1_row, player1_col + 1)) {
    			player1_col += 1;
    			}
    		break;
    	case MazeConstants.P1_UP:
    		if (Movable(player1, player1_row - 1, player1_col)) {
    			player1_row -= 1;
    			}
    		break;
    	case MazeConstants.P2_LEFT:
    		if (Movable(player2, player2_row, player2_col - 1)) {
    			player2_col -= 1;
    			}
    		break;
    	case MazeConstants.P2_DOWN:
    		if (Movable(player2, player2_row + 1, player2_col)) {
    			player2_row += 1;
    			}
    		break;
    	case MazeConstants.P2_RIGHT:
    		if (Movable(player2, player2_row, player2_col + 1)) {
    			player2_col += 1;
    			}
    		break;
    	case MazeConstants.P2_UP:
    		if (Movable(player2, player2_row - 1, player2_col)) {
    			player2_row -= 1;
    			}
    		break;
    	}

    	if (player1_row != player1.getRow() 
    			|| player1_col != player1.getColumn())
    	{	
    		eatable(player1, player1_row, player1_col);
    		maze.setCell(player1.getRow(), player1.getColumn(), 
    				new VisitedHallway (MazeConstants.VISITED, 
    						player1.getRow(), player1.getColumn()));
        	player1.move(player1_row, player1_col);
        	maze.setCell(player1_row, player1_col, player1);
        	move_mobile();
    	}
    	
    	if (player2_row != player2.getRow() 
    			|| player2_col != player2.getColumn())
    	{
    		eatable(player2, player2_row, player2_col);
    		maze.setCell(player2.getRow(), player2.getColumn(),
    				new VisitedHallway (MazeConstants.VISITED,
    						player2.getRow(), player2.getColumn()));
        	player2.move(player2_row, player2_col);
        	maze.setCell(player2_row, player2_col, player2);
        	move_mobile();
    	}
    }
    
    /**
     * Check if the input direction is movable, also eat the banana if there is one.
     * Return true is the direction is movable, false otherwise.
     * Add score = 1 if there is a banana
     * Add score = 2 if there is a mobile banana
     * @param player the monkey who is moving
     * @param row the row position of the monkey
     * @param col the column position of the monkey
     * @return true if it is movable, false if there is a wall or visited or a player is there
     */
    private boolean Movable(Monkey player, int row, int col) {
    	if (maze.getCell(row, col).getSymbol() == MazeConstants.BANANA) {
    		return true;
    	} else if (maze.getCell(row, col).getSymbol() ==
    			MazeConstants.MOBILE_BANANA) {
    		return true;
    	} else if (maze.getCell(row, col).getSymbol() ==
    			MazeConstants.VACANT) {
    		return true;
    	} else {
    		return false;
    	}
    }
    
    /**
     * Eat the banana at row and column.
     * @param player the player who is eating the banana
     * @param row the row position of the player
     * @param col the column position of the player
     */
    public void eatable(Monkey player, int row, int col) {
    	if (maze.getCell(row, col).getSymbol() == MazeConstants.BANANA) {
    		for (Banana banana: bananas) {
    			if (banana.getRow() == row & banana.getColumn() == col) {
    				bananas.remove(banana);
    				break;
    			}
    		}
    		player.eatBanana(MazeConstants.BANANA_SCORE);
    	} else if (maze.getCell(row, col).getSymbol() ==
    			MazeConstants.MOBILE_BANANA) {
    		for (Banana mobilebanana: bananas) {
    			if (mobilebanana.getRow() == row
    					& mobilebanana.getColumn() == col) {
    				bananas.remove(mobilebanana);
    				break;
    			}
    		}
    		player.eatBanana(MazeConstants.MOBILE_BANANA_SCORE);
    	}
    }

    /**
     * Return the winner of the game.
     * Only return the winner when all bananas are eaten.
     * 1 if player 1 won, 2 if player 2 won, 3 if it is a tie
     * @return the winner of the game
     */
    public int hasWon() {
    	if (bananas.size() == 0) {
	    	if (player1.getScore() > player2.getScore()) {
	    		return 1;
	    	} else if (player1.getScore() < player2.getScore()) {
	    		return 2;
	    	} else {
	    		return 3;
	    	}
    	} 
    	else {
    		return 0;
    	}
    }
    
    /**
     * Return true if both are blocked, false if not both players are blocked.
     * @return if both players are blocked
     */
    public boolean isBlocked() {
    	if (playerBlocked(player1) & playerBlocked(player2)) {
    		return true;
    	} else {
    		return false;
    	}
    }
    
    /**
     * Check if a player is blocked.
     * @param player the player that is being check
     * @return if the player is blocked
     */
    public boolean playerBlocked(Monkey player) {
    	int row = player.getRow();
    	int col = player.getColumn();
    	
    	if (!Movable(player, row + 1, col) &
    			!Movable(player, row - 1, col) &
    			!Movable(player, row, col + 1) &
    			!Movable(player, row, col - 1)) {
    		return true;
    	} else {
    		return false;
    	}
    }

    /**
     * Move the mobile banana.
     */
    public void move_mobile() {
    	for (Banana mobilebanana: bananas) {
    		if (mobilebanana.value == 2) {
    			randomMove(mobilebanana);
    		}
    	}
    }
    
	/**
	 * Random generate a direction and move the mobile banana.
	 * Assign 0 as left, 1 as down, 2 as right, 3 as up.
	 */
    public void randomMove(Banana mobilebanana) {
    	int row = mobilebanana.getRow();
    	int col = mobilebanana.getColumn();

    	
    	int number = random.nextInt(4);
    	switch (number) {
    	case 0:
    		if (maze.getCell(row, col - 1).getSymbol() == MazeConstants.VACANT)
    		{
    			col -= 1;
    		}
    		break;
    	case 1: 
    		if (maze.getCell(row + 1, col).getSymbol() == MazeConstants.VACANT)
    		{
    			row += 1;
    		}
    		break;
    	case 2:
    		if (maze.getCell(row, col + 1).getSymbol() == MazeConstants.VACANT)
    		{
    			col += 1;
    		}
    		break;
    	case 3:
    		if (maze.getCell(row - 1, col).getSymbol() == MazeConstants.VACANT)
    		{
    			row -= 1;
    		}
    		break;
    	}
    	Sprite unvisited = new UnvisitedHallway(MazeConstants.VACANT, 
    			mobilebanana.getRow(), mobilebanana.getColumn());
    	maze.setCell(mobilebanana.getRow(), mobilebanana.getColumn(),
    			unvisited);
    	maze.setCell(row, col, mobilebanana);
    	((MobileBanana) mobilebanana).move(row, col);
    }
}
