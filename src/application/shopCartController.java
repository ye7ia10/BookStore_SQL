package application;

import java.util.ArrayList;

import client.BookStoreClient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Book;
import model.CartItem;
import model.Respond;

public class shopCartController {
	private BookStoreClient model;
	private ArrayList<CartItem> list = new ArrayList<CartItem>();
    @FXML
    private TableView<CartItem> table;
    
	@FXML
    private TableColumn titleT;

    @FXML
    private TableColumn pubT;

    @FXML
    private TableColumn quantity;

    @FXML
    private TableColumn PriceT;
    @FXML
    private TableColumn yearT;

    @FXML
    private TableColumn total;
    
    @FXML
    private Button remove;
    
    @FXML
    private Button buy;
    
    private final ObservableList<CartItem> data =FXCollections.observableArrayList();

    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@FXML
    void initialize() {
    	titleT.setCellValueFactory(new PropertyValueFactory("Title"));
    	pubT.setCellValueFactory(new PropertyValueFactory("publisher"));
    	yearT.setCellValueFactory(new PropertyValueFactory("PublishYear"));
    	PriceT.setCellValueFactory(new PropertyValueFactory("price"));
    	quantity.setCellValueFactory(new PropertyValueFactory("quantity"));
    	total.setCellValueFactory(new PropertyValueFactory("totalPrice"));
    }

	public void initData(BookStoreClient model2, ArrayList<CartItem> items) {
		model = model2;
		list = items;
		for (int i = 0; i < items.size() ; i++) {
			data.add(list.get(i));
		}
		table.setItems(data);
	}
	
	public void delete(ActionEvent actionEvent) {
		CartItem cartItem = table.getSelectionModel().getSelectedItem();
		list.remove(cartItem);
		data.remove(cartItem);
		table.refresh();
	}
	
	public void buy (ActionEvent actionEvent) {
		Respond respond = model.buy(list);
		if (respond.isSuccess()) { /*** success ***/
			list.clear();
			data.clear();
			table.refresh();
			Alert a = new Alert(AlertType.NONE);  
		    a.setAlertType(AlertType.INFORMATION); 
		    a.show();
		    a.setTitle("Done");
		    a.setHeaderText("Done Successfully");
		}
	}
}
