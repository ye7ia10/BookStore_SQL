package application;

import client.BookStoreClient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Book;
import model.User;
import model.UserRespond;

public class usersController {
	
    @FXML
    private TableView<User> users;
	@FXML
	private TableColumn UserNmae;
	@FXML
	private TableColumn PassWord;

    @FXML
    private TableColumn<?, ?> firstq;

    @FXML
    private TableColumn<?, ?> Lastq;
	@FXML
	private TableColumn mail;
	@FXML
	private TableColumn phone;
	@FXML
	private TableColumn address;
	@FXML
	private Button upgrade;
	
    private final ObservableList<User> data =FXCollections.observableArrayList();
    private BookStoreClient model;

    
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@FXML
	void initialize() {
		
		UserNmae.setCellValueFactory(new PropertyValueFactory("username"));
		PassWord.setCellValueFactory(new PropertyValueFactory("password"));
		firstq.setCellValueFactory(new PropertyValueFactory("fname"));
		Lastq.setCellValueFactory(new PropertyValueFactory("lname"));
		mail.setCellValueFactory(new PropertyValueFactory("email"));
		phone.setCellValueFactory(new PropertyValueFactory("phone"));
		address.setCellValueFactory(new PropertyValueFactory("address"));
		
    	
	}
	
	void initData(BookStoreClient model) {
		this.model = model;
		for (int i = 0; i < model.getAllUsers().size() ; i++) {
			
			data.add(model.getAllUsers().get(i));
			System.out.println(model.getAllUsers().get(i).getFname()   + "   contr");
		}
		users.setItems(data);
	}
	
	public void upgrade() {
		User user = users.getSelectionModel().getSelectedItem();
		UserRespond respond = model.upgradeUser(user);
		if (respond.isSuccess()) {
			Alert a = new Alert(AlertType.NONE);  
		    a.setAlertType(AlertType.INFORMATION); 
		    a.show();
		    a.setTitle("Done");
		    a.setHeaderText("User Upgraded Successfully");
		} else {
			Alert a = new Alert(AlertType.NONE);  
    		a.setAlertType(AlertType.ERROR); 
		    a.show();
		    a.setTitle("Error");
		    a.setHeaderText("Error Updating user !!");
			}
		}
	

}
