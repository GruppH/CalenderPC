/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calender;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

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
        System.out.println("Year: "+year);
        System.out.println("Month: "+month);
        System.out.println("Day: " +day);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
