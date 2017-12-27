package communication;

import server.ClientInfo;

public class SomeoneLeftResponse extends Response{
	
	private static final long serialVersionUID = 3294466712931521592L;
	ClientInfo clientInfo;
	
	public SomeoneLeftResponse(ClientInfo clientInfo) {
		this.clientInfo = clientInfo;
	}
	
	public ClientInfo getClientInfo() {
		return clientInfo;
	}
}
