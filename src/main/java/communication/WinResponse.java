package communication;

import server.ClientInfo;

public class WinResponse extends Response{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2013667742509002379L;
	private ClientInfo winner;
	
	public WinResponse(ClientInfo winner) {
		this.winner = winner;
	}
	
	public ClientInfo getWinner() {
		return winner;
	}
	
}
