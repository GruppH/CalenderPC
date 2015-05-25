package calender;

import java.awt.event.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainWindowController implements Initializable {

    public String username;
    private String hoverColor = "#6495ED";
    private Calendar calendar;
    private Pane[] panes = new Pane[43];
    private Label[] labels = new Label[43];
    private int year;
    private int month;
    private int day;
    private int weekday;
    private DayViewController dvc;
    private Connect connect;
    ObservableList<String> list;
    private String[] array;

    public MainWindowController() {

    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    @FXML
    public void openDayView(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DayView.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        dvc = fxmlLoader.<DayViewController>getController();
        dvc.setTheActiveDate(year, month, day);
        dvc.setUsername(getUsername());

        try {
            array = connect.getActivities(getUsername(), Integer.toString(year) + Integer.toString(month) + Integer.toString(day));
            for (int i = 0; i < array.length; i++) {
                dvc.list.add(array[i]);
            }
        } catch (Exception ex) {

        }
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //testSavedData();
        connect = new Connect();
        calendar = Calendar.getInstance();
        fillPaneArray();
        fillLabelArray();
        setup();
        resetCalender();
        updateLabels(month, year);
        getCurrentDay();
    }

    public void daysWithActivity(int[] days) {
        int dayToStart = getFirstWeekdayInMonth(month, year);
        for (int i = 0; i < days.length; i++) {
            
            int lastDayToCheck = getDaysInMonth(month, year);
            
            for (int j = 0; j < labels.length; j++) {
                if (j >= dayToStart && lastDayToCheck > 0) {
                    int label = Integer.parseInt(labels[j].getText());
                    if (label == days[i]) {
                        labels[j].setStyle(("-fx-text-fill: chartreuse  "));
                    }
                    lastDayToCheck--;
                }
            }
        }
    }

    public void getCurrentDay() {

        if (calendar.get(Calendar.MONTH) + 1 == month) {
            int today = calendar.get(Calendar.DAY_OF_MONTH);

            for (int i = 1; i < labels.length; i++) {

                String labelText = labels[i].getText();

                if (!labelText.equals("")) {
                    int labelInt = Integer.parseInt(labelText);
                    if (labelInt == today) {
                        labels[i].setStyle("-fx-text-fill: red    ");
                    }
                }

            }
        }
    }

    public void resetLabels() {
        for (int i = 1; i < labels.length; i++) {
            labels[i].setStyle("-fx-text-fill: black    ");
        }
    }

    public void setup() {
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + 1;
    }

    @FXML
    public void mouseHover(MouseEvent event) {
        Pane p = (Pane) event.getSource();
        Label l = (Label) p.getChildren().get(0);
        day = Integer.parseInt(l.getText());
        p.setStyle("-fx-background-color: " + hoverColor);
    }

    @FXML
    public void mouseLeave(MouseEvent event) {
        Pane p = (Pane) event.getSource();
        p.setStyle("-fx-background-color: " + "white");
    }

    @FXML
    public void nextMonth(MouseEvent event) {
        resetCalender();
        if (month == 12) {
            year++;
            month = 1;
            updateLabels(month, year);
        } else {
            month++;
            updateLabels(month, year);
        }
    }

    @FXML
    public void previousMonth(MouseEvent event) {
        resetCalender();
        if (month == 1) {
            year--;
            month = 12;
            updateLabels(month, year);
        } else {
            month--;
            updateLabels(month, year);
        }

    }

    public void updateLabels(int month, int year) {

        //daysWithActivity(new int[] {5,4});
        int firstDay = getFirstWeekdayInMonth(month, year);
        int numberOfDays = getDaysInMonth(month, year);
        int days = 1;
        int daysNextMonth = 1;
        //This is for getting previous month days
        int daysInPreviousMonth;
        if (month > 1) {
            daysInPreviousMonth = getDaysInMonth(month - 1, year);
        } else {
            daysInPreviousMonth = 31;
        }
        System.out.println(daysInPreviousMonth);
        for (int i = 1; i < labels.length; i++) {
            if (i < firstDay) {
                labels[i].setText(String.valueOf(daysInPreviousMonth - (firstDay - i - 1)));
                panes[i].setDisable(true);
                panes[i].setStyle("-fx-background-color: grey");
            } else if (days > numberOfDays) {
                labels[i].setText(String.valueOf(daysNextMonth));
                daysNextMonth++;
                panes[i].setDisable(true);
                panes[i].setStyle("-fx-background-color: grey");
            } else {
                labels[i].setText(String.valueOf(days));
                days++;
            }
        }
        monthLabel(month);
        yearLabel.setText(String.valueOf(year));
        getCurrentDay();
        
        //Example of displaying days with activity
        //   daysWithActivity(new int[]{1, 3, 5, 24 , 31});
    
    
    }

    public void resetCalender() {
        for (int i = 1; i < panes.length; i++) {
            panes[i].setDisable(false);
            panes[i].setStyle("-fx-background-color: white");
        }
        resetLabels();
    }

    public int getFirstWeekdayInMonth(int month, int year) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, 1);
        int result = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (result == 0) {
            result = 7;
        }
        return result;
    }

    public int getDaysInMonth(int month, int year) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, 1);
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            case 2:
                if (cal.getActualMaximum(Calendar.DAY_OF_YEAR) > 365) {
                    return 29;
                } else {
                    return 28;
                }
            default:
                return 0;
        }
    }

