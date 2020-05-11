package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
	    
       


	    /*** passing data injection ***/
		public void initData(BookModel model2) {
			model = model2;
		}
	    
	
					  
	    
	    public void pressButton(ActionEvent event) throws Exception {  
	        String PublisherName = Pname.getText().toString();
	        String PublisherPhone = Pphone.getText().toString();
	        String publisherAddress = Paddress.getText().toString();
	        if (PublisherName == null || PublisherName.length() == 0 ||
	        	PublisherPhone == null || PublisherPhone.length() == 0 ||
	        	publisherAddress == null || publisherAddress.length() == 0) {
	        	System.out.println("Please !! Complete fields");
	        	Alert a = new Alert(AlertType.NONE);  
			    a.setAlertType(AlertType.ERROR); 
			    a.show();
			    a.setTitle("Error");
			    a.setHeaderText("Please Complete fields !!");
	            
	        } else {
	        	model.addPublisher(PublisherName, publisherAddress, Integer.parseInt(PublisherPhone));
	        	Alert a = new Alert(AlertType.NONE);  
			    a.setAlertType(AlertType.CONFIRMATION); 
			    a.show();
			    a.setTitle("Done");
			    a.setHeaderText("Publisher Added Successfully");
	        }
	    }
	    
	    
	    public void modifyPublisher() {
	    	String PublisherName = Pname.getText().toString();
	        String PublisherPhone = Pphone.getText().toString();
	        String publisherAddress = Paddress.getText().toString();
	        if (PublisherName == null || PublisherName.length() == 0 ||
	        	PublisherPhone == null || PublisherPhone.length() == 0 ||
	        	publisherAddress == null || publisherAddress.length() == 0) {
	        	Alert a = new Alert(AlertType.NONE);  
			    a.setAlertType(AlertType.ERROR); 
			    a.show();
			    a.setTitle("Error");
			    a.setHeaderText("Please Complete fields !!");
	            
	        } else {
	        	//call the function here
	        	Alert a = new Alert(AlertType.NONE);  
			    a.setAlertType(AlertType.CONFIRMATION); 
			    a.show();
			    a.setTitle("Done");
			    a.setHeaderText("Publisher Modified Successfully");
	        }
	    	
	    }
	    
	    public void modifyAuthor() {
	    	String PublisherName = AuthName.getText().toString();
	        String PublisherPhone = authID.getText().toString();
	        
	        if (PublisherName == null || PublisherName.length() == 0 ||
	        	PublisherPhone == null || PublisherPhone.length() == 0 ) {
	        	Alert a = new Alert(AlertType.NONE);  
			    a.setAlertType(AlertType.ERROR); 
			    a.show();
			    a.setTitle("Error");
			    a.setHeaderText("Please Complete fields !!");
	            
	        } else {
	        	//call the function here
	        	Alert a = new Alert(AlertType.NONE);  
			    a.setAlertType(AlertType.CONFIRMATION); 
			    a.show();
			    a.setTitle("Done");
			    a.setHeaderText("Author Modified Successfully");
	        }
	    	
	    }
	    
	    
	    public void modifyBookCopies() {
	    	String Quant = CopQ.getText().toString();
	        String threshold = CopTh.getText().toString();
	        String copI = copId.getText().toString();
	        if (Quant == null || Quant.length() == 0 ||
	        	threshold == null || threshold.length() == 0 ||
	        	copI == null || copI.length() == 0) {
	        	Alert a = new Alert(AlertType.NONE);  
			    a.setAlertType(AlertType.ERROR); 
			    a.show();
			    a.setTitle("Error");
			    a.setHeaderText("Please Complete fields !!");
	            
	        } else {
	        	//call the function here
	        	Alert a = new Alert(AlertType.NONE);  
			    a.setAlertType(AlertType.CONFIRMATION); 
			    a.show();
			    a.setTitle("Done");
			    a.setHeaderText("Copies Modified Successfully");
	        }
	    	
	    }
	    
	    
	    public void modifyCategory() {
	    	String cateString = CatName.getText().toString();
	        String cateID = CatId.getText().toString();
	      
	        if (cateString == null || cateString.length() == 0 ||
	        		cateID == null || cateID.length() == 0) {
	        	Alert a = new Alert(AlertType.NONE);  
			    a.setAlertType(AlertType.ERROR); 
			    a.show();
			    a.setTitle("Error");
			    a.setHeaderText("Please Complete fields !!");
	            
	        } else {
	        	//call the function here
	        	Alert a = new Alert(AlertType.NONE);  
			    a.setAlertType(AlertType.CONFIRMATION); 
			    a.show();
			    a.setTitle("Done");
			    a.setHeaderText("Category Modified Successfully");
	        }
	    	
	    }
	    
	    
	    
	    
	  
	    
	   

	    
	    
	    
	 
	
}
