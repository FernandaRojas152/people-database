package ui;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application{

	private PrincipalWindowController principal;

	@Override
	public void start(Stage primaryStage) throws Exception{
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PrincipalWindow.fxml"));
			Parent root = fxmlLoader.load();
			setPrincipal(fxmlLoader.getController());
			Scene scene= new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("database.png")));
			primaryStage.setTitle("database");
			primaryStage.setResizable(false);
			primaryStage.show();
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

				@Override
				public void handle(WindowEvent arg0) {
				}
			});
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	public PrincipalWindowController getPrincipal() {
		return principal;
	}

	public void setPrincipal(PrincipalWindowController principal) {
		this.principal = principal;
	}

}
