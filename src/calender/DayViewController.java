/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calender;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author calleskanshed
 */
public class DayViewController implements Initializable {
    int year;
    int month;
    int day;
    private Stage stage;
    ObservableList<String> list;
    
    @FXML
    Button newActivityButton = new Button();
    
    public void changeWindow() throws IOException {
        stage = (Stage) newActivityButton.getScene().getWindow();
        stage.close();
        stage = new Stage();
        AnchorPane root = FXMLLoader.load(Main.class.getResource("NewActivity.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void setTheActiveDate(int year,int month, int day){
        this.year = year;
        this.month = month;
        this.day = day;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        list = FXCollections.observableArrayList();
        listView.setItems(list);
        listView.setStyle("-fx-font-size:18");
    }    

    @FXML
    private ListView listView;
    
}
