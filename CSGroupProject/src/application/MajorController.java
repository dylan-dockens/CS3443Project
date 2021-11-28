package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeView;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MajorController implements Initializable{

	//Initializing variables used for scene switching
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	//FXML variables to reflect changes in scenebuilder
	@FXML
	Button logOutButton;
	@FXML
	Button scienceButton;
	@FXML
	Button engineerButton;
	@FXML
	Button liberalButton;
	@FXML
	Button businessButton;
	@FXML
	Button educationButton;
	
	@FXML
	Text userText;
	@FXML
	TextField searchField;
	
	/**
	 * These methods are called when a certain button is pressed.
	 * When pressed, the scene will switch depending on which one.
	 * Methods have been named accordingly.
	 */
	public void switchToInterest(ActionEvent event) throws IOException {
		Button btn = (Button) event.getSource();
		String id = btn.getText();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Interest.fxml"));
		root = loader.load();
		
		InterestController InterestView = loader.getController();
		InterestView.displayName(userText.getText(), id);
		//Parent root = FXMLLoader.load(getClass().getResource("Interest.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void displayName(String user) {
		userText.setText("Welcome, " + user + "!");
	}
	public void switchToMain(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * This method is called when Major.fxml is shown.
	 * Nothing to pass in currently as it is a demo.
	 * Once we have a lengthy csv or something we will pass the data through here to initialize the list of majors.
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
}
