package client;

import java.awt.Point;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
	private TextField result;
	
	@FXML
	private void handleMove(ActionEvent event) {
		int tmpFromX = Integer.parseInt(fromX.getText());
		int tmpFromY = Integer.parseInt(fromY.getText());
		int tmpToX = Integer.parseInt(toX.getText());
		int tmpToY = Integer.parseInt(toY.getText());
		Point from = new Point(tmpFromX, tmpFromY);
		Point to = new Point(tmpToX, tmpToY);
		System.out.println(from.x + " " + from.y);
		System.out.println(to.x + " " + to.y);
	}
	
	@FXML
	private void handleGetMoves(ActionEvent event) {
		int tmpX = Integer.parseInt(fromX.getText());
		int tmpY = Integer.parseInt(fromY.getText());
		Point point = new Point(tmpX, tmpY);
	}
	
	@FXML
	private void handleEndTurn(ActionEvent event) {
		
	}
	
	@FXML
	private void handleExit(ActionEvent event) {
		System.exit(0);
	}
	
	public void setClient(Client client) {
		this.client = client;
		System.out.println(this.client.isConnected());
	}
	
	public void setInfoFromServer(String text) {
		result.setText(text);
	}

}
