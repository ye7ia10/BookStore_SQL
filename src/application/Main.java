package application;
	
import client.BookStoreClient;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	
    private BookStoreClient model = new BookStoreClient();

	@Override
	public void start(Stage primaryStage) {
		try {
				
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("test.fxml"));
	        Parent root = (Parent) fxmlLoader.load();
	         testController controller = 
	            	  fxmlLoader.<testController>getController();
	            	  controller.initData(model);			
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	        Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
