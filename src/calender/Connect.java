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

public class Connect {

    private Connection connection;
    private String driver = "com.mysql.jdbc.Driver";
    private String databaseAddress = "89.160.102.7:3306";
    private String databaseName = "projekt";
    private String databaseUser = "ruut";
    private String databasePassword = "rooot";
    //private String result;
    private Statement statement;
    private ResultSet resultSet;

    //PUUUUUUSH
    
    public Connect() {
        databaseConnection();
    }

    public void databaseConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
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
}
