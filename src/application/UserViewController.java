package application;

import java.util.ArrayList;

import client.BookStoreClient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Book;

public class UserViewController {

	private BookStoreClient model;
    private final ObservableList<Book> data =FXCollections.observableArrayList();

    @FXML
    private TableView<Book> table;
    
	@FXML
    private TableColumn titleT;

    @FXML
    private TableColumn pubT;

    @FXML
    private TableColumn AuthT;

    @FXML
    private TableColumn PriceT;

    @FXML
    private TableColumn CatT;

    @FXML
    private TableColumn yearT;
    
    @FXML
    private TextField quan;

    @FXML
    private Button addToCart;

    @FXML
    private TextField searchCommand;

    @FXML
    private Button searchBtn;

    @FXML
    private MenuButton menu;

    @FXML
    private Button showCart;

    @FXML
    private Button editInfo;
    
	private String Selected;
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@FXML
    void initialize() {
    	titleT.setCellValueFactory(new PropertyValueFactory("title"));
    	pubT.setCellValueFactory(new PropertyValueFactory("publisher_name"));
    	yearT.setCellValueFactory(new PropertyValueFactory("publish_year"));
    	CatT.setCellValueFactory(new PropertyValueFactory("category"));
    	AuthT.setCellValueFactory(new PropertyValueFactory("authors"));
    	PriceT.setCellValueFactory(new PropertyValueFactory("sellingPrice"));
    	
        MenuItem menuItem2 = new MenuItem("Publisher");
        MenuItem menuItem3 = new MenuItem("Authers");
        MenuItem menuItem4 = new MenuItem("Title");
        MenuItem menuItem5 = new MenuItem("Publish Year");
        MenuItem menuItem7 = new MenuItem("Price");
        MenuItem menuItem8 = new MenuItem("Category");

        
        menuItem2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				menu.setText("Publisher");
				Selected = "Publisher";
			}
		});
        menuItem3.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				menu.setText("Authers");
				Selected = "Authers";
			}
		});
        menuItem4.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				menu.setText("Title");
				Selected = "Title";
			}
		});
        menuItem5.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				menu.setText("Publish Year");
				Selected = "Publish Year";
			}
		});
        menuItem7.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				menu.setText("Price");
				Selected = "Price";
			}
		});
        menuItem8.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				menu.setText("Category");
				Selected = "Category";
			}
		});
    	menu.getItems().addAll(menuItem2,menuItem3,menuItem4,menuItem5,menuItem7,menuItem8);
    }
    
    void initData (BookStoreClient model2) {
    	model = model2;
    	for (int i = 0; i < model2.getAllBooks().size() ; i++) {
    		data.add(model2.getAllBooks().get(i));
    	}
    	table.setItems(data);
    }
    
    public void AddToCart(ActionEvent actionEvent) {
    	
    	String quanString = quan.getText();
    	if (quanString.length() == 0) {
    		Alert a = new Alert(AlertType.NONE);  
    		a.setAlertType(AlertType.ERROR); 
		    a.show();
		    a.setTitle("Error");
		    a.setHeaderText("Please Complete fields !!");
    	} else {
    		
    		if (true) {
    			Alert a = new Alert(AlertType.NONE);  
			    a.setAlertType(AlertType.INFORMATION); 
			    a.show();
			    a.setTitle("Done");
			    a.setHeaderText("Book added Successfully");
    		}
    	}
    }
    
    public void EditInfo(ActionEvent actionEvent) {
    	try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EditInfo.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            EditInfoController controller = 
	            	  fxmlLoader.<EditInfoController>getController();
	            	  controller.initData(model);	
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));  
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public void PressSearch(ActionEvent actionEvent) {
 	   data.clear();
 	   if (Selected.equals("Title")) {
 		   ArrayList<Book> books = model.getBooksByTitle(searchCommand.getText());
 		   for (int i = 0; i <  books.size() ; i++) {
 			   data.add(books.get(i));
 		     }
 	   } else if (Selected.equals("Publisher")) {
 		   ArrayList<Book> books = model.getBooksByPublisherName(searchCommand.getText());
 		   for (int i = 0; i <  books.size() ; i++) {
 			   data.add(books.get(i));
 		     }
 	   } else if (Selected.equals("Category")) {
 		   ArrayList<Book> books = model.getBooksByCateggoryName(searchCommand.getText());
 		   for (int i = 0; i <  books.size() ; i++) {
 			   data.add(books.get(i));
 		     }
 	   } else if (Selected.equals("Authers")) {
 		   ArrayList<Book> books = model.getBooksByAuthorName(searchCommand.getText());
 		   for (int i = 0; i <  books.size() ; i++) {
 			   data.add(books.get(i));
 		     }
 	   }/* else if (Selected.equals("Publisher")) {
 		   ArrayList<Book> books = model.getBooksByTitle(searchCommand.getText());
 		   for (int i = 0; i <  books.size() ; i++) {
 			   data.add(books.get(i));
 		     }
 	   } else if (Selected.equals("Publisher")) {
 		   ArrayList<Book> books = model.getBooksByTitle(searchCommand.getText());
 		   for (int i = 0; i <  books.size() ; i++) {
 			   data.add(books.get(i));
 		     }
 	   } else if (Selected.equals("Publisher")) {
 		   ArrayList<Book> books = model.getBooksByTitle(searchCommand.getText());
 		   for (int i = 0; i <  books.size() ; i++) {
 			   data.add(books.get(i));
 		     }
 	   } else if (Selected.equals("Publisher")) {
 		   ArrayList<Book> books = model.getBooksByTitle(searchCommand.getText());
 		   for (int i = 0; i <  books.size() ; i++) {
 			   data.add(books.get(i));
 		     }
 	   } */
 	   table.setItems(data);
    }
    
    
    

   

}
