package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	private static Server instance;
	private ServerSocket serverSocket;
	private Thread gameThread;//will be used for gameHandler
	private Thread clientHandlerThread;
	//private int port;
	private GameHandler gameHandler;
	private ServerGUI serverGUI; //will be removed

	private Server (int port) throws IOException {
		serverSocket = new ServerSocket(port);
		gameHandler = new GameHandler();
		System.out.println("Server started");
	}
	
	public static synchronized Server getInstance(int port) {
		if(instance == null) {
			try {
				instance = new Server(port);
			} catch (IOException e) {
				System.out.println("Unable to start server");
				e.printStackTrace();
			}
		}
		return instance;
	}
	
	public void listenForNewConnections() {
		try {
			gameThread = new Thread(gameHandler);
			gameThread.start();
			while (true) {
				Socket clientSocket = serverSocket.accept();
				runClientHandler(clientSocket);				
			}	
			
		} catch (Exception e) {
			//TODO handle exception
		}
	}

	public void runClientHandler(Socket clientSocket) {
		ClientHandler clientHandler = new ClientHandler(clientSocket);
		clientHandler.setServerGUI(serverGUI);
		clientHandlerThread = new Thread(clientHandler);
		clientHandlerThread.start();
		gameHandler.addPlayer(clientHandler);
	}
	
	void setServerGUI(ServerGUI serverGUI) {
		this.serverGUI = serverGUI;
	}
}
