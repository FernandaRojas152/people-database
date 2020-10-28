package ui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.Database;

public class PrincipalWindowController {

    private Database database;
    
    @FXML
    public void initialize() {
    	database = new Database();
    	loadData();
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
}
