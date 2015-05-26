/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calender;

import java.net.URL;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author calleskanshed
 */
public class ChangeActivityController extends MainWindowController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private Stage stage;
    private int year;
    private int month;
    private int day;
    private DayViewController dvc;
    private Connect connect;
    private String username;

    public void setTheActiveDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
        System.out.println("YEAR: " + year + " MONTH: " + month + " DAY: " + day);
    }

    public void setLabels(String time) throws SQLException {
        connect.changeActivity(getUsername(), Integer.toString(year) + Integer.toString(month) + Integer.toString(day), time);
        startH.appendText(connect.getBegins());
        startMin.appendText("00");
        endMin.appendText("00");
        endH.appendText(connect.getEnds());
        activityTextField.appendText(connect.getActivityName());
        notesTextArea.appendText(connect.getNotes());
        locationTextField.appendText(connect.getLocation());
    }

    public void addActivity() {

        try {
            if (checkIfCorrect(startH.getText(), startMin.getText()) && checkIfCorrect(endH.getText(), endMin.getText())) {

                //Goto next step
                System.out.println("RIGHT");
                connect.changeActivity(connect.getID(), getUsername(), activityTextField.getText(),
                        notesTextArea.getText(), startH.getText(), endH.getText(),
                        locationTextField.getText(), Integer.toString(year)
                        + Integer.toString(month) + Integer.toString(day));

                stage = (Stage) done.getScene().getWindow();
                stage.close();
            }
        } catch (Exception ex) {

        }

    }

    @FXML
    public void resetLabel(KeyEvent event) {
        errorLabel.setVisible(false);
    }

    public boolean checkIfCorrect(String hourText, String minuteText) {
        int hour;
        int minute;
        boolean result = false;

        try {
            hour = Integer.parseInt(hourText);
            minute = Integer.parseInt(minuteText);
            if (hour > 23 || hour < 0) {
                throw new Exception();
            }
            if (minute > 59 || minute < 0) {
                throw new Exception();
            }
            result = true;
        } catch (Exception e) {
            errorLabel.setVisible(true);
        }

        return result;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        errorLabel.setVisible(false);
        connect = new Connect();
        //System.out.println("username::: " + getUsername());
    }

    @FXML
    private Button done;
    @FXML
    private TextField startH;
    @FXML
    private TextField startMin;
    @FXML
    private TextField endH;
    @FXML
    private TextField endMin;
    @FXML
    private Label errorLabel;
    @FXML
    private TextField activityTextField;
    @FXML
    private TextArea notesTextArea;
    @FXML
    private TextField locationTextField;

}
