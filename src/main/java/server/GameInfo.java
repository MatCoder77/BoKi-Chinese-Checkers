package server;

public class GameInfo {
	int playersNumber;
	BoardSize size;
	enum BoardSize {
		SMALL, STANDARD, BIG;
	}
	
	GameInfo setPlayersNumber(int n) {
		playersNumber = n;
		return this;
	}
	
	GameInfo setBoardSize(BoardSize size) {
		this.size = size;
		return this;
	}
	
	int getPlayersNumber() {
		return playersNumber;
	}
	
	BoardSize getBoardSize() {
		return size;
	}
}
