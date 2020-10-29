package ui;

import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import trie.Trie;

public class PrincipalWindowController {
	private Trie trie;
	@FXML
    private TextField amountData;

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
    
    public PrincipalWindowController() {
	}
    
    @FXML
    public void initialize() {
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
