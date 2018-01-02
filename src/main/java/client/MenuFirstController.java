package client;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


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
		System.out.println(tmpName + tmpAddress + tmpPort);
		
		Stage stage = (Stage)button.getScene().getWindow();
		
		client = new Client(tmpAddress, tmpPort, tmpName);

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
		
		/*Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("MenuSecond.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();*/
	}
}
