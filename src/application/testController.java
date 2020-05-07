package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class testController {

	
    @FXML
    private Button btn1;
    
    @FXML
    private Button search;

    @FXML
    private Button add;

    @FXML
    private Button modify;

    @FXML
    private Button result;

    @FXML
    private Button showOrders;

    

    @FXML
    private void handleButtonAction(ActionEvent event) {
        // Button was clicked, do something...
      try {
		pressButton(event);
      } catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
    }
    
    public void pressButton(ActionEvent event) throws Exception {               
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("results.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));  
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
