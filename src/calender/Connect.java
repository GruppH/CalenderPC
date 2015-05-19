/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calender;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

public class Connect {

    private Connection connection;
    private String driver = "com.mysql.jdbc.Driver";
    private String databaseAddress = "89.160.102.7:3306";
    private String databaseName = "projekt";
    private String databaseUser = "ruut";
    private String databasePassword = "rooot";
    private Statement statement;
    private ResultSet resultSet;
    private String latestActivity;
    
    ListView listView;
    
    public Connect() {
        databaseConnection();
    }

    public void databaseConnection() {
        try {
            Class.forName(driver).newInstance();
            connection = DriverManager.getConnection(
                    "jdbc:mysql://" + databaseAddress + "/" + databaseName
                    + "?user=" + databaseUser + "&password=" + databasePassword);
            System.out.println("connected");
        }  catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String executeQuery(String query, String column) {
        String result = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                result = resultSet.getString(column);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }
    
    public String[] getActivities(String username, String dateToReceive) {
        int i = 0;
        String[] array = new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"};
        String userName = null;
        String activity = null;
        String notes = null;
        String begins = null;
        String ends = null;
        String date = null;
        String location = null;
        String send = "hej";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from calendar where userName='" + username + "' and date='" + dateToReceive + "'");
            
            while (resultSet.next()) {
                begins = resultSet.getString("begins");
                activity = resultSet.getString("activity");
                notes = resultSet.getString("notes");
                begins = resultSet.getString("begins");
                ends = resultSet.getString("ends");
                date = resultSet.getString("date");
                location = resultSet.getString("location");

                //array[i] = resultSet.getString("activity");
               
                System.out.println("arr: "+array[i]);
                //i++;
                
                
                String result = resultSet.getString("begins");
                for(int j=0; j<array.length-1; j++) {
                    if(result.contentEquals(Integer.toString(j))) {
                        array[j] = array[j] + "   " + resultSet.getString("activity");
                    }
                }
            }
            
        } catch(SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return array;
    }
}
