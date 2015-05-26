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
import java.util.ArrayList;
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

    private String userName = null;
    private String activity = null;
    private String begins = null;
    private String notes = null;
    private String ends = null;
    private String date = null;
    private String location = null;
    private int id = 0;

    private String activityC = null;
    private String beginsC = null;
    private String notesC = null;
    private String endsC = null;
    private String dateC = null;
    private String locationC = null;
    private int idC = 0;

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

    public ArrayList<Integer> getActivityDates(String username) {
        ArrayList<Integer> dates = new ArrayList<Integer>();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT date FROM calendar where username='" + username + "' ORDER BY date");

            while (resultSet.next()) {
                dates.add(resultSet.getInt("date"));
            }

        } catch (SQLException ex) {

        }

        return dates;
    }

    public String getActivityName() {
        return activityC;
    }

    public String getBegins() {
        return beginsC;
    }

    public String getEnds() {
        return endsC;
    }

    public String getDate() {
        return dateC;
    }

    public String getLocation() {
        return locationC;
    }

    public int getID() {
        return idC;
    }

    public String getNotes() {
        return notesC;
    }

    public int getNewID() {
        int id = 0;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT id FROM calendar ORDER BY id");

            while (resultSet.next()) {
                id = resultSet.getInt("id");
                System.out.println(id);
            }

        } catch (SQLException ex) {

        }

        return id + 1;
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
                    "INSERT INTO calendar (id, username,activity,notes,begins,ends,location,date)"
                    + "VALUES (" + getNewID() + ",'" + username + "','" + activity + "','" + notes + "','"
                    + begins + "','" + ends + "','" + location + "',"
                    + date + ");");
        } catch (SQLException ex) {

        }
    }

    public String getString() {
        return "hej";
    }

    public void changeActivity(String username, String dateToReceive, String startTime) throws SQLException {
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from calendar where username='" + username + "' and date='" + dateToReceive + "' and begins='" + startTime + "'");

            while (resultSet.next()) {
                activityC = resultSet.getString("activity");
                beginsC = resultSet.getString("begins");
                endsC = resultSet.getString("ends");
                idC = resultSet.getInt("id");
                notesC = resultSet.getString("notes");
                locationC = resultSet.getString("location");
            }
        } catch (SQLException ex) {

        }
    }

    public String getActivity(String username, String dateToReceive, String startTime) {
        String returner = "";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from calendar where username='" + username + "' and date='" + dateToReceive + "' and begins='" + startTime + "'");

            while (resultSet.next()) {
                activity = resultSet.getString("activity");
                begins = resultSet.getString("begins");
                ends = resultSet.getString("ends");
                id = resultSet.getInt("id");
                notes = resultSet.getString("notes");
                //System.out.println("notes: " + notes);
                location = resultSet.getString("location");
                //System.out.println("location: " + location);
            }

            System.out.println("activity exist");
            if (activity != null) {
                returner = startTime + "   " + activity + "\r\n" + "Ends: " + ends + "\r\n" + "Notes: " + notes + "\r\n" + "Location: " + location;
            } else if (activity == null) {
                returner = startTime;
            }
            activity = null;
            begins = null;
            notes = null;
            ends = null;
            date = null;
            location = null;
            id = 0;

        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return returner;
    }

    public void removeActivity(String username, String date, String begins) {
        try {
            statement = connection.createStatement();
            statement.executeUpdate("delete from calendar where username='" + username + "' and date='" + date + "' and begins='" + begins + "'");
        } catch (SQLException ex) {

        }
    }

    public void newUser(String username, String password) {
        try {
            statement = connection.createStatement();
            statement.executeUpdate(
                    "INSERT INTO user (Username, password)"
                    + "VALUES ('" + username + "','" + password + "');");
        } catch (SQLException ex) {
            System.out.println("no user added");
        }
    }

    public boolean checkIfUserExist(String userName) {
        String user = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select Username from user where userName='" + userName + "'");

            while (resultSet.next()) {
                user = resultSet.getString("Username");
                System.out.println("user: " + user);
            }

        } catch (SQLException ex) {
            System.out.println("no username exist");
        }
        if(user == null) {
            return true;
        } else {
            return false;
        }
    }

    public void changeActivity(int id, String username, String activity, String notes, String begins, String ends, String location, String date) {
        try {
            statement = connection.createStatement();
            statement.executeUpdate(
                    "update calendar set username='" + username
                    + "',activity='" + activity + "',notes='" + notes
                    + "',begins='" + begins + "',ends='" + ends
                    + "',location='" + location + "',date='" + date
                    + "' where id='" + id + "'");
        } catch (SQLException ex) {
            System.out.println("activity not changed");
        }
    }

}
