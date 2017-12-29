package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

import communication.GameplayRequest;
import communication.Request;
import communication.Response;

/**
 * Class responsible for holding connection and getting data from client app.
 *
 */
public class ClientHandler implements Runnable {

	private static AtomicInteger clientCounter = new AtomicInteger(0);
	private Socket socket;
	private ObjectInputStream input;
	private ObjectOutputStream output;
	protected GameHandler game;
	protected BlockingQueue<GameplayRequest> requestQueue;
	protected ClientInfo clientInfo;

	public ClientHandler(Socket socket) {
		this.socket = socket;
		clientInfo = new ClientInfo(clientCounter.getAndIncrement());
		game = null;
		requestQueue = null;
		if (socket != null) {
			try {
				output = new ObjectOutputStream(socket.getOutputStream());
				input = new ObjectInputStream(socket.getInputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void run() {
		RequestHandler handler = new RequestHandler(this);
		try {
			Object receivedObject;
			Request request;
			while ((receivedObject = input.readObject()) != null) {
				request = (Request) receivedObject;
				request.accept(handler);
			}
		} catch (IOException | ClassNotFoundException e) {
			// TODO: handle exception
		}
	}

	synchronized void sendResponse(Response response) {
		try {
			output.reset();
			output.writeObject(response);
			output.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void sendToGame(GameplayRequest request) {
		try {
			requestQueue.put(request);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void setGameHandler(GameHandler game) {
		this.game = game;
		this.requestQueue = game.getRequestQueue();
	}

	ClientInfo getClientInfo() {
		return clientInfo;
	}

	boolean isPlayingGame() {
		if (game != null)
			return true;
		return false;
	}
}