package application;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.awt.Desktop;
import java.net.URI;
import java.net.URISyntaxException;
public class InterestController implements Initializable{

	//Initializing variables used for scene switching
		private Stage stage;
		private Scene scene;
		private Parent root;
		ArrayList<Club> clubs = new ArrayList<>();
		//FXML variables to reflect changes in scenebuilder
		
		@FXML
		Button sortMajor;
		@FXML
		Button logOutButton;
		@FXML
		Button backButton;
		@FXML
		TableView<Club> table;
		@FXML
		TableColumn<Club, String> nameColumn = new TableColumn<>("Name");
		@FXML
		TableColumn<Club, String> linkColumn = new TableColumn<>("Link");
		@FXML
		TableColumn<Club, String> emailColumn = new TableColumn<>("Email");
		@FXML
		TableColumn<Club, String> categoryColumn = new TableColumn<>("Category");
		@FXML
		TableColumn<Club, String> schoolColumn = new TableColumn<>("School");
		
		@FXML
		ListView<String> list = new ListView<String>();
		@FXML
		VBox vbox = new VBox();
		@FXML
		Text userText;
		@FXML
		Label majorText;
		@FXML
		Label selectedClub;
		@FXML
		Hyperlink linkBox;
		@FXML
		Label selectedEmail;
		String majorInfo;
		@FXML
		Button women;
		@FXML
		Button sports;
		@FXML
		Button cybersecurity;
		@FXML
		Button cultural;
		@FXML
		Button evironmental;
		@FXML
		Button health;
		@FXML
		Button wellbeing;
		@FXML
		Button military;
		@FXML
		Button political;
		@FXML
		Button robotics;
		@FXML
		Button religious;
		@FXML
		Button displayAll;
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
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Major.fxml"));
			root = loader.load();
			MajorController MajorView = loader.getController();
			MajorView.displayName(userText.getText().substring(9 ,15));
			//Parent root = FXMLLoader.load(getClass().getResource("Major.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
		public void displayName(String user, String major) {
			userText.setText(user);
			majorText.setText("School: " + major);
			majorInfo = major;
		}
		
		
		
		/**
		 * This method is called when Major.fxml is shown.
		 * puts the list of all clubs into a tableview and displays it
		*/
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			linkBox.setText("");
			Club tempClub = new Club("", "", "", "", "");
			ArrayList<Club> clubList = new ArrayList<>();
			clubList = tempClub.loadData("Display All Clubs");
			nameColumn.setMinWidth(200);
			linkColumn.setMinWidth(500);
			emailColumn.setMinWidth(200);
			categoryColumn.setMinWidth(200);
			schoolColumn.setMinWidth(200);
			nameColumn.setCellValueFactory(new PropertyValueFactory<Club, String>("name"));
			linkColumn.setCellValueFactory(new PropertyValueFactory<Club, String>("link"));
			emailColumn.setCellValueFactory(new PropertyValueFactory<Club, String>("email"));
			categoryColumn.setCellValueFactory(new PropertyValueFactory<Club, String>("school"));
			schoolColumn.setCellValueFactory(new PropertyValueFactory<Club, String>("school"));
			table.setItems(getClubs(clubList));
			//table.getColumns().addAll(nameColumn, linkColumn, emailColumn, schoolColumn);
			//vbox.getChildren().addAll(table);
			
			}
		//updates the data based on the school chosen
		public void updateDataByMajor(ActionEvent event) {
			Club tempClub = new Club("", "", "", "", "");
			table.getItems().clear();
			Button btn = (Button) event.getSource();
			String id = btn.getText();
			ArrayList<Club> clubList = new ArrayList<>();
			clubList = tempClub.loadData(majorInfo);
			nameColumn.setMinWidth(200);
			linkColumn.setMinWidth(500);
			emailColumn.setMinWidth(200);
			categoryColumn.setMinWidth(200);
			schoolColumn.setMinWidth(200);
			nameColumn.setCellValueFactory(new PropertyValueFactory<Club, String>("name"));
			linkColumn.setCellValueFactory(new PropertyValueFactory<Club, String>("link"));
			emailColumn.setCellValueFactory(new PropertyValueFactory<Club, String>("email"));
			categoryColumn.setCellValueFactory(new PropertyValueFactory<Club, String>("category"));
			schoolColumn.setCellValueFactory(new PropertyValueFactory<Club, String>("school"));
			table.setItems(getClubs(clubList));
		}
		
		//updates the data depending on which button is pressed in the "Sort by: "
		public void updateData(ActionEvent event) {
			Club tempClub = new Club("", "", "", "", "");
			table.getItems().clear();
			Button btn = (Button) event.getSource();
			String id = btn.getText();
			ArrayList<Club> clubList = new ArrayList<>();
			clubList = tempClub.loadData(id);
			nameColumn.setMinWidth(200);
			linkColumn.setMinWidth(500);
			emailColumn.setMinWidth(200);
			categoryColumn.setMinWidth(200);
			schoolColumn.setMinWidth(200);
			nameColumn.setCellValueFactory(new PropertyValueFactory<Club, String>("name"));
			linkColumn.setCellValueFactory(new PropertyValueFactory<Club, String>("link"));
			emailColumn.setCellValueFactory(new PropertyValueFactory<Club, String>("email"));
			categoryColumn.setCellValueFactory(new PropertyValueFactory<Club, String>("category"));
			schoolColumn.setCellValueFactory(new PropertyValueFactory<Club, String>("school"));
			table.setItems(getClubs(clubList));
		}
		
		//displays the currently selected club as well the email and link to the webpage
		public void displayClub(){
			Club tempClub = new Club("", "", "", "", "");
			TableViewSelectionModel<Club> selectionModel = table.getSelectionModel();
			ObservableList<Club> selectedRow = selectionModel.getSelectedItems();
			tempClub = selectedRow.get(0);
			selectedClub.setText("Selected Club: " + tempClub.getName());
			selectedEmail.setText("Email: " + tempClub.getEmail());
			linkBox.setText(tempClub.getLink());
		}
		
		//opens the webpage of the link when it is clicked
		public void openWebsite(ActionEvent event) throws IOException, URISyntaxException {
			Hyperlink tempLink = (Hyperlink) event.getSource();
			tempLink.getText();
			Desktop d = Desktop.getDesktop();
			d.browse(new URI(tempLink.getText()));
			}
		//turns an arrayList into an observable list so that it can be used in the table view
		public ObservableList<Club> getClubs(ArrayList<Club> clubs){
			ObservableList<Club> clubList = FXCollections.observableArrayList();
			for(Club temp: clubs) {
				clubList.add(temp);
			}
			return clubList;
		}
}
