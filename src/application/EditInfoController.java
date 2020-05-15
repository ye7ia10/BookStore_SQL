package application;

import client.BookStoreClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import model.User;
import model.UserRespond;

public class EditInfoController {

	
	BookStoreClient model;
    @FXML
    private Button update;

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
    private TextField phoneS;

    @FXML
    private TextField addessS;
    
    void initData(BookStoreClient model2) {
    	model = model2;
    	System.out.println(model.getUser().getEmail());
    	User user = model.getUser();
    	userS.setText(user.getUsername());
    	PassS.setText(user.getPassword());
    	FirstS.setText(user.getFname());
    	LastS.setText(user.getLname());
    	emailS.setText(user.getEmail());
    	phoneS.setText(user.getPhone());
    	addessS.setText(user.getAddress());
    }
    
    public void update(ActionEvent actionEvent) {
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
    		UserRespond respond = model.updateUser(user);
    		if (respond.isSuccess()) {
    			model.getUser().setUsername(username);
        		model.getUser().setPassword(passWord);
        		model.getUser().setFname(firsName);
        		model.getUser().setLname(lasName);
        		model.getUser().setEmail(maill);
        		model.getUser().setPhone(phone);
        		model.getUser().setAddress(addr);
        		initData(model);
    			Alert a = new Alert(AlertType.NONE);  
			    a.setAlertType(AlertType.INFORMATION); 
			    a.show();
			    a.setTitle("Done");
			    a.setHeaderText("Email made Successfully");
    		}
    	}
    	
    }
    
   

}
