package server;

import static org.junit.Assert.*;

import java.net.Socket;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import communication.StartFastGameResponse.GameState;
import server.ClientHandler;
import server.GameHandler;
import server.GameType.BoardSize;

public class GameHandlerTest {

	static GameHandler gameHandler6;
	static GameHandler gameHandler4;
	static GameHandler gameHandler3;
	static GameHandler gameHandler2;
	static FakeClientHandler firstClient6;
	static FakeClientHandler firstClient4;
	static FakeClientHandler firstClient3;
	static FakeClientHandler firstClient2;
	@Before
	public void setUp() {
		firstClient6 = new FakeClientHandler();
		gameHandler6 = new GameHandler(new GameType(6, BoardSize.STANDARD), firstClient6);
		gameHandler6.setGameThread(new Thread(gameHandler6));
		firstClient4 = new FakeClientHandler();
		gameHandler4 = new GameHandler(new GameType(4, BoardSize.STANDARD), firstClient4);
		gameHandler4.setGameThread(new Thread(gameHandler4));
		firstClient3 = new FakeClientHandler();
		gameHandler3 = new GameHandler(new GameType(3, BoardSize.STANDARD), firstClient3);
		gameHandler3.setGameThread(new Thread(gameHandler3));
		firstClient2 = new FakeClientHandler();
		gameHandler2 = new GameHandler(new GameType(2, BoardSize.STANDARD), firstClient2);
		gameHandler2.setGameThread(new Thread(gameHandler2));
	}
	
	private void addClientsToGame(GameHandler gameHandler, int number) {
		for(int i = 0; i < number; i++) {
			gameHandler.addClient(new FakeClientHandler());
		}
	}
	
	@Test
	public void gameStartsWhenAllPlayersJoined() {
		addClientsToGame(gameHandler6, 5);
		addClientsToGame(gameHandler4, 3);
		addClientsToGame(gameHandler3, 2);
		addClientsToGame(gameHandler2, 1);
		
		assertEquals(GameState.STERTED, gameHandler6.getGameInfo().getState());
		assertEquals(GameState.STERTED, gameHandler4.getGameInfo().getState());
		assertEquals(GameState.STERTED, gameHandler3.getGameInfo().getState());
		assertEquals(GameState.STERTED, gameHandler2.getGameInfo().getState());
	}
	
	@Test
	public void gameEndsWhenSomeoneLeavesBeforeEndingGame() {
		addClientsToGame(gameHandler6, 5);
		addClientsToGame(gameHandler4, 3);
		addClientsToGame(gameHandler3, 2);
		addClientsToGame(gameHandler2, 1);
		
		assertEquals(GameState.STERTED, gameHandler6.getGameInfo().getState());
		assertEquals(GameState.STERTED, gameHandler4.getGameInfo().getState());
		assertEquals(GameState.STERTED, gameHandler3.getGameInfo().getState());
		assertEquals(GameState.STERTED, gameHandler2.getGameInfo().getState());
		
		gameHandler6.removeClient(firstClient6);
		gameHandler4.removeClient(firstClient4);
		gameHandler3.removeClient(firstClient3);
		gameHandler2.removeClient(firstClient2);
		

	}
	/*@Test
	public void testAddingClientHandler() {
		GameHandler gameHandler = new GameHandler();
		gameHandler.addPlayer(new ClientHandler(null));
	}*/

}
