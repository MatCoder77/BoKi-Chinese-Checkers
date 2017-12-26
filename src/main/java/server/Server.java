package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import server.GameInfo.BoardSize;

public class Server {
	
	private static Server instance;
	private ServerSocket serverSocket;
	private Thread clientHandlerThread;
	private ServerGUI serverGUI; //will be removed
	private ArrayList<ClientHandler> clientHandlers;
	private ArrayList<GameHandler> gameHandlers;

	private Server (int port) throws IOException {
		serverSocket = new ServerSocket(port);
		clientHandlers = new ArrayList<>();
		gameHandlers = new ArrayList<>();
		System.out.println("Server started");
	}
	
	public static synchronized Server getInstance() {
		if(instance == null) {
			try {
				instance = new Server(8988);
			} catch (IOException e) {
				System.out.println("Unable to start server");
				e.printStackTrace();
			}
		}
		return instance;
	}
	
	public void listenForNewConnections() {
		try {
			while (true) {
				Socket clientSocket = serverSocket.accept();
				runClientHandler(clientSocket);				
			}	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void runClientHandler(Socket clientSocket) {
		ClientHandler clientHandler = new ClientHandler(clientSocket);
		clientHandlers.add(clientHandler);
		clientHandlerThread = new Thread(clientHandler);
		clientHandlerThread.start();
	}
	
	void setServerGUI(ServerGUI serverGUI) {
		this.serverGUI = serverGUI;
	}
	
	ServerGUI getServerGUI() {
		return serverGUI;
	}
	
	GameHandler runGameHandler(GameInfo gameInfo, ClientHandler creator) {
		GameHandler gameHandler = new GameHandler(gameInfo, creator);
		Thread gameThread = new Thread(gameHandler);
		gameHandler.setGameThread(gameThread);
		gameHandlers.add(gameHandler);
		//gameThread.start();
		return gameHandler; //CHANGE IT
	}
		
	GameHandler runFastGame(ClientHandler client) {
		if(gameHandlers.size() != 0) {
			for(GameHandler game : gameHandlers) {
				if(game.waitsForPlayers()) {
					game.addClient(client);
					return game;
				}
			}
		}
		
		return runGameHandler(new GameInfo().setBoardSize(BoardSize.STANDARD).setPlayersNumber(2), client); //CHANE IT
	}
	
	boolean joinToGame(GameHandler game, ClientHandler client) {
		if(game.waitsForPlayers()) {
			game.addClient(client);
			return true;
		} else
			return false;
	}
}
