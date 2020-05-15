package application;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


import client.BookStoreClient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Book;
import model.Order;
import model.OrderRespond;
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
	
	   @FXML
	    private TableColumn<?, ?> idd;

	    @FXML
	    private TableColumn<?, ?> quna;

	    @FXML
	    private TableColumn<?, ?> datet;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@FXML
	void initialize() {
		idd.setCellValueFactory(new PropertyValueFactory("ISBN"));
		quna.setCellValueFactory(new PropertyValueFactory("quantity"));
		datet.setCellValueFactory(new PropertyValueFactory("date"));
	}
	
    private final ObservableList<Order> data =FXCollections.observableArrayList();

	
	BookStoreClient model;
	@SuppressWarnings("unchecked")
	void initiate(BookStoreClient model2) {
		model = model2;
		for (int i = 0; i < model2.getAllOrders().size() ; i++) {
			data.add(model2.getAllOrders().get(i));
		}
		orders.setItems(data);
	}
	@SuppressWarnings("unchecked")
	public void addOrder(ActionEvent actionEvent) {
		String bId = id.getText().toString();
		String Q = quan.getText().toString();
		if (bId.length() == 0 || Q.length() == 0) {
			Alert a = new Alert(AlertType.NONE);  
    		a.setAlertType(AlertType.ERROR); 
		    a.show();
		    a.setTitle("Error");
		    a.setHeaderText("Please Complete fields !!");
		} else {
			// add func
			long millis=System.currentTimeMillis();  
	        java.sql.Date date=new java.sql.Date(millis);  
			Order order = new Order(Integer.parseInt(bId), Integer.parseInt(Q), date);
			OrderRespond orderRespond = model.addOrder(order);
			orders.getItems().add(order);

			System.out.println(orderRespond.isSuccess());
			if (orderRespond.isSuccess()) {
				Alert a = new Alert(AlertType.NONE);  
			    a.setAlertType(AlertType.CONFIRMATION); 
			    a.show();
			    a.setTitle("Done");
			    a.setHeaderText("Order Added Successfully");
			} else {
				Alert a = new Alert(AlertType.NONE);  
	    		a.setAlertType(AlertType.ERROR); 
			    a.show();
			    a.setTitle("Error");
			    a.setHeaderText("Error adding order !!");
			}
		}
	}
	
	public void Delete (ActionEvent actionEvent) {
	    	Order order = (Order) orders.getSelectionModel().getSelectedItem();
			OrderRespond orderRespond = model.approveOrder(order);
			System.out.println(orderRespond.isSuccess());
			if (orderRespond.isSuccess()) {
				orders.getItems().remove(order);
				Alert a = new Alert(AlertType.NONE);  
			    a.setAlertType(AlertType.CONFIRMATION); 
			    a.show();
			    a.setTitle("Done");
			    a.setHeaderText("Order removed Successfully");
			} else {
				Alert a = new Alert(AlertType.NONE);  
	    		a.setAlertType(AlertType.ERROR); 
			    a.show();
			    a.setTitle("Error");
			    a.setHeaderText("Error removing order !!");
			}
	}

}
