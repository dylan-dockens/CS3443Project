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
		public void displayName(String user, String major) {
			userText.setText(user);
			majorText.setText(major);
		}
		
		
		
		/**
		 * This method is called when Major.fxml is shown.
		 * Nothing to pass in currently as it is a demo.
		 * Once we have a lengthy csv or something we will pass the data through here to initialize the list of majors.
		 */
		
		public ArrayList<Club> loadData(String type) {
			clubs.removeAll(clubs);
			String tempName = "";
			String tempLink = "";
			String tempEmail = "";
			String tempCategory = "";
			String tempSchool = "";
			String text = "";
			String[] items = new String[6];
			int x = 0;
			FileReader readFile;
			try {
				readFile = new FileReader("clubs.csv");
				BufferedReader buff = new BufferedReader(readFile);
				while((text = buff.readLine()) != null) {
					items = text.split(",");
					tempName = items[0];
					tempLink = items[1];
					tempEmail = items[2];
					tempSchool = items[3];
					//tempSchool = items[4];
					Club tempClub = new Club(tempName, tempLink, tempEmail, tempSchool);
					if(type.equals("all")) {
						clubs.add(tempClub);
					}
					if(type.equals("Science") || type.equals("Business") || type.equals("Engineering") || type.equals("Liberal & Fine Arts") || type.equals("Education")) {
						if(type.equals(tempClub.getSchool())) {
							clubs.add(tempClub);
						}
					}
					if(type.equals("any")) {
					clubs.add(tempClub);
					}
					/*if(!type.equals("any")) {
						if(type.equals(tempClub.getCategory()) {
							clubs.add(tempClub);
						}
					}*/
				}
				
				
				buff.close();
				} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
			return clubs;
		}
		
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			ArrayList<Club> clubList = new ArrayList<>();
			clubList = loadData("any");
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
			//table.getColumns().addAll(nameColumn, linkColumn, emailColumn, schoolColumn);
			//vbox.getChildren().addAll(table);
			
			}
		
		public void updateDataByMajor(ActionEvent event) {
			table.getItems().clear();
			Button btn = (Button) event.getSource();
			String id = btn.getText();
			ArrayList<Club> clubList = new ArrayList<>();
			clubList = loadData(majorText.getText());
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
			//table = new TableView<Club>();
			table.setItems(getClubs(clubList));
		}
		public void updateData(ActionEvent event) {
			//vbox.getChildren().clear();
			table.getItems().clear();
			Button btn = (Button) event.getSource();
			String id = btn.getText();
			ArrayList<Club> clubList = new ArrayList<>();
			clubList = loadData("Business");
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
			table = new TableView<Club>();
			table.setItems(getClubs(clubList));
			//table.getColumns().addAll(nameColumn, linkColumn, emailColumn, schoolColumn);
			//vbox.getChildren().addAll(table);
			
		}
		
		public void displayClub(){
			Club tempClub = new Club("", "", "", "");
			TableViewSelectionModel<Club> selectionModel = table.getSelectionModel();
			ObservableList<Club> selectedRow = selectionModel.getSelectedItems();
			//System.out.println(electedRow.toString());
			tempClub = selectedRow.get(0);
			selectedClub.setText("Selected Club: " + tempClub.getName() + "   Email: " + tempClub.getEmail());
			linkBox.setText(tempClub.getLink());
			//selectedClub.setText("Selected Club" + selectedRow);
			//System.out.println(selectedRow);
			
		}
		
		public void openWebsite(ActionEvent event) throws IOException, URISyntaxException {
			Hyperlink tempLink = (Hyperlink) event.getSource();
			tempLink.getText();
			Desktop d = Desktop.getDesktop();
			d.browse(new URI(tempLink.getText()));
			}
		public ObservableList<Club> getClubs(ArrayList<Club> clubs){
			ObservableList<Club> clubList = FXCollections.observableArrayList();
			for(Club temp: clubs) {
				clubList.add(temp);
			}
			return clubList;
		}
		
}
