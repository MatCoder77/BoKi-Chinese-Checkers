package communication;

import java.util.ArrayList;

import server.ClientInfo;
import server.GameType;

public class StartFastGameResponse extends Response{

	private static final long serialVersionUID = 389489991142375004L;
	int gameID;
	String gameName;
	GameType gameType;
	public enum GameState { WAITING_FOR_PLAYERS, STERTED};
	GameState gameState;
	ArrayList<ClientInfo> connectedPlayers;
	
	public StartFastGameResponse(int gameID, String gameName, GameType gameType, GameState gameState, ArrayList<ClientInfo> connectedPlayers) {
		this.gameID = gameID;
		this.gameName = gameName;
		this.gameType = gameType;
		this.gameState = gameState;
		this.connectedPlayers = connectedPlayers;
	}
	
	public int getGameID() {
		return gameID;
	}
	
	public String getGameName() {
		return gameName;
	}
	
	public GameType getGameType() {
		return gameType;
	}
	
	public GameState getGameState() {
		return gameState;
	}
	
	public ArrayList<ClientInfo> getConnectedPlayers() {
		return connectedPlayers;
	}
}
