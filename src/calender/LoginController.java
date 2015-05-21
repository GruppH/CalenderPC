/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calender;

import calender.Main;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoginController extends MainWindowController implements Initializable {

    private Connect connect;
    private Stage stage;
    private String username;
    private MainWindowController mwc;
    private NewUserController nuc;
    
    @FXML
    private Button signInButton;

    @FXML
    private Button newUserButton;

    @FXML
    private TextField nameField;

    @FXML
    private PasswordField passwordField;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        connect = new Connect();
    }

    public void signIn() {
        try {
            System.out.println("name: " + nameField.getCharacters());
            System.out.println("pass: " + passwordField.getText());
            if (connect.executeQuery("select password from user where Username='"
                    + nameField.getText() + "'", "password").contentEquals(
                            passwordField.getText())) {
                setUsername(nameField.getText());
                changeWindow();
            }
        } catch (Exception ex) {

        }

    }
    
    public void newUser() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("NewUser.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        nuc = fxmlLoader.<NewUserController>getController();
        //nuc.setUsername(getUsername());
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void changeWindow() throws IOException {
        stage = (Stage) signInButton.getScene().getWindow();
        stage.close();
        /*
        stage = new Stage();
        AnchorPane root = FXMLLoader.load(Main.class.getResource("MainWindow.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        */
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        mwc = fxmlLoader.<MainWindowController>getController();
        mwc.setUsername(getUsername());
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

}
