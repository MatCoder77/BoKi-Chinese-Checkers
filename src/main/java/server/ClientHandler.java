package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import communication.ConnectRequest;
import communication.DisconnectRequest;
import communication.MoveRequest;
import communication.Request;

/**
 * @author filipbk
 * Class responsible for holding connection
 * and getting data from client app.
 *
 */
public class ClientHandler implements Runnable {
	
	private Socket socket;
	//private BufferedReader input;
	//private PrintWriter output;
	private ObjectInputStream input;
	private ObjectOutputStream output;
	
	public ClientHandler(Socket socket) {
		this.socket = socket;
		
		try {
			//input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			//output = new PrintWriter(socket.getOutputStream(), true);
			//output.println("Welcome to the game!");
			output = new ObjectOutputStream(socket.getOutputStream());
			input = new ObjectInputStream(socket.getInputStream());			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		
		try {
			Object receivedObject;
			Class objectType;
			Request request;
			while (true) {
				System.out.println("before");
				receivedObject = input.readObject();
				System.out.println("Request received");
				objectType = receivedObject.getClass();
				//request = objectType.cast(receivedObject);
				handleRequest(objectType.cast(receivedObject));
			}
		} catch (IOException | ClassNotFoundException e) {
			// TODO: handle exception
			System.out.println("Wyjatek");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	private void handleRequest(Object cast) {
		System.out.println("NIE TAK");
		
	}

	void handleRequest(ConnectRequest request) {
		System.out.println("Handled connectRequest for client " + request.getClientName());
	}
	
	void handleRequest(DisconnectRequest request) {
		System.out.println("Handled disconnectRequest for client " + request.getClientName());
	}
	
	void handleRequest(MoveRequest request) {
		System.out.println("Handled moveRequest, moved from " + request.getCurrentPosition() + " to " + request.getDestination());
	}
	
}