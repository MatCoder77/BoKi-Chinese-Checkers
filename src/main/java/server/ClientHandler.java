package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;

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
	private GameHandler game;
	private BlockingQueue<Request> requestQueue;
	
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

	void sendResponse(Response response) {
		try {
			output.writeObject(response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void setGameHandler(GameHandler game) {
		this.game = game;
		this.requestQueue = game.getRequestQueue();
	}
}