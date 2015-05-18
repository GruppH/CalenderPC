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
public class NewActivityController extends DayViewController implements Initializable  {

    /**
     * Initializes the controller class.
     */
    private Stage stage;
    private int year;
    private int month;
    private int day;
    private SavedData sd;

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
    
    public void setTheActiveDate(int year,int month, int day){
        this.year = year;
        this.month = month;
        this.day = day;
        System.out.println("YEAR: " + year + " MONTH: " + month + " DAY: " + day);
    }
    
    public void setSD(SavedData sd) {
        this.sd = sd;
    }
    
    
    public void addActivity() {
        try {
            String b = begins.getText();
            System.out.println("" + b);
            String e = ends.getText();
            System.out.println("" + e);

            System.out.println("hej" + year);
            System.out.println("day to save: " + year + month + day);
            sd.save(Integer.toString(year)+Integer.toString(month)+Integer.toString(day), e/*+ ":" + title + ":" + info*/);
        } catch (Exception ex) {

        }
    }

}
