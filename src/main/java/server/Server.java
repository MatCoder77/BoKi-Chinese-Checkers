package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	private static Server instance;
	private Game game;
	private ServerSocket serverSocket;
	private Thread gameThread;
	private int port;
	

	private Server (int port) throws IOException {
		serverSocket = new ServerSocket(port);
		System.out.println("Server started");
		game = new Game();
		gameThread = new Thread(game);
		gameThread.start();
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
	
	private void listenForConnectionRequest() {
		try {
			serverSocket = new ServerSocket(port);
			
			while (true) {
				Socket clientSocket = serverSocket.accept();
				PrintWriter writer = new PrintWriter(clientSocket.getOutputStream());
				runClientHandler(clientSocket);				
			}	
			
		} catch (Exception e) {
			//TODO handle exception
		}
	}

	public void runClientHandler(Socket clientSocket) {
		Thread listener = new Thread(new ClientHandler(clientSocket)); //TODO pass ClientHandler arguments when class will be implemented
		listener.start();
	}
}
