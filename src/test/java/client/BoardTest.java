package client;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import javafx.scene.paint.Paint;

public class BoardTest {
	
	Board board;
	
	@Before
	public void setUp() {
		board = new Board(6, 1, new MouseEventHandler(new Client("localhost", 8988, "Test")));
	}
	
	@Test
	public void testBoardInit() {
		assertTrue(board.getPlayerFields().size() == 10);
		assertTrue(board.getPossibleMoves().size() == 0);
	}
	
	@Test
	public void testPlayerFields() {
		board.activatePlayerFields();
		for(BoardField bf : board.getPlayerFields()) {
			assertTrue(bf.isActive());
		}
		
		board.deactivatePlayerFields();
		for(BoardField bf : board.getPlayerFields()) {
			assertFalse(bf.isActive());
		}
	}
	
	@Test
	public void testPossibleMoves() {
		ArrayList<Point> points = new ArrayList<>();
		points.add(new Point(4, 6));
		points.add(new Point(4, 7));
		board.setPossible(points);
		assertTrue(board.getPossibleMoves().size() == 2);
		for(BoardField bf : board.getPossibleMoves()) {
			assertTrue(bf.isActive());
			assertTrue(bf.isPossible());
		}
		board.setImpossible();
		assertTrue(board.getPossibleMoves().size() == 0);
	}

	@Test
	public void testSelect() {
		BoardField toSelect = board.getPlayerFields().get(9);
		board.select(toSelect);
		assertEquals(toSelect, board.getSelected());
	}
	
	@Test
	public void testMove() {
		BoardField toMove = board.getPlayerFields().get(9);
		BoardField empty = board.getBoardFields()[4][7];
		Paint fill1 = toMove.getFill();
		Paint fill2 = empty.getFill();
		board.performMove(toMove, empty);
		assertEquals(fill1, empty.getFill());
		assertEquals(fill2, toMove.getFill());
	}
	
	@Test
	public void testUpdate() {
		Point from = new Point(3, 4);
		Point to = new Point(4, 4);
		BoardField full = board.getBoardFields()[3][4];
		BoardField empty = board.getBoardFields()[4][4];
		Paint fill1 = full.getFill();
		Paint fill2 = empty.getFill();
		board.updateBoard(from, to);
		assertEquals(fill1, empty.getFill());
		assertEquals(fill2, full.getFill());
	}

}
