package client;

import java.io.IOException;

import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MenuSecondController {
	
	ObservableList<String> options;
	
	private Client client;
	
	@FXML
	private Button gameNow;
	
	@FXML
	private Button startGame;
	
	@FXML
	private ChoiceBox playersNum;
	
	@FXML
	private void initialize() {
		
		playersNum = new ChoiceBox();
		playersNum.setTooltip(new Tooltip("Iloś graczy"));
		ObservableList<String> items; 
		//playersNum.setItems( );
		playersNum.getItems().addAll("2", "4");
	}
	
	@FXML
	private void handleGameNow(ActionEvent event) {
		Stage stage=(Stage) gameNow.getScene().getWindow();
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
	private void handleStartGame(ActionEvent event) {
		
	}
	
	public void setClient(Client client) {
		this.client = client;
		System.out.println(this.client.connect());
		System.out.println(this.client.isConnected());
	}

}
