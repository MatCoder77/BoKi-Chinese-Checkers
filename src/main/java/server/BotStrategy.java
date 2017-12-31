package server;

import java.awt.Point;

import board.BoardSixFour;
import board.BoardSixSix;
import board.BoardSixThree;
import board.BoardSixTwo;
import board.BoardType;
import communication.MoveRequest;

public abstract class BotStrategy {
	Game game;
	Player me;
	int playerID;
	
	void prepareGameSimulation(GameType gameType, int myPlayerID) {
		playerID = myPlayerID;
		
		int playersNum = gameType.getPlayersNumber();
		BoardType board = null;
				
		if (playersNum == 2) {
			board = new BoardSixTwo();
		}
		if (playersNum == 3) {
			board = new BoardSixThree();
		}
		if (playersNum == 4) {
			board = new BoardSixFour();
		}
		if (playersNum == 6) {
			board = new BoardSixSix();
		}
		
		game = new Game(board);
		
		for(int i = 0; i < gameType.getPlayersNumber(); i++) {
			game.addPlayer();
		}
		
		me = game.getPlayer(myPlayerID);
	}
	
	void playerMoved(int playerID, Point from, Point to) {
		game.move(playerID, from, to);
	}
	
	public abstract MoveRequest getBestMove();
}
