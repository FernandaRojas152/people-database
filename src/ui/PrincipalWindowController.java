package ui;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import javax.imageio.ImageIO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import model.Database;
import model.Person;
import trie.Trie;

public class PrincipalWindowController {    

	public final String NAME = "Name";
	public final String LAST_NAME = "Last Name";
	public final String FULL_NAME = "Full name";
	public final String CODE = "Code";
	public final String MALE = "Male";
	public final String FEMALE = "Female";

	@FXML
	private TabPane tabPane;

	@FXML
	private TextField name;

	@FXML
	private TextField lastName;

	@FXML
	private ChoiceBox<String> genders;	

	@FXML
	private DatePicker birthdate;

	@FXML
	private TextField height;

	@FXML
	private TextField nationality;

	@FXML
	private ImageView photo;

	@FXML
	private ChoiceBox<String> searchOptions;

	@FXML
	private TextField auto;

	@FXML
	private Label matches;

	@FXML
	private ScrollPane scroll;

	@FXML
	private Tab tabSearch;

	@FXML
	private Tab tabModify;

	@FXML
	private TextField modifyName;

	@FXML
	private TextField modifyLastName;

	@FXML
	private Label code;

	@FXML
	private ChoiceBox<String> modifyGenders;

	@FXML
	private DatePicker modifyBirthdate;

	@FXML
	private TextField modifyHeight;

	@FXML
	private TextField modifyNationality;

	@FXML
	private ImageView modifyphoto;
	
	@FXML
    private ProgressBar progress;

	private Database database;
	private Trie trie;

