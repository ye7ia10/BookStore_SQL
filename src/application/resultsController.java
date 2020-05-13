package application;

import java.util.ArrayList;
import java.util.Arrays;
import client.BookStoreClient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.Book;
import model.BookRespond;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;

@SuppressWarnings("rawtypes")
public class resultsController {

	private BookStoreClient model;
    @FXML
    private TableView<Book> table;
    

    @FXML
    private TableColumn<?, ?> idT;

   
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
    private TableColumn TableT;

    @FXML
    private TableColumn QuantT;

    @FXML
    private TextField IdF;

    @FXML
    private TextField TitleF;

    @FXML
    private TextField PublisherF;

    @FXML
    private TextField YearF;

    @FXML
    private TextField PriceF;

    @FXML
    private TextField cateF;

    @FXML
    private Button add;

    @FXML
    private Button modify;

    @FXML
    private Button clear;

    @FXML
    private Button details;

    @FXML
    private Button orders;

    @FXML
    private TextField authers;

    @FXML
    private TextField thres;

    @FXML
    private TextField avaiable;
    
    
    private final ObservableList<Book> data =FXCollections.observableArrayList();
  
	@SuppressWarnings("unchecked")
	@FXML
    void initialize() {
    	idT.setCellValueFactory(new PropertyValueFactory("ISBN"));
    	titleT.setCellValueFactory(new PropertyValueFactory("title"));
    	pubT.setCellValueFactory(new PropertyValueFactory("publisher_name"));
    	yearT.setCellValueFactory(new PropertyValueFactory("publish_year"));
    	CatT.setCellValueFactory(new PropertyValueFactory("category"));
    	AuthT.setCellValueFactory(new PropertyValueFactory("authors"));
    	PriceT.setCellValueFactory(new PropertyValueFactory("sellingPrice"));
    	QuantT.setCellValueFactory(new PropertyValueFactory("available"));
    	TableT.setCellValueFactory(new PropertyValueFactory("minQuantity"));
    
    }
   
    
    
    public void pressButton(ActionEvent event) throws Exception {               
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("details.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            
            detailsController controller = 
            	    fxmlLoader.<detailsController>getController();
            	  controller.initData(model);
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));  
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public void addBook(ActionEvent actionEvent) {
    	
    	System.out.println("Add book Pressed");
    	String BookId = IdF.getText().toString();
    	String BookTitle = TitleF.getText().toString();
    	String BookPublisher = PublisherF.getText().toString();
    	String BookYear = YearF.getText().toString();
    	String BookPrice = PriceF.getText().toString();
    	String BookCategory = cateF.getText().toString();
    	String BookAuthor = authers.getText().toString();
    	String BookAvailable = avaiable.getText().toString();
    	String BookThresh = thres.getText().toString();
    	
    	if (BookId.length() == 0 || BookTitle.length() == 0 || BookPublisher.length() == 0
    		|| BookYear.length() == 0 || BookPrice.length() == 0 || BookCategory.length() == 0
    		|| BookAuthor.length() == 0 || BookAvailable.length() == 0 || BookThresh.length() == 0) {
        	Alert a = new Alert(AlertType.NONE);  
    		a.setAlertType(AlertType.ERROR); 
		    a.show();
		    a.setTitle("Error");
		    a.setHeaderText("Please Complete fields !!");
            
    	} else {
    		System.out.println("Adding New Book");
    		ArrayList<String> authors = new ArrayList<>(Arrays.asList(BookAuthor.split(",")));
    		Book book = new Book(Integer.parseInt(BookId), BookTitle, BookPublisher,  Integer.parseInt(BookYear) , 
    				Integer.parseInt(BookPrice) , BookCategory , authors,  
    				Integer.parseInt(BookThresh), Integer.parseInt(BookAvailable)); 
    		BookRespond respond = model.addBook(book);
    		if (respond.isSuccess()) {
    			table.getItems().add(book);
    			Alert a = new Alert(AlertType.NONE);  
			    a.setAlertType(AlertType.INFORMATION); 
			    a.show();
			    a.setTitle("Done");
			    a.setHeaderText("Book Added Successfully");
    		} else {
    			Alert a = new Alert(AlertType.NONE);  
        		a.setAlertType(AlertType.ERROR); 
    		    a.show();
    		    a.setTitle("Error");
    		    a.setHeaderText("Error Adding Book !!");
    		}
    	}

    }
    
    
    void initData (BookStoreClient model2) {
    	model = model2;
    	for (int i = 0; i < model2.getAllBooks().size() ; i++) {
    		data.add(model2.getAllBooks().get(i));
    	}
    	table.setItems(data);
    }
    
