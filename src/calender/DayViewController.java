/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calender;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

/**
 * FXML Controller class
 *
 * @author calleskanshed
 */
public class DayViewController implements Initializable {

    int year;
    int month;
    int day;
    
    
    public void setTheActiveDate(int year,int month, int day){
        this.year = year;
        this.month = month;
        this.day = day;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> list = FXCollections.observableArrayList();
        list.add("asdasd");
        listView.setItems(list);
        list.add("JJJJ");
        listView.setStyle("-fx-font-size:18");
    }    
    
    
    
    @FXML
    private ListView listView;
    
}
