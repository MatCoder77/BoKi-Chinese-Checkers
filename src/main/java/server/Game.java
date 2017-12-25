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
	
	/**
	 * @param boardType type of board to set
	 * Creates the game with a given type of board
	 */
	public Game(BoardType boardType) {
		String[][] tmpBoard = boardType.getBoard();
		boolean[][] tmpEmpty = boardType.getBoardEmpty();
		this.boardHeight = tmpBoard.length;
		this.boardWidth = tmpBoard[0].length;
		this.board = new Field[boardHeight][boardWidth];
		
		for(int i = 0; i < tmpBoard.length; i++) {
			for(int j = 0; j < tmpBoard[i].length; j++) {
				this.board[i][j] = new Field(tmpBoard[i][j], tmpEmpty[i][j]);
				System.out.print(board[i][j].getFieldType() + " ");
			}
			System.out.println();
		}
	}
	
	/**
	 * @param pawn pawn to check
	 * @return ArrayList of Point with possible moves
	 */
	public ArrayList<Point> checkValidMoves(Pawn pawn) {
		ArrayList<Point> validMoves = new ArrayList<>();
		int x = pawn.getLocation().x;
		int y = pawn.getLocation().y;
		 
		if(isValid(x - 1, y - 1, pawn)) {
			validMoves.add(new Point(x - 1, y - 1));
		}
		else {
			if(isValid(x - 2, y - 2, pawn)) {
				validMoves.add(new Point(x - 2, y - 2));
			}
		}
		 
		if(isValid(x - 1, y, pawn)) {
			validMoves.add(new Point(x - 1, y));
		}
		else {
			if(isValid(x - 2, y, pawn)) {
				validMoves.add(new Point(x - 2, y));
			}
		}
		 
		if(isValid(x, y - 1, pawn)) {
			validMoves.add(new Point(x, y - 1));
		}
		else {
			if(isValid(x, y - 2, pawn)) {
				validMoves.add(new Point(x, y - 2));
			}
		}
		 
		if(isValid(x, y + 1, pawn)) {
			validMoves.add(new Point(x, y + 1));
		}
		else {
			if(isValid(x, y + 2, pawn)) {
				validMoves.add(new Point(x, y + 2));
			}
		}
		 
		if(isValid(x + 1, y, pawn)) {
			validMoves.add(new Point(x + 1, y));
		}
		else {
			if(isValid(x + 2, y, pawn)) {
				validMoves.add(new Point(x + 2, y));
			}
		}
		 
		if(isValid(x + 1, y + 1, pawn)) {
			validMoves.add(new Point(x + 1, y + 1));
		}
		else {
			if(isValid(x + 2, y + 2, pawn)) {
				validMoves.add(new Point(x + 2, y + 2));
			}
		}
		
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
		if(x < 0 || x >= boardHeight || y < 0 || y >= boardWidth) {
			return false;
		}
		
		if(board[x][y].isEmpty() == true && pawn.isInTarget() == false) {
			return true;
		}
		else if(board[x][y].isEmpty() == true && board[x][y].getFieldType().equals(pawn.getTarget()) == true) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public ArrayList<Pawn> getPawns(int corner) {
		String clientCorner = Integer.toString(corner);
		ArrayList<Pawn> pawns = new ArrayList<>();
		String target = Integer.toString((corner + 3) % 6);
		
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				if(board[i][j].getFieldType().equals(clientCorner)) {
					pawns.add(new Pawn(new Point(i, j), target));
				}
			}
		}
		
		return pawns;
	}
	
	public void move(Pawn pawn, Point destination) {
		board[pawn.getLocation().x][pawn.getLocation().y].setEmpty();
		board[destination.x][destination.y].setTaken();
		pawn.setLocation(destination);
		
		if(board[destination.x][destination.y].getFieldType().equals(pawn.getTarget())) {
			pawn.setInTarget();
		}
	}
	
}

