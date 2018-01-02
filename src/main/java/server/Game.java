package server;

import java.awt.Point;
import java.util.ArrayList;
import board.BoardType;
import board.Field;
import board.Pawn;
import server.Player.MoveType;

/**
 * @author filipbk
 * Class for storing current state of game,
 * board, etc.
 *
 */
public class Game {
	
	private Field board[][];
	private int boardHeight, boardWidth;
	private int[] corners;
	private int currentPlayer;
	private ArrayList<Player> players;
	
	/**
	 * @param boardType type of board to set
	 * Creates the game with a given type of board
	 */
	public Game(BoardType boardType) {
		String[][] tmpBoard = boardType.getBoard();
		boolean[][] tmpEmpty = boardType.getBoardEmpty();
		
		this.currentPlayer = 0;
		this.boardHeight = tmpBoard.length;
		this.boardWidth = tmpBoard[0].length;
		this.board = new Field[boardHeight][boardWidth];
		this.corners = boardType.getCorners();
		this.players = new ArrayList<>();
		
		for(int i = 0; i < tmpBoard.length; i++) {
			for(int j = 0; j < tmpBoard[i].length; j++) {
				this.board[i][j] = new Field(tmpBoard[i][j], tmpEmpty[i][j]);
				System.out.print(board[i][j].getFieldType() + " ");
			}
			System.out.println();
		}
	}
	
	synchronized public void addPlayer() {
		Player player = new Player(currentPlayer, corners[currentPlayer]);
		player.setPawns(getPawns(corners[currentPlayer]));
		players.add(player);
		currentPlayer++;
	}
	
	public ArrayList<Point> checkValidMoves(int clientIndex, Point point) {
		Pawn tmpPawn = null;
		Player tmpPlayer = null;
		ArrayList<Pawn> tmpPawns = null;
		for(Player pl : players) {
			if(pl.getClientID() == clientIndex) {
				//tmpPawns = pl.getPawns();
				tmpPlayer = pl;
				break;
			}
		}
		tmpPawns = tmpPlayer.getPawns();
		
		for(Pawn pa : tmpPawns) {
			if(pa.getLocation().equals(point)) {
				tmpPawn = pa;
				break;
			}
		}
		
		ArrayList<Point> validMoves;
		if(tmpPawn == null) {
			validMoves = new ArrayList<>();
		}
		else if(tmpPawn.isAfterStep()) {
			validMoves = new ArrayList<>();
		}
		else if(tmpPlayer.getMoveType().equals(MoveType.FIRST)) {
			validMoves = checkValidMoves(tmpPawn);
		}
		else {
			validMoves = checkValidJumps(tmpPawn);
		}
		
		return validMoves;
	}
	
