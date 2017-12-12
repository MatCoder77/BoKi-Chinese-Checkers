package server;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author filipbk
 * Class that holds game session
 *
 */
public class GameHandler implements Runnable {
	
	private ArrayList<ClientHandler> clients;
	private Game game;
	
	public GameHandler() {
		clients = new ArrayList<ClientHandler>();
		game = new Game();
	}
	
	public void addPlayer(ClientHandler player) {
		clients.add(player);
		System.out.println("Added player");
	}
	
	private void checkAndStart() {
		if(clients.size() == 2) {
			for(ClientHandler clientHandler : clients) {
				Thread thread = new Thread(clientHandler);
				thread.start();
			}
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
