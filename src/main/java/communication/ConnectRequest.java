package communication;

public class ConnectRequest implements Request{

	/**
	 * 
	 */
	private static final long serialVersionUID = -369009006186019524L;

	ConnectRequest(String clientName) {
		this.clientName = clientName;
	}
	
	private String clientName;
	
	String getClientName() {
		return clientName;
	}
}
