package server;

import java.io.Serializable;

import board.BoardType;

public class GameType implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -697104663877608756L;
	int playersNumber;
	BoardSize boardType;
	
	public enum BoardSize {SMALL, STANDARD, BIG};
	
	public GameType(int playersNumber, BoardSize boardType) {
		this.playersNumber = playersNumber;
		this.boardType = boardType;
	}
	public GameType setPlayersNumber(int n) {
		playersNumber = n;
		return this;
	}
	
	public GameType setBoardSize(BoardSize size) {
		this.boardType = size;
		return this;
	}
	
	public int getPlayersNumber() {
		return playersNumber;
	}
	
	public BoardSize getBoardSize() {
		return boardType;
	}
}
