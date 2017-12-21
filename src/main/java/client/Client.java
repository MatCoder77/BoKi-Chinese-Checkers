package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import communication.ConnectRequest;
import communication.ConnectResponse;
import communication.DisconnectRequest;
import communication.Request;

public class Client {

	private Socket socket;
	private Thread serverListener;
	private volatile ObjectInputStream input;
	private ObjectOutputStream output;
	private int port;
	private String address;
	private boolean connected;
	private String name;

	public Client(String address, int port, String name) {
		this.address = address;
		this.port = port;
		this.name = name;
	}

	/**
	 * Returns the state of connection
	 * 
	 * @return true if client is connected, false otherwise
	 */
	boolean isConnected() {
		return connected;
	}

	/**
	 * Sets connection status
	 * 
	 * @param status
	 */
	private void setConnected(boolean connectionStatus) {
		connected = connectionStatus;
	}
	
	/**
	 * Object of this class is created when client makes a connection with server. It works on new thread, 
	 * and handles Responds received from server.
	 * 
	 */
	class ServerListener implements Runnable {
		
		@Override
		public void run() {
			Object receivedObject;
			try {
				while((receivedObject = input.readObject()) != null) {
					if(receivedObject instanceof ConnectResponse) {
						System.out.println("You were succesfully connected");
					}
				}
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
			}
			
		}

	}

	/**
	 * Establishes ServerListener for client which will be listening to Responds
	 * from Server
	 */
	public void runServerListener() {
		serverListener = new Thread(new ServerListener()); 
		serverListener.start();
	}

	/**
	 * Establishes connection between Server and this Client. Initializes Client's
	 * BufferdReader and PrinterWriter connecting it to Server's OutupStream and
	 * InputStream. When exception occurs TODO
	 * 
	 * @return true if connection was established, false if some kind of problem
	 *         occurred
	 */
	public boolean connect() {
		if (!isConnected()) {
			try {
				socket = new Socket(address, port);
				output = new ObjectOutputStream(socket.getOutputStream());
				input = new ObjectInputStream(socket.getInputStream());				
				runServerListener();
				sendRequest(new ConnectRequest(name));
				setConnected(true);
			} catch (Exception ex) {
				// TODO handle exception
			}


		} else {
			// TODO show message "You're already connected"
		}
		return true; // TODO error indicating when something will go wrong
	}

	/**
	 * Closes connection between Client and Server and frees resources.
	 * 
	 * @return true if no problems occurred, false otherwise
	 */
	boolean disconnect() {
		if (isConnected()) {
			sendRequest(new DisconnectRequest(name));
			try {
				socket.close();
			} catch (Exception ex) {
				//TODO error handling
			}
			setConnected(false);
		} else {
			//TODO show message "You're already disconnected"
		}
		return true; //TODO error indicating
	}
	
	public void sendRequest(Request request) {
		try {
			output.writeObject(request);
			output.flush();
			System.out.println("Sended request");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
