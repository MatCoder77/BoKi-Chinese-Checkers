package server;

import communication.Command;
import communication.CommandHandler;
import communication.ConnectRequest;
import communication.ConnectResponse;
import communication.DisconnectRequest;
import communication.DisconnectResponse;
import communication.EndTurnRequest;
import communication.LeaveGameRequest;
import communication.LeaveGameResponse;
import communication.MoveRequest;
import communication.StartFastGameRequest;
import communication.StartFastGameResponse;
import communication.StartGameRequest;
import communication.StartTurnRequest;

/**
 * Handles requests received by server from client. ClientHandler uses objects of this class to perform requests handling in clean
 * and elegant way. Class can easily be extended to add new handler methods or reimplement existing ones.
 * @author Mateusz
 *
 */
public class RequestHandler extends CommandHandler {

	private ClientHandler clientHandler;
	private volatile GameHandler gameHandler;
	public RequestHandler(ClientHandler clientHandler) {
		this.clientHandler = clientHandler;
		this.gameHandler = null;
	} 
	
	public void handle(ConnectRequest request) {
		clientHandler.getClientInfo().setName(request.getClientName());
		clientHandler.sendResponse(new ConnectResponse(clientHandler.getClientInfo().getID()));
		Server.getInstance().getServerGUI().addToLog("Połączono użytkownika " + request.getClientName());
	}

	public void handle(DisconnectRequest request) {
		clientHandler.sendResponse(new DisconnectResponse());
		Server.getInstance().getServerGUI().addToLog("Rozłączono użytkownika " + request.getClientName());
	}

	public void handle(MoveRequest request) {
		try {
			gameHandler.getRequestQueue().put(request);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void handle(StartFastGameRequest request) {
		Server.getInstance().getServerGUI().addToLog("Otrzymano zapytanie StartFastGame od " + request.getClient());
		gameHandler = Server.getInstance().runFastGame(clientHandler);
		clientHandler.setGameHandler(gameHandler);
		clientHandler.sendResponse(new StartFastGameResponse(gameHandler.getGameInfo().getID(), gameHandler.getGameInfo().getName(), gameHandler.getGameInfo().getType(), gameHandler.getGameInfo().getState(), gameHandler.getGameInfo().getConnectedClientsInfo()));
	}

	public void handle(StartGameRequest request) {
		// TODO Auto-generated method stub
		
	}

	public void handle(LeaveGameRequest request) {
		Server.getInstance().getServerGUI().addToLog("Użytkownik " + clientHandler.getClientInfo().getName() + ", ID: " + clientHandler.getClientInfo().getID()
				+ " opuszcza grę " + gameHandler.getGameInfo().getName() + ", ID: " + gameHandler.getGameInfo().getID());
		gameHandler.removeClient(clientHandler);
		clientHandler.sendResponse(new LeaveGameResponse());
	}
	
	public void handle(StartTurnRequest request) {
		
	}
	
	public void handle(EndTurnRequest request) {
		
	}

	@Override
	public void defaultHandle(Command request) {
		//TODO
		Server.getInstance().getServerGUI().addToLog("Unknown command");
	}
	
	


}
