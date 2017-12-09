package server;

import java.io.IOException;
import java.net.ServerSocket;

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
	
	private void listen() {
		while (true) {
			
		}
	}
}
