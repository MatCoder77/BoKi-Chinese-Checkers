package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import org.hamcrest.core.IsInstanceOf;

import communication.ConnectRequest;
import communication.ConnectResponse;
import communication.DisconnectRequest;
import communication.MoveRequest;
import communication.Request;
import communication.Response;

/**
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
			System.out.println("W konstruktorze clhandl");
			output = new ObjectOutputStream(socket.getOutputStream());
			input = new ObjectInputStream(socket.getInputStream());			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		System.out.println("W run w clhandl");
		try {
			Object receivedObject;
			Class objectType;
			Request request;
			String name;
			while (true) {
				System.out.println("before");
				System.out.flush();
				receivedObject = input.readObject();
				System.out.println("Request received");
				System.out.flush();
				objectType = receivedObject.getClass();
				name = receivedObject.getClass().getName();
				request = (Request) receivedObject;
				//request = objectType.cast(receivedObject);
				//handleRequest(request.getRequest());
				//handleRequest((receivedObject.getClass()).cast(receivedObject));
				
				if(receivedObject instanceof ConnectRequest){
					ConnectRequest r = (ConnectRequest) receivedObject;
					System.out.println("Handled connectRequest for client " + r.getClientName());
					sendResponse(new ConnectResponse());
				}
				
				else if(receivedObject instanceof DisconnectRequest) {
					DisconnectRequest r = (DisconnectRequest) receivedObject;
					System.out.println("Handled disconnectRequest for client " + r.getClientName());
				}
				
				else if(receivedObject instanceof MoveRequest) {
					MoveRequest r = (MoveRequest) receivedObject;
					System.out.println("Handled moveRequest, moved from " + r.getCurrentPosition() + " to " + r.getDestination());				
				}
				
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
		System.out.flush();
		
	}

	void handleRequest(ConnectRequest request) {
		System.out.println("Handled connectRequest for client " + request.getClientName());
		System.out.flush();
	}
	
	void handleRequest(DisconnectRequest request) {
		System.out.println("Handled disconnectRequest for client " + request.getClientName());
	}
	
	void handleRequest(MoveRequest request) {
		System.out.println("Handled moveRequest, moved from " + request.getCurrentPosition() + " to " + request.getDestination());
	}
	
	void handleRequest(Request request) {
		System.out.println("NIE TAK");
	}
	
	void sendResponse(Response response) {
		try {
			output.writeObject(response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}