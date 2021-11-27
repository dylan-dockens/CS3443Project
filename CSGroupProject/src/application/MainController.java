package application;

import java.io.IOException;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MainController {

	private Stage stage;
	private Scene scene;
	private Parent root;
	public boolean b;
	public static String usr;

	@FXML
	Button continueButton;
	@FXML
	Label err;
	@FXML
	TextField tfield;

	public void checker(ActionEvent e) throws IOException {
		b = Pattern.matches("[a-zA-z]{3}[1-9]{3}", tfield.getText());
		if (b) {
			usr = tfield.getText();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Major.fxml"));
			root = loader.load();
			
			MajorController majorView = loader.getController();
			majorView.displayName(usr);
			//root = FXMLLoader.load(getClass().getResource("Major.fxml"));
			stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} else {
			err.setText("Error invalid id");

		}
	}
}
