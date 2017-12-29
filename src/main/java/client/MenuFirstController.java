package client;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/*public class MenuFirstController extends GridPane {
	
	private Stage stage;
	private TextField name, port, address;
	private Label nameLbl, portLbl, addressLbl;
	private Button confirm;
	
	public MenuFirstController(Stage stage) {
		super();
		this.stage = stage;
		this.setPadding(new Insets(10));
		this.setHgap(10);
		this.setVgap(5);
		this.setPrefSize(300, 200);
		this.setAlignment(Pos.CENTER);
		name = new TextField();
		port = new TextField("8988");
		address = new TextField("localhost");
		nameLbl = new Label("Imię");
		portLbl = new Label("Port");
		addressLbl = new Label("Adres");
		confirm = new Button("Połącz z serwerem");
		confirm.setMaxWidth(Double.MAX_VALUE);
		
		confirm.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				String tmpName = name.getText();
				String tmpAddress = address.getText();
				Stage st = (Stage)confirm.getScene().getWindow();
				try {
					int tmpPort = Integer.parseInt(port.getText());
				} catch (NumberFormatException e) {
					System.out.println(e.getMessage());
				}
				
			}
		});
		
		this.add(nameLbl, 0, 0);
		this.add(name, 1, 0);
		this.add(portLbl, 0, 1);
		this.add(port, 1, 1);
		this.add(addressLbl, 0, 2);
		this.add(address, 1, 2);
		this.add(confirm, 0, 3, 2, 1);
		
		Scene scene = new Scene(this, Color.ALICEBLUE);
		this.stage.setScene(scene);
	}

}*/

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
		
		Stage stage=(Stage) button.getScene().getWindow();
		
		client = new Client(tmpAddress, tmpPort, tmpName);

		FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuSecond.fxml"));
		try {
			stage.setScene(new Scene((Pane) loader.load()));
		} catch (IOException e) {
			e.printStackTrace();
		}

		MenuSecondController controller = loader.<MenuSecondController>getController();
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
