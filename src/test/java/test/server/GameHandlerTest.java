package test.server;

import static org.junit.Assert.*;

import java.net.Socket;

import org.junit.Test;

import server.ClientHandler;
import server.GameHandler;

public class GameHandlerTest {

	@Test
	public void testInitialization() {
		GameHandler gameHandler = new GameHandler();
		assertNotNull(gameHandler);
	}
	
	/*@Test
	public void testAddingClientHandler() {
		GameHandler gameHandler = new GameHandler();
		gameHandler.addPlayer(new ClientHandler(null));
	}*/

}
