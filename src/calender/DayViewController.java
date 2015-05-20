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
public class DayViewController extends MainWindowController implements Initializable {
    private int year;
    private int month;
    private int day;
    private Stage stage;
    ObservableList<String> list;
    NewActivityController nac;
    private Connect connect;
    private String username;
    
    @FXML
    private ListView listView;
    @FXML
    Button newActivityButton = new Button();
    
    public void changeWindow() throws IOException {
        stage = (Stage) newActivityButton.getScene().getWindow();
            stage.close();
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("NewActivity.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        nac = fxmlLoader.<NewActivityController>getController();
        nac.setTheActiveDate(year, month, day);
        nac.setUsername(getUsername());
        System.out.println("in change window:" + getUsername());

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void setTheActiveDate(int year,int month, int day){
        this.year = year;
        System.out.println("this.year= " + year);
        this.month = month;
        this.day = day;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        list = FXCollections.observableArrayList();
        listView.setItems(list);
        listView.setStyle("-fx-font-size:18");
    }
    
}
