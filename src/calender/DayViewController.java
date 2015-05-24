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
    ChangeActivityController cac;
    private Connect connect;
    private String username;
    String selectedItem = null;
    @FXML
    private ListView listView;
    @FXML
    Button newActivityButton = new Button();
    @FXML
    private Button removeButton;
    @FXML
    private Button changeButton;
    
    private String activity;
    
    public void changeActivity() throws IOException {
        stage = (Stage) changeButton.getScene().getWindow();
        stage.close();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ChangeActivity.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        cac = fxmlLoader.<ChangeActivityController>getController();
        cac.setTheActiveDate(year, month, day);
        cac.setUsername(getUsername());
        cac.setLabels(selectedItem.substring(0, 2));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void getSelectedItem() {
        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                selectedItem = newValue;
                String value = selectedItem + activity;

                Collections.replaceAll(list, selectedItem, connect.getActivity(getUsername(),
                        Integer.toString(year)
                        + Integer.toString(month)
                        + Integer.toString(day),
                        selectedItem.substring(0, 2)));
            }
        });
    }
    
    public void removeActivity() {
        connect.removeActivity(getUsername(), Integer.toString(year)
                        + Integer.toString(month)
                        + Integer.toString(day), selectedItem.substring(0, 2));
        Collections.replaceAll(list, selectedItem, selectedItem.substring(0, 2));
        stage = (Stage) removeButton.getScene().getWindow();
        stage.close();
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
