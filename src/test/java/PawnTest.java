import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import board.Pawn;

public class PawnTest {

	Pawn pawn;

	@Before
	public void setUp() throws Exception {
		pawn = new Pawn(new Point(4,5), "1");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void pawnCreation() {
		assertEquals(new Point(4, 5), pawn.getLocation());
		assertEquals("1", pawn.getTarget());
	}
	
	@Test
	public void pawnMove() {
		pawn.setLocation(new Point(4, 6));
		assertEquals(new Point(4, 6), pawn.getLocation());
		pawn.setLastLocation(new Point(4, 5));
		assertEquals(new Point(4, 5), pawn.getLastLocation());
	}
	
	@Test
	public void pawnMovesInTarget() {
		assertFalse(pawn.isInTarget());
		pawn.setInTarget();
		assertTrue(pawn.isInTarget());
	}

}
