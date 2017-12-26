package server;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import communication.MoveRequest;
import communication.Request;

public class GameHandler implements Runnable {
	
	private ArrayList<ClientHandler> clients;
	private BlockingQueue<Request> receivedRequests;
	private Thread gameThread;
	private GameInfo gameInfo;
	private Game game;
	
	public GameHandler(GameInfo gameInfo, ClientHandler creator) {
		this.gameInfo = gameInfo;
		receivedRequests = new LinkedBlockingQueue<>();
		clients = new ArrayList<>();
		clients.add(creator);
		Server.getInstance().getServerGUI().addToLog("Utowrzono ClientHandler'a");
	}
	
	void setGameThread(Thread thread) {
		gameThread = thread;
	}
	
	int getConnectedClientsNumber() {
		return clients.size();
	}
	
	int getExpectedClientsNumber() {
		return gameInfo.getPlayersNumber();
	}
	
	boolean waitsForPlayers() {
		if(getConnectedClientsNumber() != getExpectedClientsNumber())
			return true;
		return false;
	}
	
	public void addClient(ClientHandler client) {
		clients.add(client);
		System.out.println("Added player");
		if(!waitsForPlayers()) {
			gameThread.start();
		}
	}
	
	BlockingQueue<Request> getRequestQueue() {
		return receivedRequests;
	}

	@Override
	public void run() {
		MoveRequest moveRequest;
		try {
			while((moveRequest = (MoveRequest) receivedRequests.take()) != null) {
				Server.getInstance().getServerGUI().addToLog("W CH Received MoveRequest: form " + moveRequest.getOldPos() + moveRequest.getNewPos());
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
