package server;

import java.io.Serializable;

public class ClientInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3571281344888585327L;
	int ID;
	String name;
	int playerID;
	
	ClientInfo(int ID) {
		this.ID = ID;
	}
	
	void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public int getID() {
		return ID;
	}
	
	void setPlayerID(int playerID) {
		this.playerID = playerID;
	}
	
	public int getPlayerID() {
		return playerID;
	}
}
