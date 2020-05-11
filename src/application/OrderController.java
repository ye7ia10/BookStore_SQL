package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableView;

public class OrderController {
	@FXML
	private TableView orders;
	@FXML
	private TextField id;
	@FXML
	private TextField quan;
	@FXML
	private TextField date;
	@FXML
	private Button add;
	@FXML
	private Button delete;
	
	public void addOrder(ActionEvent actionEvent) {
		String bId = id.getText().toString();
		String Q = quan.getText().toString();
		String D = date.getText().toString();
		if (bId.length() == 0 || Q.length() == 0 || D.length() == 0) {
			Alert a = new Alert(AlertType.NONE);  
    		a.setAlertType(AlertType.ERROR); 
		    a.show();
		    a.setTitle("Error");
		    a.setHeaderText("Please Complete fields !!");
		} else {
			// add func
			if (true) {
				Alert a = new Alert(AlertType.NONE);  
			    a.setAlertType(AlertType.CONFIRMATION); 
			    a.show();
			    a.setTitle("Done");
			    a.setHeaderText("Order Added Successfully");
			}
		}
	}
	
	public void Delete (ActionEvent actionEvent) {
		
	}

}
