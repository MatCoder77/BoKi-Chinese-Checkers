package communication;

import server.ClientInfo;

public class EndTurnResponse extends GameplayResponse{

	private static final long serialVersionUID = -4148845673644060745L;
	ClientInfo clientInfo;
	
	public EndTurnResponse(ClientInfo clientInfo) {
		this.clientInfo = clientInfo;
	}
	
	public ClientInfo getClientInfo() {
		return clientInfo;
	}
}
