package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
 
public class TableViewSample extends Application {
 
    final HBox hb = new HBox();

    @SuppressWarnings("rawtypes")
	private TableView table = new TableView();
    public static void main(String[] args) {
        launch(args);
    }
 
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
    public void start(Stage stage) {
        Scene scene = new Scene(new Group());
        stage.setTitle("Admenstration");
        stage.setWidth(450);
        stage.setHeight(450);
 
        final Label label = new Label("Book Results");
        label.setFont(new Font("Arial", 20));
        label.setCenterShape(true);
 
        table.setEditable(false);
        
        TableColumn Id = new TableColumn("ID");
        TableColumn Title = new TableColumn("Title");
        TableColumn Publisher = new TableColumn("Publisher");
        TableColumn Year = new TableColumn("Year");
        TableColumn Price = new TableColumn("Price");
        TableColumn Catagory = new TableColumn("Category");
        
        table.getColumns().addAll(Id, Title, Publisher, Year, Price, Catagory);
        
        final TextField IDf = new TextField();
        IDf.setPromptText("Id");
        final TextField  TitleF = new TextField();
        TitleF.setPromptText("Title");
        final TextField PublisherF = new TextField();
        PublisherF.setPromptText("Publisher");
        final TextField YearF = new TextField();
        YearF.setPromptText("Year");
        final TextField  CatF = new TextField();
        CatF.setPromptText("Category");
        final TextField PriceF = new TextField();
        PriceF.setPromptText("Price");
        
        hb.getChildren().addAll(IDf, TitleF, PublisherF);
        hb.setSpacing(5);
        
 
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().add(label);
        vbox.getChildren().add(table); 
        vbox.getChildren().add(hb);
        
        

 
        ((Group) scene.getRoot()).getChildren().addAll(vbox);
        

        stage.setScene(scene);
        stage.show();
    }
}
