package communication;

import server.ClientInfo;

public class SomeoneJoinedResponse extends Response{
	
	private static final long serialVersionUID = -2595346301092234434L;
	private ClientInfo clientInfo;
	
	public SomeoneJoinedResponse(ClientInfo clientInfo) {
		this.clientInfo = clientInfo;
	}
	
	public ClientInfo getClientInfo() {
		return clientInfo;
	}
}
