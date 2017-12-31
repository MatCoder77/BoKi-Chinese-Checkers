package server;

import java.awt.Point;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import board.BoardSixFour;
import board.BoardSixSix;
import board.BoardSixThree;
import board.BoardSixTwo;
import board.BoardType;
import communication.Command;
import communication.CommandHandler;
import communication.EndTurnRequest;
import communication.EndTurnResponse;
import communication.GameEndedResponse;
import communication.GameStartedResponse;
import communication.LeaveGameResponse;
import communication.MoveRequest;
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

public class Bot extends ClientHandler {

	private BlockingQueue<Response> receivedResponses;
	private BotStrategy botStrategy;

	Bot(BotStrategy botStrategy) {
		super(null);
		clientInfo.setName("Bot " + clientInfo.getID());
		receivedResponses = new LinkedBlockingQueue<>();
		this.botStrategy = botStrategy;
	}

	@Override
	public void run() {
		Response request;
		BootResponseHandler handler = new BootResponseHandler();
		try {
			while ((request = receivedResponses.take()) != null) {
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
	
	void play() {
		MoveRequest moveRequest = botStrategy.getBestMove();
		if (moveRequest == null) {
			sendToGame(new EndTurnRequest());
		} else {
			sendToGame(moveRequest);
		}
	}

	public class BootResponseHandler extends CommandHandler {

		public void handle(StartTurnResponse response) {
			if (response.getClientInfo().getID() == clientInfo.getID()) {
				play();
			} else {

			}
		}

		public void handle(EndTurnResponse response) {
			if (response.getClientInfo().getID() == clientInfo.getID()) {

			} else {
				// here
			}
		}

		public void handle(PossibleMovesResponse response) {
			play();
		}

		public void handle(MoveResponse response) {
			botStrategy.playerMoved(clientInfo.getPlayerID(), response.getOldPos(), response.getNewPos());
		}

		public void handle(StartComputerGameResponse response) {
			// myCorner = response.getConnectedPlayers().size(); //can occur problems with
			// synchronization, boots are added very fast
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
			botStrategy.prepareGameSimulation(game.getGameInfo().getType(), clientInfo.getPlayerID());
		}

		public void handle(LeaveGameResponse response) {

		}

		public void handle(WinResponse response) {
			if (response.getWinner().getID() == clientInfo.getID()) {

			} else {

			}
		}

		@Override
		public void defaultHandle(Command command) {
			// TODO Auto-generated method stub

		}

	}

}
