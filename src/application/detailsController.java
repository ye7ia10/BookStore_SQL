package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.BookModel;

public class detailsController {
	
		private BookModel model;
	    @FXML
	    private Pane pane;

	    @FXML
	    private TableView<?> tablePublisher;

	    @FXML
	    private TextField Pname;

	    @FXML
	    private TextField Pphone;

	    @FXML
	    private TextField Paddress;

	    @FXML
	    private Button AddCop;

	    @FXML
	    private Button ModCop;

	    @FXML
	    private TableView<?> tableAuthor;

	    @FXML
	    private TextField authID;

	    @FXML
	    private TextField AuthName;

	    @FXML
	    private Button AddAuth;

	    @FXML
	    private Button ModAuth;

	    @FXML
	    private TableView<?> tableCopies;

	    @FXML
	    private TextField copId;

	    @FXML
	    private TextField CopTh;

	    @FXML
	    private TextField CopQ;

	    @FXML
	    private TableView<?> tableCat;

	    @FXML
	    private TextField CatId;

	    @FXML
	    private TextField CatName;

	    @FXML
	    private Button addCat;

	    @FXML
	    private Button ModCat;

	    @FXML
	    private Button addP;

	    @FXML
	    private Button ModP;
	    
	    
	    public void pressButton(ActionEvent event) throws Exception {  
	    	System.out.println("snsnsn");
	        String PublisherName = Pname.getText().toString();
	        String PublisherPhone = Pphone.getText().toString();
	        String publisherAddress = Paddress.getText().toString();
	        if (PublisherName == null || PublisherName.length() == 0 ||
	        	PublisherPhone == null || PublisherPhone.length() == 0 ||
	        	publisherAddress == null || PublisherPhone.length() == 0) {
	        	System.out.println("Please !! Complete fields");
	        } else {
	        	model.addPublisher(PublisherName, publisherAddress, Integer.parseInt(PublisherPhone));
	        }
	    }


		public void initData(BookModel model2) {
			model = model2;
		}
	    
	    
	    
	    
	 
	
}
