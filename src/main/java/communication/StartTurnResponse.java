package communication;

import server.ClientInfo;

public class StartTurnResponse extends Response{

	private static final long serialVersionUID = 2572088960805879095L;
	ClientInfo clientInfo;
	
	public StartTurnResponse(ClientInfo clientInfo) {
		this.clientInfo = clientInfo;
	}
	
	public ClientInfo getClientInfo() {
		return clientInfo;
	}
}
