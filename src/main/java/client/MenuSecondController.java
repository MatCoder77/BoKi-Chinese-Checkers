package client;

import java.io.IOException;

import communication.DisconnectRequest;
import communication.StartComputerGameRequest;
import communication.StartFastGameRequest;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import server.GameType;
import server.GameType.BoardSize;

public class MenuSecondController {
	
	ObservableList<String> options;
	
	private Client client;
	private Stage stage;
	private int numOfPlayers;
	private int numOfBots;
	private String gameType;
	
	@FXML
	private VBox vboxPlayers;
	
	@FXML
	private VBox vboxBots;
	
	@FXML
	private Button startGameBots;
	
	@FXML
	private Button startGamePlayers;
	
	@FXML
	private Button disconnect;
	
	@FXML
	private ComboBox<Integer> botsSize;
	
	@FXML
	private ComboBox<Integer> playersSize;
	
	@FXML
	private TextArea message;
	
	@FXML
	private void initialize() {
		playersSize = new ComboBox<>();
		playersSize.getItems().removeAll(playersSize.getItems());
		playersSize.getItems().addAll(2, 3, 4, 6);
		playersSize.setValue(2);
		playersSize.setMaxWidth(Double.MAX_VALUE);
		vboxPlayers.getChildren().add(playersSize);
		vboxPlayers.setAlignment(Pos.CENTER);
		
		botsSize = new ComboBox<>();
		botsSize.getItems().removeAll(botsSize.getItems());
		botsSize.getItems().addAll(2, 3, 4, 6);
		botsSize.setValue(2);
		botsSize.setMaxWidth(Double.MAX_VALUE);
		vboxBots.getChildren().add(botsSize);
		vboxBots.setAlignment(Pos.CENTER);
	}
	
	@FXML
	private void handleStartGameBots(ActionEvent event) {
		startGameBots.setDisable(true);
		startGamePlayers.setDisable(true);
		gameType = "BOTS";
		numOfBots = botsSize.getValue();
		client.sendRequest(new StartComputerGameRequest(client.getName(), new GameType(numOfBots, BoardSize.STANDARD)));
	}
	
	@FXML
	private void handleStartGamePlayers(ActionEvent event) {
		startGameBots.setDisable(true);
		startGamePlayers.setDisable(true);
		gameType = "PLAYERS";
		numOfPlayers = playersSize.getValue();
		client.sendRequest(new StartFastGameRequest(client.getName(), new GameType(numOfPlayers, BoardSize.STANDARD)));
		
	}
	
	@FXML
	private void handleDisconnect(ActionEvent event) {
		client.sendRequest(new DisconnectRequest(client.getName()));
		stage = (Stage)disconnect.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuFirst.fxml"));
		try {
			Scene scene = new Scene((Pane) loader.load());
			scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
			stage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		stage.show();
	}
	
	public void openGameWindow(int playerID) {
		if (gameType.equals("BOTS")) {
			stage = (Stage)startGameBots.getScene().getWindow();
		}
		else {
			stage = (Stage)startGamePlayers.getScene().getWindow();
		}
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("GameWindow.fxml"));
		
		try {
			Scene scene = new Scene((Pane) loader.load());
			scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
			stage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		GameWindowController controller = loader.<GameWindowController>getController();
		MouseEventHandler mouseHandler = new MouseEventHandler(client);
		Board board = null;
		
		if (gameType.equals("BOTS")) {
			board = new Board(numOfBots, playerID, mouseHandler);
		}
		else {
			board = new Board(numOfPlayers, playerID, mouseHandler);
		}
		
		controller.setBoard(board);
		controller.showBoard();
		client.setBoard(board);
		client.setGameWindow(controller);
		controller.setClient(client);
		stage.show();
	}
	
	public void setClient(Client client) {
		this.client = client;
		this.client.connect();
	}
	
	public void setMessage(String text) {
		message.setText(text);
	}

}
