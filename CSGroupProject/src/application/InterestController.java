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
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class InterestController implements Initializable{

	//Initializing variables used for scene switching
		private Stage stage;
		private Scene scene;
		private Parent root;
		
		//FXML variables to reflect changes in scenebuilder
		@FXML
		Button logOutButton;
		@FXML
		Button backButton;
		
		@FXML
		TreeView interestList;
		
		@FXML
		Text userText;
		
		/**
		 * When pressed, the scene will switch depending on which one.
		 * Methods have been named accordingly.
		 */
		public void switchToMain(ActionEvent event) throws IOException {
			Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
		public void switchToMajor(ActionEvent event) throws IOException {
			Parent root = FXMLLoader.load(getClass().getResource("Major.fxml"));
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
