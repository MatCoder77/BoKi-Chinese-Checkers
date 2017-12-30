package client;

import java.io.IOException;

import board.BoardType;
import communication.StartComputerGameRequest;
import communication.StartFastGameRequest;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import server.GameType;
import server.GameType.BoardSize;

public class MenuSecondController {
	
	ObservableList<String> options;
	
	private Client client;
	private Stage stage;
	
	@FXML
	private VBox vboxPlayers;
	
	@FXML
	private VBox vboxBots;
	
	@FXML
	private Button startGameBots;
	
	@FXML
	private Button startGamePlayers;
	
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
		Stage stage=(Stage) startGameBots.getScene().getWindow();
		//Parent root = null;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("FastGame.fxml"));
		try {
			stage.setScene(new Scene((Pane) loader.load()));
		} catch (IOException e) {
			e.printStackTrace();
		}

		FastGameController controller = loader.<FastGameController>getController();
		client.setFastGame(controller);
		controller.setClient(client);
		System.out.println(client.getName());
		client.sendRequest(new StartComputerGameRequest(client.getName(), new GameType(botsSize.getValue(), BoardSize.STANDARD)));
		stage.show();
		/*try {
			root = FXMLLoader.load(getClass().getResource("FastGame.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();*/
	}
	
	@FXML
	private void handleStartGamePlayers(ActionEvent event) {
		stage = (Stage)startGamePlayers.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("FastGame.fxml"));
		try {
			stage.setScene(new Scene((Pane) loader.load()));
		} catch (IOException e) {
			e.printStackTrace();
		}

		FastGameController controller = loader.<FastGameController>getController();
		client.setFastGame(controller);
		controller.setClient(client);
		System.out.println(client.getName());
		
		client.sendRequest(new StartFastGameRequest(client.getName(), new GameType(playersSize.getValue(), BoardSize.STANDARD)));
		
		stage.show();
	}
	
	public void openFastGameWindow() {
		//Stage stage=(Stage) startGamePlayers.getScene().getWindow();
		//Parent root = null;
		/*FXMLLoader loader = new FXMLLoader(getClass().getResource("FastGame.fxml"));
		try {
			stage.setScene(new Scene((Pane) loader.load()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		FastGameController controller = loader.<FastGameController>getController();
		client.setFastGame(controller);
		controller.setClient(client);
		System.out.println(client.getName());*/
		stage.show();
	}
	
	public void setClient(Client client) {
		this.client = client;
		System.out.println(this.client.connect());
		System.out.println(this.client.isConnected());
	}
	
	public void setMessage(String text) {
		message.setText(text);
	}

}
