package client;

import java.awt.Point;
import java.util.ArrayList;

import board.BoardSixFour;
import board.BoardSixSix;
import board.BoardSixThree;
import board.BoardSixTwo;
import board.BoardType;
import javafx.event.EventHandler;
import javafx.scene.effect.Glow;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Board {
	
	private BoardField[][] boardFields;
	private String playerCorner;
	private double radius;
	private ArrayList<BoardField> playerFields;
	private ArrayList<BoardField> possibleMoves;
	private MouseEventHandler eventHandler;
	private EventHandler<MouseEvent> mouseEntered;
	private EventHandler<MouseEvent> mouseExited;
	private boolean isInMove;
	
	public Board(int players, int playerID, MouseEventHandler eventHandler) {
		this.playerFields = new ArrayList<>();
		this.possibleMoves = new ArrayList<>();
		this.radius = 16;
		this.eventHandler = eventHandler;
		this.isInMove = false;
		this.mouseEntered = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				BoardField boardField = (BoardField)event.getSource();
				if(boardField.isActive()) {
					boardField.setEffect(new Glow(0.7));
				}
			}
		};
		this.mouseExited = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				BoardField boardField = (BoardField)event.getSource();
				boardField.setEffect(null);
			}
		};
		initializeBoard(players, playerID);
	}
	
	private void initializeBoard(int players, int playerID) {
		BoardType boardType = null;
		
		if(players == 2) {
			boardType = new BoardSixTwo();
		}
		if(players == 3) {
			boardType = new BoardSixThree();
		}
		if(players == 4) {
			boardType = new BoardSixFour();
		}
		if(players == 6) {
			boardType = new BoardSixSix();
		}
		
		playerCorner = Integer.toString(boardType.getCorners()[playerID]);
		String board[][] = boardType.getBoard();
		boolean empty[][] = boardType.getBoardEmpty();
		int height = board.length;
		int width = board[0].length;
		boardFields = new BoardField[height][width];
		
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				if(board[i][j].equals("N")) {
					boardFields[i][j] = new BoardField(radius, new Point(i, j), board[i][j]);
				}
				else if(empty[i][j] == true) {
					boardFields[i][j] = new BoardField(radius, new Point(i, j), "C");
					boardFields[i][j].setFill(Color.rgb(201, 128, 64, 0.3));
					boardFields[i][j].addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
					boardFields[i][j].addEventFilter(MouseEvent.MOUSE_ENTERED, mouseEntered);
					boardFields[i][j].addEventFilter(MouseEvent.MOUSE_EXITED, mouseExited);
				}
				else {
					boardFields[i][j] = new BoardField(radius, new Point(i, j), board[i][j]);
					boardFields[i][j].addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
					boardFields[i][j].addEventFilter(MouseEvent.MOUSE_ENTERED, mouseEntered);
					boardFields[i][j].addEventFilter(MouseEvent.MOUSE_EXITED, mouseExited);
					
					if(board[i][j].equals("1")) {
						boardFields[i][j].setFill(Color.rgb(216, 36, 36));
					}
					else if(board[i][j].equals("2")) {
						boardFields[i][j].setFill(Color.rgb(48, 41, 41));
					}
					else if(board[i][j].equals("3")) {
						boardFields[i][j].setFill(Color.rgb(239, 219, 2));
					}
					else if(board[i][j].equals("4")) {
						boardFields[i][j].setFill(Color.rgb(4, 166, 206));
					}
					else if(board[i][j].equals("5")) {
						boardFields[i][j].setFill(Color.rgb(247, 244, 244));
					}
					else if(board[i][j].equals("6")) {
						boardFields[i][j].setFill(Color.rgb(11, 142, 0));
					}
					if(board[i][j].equals(playerCorner)) {
						playerFields.add(boardFields[i][j]);
					}
				}
			}
		}
	}
	
	public BoardField[][] getBoardFields() {
		return boardFields;
	}
	
	public void activatePlayerFields() {
		for(BoardField pf : playerFields) {
			pf.activate();
		}
	}
	
	public void deactivatePlayerFields() {
		for(BoardField pf : playerFields) {
			pf.deactivate();
		}
	}
	
	public void setPossible(ArrayList<Point> points) {
		for(Point p : points) {
			BoardField boardField = boardFields[p.x][p.y];
			possibleMoves.add(boardField);
			boardField.activate();
			boardField.setPossible();
		}
	}
	
	public void setImpossible() {
		for(BoardField pm : possibleMoves) {
			pm.deactivate();
		}
		possibleMoves.clear();
	}
	
	public boolean select(BoardField boardField) {
		if(isInMove) {
			return false;
		}
		if(boardField.isSelected()) {
			boardField.deselect();
			setImpossible();
			return false;
		}
		else {
			for(BoardField pf : playerFields) {
				pf.deselect();
			}
			boardField.select();
			setImpossible();
			return true;
		}
	}
	
	public BoardField getSelected() {
		BoardField boardField = null;
		for(BoardField pf : playerFields) {
			if(pf.isSelected()) {
				boardField = pf;
				break;
			}
		}
		return boardField;
	}
	
	public void performMove(BoardField from, BoardField to) {
		if(isInMove == false) {
			isInMove = true;
		}
		setImpossible();
		for(BoardField pf : playerFields) {
			pf.deactivate();
		}
		
		Paint fill = from.getFill();
		from.setFill(to.getFill());
		to.setFill(fill);
		to.select();
		playerFields.remove(from);
		playerFields.add(to);
	}
	
	public void endTurn() {
		for(BoardField pf : playerFields) {
			pf.deactivate();
		}
		setImpossible();
		isInMove = false;
	}
	
	public void updateBoard(Point from, Point to) {
		BoardField first = boardFields[from.x][from.y];
		BoardField second = boardFields[to.x][to.y];
		Paint fill = first.getFill();
		first.setFill(second.getFill());
		second.setFill(fill);
	}
	
	public ArrayList<BoardField> getPlayerFields() {
		return playerFields;
	}
	
	public ArrayList<BoardField> getPossibleMoves() {
		return possibleMoves;
	}
	
}
