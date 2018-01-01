package client;

import communication.MoveRequest;
import communication.PossibleMovesRequest;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class MouseEventHandler implements EventHandler<MouseEvent> {
	
	private Client client;
	
	public MouseEventHandler(Client client) {
		this.client = client;
	}

	@Override
	public void handle(MouseEvent event) {
		BoardField boardField = (BoardField)event.getSource();
	//	System.out.println(boardField.getLocation());
		if(boardField.isActive()) {
			System.out.println(boardField.getLocation());
			if(boardField.isPossible()) {
				// TODO send move
				BoardField from = client.getBoard().getSelected();
				client.getBoard().performMove(from, boardField);
				client.sendRequest(new MoveRequest(from.getLocation(), boardField.getLocation()));
			}
			else if(client.getBoard().select(boardField)) {
				// TODO get possible moves
				client.sendRequest(new PossibleMovesRequest(boardField.getLocation()));
			}
		}
		
	}

}
