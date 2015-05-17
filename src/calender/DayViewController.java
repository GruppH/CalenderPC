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
    SavedData sd = new SavedData();
    int year;
    int month;
    int day;
    String yearString;
    String monthString;
    String dayString;
    private Stage stage;
    
    @FXML
    Button newActivityButton = new Button();
    
    /*public void newActivity() throws IOException {
        openNewActivity();
    }*/
    
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
        yearString = Integer.toString(year);
        monthString = Integer.toString(month);
        dayString = Integer.toString(day);
        System.out.println(yearString+monthString+dayString);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> list = FXCollections.observableArrayList();
        sd.save(yearString+monthString+dayString, "bejehej");
       
        list.add(sd.getData(yearString + monthString + dayString));

        listView.setItems(list);
        
        //list.add("JJJJ");
        listView.setStyle("-fx-font-size:18");
    }    
    
    
    
    @FXML
    private ListView listView;
    
}
