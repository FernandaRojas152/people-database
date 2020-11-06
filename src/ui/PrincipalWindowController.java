package ui;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

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
	public static final String PATH = "data/data.txt";

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

	@FXML
	private Label time;

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
				matches.setText(null);

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
				scroll.setVisible(true);
				String entry = auto.getText();
				GridPane gridPane = new GridPane();
				if (entry.length()==0) {
					gridPane.getChildren().clear();
					scroll.setContent(gridPane);
					scroll.setVisible(false);
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
			final String urlStr = "https://thispersondoesnotexist.com/image";
			final URL url = new URL(urlStr);
			final HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setRequestProperty(
					"User-Agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
			BufferedImage bufferedImage = ImageIO.read(connection.getInputStream());
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
			final String urlStr= "https://thispersondoesnotexist.com/image";
			final URL url = new URL(urlStr);
			final HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setRequestProperty(
					"User-Agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
			BufferedImage bufferedImage = ImageIO.read(connection.getInputStream());
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

	public void loadInfo() throws IOException{
		File myFile = new File(PATH);
		FileReader fr= new FileReader(myFile);
		BufferedReader br= new BufferedReader(fr);
		String line= br.readLine();
		while(line!=null) {
			String[]data=line.split(",");
			database.createPerson(data[0], data[1], data[2],
					data[3], LocalDate.parse(data[4]), Double.parseDouble(data[5]), data[6]);
			line= br.readLine();
		}
		br.close();
		fr.close();
	}

	@FXML
	public void generateData(ActionEvent event) {
		time.setText(null);
		double timePassed = System.currentTimeMillis();

		Task<Void> task = new Task<Void>() {
			@Override
			public Void call() throws ClassNotFoundException, IOException {
				progress.setVisible(true);
				try {

					for (int i = 0; i < 100000; i++) {
						System.out.println(generateBirthDate().toString());
					}
					

				}
				catch (Exception e) {
					e.printStackTrace();
				}
				progress.setMaxHeight(System.currentTimeMillis()-timePassed);
				return null;
			}
		};
		progress.progressProperty().bind(task.progressProperty());
		task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {

			@Override
			public void handle(WorkerStateEvent arg0) {
				progress.setVisible(false);
				time.setText(progress.getMaxHeight()/1000+" sec");
			}
		});
		Thread loadingThread = new Thread(task);
		loadingThread.start();
	}
	
	private LocalDate generateBirthDate() throws Exception {
		
		Random r = new Random();
		double randomValue = 99.99*r.nextDouble();
		int age = 0;
		if(randomValue > 0 && randomValue <= 18.62) 
			age = (int) (14*r.nextDouble()); 
		else if(randomValue > 18.62 && randomValue <= 31.74) 
			age = (int) (15+(24-15)*r.nextDouble());	
		else if(randomValue > 31.74 && randomValue <= 71.03) 
			age = (int) (25+(54-25)*r.nextDouble());
		else if(randomValue > 73.03 && randomValue <= 83.87) 
			age = (int) (55+(64-55)*r.nextDouble());	
		else if(randomValue > 83.87 && randomValue <= 99.99) 
			age = (int) (65+(90-65)*r.nextDouble()); 
		
		LocalDate localDate = LocalDate.now().minusYears(age);
		localDate = localDate.minusDays((long) ThreadLocalRandom.current().nextDouble(1, 31));
		localDate = localDate.minusMonths((long) ThreadLocalRandom.current().nextDouble(1, 12));
		return localDate;
	}
	
	public void loadData() {
		try {
			FileInputStream fileIn = new FileInputStream("data\\database.txt");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			database = (Database) in.readObject();
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
