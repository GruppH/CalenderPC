package calender;

import java.awt.event.*;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class MainWindowController implements Initializable {

    private String hoverColor = "#6495ED";
    private Calendar cal;
    private Pane[] panes = new Pane[37];
    private int year;
    private int month;
    private int day;
    private int weekday;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
            fillPaneArray();
            cal = Calendar.getInstance();
            
    }
    
    public void updateLabels(int month,int year){
        
    }
    
    public void getFirstWeekDayInMonth(int month, int year){
        
    }
    
    public int getDaysInMonth(int month, int year){
        Calendar localCal = Calendar.getInstance();
        localCal.set(year, month, 1);
        switch (month){
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                return 31;
            case 4: case 6: case 9: case 11:
                return 30;
            case 2:
                if (localCal.getActualMaximum(Calendar.DAY_OF_YEAR) > 365){
                    return 29;
                } else {
                    return 28;
                }
            default:
                return 0;
        }
    }
    
    
    
//<editor-fold defaultstate="collapsed" desc="Method for filling pane array">
    public void fillPaneArray(){
        panes[0] = pane1;
        panes[1] = pane2;
        panes[2] = pane3;
        panes[3] = pane4;
        panes[4] = pane5;
        panes[5] = pane6;
        panes[6] = pane7;
        panes[7] = pane8;
        panes[8] = pane9;
        panes[9] = pane10;
        panes[10] = pane11;
        panes[11] = pane12;
        panes[12] = pane13;
        panes[13] = pane14;
        panes[14] = pane15;
        panes[15] = pane16;
        panes[16] = pane17;
        panes[17] = pane18;
        panes[18] = pane19;
        panes[19] = pane20;
        panes[20] = pane21;
        panes[21] = pane22;
        panes[22] = pane23;
        panes[23] = pane24;
        panes[24] = pane25;
        panes[25] = pane26;
        panes[26] = pane27;
        panes[27] = pane28;
        panes[28] = pane29;
        panes[29] = pane30;
        panes[30] = pane31;
        panes[31] = pane32;
        panes[32] = pane33;
        panes[33] = pane34;
        panes[34] = pane35;
        panes[35] = pane36;
        panes[36] = pane37;               
    }
    //</editor-fold>
    
