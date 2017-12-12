package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import communication.Request;

public class Client {

	private Socket socket;
	private Thread serverListener;
	//private BufferedReader input;
	private ObjectInputStream input;
	//private PrintWriter output;
	private ObjectOutputStream output;
	private int port;
	private String address;
	private boolean connected;

	public Client(String address, int port) {
		this.address = address;
		this.port = port;
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
	 * Establishes ServerListener for client which will be listening to Responds
	 * from Server
	 */
	public void runServerListener() {
		serverListener = new Thread(new ServerListener()); // TODO add ServerListener arguments when ServerListener will
															// be implemented
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
				/*nputStreamReader streamReader = new InputStreamReader(socket.getInputStream());
				input = new BufferedReader(streamReader);
				output = new PrintWriter(socket.getOutputStream());*/

				// TODO send CONNECT_REQUEST

				setConnected(true);
			} catch (Exception ex) {
				// TODO handle exception
			}

			runServerListener();

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
			//TODO send DISCONNECT_REQUEST
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
