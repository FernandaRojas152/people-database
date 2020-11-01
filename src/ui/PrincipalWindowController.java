package ui;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import model.Database;
import trie.Trie;

public class PrincipalWindowController {    
	
	public final String NAME = "Name";
	public final String LAST_NAME = "Last Name";
	public final String FULL_NAME = "Full name";
	public final String CODE = "Code";
	
	@FXML
	private TextField name;

	@FXML
	private TextField lastName;

	@FXML
	private TextField gender;

	@FXML
	private DatePicker birthdate;

	@FXML
	private TextField height;

	@FXML
	private TextField nationality;

	@FXML
	private ImageView photo;
	
	@FXML
	private GridPane container;

	@FXML
	private TextField auto;
	
	@FXML
	private Label matches;

	@FXML
	private TextField delete;

	@FXML
	private TextField modify;
	
	@FXML
	private ChoiceBox<String> searchOptions;	
	
	@FXML
    private ScrollPane scroll;

	private Trie trie;
	private Database database;

	@FXML
	public void initialize() {
		database = new Database();
		trie= new Trie();
		loadData();
		updateEmergenceList();
		searchOptions.setItems(FXCollections.observableArrayList(NAME, LAST_NAME, FULL_NAME, CODE));
	}
	
	void updateEmergenceList() {
		
		trie.insert("Edgar Allan Poe");
		trie.insert("James Barrie");
		trie.insert("Emily Bronte");
		trie.insert("Euripides");
		trie.insert("Ernest Hemingway");
		trie.insert("Arthur Conan Doyle");
		trie.insert("Lewis Carroll");
		trie.insert("JRR Tolkien");
		trie.insert("Elvira Sastre");
		trie.insert("Alejandra Pizarnik");
		
		auto.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				String name= auto.getText();
				VBox suggestions= new VBox();
				if (name.length()==0) {
					suggestions.getChildren().clear();
					scroll.setContent(suggestions);
				} else {
					List<String> data= trie.autocomplete(name);
					suggestions.getChildren().clear();
					matches.setText("("+data.size()+") results");
					for (int i = 0; i < 100; i++) {
						Label l= new Label(data.get(i));
						suggestions.getChildren().add(l);
						scroll.setContent(suggestions);
					}
				}
			}
		});
	}
	
	@FXML
	private void createPerson(ActionEvent event) {
		database.createPerson(generateCode(), name.getText(), lastName.getText(), gender.getText(), 
				birthdate.getValue(), Double.parseDouble(height.getText()), nationality.getText());
	}
	
	private String generateCode() {
		return null;
	}

	@FXML
	void deletePerson(ActionEvent event) {

	}

	@FXML
	void generateData(ActionEvent event) {

	}

	@FXML
	void modifyPerson(ActionEvent event) {

	}
	
	public void loadData() {
		try {
			FileInputStream fileIn = new FileInputStream("data\\database.txt");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			database = (Database) in.readObject();
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	public void saveData() {
		try {
			FileOutputStream fileOut = new FileOutputStream("data\\database.txt", false);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(database);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
