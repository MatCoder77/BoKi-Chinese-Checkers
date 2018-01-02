package client;

import java.io.IOException;

import communication.EndTurnRequest;
import communication.LeaveGameRequest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GameWindowController {

	@FXML
	private VBox vbox;
	
	@FXML
	private Button endTurn;
	
	@FXML
	private Button exit;
	
	@FXML 
	private TextArea message;
	
	private BoardField[][] boardFields;
	private Board board;
	private Client client;
	
	@FXML
	private void initialize() {
		//board = new Board(6, 1);
		/*boardFields = board.getBoardFields();
		vbox.setAlignment(Pos.CENTER);
		vbox.setSpacing(2);
		for (int i = 0 ; i < boardFields.length; i++) {
			HBox hbox = new HBox(5);
			hbox.setAlignment(Pos.CENTER);
			for (int j = 0 ; j < boardFields[i].length ; j++) {
				if(!boardFields[i][j].getFieldType().equals("N")) {
					hbox.getChildren().add(boardFields[i][j]);
				}
			}
			vbox.getChildren().add(hbox);
		}*/
		deactivateEndTurn();
	}
	
	@FXML
	private void handleEndTurn(ActionEvent event) {
		client.sendRequest(new EndTurnRequest());
		board.endTurn();
		deactivateEndTurn();
	}
	
	@FXML
	private void handleExit(ActionEvent event) {
		client.sendRequest(new LeaveGameRequest());
		Stage stage = (Stage)exit.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuSecond.fxml"));
		try {
			Scene scene = new Scene((Pane) loader.load());
			scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
			stage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}

		MenuSecondController controller = loader.<MenuSecondController>getController();
		client.setMenuSecond(controller);
		controller.setClient(client);
		stage.show();
		//System.exit(0);
	}
	
	public void setBoard(Board board) {
		this.board = board;
	}
	
	public void showBoard() {
		boardFields = board.getBoardFields();
		vbox.setAlignment(Pos.CENTER);
		vbox.setSpacing(2);
		for (int i = 0 ; i < boardFields.length; i++) {
			HBox hbox = new HBox(5);
			hbox.setAlignment(Pos.CENTER);
			for (int j = 0 ; j < boardFields[i].length ; j++) {
				if(!boardFields[i][j].getFieldType().equals("N")) {
					hbox.getChildren().add(boardFields[i][j]);
				}
			}
			vbox.getChildren().add(hbox);
		}
	}
	
	public void setClient(Client client) {
		this.client = client;
	}
	
	public void setMessage(String message) {
		this.message.appendText("> " + message + "\n");
	}
	
	public void activateEndTurn() {
		endTurn.setDisable(false);
	}
	
	public void deactivateEndTurn() {
		endTurn.setDisable(true);
	}

}
