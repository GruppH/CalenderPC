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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
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
        String[] array = new String[]{"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"};
        String userName = null;
        String activity = null;
        String begins = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from calendar where username='" + username + "' and date='" + dateToReceive + "'");

            while (resultSet.next()) {
                begins = resultSet.getString("begins");
                activity = resultSet.getString("activity");

                for (int j = 0; j < array.length; j++) {
                    if (begins.contentEquals(Integer.toString(j))) {
                        array[j] = array[j] + "   " + activity;
                    }
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return array;
    }

    public void newActivity(String username, String activity, String notes,
            String begins, String ends, String location, String date) {
        try {
            statement = connection.createStatement();
            statement.executeUpdate(
                    "INSERT INTO calendar (username,activity,notes,begins,ends,location,date)"
                    + "VALUES ('" + username + "','" + activity + "','" + notes + "','"
                    + begins + "','" + ends + "','" + location + "',"
                    + date + ");");
        } catch (SQLException ex) {

        }
    }

    public String getActivity(String username, String dateToReceive, String startTime) {
        String userName = null;
        String activity = null;
        String begins = null;
        String notes = null;
        String ends = null;
        String date = null;
        String location = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from calendar where username='" + username + "' and date='" + dateToReceive + "' and begins='" + startTime + "'");

            while (resultSet.next()) {
                ends = resultSet.getString("ends");
                notes = resultSet.getString("notes");
                location = resultSet.getString("location");
            }

        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (ends != null) {
            return "Ends:" + ends + "\r\n" + "Notes: " + notes + "\r\n" + "Location: " + location;
        } else {
            return "";
        }

    }

}
