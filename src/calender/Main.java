/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calender;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Niclas
 */
public class Main extends Application {
    private String address = "89.160.102.7:3306";
    private String databaseName = null;
    private String user = "ruut";
    private String password = "rooot";
    private Connection connection = null;
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
    
    public void dataBaseConnection() {
        try {
            connection = (Connection) DriverManager.getConnection(
                    "jdbc:mysql://"+ address +"/" + databaseName
                    + "?user=" + user + "&password=" + password);
        } catch (SQLException ex) {

        }
    }
    
}
