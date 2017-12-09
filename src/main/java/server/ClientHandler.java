package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {
	
	private Socket socket;
	private BufferedReader input;
	private PrintWriter output;
	
	public ClientHandler(Socket socket) {
		this.socket = socket;
		
		try {
			input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			output = new PrintWriter(socket.getOutputStream(), true);
			output.println("Welcome to the game!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		
		try {
			output.println("Starting the game");
			while (true) {
				String command = input.readLine();
			}
		} catch (IOException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
}