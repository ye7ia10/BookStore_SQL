package application;

import java.util.ArrayList;
import java.util.Arrays;

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
import model.BookStore;
import javafx.scene.control.TableView;

public class resultsController {

	private BookStore model = new BookStore();
    @FXML
    private TableView<?> table;

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
    		model.addBook(Integer.parseInt(BookId), BookTitle, authors, BookPublisher, 
    				Integer.parseInt(BookThresh), Integer.parseInt(BookYear),
    				Integer.parseInt(BookPrice), BookCategory, Integer.parseInt(BookAvailable));
    		if (true) {
    			Alert a = new Alert(AlertType.NONE);  
			    a.setAlertType(AlertType.CONFIRMATION); 
			    a.show();
			    a.setTitle("Done");
			    a.setHeaderText("Book Added Successfully");
    		}
    	}

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
}
