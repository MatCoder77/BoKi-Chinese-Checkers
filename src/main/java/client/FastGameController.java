package client;

import java.awt.Point;

import communication.EndTurnRequest;
import communication.LeaveGameRequest;
import communication.MoveRequest;
import communication.PossibleMovesRequest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FastGameController {
	
	private Client client;
	
	@FXML
	private Button move;
	
	@FXML
	private Button getMoves;
	
	@FXML
	private Button endTurn;
	
	@FXML
	private Button exit;
	
	@FXML
	private TextField fromX;
	
	@FXML
	private TextField fromY;
	
	@FXML
	private TextField toX;
	
	@FXML
	private TextField toY;
	
	@FXML
	private TextArea result;
	
	@FXML
	private void handleMove(ActionEvent event) {
		int tmpFromX = Integer.parseInt(fromX.getText());
		int tmpFromY = Integer.parseInt(fromY.getText());
		int tmpToX = Integer.parseInt(toX.getText());
		int tmpToY = Integer.parseInt(toY.getText());
		Point from = new Point(tmpFromX, tmpFromY);
		Point to = new Point(tmpToX, tmpToY);
		//System.out.println(from.x + " " + from.y);
		//System.out.println(to.x + " " + to.y);
		client.sendRequest(new MoveRequest(from, to));
	}
	
	@FXML
	private void handleGetMoves(ActionEvent event) {
		int tmpX = Integer.parseInt(fromX.getText());
		int tmpY = Integer.parseInt(fromY.getText());
		Point point = new Point(tmpX, tmpY);
		client.sendRequest(new PossibleMovesRequest(point));
	}
	
	@FXML
	private void handleEndTurn(ActionEvent event) {
		client.sendRequest(new EndTurnRequest());
	}
	
	@FXML
	private void handleExit(ActionEvent event) {
		client.sendRequest(new LeaveGameRequest());
	}
	
	public void setClient(Client client) {
		this.client = client;
		System.out.println(this.client.isConnected());
	}
	
	public void setInfoFromServer(String text) {
		result.appendText("> " + text + "\n");
	}

}
