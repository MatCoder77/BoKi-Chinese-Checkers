package client;

import java.io.IOException;

import communication.DisconnectRequest;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class MenuFirstController {
	
	private Client client;
	
	@FXML
	private Button button;
	
	@FXML
	private TextField address;
	
	@FXML
	private TextField name;
	
	@FXML
	private TextField port;
	
	@FXML
	private void handleButtonAction(ActionEvent event) {
		String tmpName = name.getText();
		String tmpAddress = address.getText();
		int tmpPort = 8988;
		try {
			tmpPort = Integer.parseInt(port.getText());
		} catch (NumberFormatException ex) {
			System.out.println(ex.getMessage());
		}
		
		Stage stage = (Stage)button.getScene().getWindow();
		
		client = new Client(tmpAddress, tmpPort, tmpName);
		
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			
			@Override
			public void handle(WindowEvent event) {
				if(client.isConnected())
					client.sendRequest(new DisconnectRequest(client.getName()));
				Platform.exit();
			}
		});

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
	}
}
