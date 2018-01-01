import static org.junit.Assert.*;

import java.awt.Point;
import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import board.Pawn;
import server.Player;

public class PlayerTest {
	
	static Player player;
	ArrayList<Pawn> pawns = new ArrayList<>();

	@Before
	public void setUp() throws Exception {
		player = new Player(37, 4);
		pawns.add(new Pawn(new Point(0, 4), "1"));
		pawns.add(new Pawn(new Point(1, 4), "1"));
		pawns.add(new Pawn(new Point(2, 4), "1"));
		pawns.add(new Pawn(new Point(3, 4), "1"));
		pawns.add(new Pawn(new Point(1, 5), "1"));
		pawns.add(new Pawn(new Point(2, 5), "1"));
		pawns.add(new Pawn(new Point(3, 5), "1"));
		pawns.add(new Pawn(new Point(2, 6), "1"));
		pawns.add(new Pawn(new Point(3, 6), "1"));
		pawns.add(new Pawn(new Point(4, 7), "1"));
		player.setPawns(pawns);
	}

	@Test
	public void getPlayerID() {
		assertEquals(37, player.getClientID());
	}
	
	@Test
	public void setPlayerID() {
		player.setClientID(28);
		assertEquals(28, player.getClientID());
	}
	
	@Test
	public void getPlayerPawns() {
		assertNotNull(player.getPawns());
		assertEquals(pawns, player.getPawns());
	}
	
	@Test
	public void movePawnToTarget() {
		assertFalse(player.hasFinished());
		for(Pawn p : player.getPawns()) {
			p.setInTarget();
		}
		assertTrue(player.hasFinished());
	}
	
	@Test
	public void settingCorner() {
		player.setCorner(3);
		assertEquals(3, player.getCorner());
	}

}
