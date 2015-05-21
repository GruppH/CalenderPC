/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calender;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
    String selectedItem = null;
    @FXML
    private ListView listView;
    @FXML
    Button newActivityButton = new Button();
    private String activity;

    public void getSelectedItem() {
        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                //System.out.println("ListView selection changed from oldValue = "
                //      + oldValue + " to newValue = " + newValue);
                selectedItem = newValue;
                activity = connect.getActivity(getUsername(),
                                Integer.toString(year)
                                + Integer.toString(month)
                                + Integer.toString(day),
                                selectedItem.substring(0, 2));
                //System.out.println("selectedItem: " + selectedItem);
                //String substring = selectedItem.substring(0, 2);
                //System.out.println("substring: " + substring);
                //activity = connect.getActivity(getUsername(), Integer.toString(year) + Integer.toString(month) + Integer.toString(day), substring);
                String value = selectedItem + activity;
                Collections.replaceAll(list, selectedItem, connect.getActivity(getUsername(),
                                Integer.toString(year)
                                + Integer.toString(month)
                                + Integer.toString(day),
                                selectedItem.substring(0, 2)));

            }
        });
    }

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

    public void setTheActiveDate(int year, int month, int day) {
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
        getSelectedItem();
        connect = new Connect();
    }

}
