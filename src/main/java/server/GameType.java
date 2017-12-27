package server;

import java.io.Serializable;

public class GameType implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -697104663877608756L;
	int playersNumber;
	BoardSize size;
	public enum BoardSize {
		SMALL, STANDARD, BIG;
	}
	
	GameType setPlayersNumber(int n) {
		playersNumber = n;
		return this;
	}
	
	GameType setBoardSize(BoardSize size) {
		this.size = size;
		return this;
	}
	
	public int getPlayersNumber() {
		return playersNumber;
	}
	
	public BoardSize getBoardSize() {
		return size;
	}
}
