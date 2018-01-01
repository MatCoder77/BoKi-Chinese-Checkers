package server;

import java.net.Socket;

import communication.Response;

public class FakeClientHandler extends ClientHandler{

	public FakeClientHandler() {
		super(null);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	synchronized void sendResponse(Response response) {
		System.out.println("Sent " + response.toString());
	}
	
	

}
