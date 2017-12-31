package communication;

import java.util.ArrayList;

import server.ClientInfo;

public class GameStartedResponse extends Response {

	private static final long serialVersionUID = -2945361485846888280L;
	ArrayList<ClientInfo> connectedPlayers;
	
	public GameStartedResponse(ArrayList<ClientInfo> connectedPlayers) {
		this.connectedPlayers = connectedPlayers;
	}
	
	public ClientInfo getClientInfo(int ClientID) {
		for(ClientInfo c : connectedPlayers) {
			if(c.getID() == ClientID)
				return c;
		}
		return null;
	}
}