	/**
	 * @param pawn pawn to check
	 * @return ArrayList of Point with possible moves
	 */
	public ArrayList<Point> checkValidMoves(Pawn pawn) {
		// TODO Will be changed to private
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
	
	public ArrayList<Point> checkValidJumps(Pawn pawn) {
		ArrayList<Point> validJumps = new ArrayList<>();
		int x = pawn.getLocation().x;
		int y = pawn.getLocation().y;
		 
		if(isEmpty(x - 1, y - 1) == false && isValid(x - 2, y - 2, pawn) == true) {
				validJumps.add(new Point(x - 2, y - 2));
		}
		 
		if(isEmpty(x - 1, y) == false && isValid(x - 2, y, pawn) == true) {
				validJumps.add(new Point(x - 2, y));
		}
		 
		if(isEmpty(x, y - 1) == false && isValid(x, y - 2, pawn) == true) {
				validJumps.add(new Point(x, y - 2));
		}
		 
		if(isEmpty(x, y + 1) == false && isValid(x, y + 2, pawn) == true) {
				validJumps.add(new Point(x, y + 2));
		}
		 
		if(isEmpty(x + 1, y) == false && isValid(x + 2, y, pawn) == true) {
				validJumps.add(new Point(x + 2, y));
		}
		 
		if(isEmpty(x + 1, y + 1) == false && isValid(x + 2, y + 2, pawn) == true) {
				validJumps.add(new Point(x + 2, y + 2));
		}
		
		return validJumps;
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
		
		if(board[x][y].isEmpty() == true && pawn.isInTarget() == false && (new Point(x, y)).equals(pawn.getLastLocation()) == false) {
			return true;
		}
		else if(board[x][y].isEmpty() == true && pawn.isInTarget() == false && (new Point(x, y)).equals(pawn.getLastLocation()) == true && pawn.isInMove() == false) {
			return true;
		}
		else if(board[x][y].isEmpty() == true && board[x][y].getFieldType().equals(pawn.getTarget()) == true && (new Point(x, y)).equals(pawn.getLastLocation()) == false) {
			return true;
		}
		else if(board[x][y].isEmpty() == true && board[x][y].getFieldType().equals(pawn.getTarget()) == true && (new Point(x, y)).equals(pawn.getLastLocation()) == true && pawn.isInMove() == false) {
			return true;
		}
		else {
			return false;
		}
	}
	
	private ArrayList<Pawn> getPawns(int corner) {
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
	
	public void move(int clientIndex, Point from, Point to) {
		Pawn tmpPawn = null;
		Player tmpPlayer = null;
		ArrayList<Pawn> tmpPawns = null;
		for(Player pl : players) {
			if(pl.getClientID() == clientIndex) {
				tmpPawns = pl.getPawns();
				tmpPlayer = pl;
				break;
			}
		}
		
		for(Pawn pa : tmpPawns) {
			if(pa.getLocation().equals(from)) {
				tmpPawn = pa;
				break;
			}
		}
		move(tmpPawn, to);
		tmpPawn.setInMove();
		if(tmpPlayer.getMoveType().equals(MoveType.FIRST)) {
			tmpPlayer.setMoveType(MoveType.NEXT);
		}
	}
	
	public void move(Pawn pawn, Point destination) {
		// TODO Will be changed to private
		Point lastLocation = pawn.getLocation();
		board[lastLocation.x][lastLocation.y].setEmpty();
		board[destination.x][destination.y].setTaken();
		pawn.setLocation(destination);
		pawn.setLastLocation(lastLocation);
		
		if(isNeighbor(lastLocation, destination)) {
			pawn.setAfterStep();
		}
		
		if(board[destination.x][destination.y].getFieldType().equals(pawn.getTarget())) {
			pawn.setInTarget();
		}
	}
	
	enum GameState {PENDING, ENDDED};
	
	public GameState getState() {
		for(Player pl : players) {
			if(!pl.hasFinished()) {
				return GameState.PENDING;
			}
		}
		return GameState.ENDDED;
	}
	
	Player getPlayer(int i) {
		return players.get(i);
	}
	
	public void endTurn(int PlayerID) {
		players.get(PlayerID).setMoveType(MoveType.FIRST);
		players.get(PlayerID).endTurn();
	}
	
	private boolean isEmpty(int x, int y) {
		if(x < 0 || x >= boardHeight || y < 0 || y >= boardWidth) {
			return false;
		}
		else if(board[x][y].isEmpty()) {
			return true;
		}
		return false;
	}
	
	private boolean isNeighbor(Point from, Point to) {
		int fromX = from.x;
		int fromY = from.y;
		if(new Point(fromX - 1, fromY - 1).equals(to)) {
			return true;
		}
		if(new Point(fromX - 1, fromY).equals(to)) {
			return true;
		}
		if(new Point(fromX, fromY - 1).equals(to)) {
			return true;
		}
		if(new Point(fromX, fromY + 1).equals(to)) {
			return true;
		}
		if(new Point(fromX + 1, fromY).equals(to)) {
			return true;
		}
		if(new Point(fromX + 1, fromY + 1).equals(to)) {
			return true;
		}
		return false;
	}
}