    public void pressButtonOrder(ActionEvent event) throws Exception {               
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Order.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));  
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public void getData() {

    	Book book = table.getSelectionModel().getSelectedItem();

    	IdF.setText(String.valueOf(book.getISBN()));
        TitleF.setText(book.getTitle());
    	PublisherF.setText(book.getPublisher_name());
    	YearF.setText(String.valueOf(book.getPublish_year()));
        PriceF.setText(String.valueOf(book.getSellingPrice()));
        cateF.setText(book.getCategory());
        String authoring = "";
        for (int i = 0; i < book.getAuthors().size() ; i++) {
        	authoring += book.getAuthors().get(i);
        	if (i + 1 != book.getAuthors().size()) {
        		authoring += ",";
        	}
        }
    	authers.setText(authoring);
    	avaiable.setText(String.valueOf(book.getAvailable()));
    	thres.setText(String.valueOf(book.getMinQuantity()));
    	
    }
    
    public void ClearFields(ActionEvent actionEvent) {
    	IdF.setText("");
        TitleF.setText("");
    	PublisherF.setText("");
    	YearF.setText("");
        PriceF.setText("");
        cateF.setText("");
    	authers.setText("");
    	avaiable.setText("");
    	thres.setText("");
    }
    
    
    public void modifyBook(ActionEvent event) {
    	String BookId = IdF.getText().toString();
    	String BookTitle = TitleF.getText().toString();
    	String BookPublisher = PublisherF.getText().toString();
    	String BookYear = YearF.getText().toString();
    	String BookPrice = PriceF.getText().toString();
    	String BookCategory = cateF.getText().toString();
    	String BookAuthor = authers.getText().toString();
    	String BookAvailable = avaiable.getText().toString();
    	String BookThresh = thres.getText().toString();
    	
    	if (BookId.length() == 0 || BookTitle.length() == 0 || BookPublisher.length() == 0
    		|| BookYear.length() == 0 || BookPrice.length() == 0 || BookCategory.length() == 0
    		|| BookAuthor.length() == 0 || BookAvailable.length() == 0 || BookThresh.length() == 0) {
        	Alert a = new Alert(AlertType.NONE);  
    		a.setAlertType(AlertType.ERROR); 
		    a.show();
		    a.setTitle("Error");
		    a.setHeaderText("Please Complete fields !!");
            
    	} else {
    		
	    	Book person = table.getSelectionModel().getSelectedItem();

    		ArrayList<String> authors = new ArrayList<>(Arrays.asList(BookAuthor.split(",")));
    		Book book = new Book(Integer.parseInt(BookId), BookTitle, BookPublisher,  Integer.parseInt(BookYear) , 
    				Integer.parseInt(BookPrice) , BookCategory , authors,  
    				Integer.parseInt(BookThresh), Integer.parseInt(BookAvailable)); 
    		BookRespond respond = model.updateBook(person, book);
    		if (respond.isSuccess()) {
    			table.refresh();
    			person.setAuthors(authors);
    			person.setAvailable(Integer.parseInt(BookAvailable));
    			person.setISBN(Integer.parseInt(BookId));
    			person.setMinQuantity(Integer.parseInt(BookThresh));
    			person.setPublish_year(Integer.parseInt(BookYear));
    			person.setTitle(BookTitle);
    			person.setCategory(BookCategory);
    			person.setSellingPrice(Integer.parseInt(BookPrice));
    			person.setPublisher_name(BookPublisher);
    			

    			
    			Alert a = new Alert(AlertType.NONE);  
			    a.setAlertType(AlertType.INFORMATION); 
			    a.show();
			    a.setTitle("Done");
			    a.setHeaderText("Book Updated Successfully");
    		} else {
    			Alert a = new Alert(AlertType.NONE);  
        		a.setAlertType(AlertType.ERROR); 
    		    a.show();
    		    a.setTitle("Error");
    		    a.setHeaderText("Error Adding Book !!");
    		}
    	}

    }
    
    
   
}
