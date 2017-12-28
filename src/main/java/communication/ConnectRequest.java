package communication;

/**
 * Request sent by client when connection with server is established. Contains
 * client nickname. As a response server sends ConnectRequest containing ID
 * number assigned to client.
 * 
 * @author Mateusz
 *
 */
public class ConnectRequest extends Request {

	private static final long serialVersionUID = -369009006186019524L;
	private String clientName;

	public ConnectRequest(String clientName) {
		this.clientName = clientName;
	}

	public String getClientName() {
		return clientName;
	}
}
