package server;

import java.util.ArrayList;

/**
 * @author filipbk
 * Class that holds game session
 *
 */
public class GameHandler {
	
	private ArrayList<ClientHandler> players;
	private Game game;
	
	public GameHandler() {
		players = new ArrayList<ClientHandler>();
		game = new Game();
	}
	
	public void addPlayer(ClientHandler player) {
		players.add(player);
	}
}
