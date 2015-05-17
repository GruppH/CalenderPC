/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calender;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author calleskanshed
 */
public class NewActivityController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private Stage stage;

    private SavedData sd = new SavedData();
    private DayViewController dvc;

    @FXML
    private Button done;

    @FXML
    private TextField begins;

    @FXML
    private TextField ends;

    @FXML
    private TextField title;

    @FXML
    private TextField info;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void addActivity() {
        try {
            String b = begins.getText();
            System.out.println("" + b);
            String e = ends.getText();
            System.out.println("" + e);
            System.out.println("hej" + dvc.dayString);
            sd.save(dvc.yearString+dvc.monthString+dvc.dayString+b, e+ ":" + title + ":" + info);
        } catch (Exception ex) {

        }
    }

}
