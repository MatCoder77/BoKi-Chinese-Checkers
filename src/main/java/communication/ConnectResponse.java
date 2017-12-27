package communication;

public class ConnectResponse extends Response{
	
	private static final long serialVersionUID = -6892271359020638970L;
	int clientID;
	
	public ConnectResponse(int clientID) {
		this.clientID = clientID;
	}
	
	public int getClientID() {
		return clientID;
	}
}
