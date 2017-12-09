package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author Mateusz
 *
 */
/**
 * @author Mateusz
 *
 */
public class Client {

	private Socket socket;
	private Thread serverListener;
	private BufferedReader input;
	private PrintWriter output;
	private int port;
	private String address;
	private boolean connected;

	public Client(String host, int port) {
		try {
			socket = new Socket(host, port);
			input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			output = new PrintWriter(socket.getOutputStream(), true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	boolean connect() {
		if (!isConnected()) {
			try {
				socket = new Socket(address, port);
				InputStreamReader streamReader = new InputStreamReader(socket.getInputStream());
				input = new BufferedReader(streamReader);
				output = new PrintWriter(socket.getOutputStream());

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
	
	//TODO sendRequest function
}
