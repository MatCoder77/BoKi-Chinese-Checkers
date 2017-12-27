package test.server;

import static org.junit.Assert.*;
import java.awt.Point;
import java.util.ArrayList;

import org.junit.Test;
import board.BoardSixSix;
import board.Pawn;
import server.Game;

public class GameTest {

	@Test
	public void testGameBoard() {
		Game game = new Game(new BoardSixSix());
		Pawn pawn = new Pawn(new Point(5, 5), "2");
		pawn.setLastLocation(new Point(4, 4));
		System.out.println(game.checkValidMoves(pawn).size());
		Pawn pawn1 = new Pawn(new Point(14, 11), "6");
		//pawn1.setInTarget();
		System.out.println(game.checkValidMoves(pawn1).size());
		Pawn pawn2 = new Pawn(new Point(12, 16), "6");
		System.out.println(game.checkValidMoves(pawn2).size());
		ArrayList<Pawn> pawns = game.getPawns(1);
		assertTrue(pawns.size() == 10);
	}
	
	@Test
	public void testMove() {
		Pawn pawn = new Pawn(new Point(13, 12), "4");
		Game game = new Game(new BoardSixSix());
		game.move(pawn, new Point(12, 12));
		assertFalse(pawn.isInTarget());
		game.move(pawn, new Point(13, 12));
		assertTrue(pawn.isInTarget());
	}

}
