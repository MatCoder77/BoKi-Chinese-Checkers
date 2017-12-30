package server;

import java.awt.Point;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import communication.Command;
import communication.CommandHandler;
import communication.EndTurnResponse;
import communication.GameEndedResponse;
import communication.GameStartedResponse;
import communication.LeaveGameResponse;
import communication.MoveResponse;
import communication.PossibleMovesRequest;
import communication.PossibleMovesResponse;
import communication.Request;
import communication.Response;
import communication.SomeoneJoinedResponse;
import communication.SomeoneLeftResponse;
import communication.StartComputerGameResponse;
import communication.StartFastGameResponse;
import communication.StartTurnResponse;
import communication.WinResponse;

public class Bot extends ClientHandler{
	
	BlockingQueue<Response> receivedResponses;
	BotStrategy bootStategy;
	int myCorner;
	int targetCorner;
	
	Bot(int targetCorner) {
		super(null);
		clientInfo.setName("Bot " + clientInfo.getID());
		this.targetCorner = targetCorner;
		receivedResponses = new LinkedBlockingQueue<>();
	}
	
	@Override
	public void run() {
		Response request;
		BootResponseHandler handler = new BootResponseHandler();
		try {
			while((request = receivedResponses.take()) != null) {
				request.accept(handler);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Override
	synchronized void sendResponse(Response response) {
		try {
			receivedResponses.put(response);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public class BootResponseHandler extends CommandHandler {

		public void handle(StartTurnResponse response) {
			if (response.getClientInfo().getID() == clientInfo.getID()) {
				
			}
			else {
				
			}
		}

		public void handle(EndTurnResponse response) {
			if (response.getClientInfo().getID() == clientInfo.getID()) {
				
			}
			else {
				//here
			}
		}
		
		public void handle(PossibleMovesResponse response) {
			
		}

		public void handle(MoveResponse response) {
			if(response.getClientInfo().getID() == clientInfo.getID()) {
			
			}
			else {
				
			}
		}
		
		public void handle(StartComputerGameResponse response) {
			//myCorner = response.getConnectedPlayers().size(); //can occur problems with synchronization, boots are added very fast
		}
		
		public void handle(GameEndedResponse response) {
			
		}

		public void handle(StartFastGameResponse response) {
			
		}

		public void handle(SomeoneJoinedResponse response) {
			
		}

		public void handle(SomeoneLeftResponse response) {
			
		}

		public void handle(GameStartedResponse repsonse) {
			
		}

		public void handle(LeaveGameResponse response) {
			
		}
		
		public void handle(WinResponse response) {
			if(response.getWinner().getID() == clientInfo.getID()) {
				
			}
			else {
				
			}
		}

		@Override
		public void defaultHandle(Command command) {
			// TODO Auto-generated method stub

		}

		
	}

}
