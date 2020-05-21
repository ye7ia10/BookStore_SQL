package application;

import java.io.IOException;
import client.BookStoreClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.User;
import model.UserRespond;

public class testController {

	  

	    @FXML
	    private TextField userName;

	    @FXML
	    private TextField password;

	    @FXML
	    private Button login;

	    @FXML
	    private TextField userS;

	    @FXML
	    private TextField PassS;

	    @FXML
	    private TextField emailS;

	    @FXML
	    private TextField FirstS;

	    @FXML
	    private TextField LastS;

	    @FXML
	    private TextField addessS;

	    @FXML
	    private Button signUp;
	    @FXML
	    private TextField phoneS;
	    
	    BookStoreClient bookStoreClient;

	    
	    public void signup(ActionEvent actionEvent) {
	    	String username = userS.getText().toString();
	    	String passWord = PassS.getText().toString();
	    	String maill = emailS.getText().toString();
	    	String firsName = FirstS.getText().toString();
	    	String lasName = LastS.getText().toString();
	    	String addr = addessS.getText().toString();
	    	String phone = phoneS.getText().toString();

	    	if (username.length() == 0 || passWord.length() == 0 || maill.length() == 0 
	    			|| firsName.length() == 0 || lasName.length() == 0 || addr.length() == 0 ) {
	    		Alert a = new Alert(AlertType.NONE);  
	    		a.setAlertType(AlertType.ERROR); 
			    a.show();
			    a.setTitle("Error");
			    a.setHeaderText("Please Complete fields !!");
	    	} else {
	    		
	    		User user = new User(username, passWord, firsName, lasName, maill, phone, addr, 0);
	    		UserRespond respond = bookStoreClient.signUp(user);
	    		if (respond.isSuccess()) {
	    			Alert a = new Alert(AlertType.NONE);  
				    a.setAlertType(AlertType.INFORMATION); 
				    a.show();
				    a.setTitle("Done");
				    a.setHeaderText("Email made Successfully");
	    		}
	    	}
	    	
	    }
	    
	    public void login(ActionEvent actionEvent) throws IOException {
	    	String username = userName.getText().toString();
	    	String passWord = password.getText().toString();
	    	
	    	if (username.length() == 0 || passWord.length() == 0) {
	    		Alert a = new Alert(AlertType.NONE);  
	    		a.setAlertType(AlertType.ERROR); 
			    a.show();
			    a.setTitle("Error");
			    a.setHeaderText("Please Complete fields !!");
	    	} else {
	    		User user = new User(username, passWord, "", "", "", "", "", 0);
	    		UserRespond respond = bookStoreClient.login(user);
	    		user = respond.getUser();
	    		if (respond.isSuccess()) {
	    			if (user.getAdmin() == 1) {
	    				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("results.fxml"));
	    	            Parent root1 = (Parent) fxmlLoader.load();
	    	            resultsController controller = 
	    		            	  fxmlLoader.<resultsController>getController();
	    		            	  controller.initData(bookStoreClient);	
	    	            Stage stage = new Stage();
	    	            stage.setScene(new Scene(root1));  
	    	            stage.show();
	    			} else {
	    				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UserView.fxml"));
	    	            Parent root1 = (Parent) fxmlLoader.load();
	    	            UserViewController controller = 
	    		            	  fxmlLoader.<UserViewController>getController();
	    		            	  controller.initData(bookStoreClient);	
	    	            Stage stage = new Stage();
	    	            stage.setScene(new Scene(root1));  
	    	            stage.show();
	    			}
	    		} else {
	    			Alert a = new Alert(AlertType.NONE);  
		    		a.setAlertType(AlertType.ERROR); 
				    a.show();
				    a.setTitle("Error");
				    a.setHeaderText("You do not have an account !!");
	    		}
	    	}
	    	
	    }

		public void initData(BookStoreClient model) {
			// TODO Auto-generated method stub
			bookStoreClient = model;
		}
	
   
}
