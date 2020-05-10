package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.TableView;

public class resultsController {

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
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));  
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
