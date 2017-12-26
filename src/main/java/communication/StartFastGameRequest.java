package communication;

public class StartFastGameRequest extends Request{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4431691068088746497L;
	String client;
	public StartFastGameRequest(String client) {
		this.client = client;
	}

	public String getClient() {
		return client;
	}
}
