package test.server;

import static org.junit.Assert.*;
import java.awt.Point;
import org.junit.Test;
import board.BoardSixSix;
import board.Pawn;
import server.Game;

public class GameTest {

	@Test
	public void testGameBoard() {
		Game game = new Game(new BoardSixSix());
		Pawn pawn = new Pawn(new Point(5, 5), "2");
		System.out.println(game.checkValidMoves(pawn).size());
		Pawn pawn1 = new Pawn(new Point(14, 11), "6");
		System.out.println(game.checkValidMoves(pawn1).size());
	}

}
