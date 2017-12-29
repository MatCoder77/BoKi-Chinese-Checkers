package communication;

import server.GameType;

public class StartFastGameRequest extends Request{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4431691068088746497L;
	String client;
	GameType gameType;
	
	public StartFastGameRequest(String client, GameType gameType) {
		this.client = client;
		this.gameType = gameType;
	}

	public String getClient() {
		return client;
	}
	
	public GameType getGameType() {
		return gameType;
	}
}
