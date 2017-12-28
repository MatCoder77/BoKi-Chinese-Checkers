package client;

import java.awt.Point;

import communication.Command;
import communication.CommandHandler;
import communication.ConnectResponse;
import communication.DisconnectResponse;
import communication.EndTurnResponse;
import communication.GameEndedResponse;
import communication.GameStartedResponse;
import communication.LeaveGameResponse;
import communication.MoveResponse;
import communication.PossibleMovesResponse;
import communication.SomeoneJoinedResponse;
import communication.SomeoneLeftResponse;
import communication.StartFastGameResponse;
import communication.StartFastGameResponse.GameState;
import communication.StartGameResponse;
import communication.StartTurnResponse;
import communication.WinResponse;
import server.ClientInfo;

public class ResponseHandler extends CommandHandler {

	Client client;

	public ResponseHandler(Client client) {
		this.client = client;
	}

	public void handle(ConnectResponse response) {
		client.setClientID(response.getClientID());
		client.getClientGUI().addToLog("Connected to server, assigned ClientID: " + response.getClientID());
	}

	public void handle(DisconnectResponse response) {
		client.getClientGUI().addToLog("You were disconnected");
	}

	public void handle(StartTurnResponse response) {
		if (response.getClientInfo().getID() == client.getID()) {
			client.getClientGUI().addToLog("It's your turn");
			client.getClientGUI().endTurnButtonActive(true);
			client.getClientGUI().moveButtonActive(true);
		} else {
			client.getClientGUI().addToLog(response.getClientInfo().getName() + " started turn");
		}
	}

	public void handle(EndTurnResponse response) {
		if (response.getClientInfo().getID() == client.getID()) {
			client.getClientGUI().addToLog("You've ended your turn");
			client.getClientGUI().endTurnButtonActive(false);
			client.getClientGUI().moveButtonActive(false);
		}
		else {
			client.getClientGUI().addToLog(response.getClientInfo().getName() + " ended turn");
		}
	}
	
	public void handle(PossibleMovesResponse response) {
		client.getClientGUI().addToLog("You received possible moves response: ");
		for(Point p : response.getPossibleMoves()) {
			client.getClientGUI().addToLog(p.toString());
		}
	}

	public void handle(MoveResponse response) {
		if(response.getClientInfo().getID() == client.getID())
			client.getClientGUI().addToLog("MoveRequest accepted");
		else
			client.getClientGUI().addToLog(response.getClientInfo().getName() + "moved from " + response.getOldPos().toString() + "to " + response.getNewPos().toString());
	}
	
	public void handle(GameEndedResponse response) {
		client.getClientGUI().addToLog("GAME ENDED");
	}

	public void handle(StartFastGameResponse response) {
		client.getClientGUI().addToLog("Joined Fast Game");
		client.getClientGUI().addToLog("ID: " + response.getGameID());
		client.getClientGUI().addToLog("Name: " + response.getGameName());
		client.getClientGUI().addToLog("Type: " + response.getGameType().getPlayersNumber() + "players, "
				+ response.getGameType().getBoardSize().toString());
		client.getClientGUI().addToLog("State: " + response.getGameState().toString());
		String players = "";
		for (ClientInfo c : response.getConnectedPlayers()) {
			players += c.getName() + ", ";
		}
		client.getClientGUI().addToLog("Connected players: " + players);
	}


	public void handle(SomeoneJoinedResponse response) {
		client.getClientGUI().addToLog("User " + response.getClientInfo().getName() + ", ID: "
				+ response.getClientInfo().getID() + " joined your game");
	}

	public void handle(SomeoneLeftResponse response) {
		client.getClientGUI().addToLog("User " + response.getClientInfo().getName() + ", ID: "
				+ response.getClientInfo().getID() + " left game");
	}

	public void handle(GameStartedResponse repsonse) {
		client.getClientGUI().addToLog("GAME STARTED");
	}

	public void handle(StartGameResponse response) {

	}

	public void handle(LeaveGameResponse response) {
		client.getClientGUI().addToLog("You left game");
	}
	
	public void handle(WinResponse response) {
		if(response.getWinner().getID() == client.getID()) 
			client.getClientGUI().addToLog("You won!");
		else
			client.getClientGUI().addToLog(response.getWinner().getName() + "won");
	}

	@Override
	public void defaultHandle(Command command) {
		// TODO Auto-generated method stub

	}

}
