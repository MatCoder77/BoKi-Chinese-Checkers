package communication;

import server.GameType;

public class StartComputerGameRequest extends Request{

	private static final long serialVersionUID = -2696058633439541045L;
	String client;
	GameType gameType;
	
	public StartComputerGameRequest(String client, GameType gameType) {
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
