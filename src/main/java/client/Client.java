package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import communication.ConnectRequest;
import communication.DisconnectRequest;
import communication.Request;
import communication.Response;

public class Client {

	private Socket socket;
	private Thread serverListener;
	private volatile ObjectInputStream input;
	private ObjectOutputStream output;
	private int port;
	private String address;
	private boolean connected;
	private String name;
	private int ID;
	private MenuSecondController menuSecond;
	private GameWindowController gameWindow;
	private Board board;

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
	public boolean isConnected() {
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
			ResponseHandler handler = new ResponseHandler(Client.this);
			Object receivedObject;
			Response response;
			try {
				while((receivedObject = input.readObject()) != null) {
					response = (Response) receivedObject;
					response.accept(handler);
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
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public boolean connect() throws UnknownHostException, IOException {
		if (!isConnected()) {
			socket = createSocket();
			output = createOutputStream();
			input = createInputStream();
			runServerListener();
			sendRequest(new ConnectRequest(name));
			setConnected(true);
		} else {
			
		}
		return true; 
	}
	
	ObjectInputStream createInputStream() throws IOException {
		return new ObjectInputStream(socket.getInputStream());	
	}
	
	ObjectOutputStream createOutputStream() throws IOException {
		return  new ObjectOutputStream(socket.getOutputStream());
	}
	
	Socket createSocket() throws UnknownHostException, IOException {
		return new Socket(address, port);
	}

	/**
	 * Closes connection between Client and Server and frees resources.
	 * 
	 * @return true if no problems occurred, false otherwise
	 */
	public boolean disconnect() {
		if (isConnected()) {
			sendRequest(new DisconnectRequest(name));
			try {
				socket.close();
			} catch (Exception ex) {
		
			}
			setConnected(false);
		} else {
			
		}
		return true; 
	}
	
	public void sendRequest(Request request) {
		try {
			output.writeObject(request);
			output.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}
	
	void setClientID(int clientID) {
		this.ID = clientID;
	}
	
	int getID() {
		return ID;
	}
	
	public String getName() {
		return name;
	}
	
	public void setMenuSecond(MenuSecondController menuSecond) {
		this.menuSecond = menuSecond;
	}
	
	public MenuSecondController getMenuSecond() {
		return menuSecond;
	}
	
	public void setGameWindow(GameWindowController gameWindow) {
		this.gameWindow = gameWindow;
	}
	
	public GameWindowController getGameWindow() {
		return gameWindow;
	}
	
	public void setBoard(Board board) {
		this.board = board;
	}
	
	public Board getBoard() {
		return board;
	}
}
