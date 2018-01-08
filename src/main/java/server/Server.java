package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import server.GameType.BoardSize;

public class Server {
	
	private static Server instance;
	private ServerSocket serverSocket;
	private Thread clientHandlerThread;
	private ServerGUI serverGUI; //will be removed
	private CopyOnWriteArrayList<ClientHandler> clientHandlers;
	private CopyOnWriteArrayList<GameHandler> gameHandlers;

	private Server (int port) throws IOException {
		serverSocket = createSocket(port);
		clientHandlers = new CopyOnWriteArrayList<>();
		gameHandlers = new CopyOnWriteArrayList<>();
		System.out.println("Server started");
	}
	
	ServerSocket createSocket(int port) throws IOException {
		return new ServerSocket(port);
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
	
	public void removeClientHandler(ClientHandler clientHandler) {
		clientHandlers.remove(clientHandler);
	}
	
	public void removeGameHandler(GameHandler gameHandler) {
		gameHandlers.remove(gameHandler);
	}
	
	void setServerGUI(ServerGUI serverGUI) {
		this.serverGUI = serverGUI;
	}
	
	ServerGUI getServerGUI() {
		return serverGUI;
	}
	
	GameHandler runGameHandler(GameType gameType, ClientHandler creator) {
		GameHandler gameHandler = new GameHandler(gameType, creator);
		Thread gameThread = new Thread(gameHandler);
		gameHandler.setGameThread(gameThread);
		gameHandlers.add(gameHandler);
		//gameThread.start();
		return gameHandler; //CHANGE IT
	}
		
	synchronized GameHandler runFastGame(ClientHandler client, GameType gameType) {
		if(gameHandlers.size() != 0) {
			for(GameHandler game : gameHandlers) {
				if(game.waitsForPlayers() && (gameType.getPlayersNumber() == game.getGameInfo().getType().getPlayersNumber())) {
					game.addClient(client);
					return game;
				}
			}
		}
		
		return runGameHandler(gameType, client); //CHANE IT
	}
	
	synchronized GameHandler runComputerGame(ClientHandler client, GameType gameType) {
		GameHandler gameHandler = runGameHandler(gameType, client);
		Bot bot;
		for(int i = 1; i < gameType.getPlayersNumber(); i++) {
			bot = new Bot(new EasyBot());
			clientHandlerThread = new Thread(bot);
			clientHandlerThread.start();
			joinToGame(gameHandler, bot);
		}	
		return gameHandler;
	}
	
	synchronized boolean joinToGame(GameHandler game, ClientHandler client) {
		if(game.waitsForPlayers()) {
			game.addClient(client);
			return true;
		} else
			return false;
	}
	
}
