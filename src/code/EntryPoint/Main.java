package code.EntryPoint;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application{
	

	public static void main(String[] args) 
	{
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			Parent root=FXMLLoader.load(getClass().getResource("../../UI/Log In.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Log In");
			primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("../../UI/ABC.png")));
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
