package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	private static Server instance;
	private ServerSocket serverSocket;
	private Thread gameThread;//will be used for gameHandler
	//private int port;
	private GameHandler gameHandler;
	

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
	
	private void listenForNewConnections() {
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
		//Thread listener = new Thread(new ClientHandler(clientSocket)); //TODO pass ClientHandler arguments when class will be implemented
		//listener.start();
		ClientHandler clientHandler = new ClientHandler(clientSocket);
		gameHandler.addPlayer(clientHandler);
	}
}
