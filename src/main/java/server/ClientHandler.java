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
	private ObjectInputStream input;
	private ObjectOutputStream output;
	private ServerGUI serverGUI;
	
	public ClientHandler(Socket socket) {
		this.socket = socket;
		
		try {
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
			String name;
			while ((receivedObject = input.readObject()) != null) {
				
				objectType = receivedObject.getClass();
				name = receivedObject.getClass().getName();
				request = (Request) receivedObject;
				//request = objectType.cast(receivedObject);
				//handleRequest(request.getRequest());
				//handleRequest((receivedObject.getClass()).cast(receivedObject));
				
				if(receivedObject instanceof ConnectRequest){
					ConnectRequest r = (ConnectRequest) receivedObject;
					serverGUI.addToLog("Połączono użytkownika " + r.getClientName());
					sendResponse(new ConnectResponse());
				}
				
				else if(receivedObject instanceof DisconnectRequest) {
					DisconnectRequest r = (DisconnectRequest) receivedObject;
					serverGUI.addToLog("Rozłączono użytkownika " + r.getClientName());
				}
				
				else if(receivedObject instanceof MoveRequest) {
					MoveRequest r = (MoveRequest) receivedObject;
					serverGUI.addToLog("Przesunięto z " + r.getCurrentPosition() + " do " + r.getDestination());				
				}
				
			}
		} catch (IOException | ClassNotFoundException e) {
			// TODO: handle exception
		}
	}
	/*
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
	*/
	void sendResponse(Response response) {
		try {
			output.writeObject(response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void setServerGUI(ServerGUI serverGUI) {
		this.serverGUI = serverGUI;
	}
}