// <editor-fold defaultstate="collapsed" desc="Methods for mouseHovering">
    @FXML
    public void mouseHover(MouseEvent event) {
        String paneFullId = event.getSource().toString();
        String pane = paneFullId.substring(8, paneFullId.length() - 1);
        System.out.println(pane);
        switch (pane) {
            case "pane1":
                pane1.setStyle("-fx-background-color: " + hoverColor);
                break;
            case "pane2":
                pane2.setStyle("-fx-background-color: " + hoverColor);
                break;
            case "pane3":
                pane3.setStyle("-fx-background-color: " + hoverColor);
                break;
            case "pane4":
                pane4.setStyle("-fx-background-color: " + hoverColor);
                break;
            case "pane5":
                pane5.setStyle("-fx-background-color: " + hoverColor);
                break;
            case "pane6":
                pane6.setStyle("-fx-background-color: " + hoverColor);
                break;
            case "pane7":
                pane7.setStyle("-fx-background-color: " + hoverColor);
                break;
            case "pane8":
                pane8.setStyle("-fx-background-color: " + hoverColor);
                break;
            case "pane9":
                pane9.setStyle("-fx-background-color: " + hoverColor);
                break;
            case "pane10":
                pane10.setStyle("-fx-background-color: " + hoverColor);
                break;
            case "pane11":
                pane11.setStyle("-fx-background-color: " + hoverColor);
                break;
            case "pane12":
                pane12.setStyle("-fx-background-color: " + hoverColor);
                break;
            case "pane13":
                pane13.setStyle("-fx-background-color: " + hoverColor);
                break;
            case "pane14":
                pane14.setStyle("-fx-background-color: " + hoverColor);
                break;
            case "pane15":
                pane15.setStyle("-fx-background-color: " + hoverColor);
                break;
            case "pane16":
                pane16.setStyle("-fx-background-color: " + hoverColor);
                break;
            case "pane17":
                pane17.setStyle("-fx-background-color: " + hoverColor);
                break;
            case "pane18":
                pane18.setStyle("-fx-background-color: " + hoverColor);
                break;
            case "pane19":
                pane19.setStyle("-fx-background-color: " + hoverColor);
                break;
            case "pane20":
                pane20.setStyle("-fx-background-color: " + hoverColor);
                break;
            case "pane21":
                pane21.setStyle("-fx-background-color: " + hoverColor);
                break;
            case "pane22":
                pane22.setStyle("-fx-background-color: " + hoverColor);
                break;
            case "pane23":
                pane23.setStyle("-fx-background-color: " + hoverColor);
                break;
            case "pane24":
                pane24.setStyle("-fx-background-color: " + hoverColor);
                break;
            case "pane25":
                pane25.setStyle("-fx-background-color: " + hoverColor);
                break;
            case "pane26":
                pane26.setStyle("-fx-background-color: " + hoverColor);
                break;
            case "pane27":
                pane27.setStyle("-fx-background-color: " + hoverColor);
                break;
            case "pane28":
                pane28.setStyle("-fx-background-color: " + hoverColor);
                break;
            case "pane29":
                pane29.setStyle("-fx-background-color: " + hoverColor);
                break;
            case "pane30":
                pane30.setStyle("-fx-background-color: " + hoverColor);
                break;
            case "pane31":
                pane31.setStyle("-fx-background-color: " + hoverColor);
                break;
            case "pane32":
                pane32.setStyle("-fx-background-color: " + hoverColor);
                break;
            case "pane33":
                pane33.setStyle("-fx-background-color: " + hoverColor);
                break;
            case "pane34":
                pane34.setStyle("-fx-background-color: " + hoverColor);
                break;
            case "pane35":
                pane35.setStyle("-fx-background-color: " + hoverColor);
                break;
            case "pane36":
                pane36.setStyle("-fx-background-color: " + hoverColor);
                break;
            case "pane37":
                pane37.setStyle("-fx-background-color: " + hoverColor);
                break;

        }
    }

    @FXML
    public void mouseLeave(MouseEvent event) {
        String paneFullId = event.getSource().toString();
        String pane = paneFullId.substring(8,paneFullId.length()-1);

        switch (pane) {
            case "pane1":
                pane1.setStyle("-fx-background-color: " + "White");
                break;
            case "pane2":
                pane2.setStyle("-fx-background-color: " + "White");
                break;
            case "pane3":
                pane3.setStyle("-fx-background-color: " + "White");
                break;
            case "pane4":
                pane4.setStyle("-fx-background-color: " + "White");
                break;
            case "pane5":
                pane5.setStyle("-fx-background-color: " + "White");
                break;
            case "pane6":
                pane6.setStyle("-fx-background-color: " + "White");
                break;
            case "pane7":
                pane7.setStyle("-fx-background-color: " + "White");
                break;
            case "pane8":
                pane8.setStyle("-fx-background-color: " + "White");
                break;
            case "pane9":
                pane9.setStyle("-fx-background-color: " + "White");
                break;
            case "pane10":
                pane10.setStyle("-fx-background-color: " + "White");
                break;
            case "pane11":
                pane11.setStyle("-fx-background-color: " + "White");
                break;
            case "pane12":
                pane12.setStyle("-fx-background-color: " + "White");
                break;
            case "pane13":
                pane13.setStyle("-fx-background-color: " + "White");
                break;
            case "pane14":
                pane14.setStyle("-fx-background-color: " + "White");
                break;
            case "pane15":
                pane15.setStyle("-fx-background-color: " + "White");
                break;
            case "pane16":
                pane16.setStyle("-fx-background-color: " + "White");
                break;
            case "pane17":
                pane17.setStyle("-fx-background-color: " + "White");
                break;
            case "pane18":
                pane18.setStyle("-fx-background-color: " + "White");
                break;
            case "pane19":
                pane19.setStyle("-fx-background-color: " + "White");
                break;
            case "pane20":
                pane20.setStyle("-fx-background-color: " + "White");
                break;
            case "pane21":
                pane21.setStyle("-fx-background-color: " + "White");
                break;
            case "pane22":
                pane22.setStyle("-fx-background-color: " + "White");
                break;
            case "pane23":
                pane23.setStyle("-fx-background-color: " + "White");
                break;
            case "pane24":
                pane24.setStyle("-fx-background-color: " + "White");
                break;
            case "pane25":
                pane25.setStyle("-fx-background-color: " + "White");
                break;
            case "pane26":
                pane26.setStyle("-fx-background-color: " + "White");
                break;
            case "pane27":
                pane27.setStyle("-fx-background-color: " + "White");
                break;
            case "pane28":
                pane28.setStyle("-fx-background-color: " + "White");
                break;
            case "pane29":
                pane29.setStyle("-fx-background-color: " + "White");
                break;
            case "pane30":
                pane30.setStyle("-fx-background-color: " + "White");
                break;
            case "pane31":
                pane31.setStyle("-fx-background-color: " + "White");
                break;
            case "pane32":
                pane32.setStyle("-fx-background-color: " + "White");
                break;
            case "pane33":
                pane33.setStyle("-fx-background-color: " + "White");
                break;
            case "pane34":
                pane34.setStyle("-fx-background-color: " + "White");
                break;
            case "pane35":
                pane35.setStyle("-fx-background-color: " + "White");
                break;
            case "pane36":
                pane36.setStyle("-fx-background-color: " + "White");
                break;
            case "pane37":
                pane37.setStyle("-fx-background-color: " + "White");
                break;
        }
    }
    
    //</editor-fold>
    
// <editor-fold defaultstate="collapsed" desc="All the panes and Labels">
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
// </editor-fold>
    
    
}
