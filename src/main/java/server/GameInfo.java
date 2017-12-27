package server;

import java.io.Serializable;
import java.util.ArrayList;

import communication.StartFastGameResponse.GameState;

public class GameInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2223047579181064306L;
	int ID;
	String name;
	GameType type;
	GameState state;
	volatile ArrayList<ClientInfo> connectedClientsInfo;
	
	GameInfo(int ID, String name, GameType type, GameState state, ArrayList<ClientInfo> connectedClientsInfo) {
		this.ID = ID;
		this.name = name;
		this.type = type; 
		this.state = state;
		this.connectedClientsInfo = connectedClientsInfo;
	}
	
	GameInfo setID(int ID) {
		this.ID = ID;
		return this;
	}
	
	GameInfo setName(String name) {
		this.name = name;
		return this;
	}
	
	GameInfo setType(GameType type) {
		this.type = type;
		return this;
	}
	
	GameInfo setState(GameState state) {
		this.state = state;
		return this;
	}
	
	GameInfo addClientInfo(ClientInfo info) {
		connectedClientsInfo.add(info);
		return this;
	}
	
	GameInfo removeClientInfo(ClientInfo info) {
		connectedClientsInfo.remove(info);
		return this;
	}
	
	int getID() {
		return ID;
	}
	
	String getName() {
		return name;
	}
	
	GameType getType() {
		return type;
	}
	
	GameState getState() {
		return state;
	}
	
	ArrayList<ClientInfo> getConnectedClientsInfo() {
		return connectedClientsInfo;
	}
}
