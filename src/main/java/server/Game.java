package server;

import java.awt.Point;
import java.util.ArrayList;
import board.BoardType;
import board.Field;
import board.Pawn;

/**
 * @author filipbk
 * Class for storing current state of game,
 * board, etc.
 *
 */
public class Game {
	
	private Field board[][];
	private int boardHeight, boardWidth;
	
	public Game() {
		// TODO Will be removed after changes in GameHandler
	}
	
	//Creates the game with a given type of board
	public Game(BoardType boardType) {
		String[][] tmpBoard = boardType.getBoard();
		this.boardHeight = tmpBoard.length;
		this.boardWidth = tmpBoard[0].length;
		this.board = new Field[boardHeight][boardWidth];
		
		for(int i = 0; i < tmpBoard.length; i++) {
			for(int j = 0; j < tmpBoard[i].length; j++) {
				this.board[i][j] = new Field(tmpBoard[i][j]);
				//System.out.print(board[i][j].getFieldType() + " ");
			}
			//System.out.println();
		}
	}
	
	public ArrayList<Point> checkValidMoves(Pawn pawn) {
		ArrayList<Point> validMoves = new ArrayList<>();
		int x = pawn.getLocation().x;
		int y = pawn.getLocation().y;
		 
		if (isValid(x - 1, y - 1, pawn)) {
			validMoves.add(new Point(x - 1, y - 1));
		}
		else {
			if (isValid(x - 2, y - 2, pawn)) {
				validMoves.add(new Point(x - 2, y - 2));
			}
		}
		
		// TODO will be added other possible directions
		
		return validMoves;
	}
	
	/**
	 * @param x x coord
	 * @param y y coord
	 * @param pawn pawn
	 * @return true if filed is valid, false if field is
	 * over the board, is taken or pawn is in target and field
	 * is over target corner
	 */
	private boolean isValid(int x, int y, Pawn pawn) {
		// TODO Changes should be added after implementation of move()
		if(x < 0 || x >= boardHeight || y < 0 || y >= boardWidth) {
			return false;
		}
		
		if(board[x][y].isEmpty() == true && pawn.isInTarget() == false) {
			return true;
		}
		else if (board[x][y].isEmpty() == true && pawn.isInTarget() == true && board[x][y].getFieldType().equals(pawn.getTarget()) == true ) {
			return true;
		}
		else {
			return false;
		}
	}
	
}

