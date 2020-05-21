package application;

import java.util.ArrayList;
import java.util.Arrays;
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
    
    @FXML
    private MenuButton menus;
    @FXML
    private TextField searchCommand;
    @FXML
    private Button btnSearch;
    @FXML
    private Button sales;

    @FXML
    private Button topsales;

    @FXML
    private Button topCust;
    
    private String Selected ;
    
    
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
    	
    	MenuItem menuItem1 = new MenuItem("ISBN");
        MenuItem menuItem2 = new MenuItem("Publisher");
        MenuItem menuItem3 = new MenuItem("Authers");
        MenuItem menuItem4 = new MenuItem("Title");
        MenuItem menuItem5 = new MenuItem("Publish Year");
        MenuItem menuItem6 = new MenuItem("Threshold");
        MenuItem menuItem7 = new MenuItem("Price");
        MenuItem menuItem8 = new MenuItem("Category");
        MenuItem menuItem9 = new MenuItem("Available");

        menuItem1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				menus.setText("ISBN");
				Selected = "ISBN";
			}
		});
        menuItem2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				menus.setText("Publisher");
				Selected = "Publisher";
			}
		});
        menuItem3.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				menus.setText("Authers");
				Selected = "Authers";
			}
		});
        menuItem4.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				menus.setText("Title");
				Selected = "Title";
			}
		});
        menuItem5.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				menus.setText("Publish Year");
				Selected = "Publish Year";
			}
		});
        menuItem6.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				menus.setText("Threshold");
				Selected = "Threshold";
			}
		});
        menuItem7.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				menus.setText("Price");
				Selected = "Price";
			}
		});
        menuItem8.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				menus.setText("Category");
				Selected = "Category";
			}
		});
       menuItem9.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				menus.setText("Available");
				Selected = "Available";
			}
		}); 
    	menus.getItems().addAll(menuItem1,menuItem2,menuItem3,menuItem4,menuItem5,menuItem6,menuItem7,menuItem8,menuItem9);
    }
   
    
   public void PressSearch(ActionEvent actionEvent) {
	   data.clear();
	   if (Selected.equals("ISBN")) {
	   ArrayList<Book> books = model.getBooksByISBN(Integer.parseInt(searchCommand.getText()));
	   System.out.println(model.getBooksByISBN(Integer.parseInt(searchCommand.getText())).size());
	   for (int i = 0; i <  books.size() ; i++) {
		   data.add(books.get(i));
	     }
	   } else if (Selected.equals("Title")) {
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
    
    public void refresh(ActionEvent actionEvent) {
		initData(model);
	}
    void initData (BookStoreClient model2) {
    	model = model2;
    	data.clear();
    	for (int i = 0; i < model2.getAllBooks().size() ; i++) {
    		data.add(model2.getAllBooks().get(i));
    	}
    	table.setItems(data);
    }
    
    public void pressButtonOrder(ActionEvent event) throws Exception {               
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Order.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            OrderController controller = 
	            	  fxmlLoader.<OrderController>getController();
	            	  controller.initiate(model);	
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
    
    public void showUsers(ActionEvent actionEvent) {
    	try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("users.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            usersController controller = 
	            	  fxmlLoader.<usersController>getController();
	            	  controller.initData(model);	
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));  
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public void ShowBooksSelling(ActionEvent actionEvent) {
    	model.ShowBooksSelling();
    }
    
    public void ShowToPBooksSelling(ActionEvent actionEvent) {
    	
    }
    
    public void ShowTopFiveCustomer(ActionEvent actionEvent) {
    	
    }

   
}