	@FXML
	public void initialize() throws Exception {
		database = new Database();
		tabModify.setDisable(true);
		loadData();
		updateEmergenceList();
		try {
			final String urlStr = "https://thispersondoesnotexist.com/image";
			final URL url = new URL(urlStr);
			final HttpURLConnection connection = (HttpURLConnection) url
			        .openConnection();
			connection.setRequestProperty(
			    "User-Agent",
			    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
			BufferedImage bufferedImage = ImageIO.read(connection.getInputStream());
//			BufferedImage bufferedImage = ImageIO.read(new URL("https://thispersondoesnotexist.com/image"));
			Image image = SwingFXUtils.toFXImage(bufferedImage, null);
			photo.setImage(image);
		} catch (IOException e) {
			e.printStackTrace();
		}	
		searchOptions.setItems(FXCollections.observableArrayList(NAME, LAST_NAME, FULL_NAME, CODE));
		genders.setItems(FXCollections.observableArrayList(MALE, FEMALE));
		modifyGenders.setItems(FXCollections.observableArrayList(MALE, FEMALE));
	}

	private void updateEmergenceList() {

		matches.setText(null);
		searchOptions.setValue(null);
		trie = new Trie();

		searchOptions.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number newValue) {

				auto.setText(null);
				scroll.setContent(null);
				try {
					if((int)newValue==0) {
						trie = new Trie();
						for (Person person : database.getPersonsByName()) {
							trie.insert(person.getName());
						}
					}else if((int)newValue==1) {
						trie = new Trie();
						for (Person person : database.getPersonsByLastName()) {
							trie.insert(person.getLastName());
						}
					}else if((int)newValue==2) {
						trie = new Trie();
						for (Person person : database.getPersonsByFullName()) {
							trie.insert(person.getName()+" "+person.getLastName());
						}
					}else if((int)newValue==3) {
						trie = new Trie();
						for (Person person : database.getPersonsByCode()) {
							trie.insert(person.getCode());
						}
					}
				}catch (NullPointerException e) {
				}
			}
		});

		auto.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

				String entry = auto.getText();
				GridPane gridPane = new GridPane();

				if (entry.length()==0) {
					gridPane.getChildren().clear();
					scroll.setContent(gridPane);
				} else {
					List<String> data= trie.autocomplete(entry);
					gridPane.getChildren().clear();
					scroll.setContent(gridPane);
					matches.setText("("+data.size()+") results");

					if(data.size()<=100) {
						for (int i = 0; i < data.size(); i++) {
							Label label = new Label(data.get(i));
							gridPane.add(label, 1, i);

							if(data.size()<=20) {
								Button edit = new Button("edit");
								gridPane.add(edit, 2, i);
								edit.setOnAction(new EventHandler<ActionEvent>() {

									@Override
									public void handle(ActionEvent arg0) {
										auto.setText(null);
										gridPane.getChildren().clear();
										scroll.setContent(gridPane);
										matches.setText(null);
										tabPane.getSelectionModel().select(tabModify);
										tabModify.setDisable(false);
										searchPerson(label.getText());
									}
								});
							}
							scroll.setContent(gridPane);
						}
					}
				}
			}
		});
	}

	private void searchPerson(String data) {

		Person person;

		if(searchOptions.getValue().equals(NAME)) {
			person = database.searchByName(data);
		}else if(searchOptions.getValue().equals(LAST_NAME)) {
			person = database.searchByLastName(data);
		}else if(searchOptions.getValue().equals(FULL_NAME)) {
			person = database.searchByFullName(data);
		}else {
			person = database.searchByCode(data);
		}

		modifyName.setText(person.getName());
		modifyLastName.setText(person.getLastName());
		code.setText(person.getCode());
		modifyGenders.setValue(person.getGender());
		modifyBirthdate.setValue(person.getBirthDate());
		modifyHeight.setText(person.getHeight()+"");
		modifyNationality.setText(person.getNationality());

		try {
			BufferedImage bufferedImage = ImageIO.read(new URL("https://thispersondoesnotexist.com/image"));
			Image image = SwingFXUtils.toFXImage(bufferedImage, null);
			modifyphoto.setImage(image);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void createPerson(ActionEvent event) {
		try {
			database.createPerson(name.getText(), lastName.getText(), genders.getValue(), 
					birthdate.getValue(), Double.parseDouble(height.getText()), nationality.getText());
			name.setText(null);
			lastName.setText(null);
			genders.setValue(null);
			birthdate.setValue(null);
			height.setText(null);
			nationality.setText(null);
			BufferedImage bufferedImage = ImageIO.read(new URL("https://thispersondoesnotexist.com/image"));
			Image image = SwingFXUtils.toFXImage(bufferedImage, null);
			photo.setImage(image);
		} catch (NullPointerException e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText("Invalid Entry");
			alert.setContentText("Entries cannot be null.");
			alert.show();
			e.printStackTrace();
		} catch (NumberFormatException e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText("Invalid Entry");
			alert.setContentText("Height must be a number.");
			alert.show();
		} catch (IllegalArgumentException e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText("Invalid Entry");
			alert.setContentText(e.getMessage());
			alert.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		updateEmergenceList();
	}

	@FXML
	public void modifyPerson(ActionEvent event) {
		database.updatePerson(code.getText(), modifyName.getText(), modifyLastName.getText(), modifyGenders.getValue(),
				modifyBirthdate.getValue(), Double.parseDouble(modifyHeight.getText()), modifyNationality.getText());
		tabModify.setDisable(true);
		tabPane.getSelectionModel().select(tabSearch);
		updateEmergenceList();
	}

	@FXML
	public void deletePerson(ActionEvent event) {
		database.deletePerson(modifyName.getText(), modifyLastName.getText(), code.getText());
		tabModify.setDisable(true);
		tabPane.getSelectionModel().select(tabSearch);
		updateEmergenceList();
	}

	@FXML
	public void generateData(ActionEvent event) {
		progress.setVisible(true);
		Task<Void> task = new Task<Void>()
		{
			@Override
			public Void call()
			{
				try
				{
					loadData();
					return null;
				}
				catch (Exception ex)
				{
					ex.printStackTrace();
				}
				return null;
			}
		};
		progress.progressProperty().bind(task.progressProperty());
		task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {

			@Override
			public void handle(WorkerStateEvent arg0) {
				System.out.println("Finish");
				//progress.setVisible(false);
			}
		});
		Thread loadingThread = new Thread(task);
		loadingThread.start();
	}

	public void loadData() {
		try {
			FileInputStream fileIn = new FileInputStream("data\\database.txt");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			database = (Database) in.readObject();
			System.out.println(database.getPersonsByName().get(0).getName());
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
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
			e.printStackTrace();
		}
	}
}
