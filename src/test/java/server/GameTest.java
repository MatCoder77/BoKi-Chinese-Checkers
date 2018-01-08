package server;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import board.BoardSixSix;
import board.Pawn;

public class GameTest {
	
	Game game;
	
	@Before
	public void setUp() {
		game = new Game(new BoardSixSix());
	}
	
	@Test
	public void testValidJumps() {
		Pawn pawn = new Pawn(new Point(5, 2), "3");
		ArrayList<Point> validJumps = game.checkValidJumps(pawn);
		assertTrue(validJumps.size() == 2);
		
	}

	@Test
	public void testMove() {
		game.addPlayer();
		assertFalse(game.isEmpty(3, 6));
		assertTrue(game.isEmpty(4, 6));
		Point from = new Point(3, 6);
		Point to = new Point(4, 6);
		game.move(0, from, to);
		assertTrue(game.isEmpty(3, 6));
		assertFalse(game.isEmpty(4, 6));
		assertFalse(game.isEmpty(20, 20));
	}
	
	@Test
	public void testEndTurn() {
		game.addPlayer();
		game.endTurn(0);
		assertEquals(game.getPlayer(0).getMoveType(), Player.MoveType.FIRST);
	}

}