// <editor-fold defaultstate="collapsed" desc="Update the month label">    
    public void monthLabel(int month) {
        switch (month) {
            case 1:
                monthLabel.setText("Januari");
                break;
            case 2:
                monthLabel.setText("Februari");
                break;
            case 3:
                monthLabel.setText("Mars");
                break;
            case 4:
                monthLabel.setText("April");
                break;
            case 5:
                monthLabel.setText("Maj");
                break;
            case 6:
                monthLabel.setText("Juni");
                break;
            case 7:
                monthLabel.setText("Juli");
                break;
            case 8:
                monthLabel.setText("Augusti");
                break;
            case 9:
                monthLabel.setText("September");
                break;
            case 10:
                monthLabel.setText("Oktober");
                break;
            case 11:
                monthLabel.setText("November");
                break;
            case 12:
                monthLabel.setText("December");
                break;
        }
    }
//</editor-fold>    

//<editor-fold defaultstate="collapsed" desc="Method for filling pane array">
    public void fillPaneArray() {
        panes[1] = pane1;
        panes[2] = pane2;
        panes[3] = pane3;
        panes[4] = pane4;
        panes[5] = pane5;
        panes[6] = pane6;
        panes[7] = pane7;
        panes[8] = pane8;
        panes[9] = pane9;
        panes[10] = pane10;
        panes[11] = pane11;
        panes[12] = pane12;
        panes[13] = pane13;
        panes[14] = pane14;
        panes[15] = pane15;
        panes[16] = pane16;
        panes[17] = pane17;
        panes[18] = pane18;
        panes[19] = pane19;
        panes[20] = pane20;
        panes[21] = pane21;
        panes[22] = pane22;
        panes[23] = pane23;
        panes[24] = pane24;
        panes[25] = pane25;
        panes[26] = pane26;
        panes[27] = pane27;
        panes[28] = pane28;
        panes[29] = pane29;
        panes[30] = pane30;
        panes[31] = pane31;
        panes[32] = pane32;
        panes[33] = pane33;
        panes[34] = pane34;
        panes[35] = pane35;
        panes[36] = pane36;
        panes[37] = pane37;
        panes[38] = pane38;
        panes[39] = pane39;
        panes[40] = pane40;
        panes[41] = pane41;
        panes[42] = pane42;

    }
    //</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Method for filling label array">
    public void fillLabelArray() {
        labels[1] = label1;
        labels[2] = label2;
        labels[3] = label3;
        labels[4] = label4;
        labels[5] = label5;
        labels[6] = label6;
        labels[7] = label7;
        labels[8] = label8;
        labels[9] = label9;
        labels[10] = label10;
        labels[11] = label11;
        labels[12] = label12;
        labels[13] = label13;
        labels[14] = label14;
        labels[15] = label15;
        labels[16] = label16;
        labels[17] = label17;
        labels[18] = label18;
        labels[19] = label19;
        labels[20] = label20;
        labels[21] = label21;
        labels[22] = label22;
        labels[23] = label23;
        labels[24] = label24;
        labels[25] = label25;
        labels[26] = label26;
        labels[27] = label27;
        labels[28] = label28;
        labels[29] = label29;
        labels[30] = label30;
        labels[31] = label31;
        labels[32] = label32;
        labels[33] = label33;
        labels[34] = label34;
        labels[35] = label35;
        labels[36] = label36;
        labels[37] = label37;
        labels[38] = label38;
        labels[39] = label39;
        labels[40] = label40;
        labels[41] = label41;
        labels[42] = label42;
    }
    //</editor-fold>

