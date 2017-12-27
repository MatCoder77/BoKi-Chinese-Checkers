package server;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

import communication.MoveRequest;
import communication.Request;
import communication.Response;
import communication.StartFastGameResponse;
import communication.StartFastGameResponse.GameState;

public class GameHandler implements Runnable {
	
	private ArrayList<ClientHandler> clients;
	private BlockingQueue<Request> receivedRequests;
	private Thread gameThread;
	private volatile GameInfo gameInfo;
	private Game game;
	private static AtomicInteger gameCounter = new AtomicInteger(0);
	
	public GameHandler(GameType gameType, ClientHandler creator) {
		clients = new ArrayList<>();
		receivedRequests = new LinkedBlockingQueue<>();
		gameInfo = new GameInfo(gameCounter.getAndIncrement(), "Game", gameType, GameState.WAITING_FOR_PLAYERS, new ArrayList<>());
		clients.add(creator);
		gameInfo.addClientInfo(creator.getClientInfo());
		Server.getInstance().getServerGUI().addToLog("Utowrzono GameHandler'a");
	}
	
	void setGameThread(Thread thread) {
		gameThread = thread;
	}
	
	int getConnectedClientsNumber() {
		return clients.size();
	}
	
	int getExpectedClientsNumber() {
		return gameInfo.getType().getPlayersNumber();
	}
	
	boolean waitsForPlayers() {
		if(getConnectedClientsNumber() != getExpectedClientsNumber())
			return true;
		return false;
	}
	
	public void addClient(ClientHandler client) {
		clients.add(client);
		gameInfo.addClientInfo(client.getClientInfo());
		
		if(!waitsForPlayers()) {
			gameInfo.setState(GameState.STERTED);
			gameThread.start();
		}
		notifyClients(new StartFastGameResponse(gameInfo.getID(), gameInfo.getName(), gameInfo.getType(), gameInfo.getState(), gameInfo.getConnectedClientsInfo()));
	}
	
	void notifyClients(Response response) {
		for(ClientHandler c : clients) {
			c.sendResponse(response);
		}
	}
	
	BlockingQueue<Request> getRequestQueue() {
		return receivedRequests;
	}
	
	GameInfo getGameInfo() {
		return gameInfo;
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
