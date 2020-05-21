package application;

import client.BookStoreClient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import model.Publisher;
import model.PublisherRespond;

public class detailsController {
	
		private BookStoreClient model;
	    @FXML
	    private Pane pane;

	    @FXML
	    private TableView<Publisher> tablePublisher;

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
	    
       
	    @FXML
	    private TableColumn<?, ?> pubT;

	    @FXML
	    private TableColumn<?, ?> phoneT;

	    @FXML
	    private TableColumn<?, ?> addressT;
	    @FXML
	    private TableColumn<?, ?> auId;

	    @FXML
	    private TableColumn<?, ?> auName;
	    @FXML
	    private TableColumn<?, ?> copIId;

	    @FXML
	    private TableColumn<?, ?> minCop;

	    @FXML
	    private TableColumn<?, ?> copQuan;
	    
	    @FXML
	    private TableColumn<?, ?> caatid;

	    @FXML
	    private TableColumn<?, ?> caatNamr;
	    
	    private final ObservableList<Publisher> data =FXCollections.observableArrayList();


	    /*** passing data injection ***/
		public void initData(BookStoreClient model2) {
			model = model2;
			for (int i = 0; i < model2.getAllPublishers().size() ; i++) {
				data.add(model2.getAllPublishers().get(i));
			}
			tablePublisher.setItems(data);
		}
	    
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		@FXML
	    void initialize() {
			pubT.setCellValueFactory(new PropertyValueFactory("name"));
	    	phoneT.setCellValueFactory(new PropertyValueFactory("phone"));
	    	addressT.setCellValueFactory(new PropertyValueFactory("address"));
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
	        	Publisher publisher = new Publisher(PublisherName, publisherAddress,  Integer.parseInt(PublisherPhone));
	        	PublisherRespond respond = model.addPublisher(publisher);
	        	if (respond.isSuccess()) {
	        	tablePublisher.getItems().add(publisher);
	        	Alert a = new Alert(AlertType.NONE);  
			    a.setAlertType(AlertType.CONFIRMATION); 
			    a.show();
			    a.setTitle("Done");
			    a.setHeaderText("Publisher Added Successfully");
	        	} else {
	        		Alert a = new Alert(AlertType.NONE);  
				    a.setAlertType(AlertType.ERROR); 
				    a.show();
				    a.setTitle("Error");
				    a.setHeaderText("Error Adding Publisher");
	        	}
	        }
	    }
	    
	    
	    public void modifyPublisher(ActionEvent actionEvent) {
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
		    	Publisher person = tablePublisher.getSelectionModel().getSelectedItem();
		        Publisher publisher = new Publisher(PublisherName, publisherAddress, Integer.parseInt(PublisherPhone));
		    	PublisherRespond respond = model.updatePublisher(person, publisher);
		    	if (respond.isSuccess()) {
		    		tablePublisher.refresh();
			    	person.setName(PublisherName);
			    	person.setPhone(Integer.parseInt(PublisherPhone));
			    	person.setAddress(publisherAddress);
		    		Alert a = new Alert(AlertType.NONE);  
				    a.setAlertType(AlertType.INFORMATION); 
				    a.show();
				    a.setTitle("Done");
				    a.setHeaderText("Publisher Modified Successfully");
		    	}
	        
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
	        	/*Publisher person = tablePublisher.getSelectionModel().getSelectedItem();
		    	tablePublisher.refresh();
		    	person.setName(PublisherName);
		    	person.setPhone(Integer.parseInt(PublisherPhone));
		    	person.setAddress(publisherAddress);*/
	        	Alert a = new Alert(AlertType.NONE);  
			    a.setAlertType(AlertType.INFORMATION); 
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
	    
	    
	    public void getData () {
	    	Publisher person = tablePublisher.getSelectionModel().getSelectedItem();
	    	Pname.setText(person.getName());
	    	Pphone.setText(String.valueOf(person.getPhone()));
	    	Paddress.setText(person.getAddress());
	    }
	    
	 
	    
	
}
