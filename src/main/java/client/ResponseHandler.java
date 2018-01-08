package client;

import communication.Command;
import communication.CommandHandler;
import communication.ConnectResponse;
import communication.DisconnectResponse;
import communication.EndTurnResponse;
import communication.GameEndedResponse;
import communication.GameStartedResponse;
import communication.LeaveGameResponse;
import communication.MoveResponse;
import communication.PossibleMovesResponse;
import communication.SomeoneJoinedResponse;
import communication.SomeoneLeftResponse;
import communication.StartFastGameResponse;
import communication.StartComputerGameResponse;
import communication.StartTurnResponse;
import communication.WinResponse;
import javafx.application.Platform;

public class ResponseHandler extends CommandHandler {

	Client client;

	public ResponseHandler(Client client) {
		this.client = client;
	}

	public void handle(ConnectResponse response) {
		client.setClientID(response.getClientID());
		//System.out.println("Connected to server, assigned ClientID: " + response.getClientID());
	}

	public void handle(DisconnectResponse response) {
		//System.out.println("You were disconnected");
	}

	public void handle(StartTurnResponse response) {
		if (response.getClientInfo().getID() == client.getID()) {
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					client.getGameWindow().activateEndTurn();
					client.getBoard().activatePlayerFields();
					client.getGameWindow().setMessage("Your turn");
				}
			});
		} else {
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					client.getGameWindow().setMessage(response.getClientInfo().getName() + "'s turn");
				}
			});
		}
	}

	public void handle(EndTurnResponse response) {
		if (response.getClientInfo().getID() == client.getID()) {
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					client.getGameWindow().setMessage("You've ended your turn");
				}
			});
		}
		else {
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					client.getGameWindow().setMessage(response.getClientInfo().getName() + " has ended turn");
				}
			});
		}
	}
	
	public void handle(PossibleMovesResponse response) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				client.getBoard().setPossible(response.getPossibleMoves());
			}
		});
	}

	public void handle(MoveResponse response) {
		if(response.getClientInfo().getID() == client.getID()) {
			//System.out.println("Move performed");
		}
		else {
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					client.getBoard().updateBoard(response.getOldPos(), response.getNewPos());
				}
			});
		}
	}
	
	public void handle(GameEndedResponse response) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				client.getGameWindow().setMessage("Game ended");
			}
		});
	}

	public void handle(StartFastGameResponse response) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				client.getMenuSecond().setMessage("Waiting for other players");
			}
		});
		
	}
	
	public void handle(StartComputerGameResponse response) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				client.getMenuSecond().setMessage("Game will start soon");
			}
		});
	}


	public void handle(SomeoneJoinedResponse response) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				client.getMenuSecond().setMessage(response.getClientInfo().getName() + " joined the game");
			}
		});
	}

	public void handle(SomeoneLeftResponse response) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				client.getGameWindow().setMessage(response.getClientInfo().getName() + " left game");
			}
		});
	}

	public void handle(GameStartedResponse response) {
		int playerID = response.getClientInfo(client.getID()).getPlayerID();
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				client.getMenuSecond().openGameWindow(playerID);
				client.getGameWindow().setMessage("Game started");
			}
		});
	}

	public void handle(LeaveGameResponse response) {
		//System.out.println("You left game");
		
	}
	
	public void handle(WinResponse response) {
		if(response.getWinner().getID() == client.getID()) {
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					client.getGameWindow().setMessage("You won!");
				}
			});
		}
		else {
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					client.getGameWindow().setMessage(response.getWinner().getName() + " won");
				}
			});
		}
	}

	@Override
	public void defaultHandle(Command command) {
		// TODO Auto-generated method stub

	}

}