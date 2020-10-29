package ui;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.Database;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import trie.Trie;

public class PrincipalWindowController {
    
    @FXML
    private TextField name;

    @FXML
    private TextField lastName;

    @FXML
    private TextField gender;

    @FXML
    private TextField birthdate;

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
    private VBox suggestions;

    @FXML
    private TextField delete;

    @FXML
    private TextField modify;
    
	private Trie trie;
    private Database database;
    
    @FXML
    public void initialize() {
    	database = new Database();
    	loadData();
    	trie= new Trie();
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
		auto= new TextField();
		String name= auto.getText();
		List<String> data= trie.autocomplete(name);
		suggestions= new VBox();
		for (int i = 0; i < data.size(); i++) {
			System.out.println(data.get(i));
			Label l= new Label(data.get(i));
			suggestions.getChildren().add(l);
		}
    }

    public void loadData() {
    	try {
    		FileInputStream fileIn = new FileInputStream("resources\\database.txt");
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
    
	public void saveData() {
		try {
			FileOutputStream fileOut = new FileOutputStream("resources\\database.txt", false);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(database);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
    @FXML
    void createPerson(ActionEvent event) {

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
    
    @FXML
    void saveData(ActionEvent event) {

    }

}
