package client;

import communication.Command;
import communication.CommandHandler;
import communication.ConnectResponse;
import communication.DisconnectResponse;
import communication.EndTurnResponse;
import communication.LeaveGameResponse;
import communication.MoveResponse;
import communication.StartFastGameResponse;
import communication.StartGameResponse;
import communication.StartTurnResponse;

public class ResponseHandler extends CommandHandler {

	public void handle(ConnectResponse response) {
		
	}
	
	public void handle(DisconnectResponse response) {
		
	}
	
	public void handle(StartTurnResponse response) {
		
	}
	
	public void handle(EndTurnResponse response) {
		
	}
	
	public void handle(MoveResponse response) {
		
	}
	
	public void handle(StartFastGameResponse response) {
		
	}
	
	void handle(StartGameResponse response) {
		
	}
	
	void handle(LeaveGameResponse response) {
		
	}
	
	@Override
	public void defaultHandle(Command command) {
		// TODO Auto-generated method stub

	}

}