// <editor-fold defaultstate="collapsed" desc="All the references to panes and Labels">
    @FXML
    private Label monthLabel;
    @FXML
    private Label yearLabel;
    @FXML
    private Pane pane1;
    @FXML
    private Pane pane2;
    @FXML
    private Pane pane3;
    @FXML
    private Pane pane4;
    @FXML
    private Pane pane5;
    @FXML
    private Pane pane6;
    @FXML
    private Pane pane7;
    @FXML
    private Pane pane8;
    @FXML
    private Pane pane9;
    @FXML
    private Pane pane10;
    @FXML
    private Pane pane11;
    @FXML
    private Pane pane12;
    @FXML
    private Pane pane13;
    @FXML
    private Pane pane14;
    @FXML
    private Pane pane15;
    @FXML
    private Pane pane16;
    @FXML
    private Pane pane17;
    @FXML
    private Pane pane18;
    @FXML
    private Pane pane19;
    @FXML
    private Pane pane20;
    @FXML
    private Pane pane21;
    @FXML
    private Pane pane22;
    @FXML
    private Pane pane23;
    @FXML
    private Pane pane24;
    @FXML
    private Pane pane25;
    @FXML
    private Pane pane26;
    @FXML
    private Pane pane27;
    @FXML
    private Pane pane28;
    @FXML
    private Pane pane29;
    @FXML
    private Pane pane30;
    @FXML
    private Pane pane31;
    @FXML
    private Pane pane32;
    @FXML
    private Pane pane33;
    @FXML
    private Pane pane34;
    @FXML
    private Pane pane35;
    @FXML
    private Pane pane36;
    @FXML
    private Pane pane37;
    @FXML
    private Pane pane38;
    @FXML
    private Pane pane39;
    @FXML
    private Pane pane40;
    @FXML
    private Pane pane41;
    @FXML
    private Pane pane42;

    @FXML
    private Label label1;
    @FXML
    private Label label2;
    @FXML
    private Label label3;
    @FXML
    private Label label4;
    @FXML
    private Label label5;
    @FXML
    private Label label6;
    @FXML
    private Label label7;
    @FXML
    private Label label8;
    @FXML
    private Label label9;
    @FXML
    private Label label10;
    @FXML
    private Label label11;
    @FXML
    private Label label12;
    @FXML
    private Label label13;
    @FXML
    private Label label14;
    @FXML
    private Label label15;
    @FXML
    private Label label16;
    @FXML
    private Label label17;
    @FXML
    private Label label18;
    @FXML
    private Label label19;
    @FXML
    private Label label20;
    @FXML
    private Label label21;
    @FXML
    private Label label22;
    @FXML
    private Label label23;
    @FXML
    private Label label24;
    @FXML
    private Label label25;
    @FXML
    private Label label26;
    @FXML
    private Label label27;
    @FXML
    private Label label28;
    @FXML
    private Label label29;
    @FXML
    private Label label30;
    @FXML
    private Label label31;
    @FXML
    private Label label32;
    @FXML
    private Label label33;
    @FXML
    private Label label34;
    @FXML
    private Label label35;
    @FXML
    private Label label36;
    @FXML
    private Label label37;
    @FXML
    private Label label38;
    @FXML
    private Label label39;
    @FXML
    private Label label40;
    @FXML
    private Label label41;
    @FXML
    private Label label42;

// </editor-fold>
}
