package communication;

public class DisconnectRequest extends Request{

	private static final long serialVersionUID = -6479705954524094648L;
	
	public DisconnectRequest(String clientName) {
		this.clientName = clientName;
	}
	
	private String clientName;
	
	public String getClientName() {
		return clientName;
	}
}
