package client;

import communication.Command;
import communication.CommandHandler;
import communication.ConnectResponse;
import communication.DisconnectResponse;
import communication.EndTurnResponse;
import communication.LeaveGameResponse;
import communication.MoveResponse;
import communication.StartFastGameResponse;
import communication.StartFastGameResponse.GameState;
import communication.StartGameResponse;
import communication.StartTurnResponse;
import server.ClientInfo;

public class ResponseHandler extends CommandHandler {

	Client client;
	
	void setClient(Client client) {
		this.client = client;
	}
	
	public void handle(ConnectResponse response) {
		client.setClientID(response.getClientID());
		client.getClientGUI().addToLog("Connected to server, assigned ClientID: " + response.getClientID());
	}
	
	public void handle(DisconnectResponse response) {
		
	}
	
	public void handle(StartTurnResponse response) {
		client.getClientGUI().addToLog("It's your turn");
		client.getClientGUI().endTurnButtonActive(true);
		client.getClientGUI().moveButtonActive(true);
	}
	
	public void handle(EndTurnResponse response) {
		client.getClientGUI().addToLog("You've ended your turn");
		client.getClientGUI().endTurnButtonActive(false);
		client.getClientGUI().moveButtonActive(false);
	}
	
	public void handle(MoveResponse response) {
		client.getClientGUI().addToLog("MoveRequest accepted");
	}
	
	public void handle(StartFastGameResponse response) {
		client.getClientGUI().addToLog("Joined Fast Game");
		client.getClientGUI().addToLog("ID: " + response.getGameID());
		client.getClientGUI().addToLog("Name: " + response.getGameName());
		client.getClientGUI().addToLog("Type: " + response.getGameType().getPlayersNumber() + "players, " + response.getGameType().getBoardSize().toString());
		client.getClientGUI().addToLog("State: " + response.getGameState().toString());
		String players = "";
		for(ClientInfo c : response.getConnectedPlayers()) {
			players += c.getName() + ", ";
		}
		client.getClientGUI().addToLog("Connected players: " + players);
		if(response.getGameState().equals(GameState.STERTED)) {
			client.getClientGUI().addToLog("GAME STARTED \n-------------------------------");
			//client starts game
		}
	}
	
	void handle(StartGameResponse response) {
		
	}
	
	void handle(LeaveGameResponse response) {
		
	}
	
	@Override
	public void defaultHandle(Command command) {
		// TODO Auto-generated method stub

	}

}
