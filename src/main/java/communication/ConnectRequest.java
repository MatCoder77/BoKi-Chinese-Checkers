package communication;

public class ConnectRequest implements Request{

	/**
	 * 
	 */
	private static final long serialVersionUID = -369009006186019524L;

	public ConnectRequest(String clientName) {
		this.clientName = clientName;
	}
	
	private String clientName;
	
	public String getClientName() {
		return clientName;
	}

	@Override
	public ConnectRequest getRequest() {
		return this;
	}
}
