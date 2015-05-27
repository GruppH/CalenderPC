/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calender;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
        root.setStyle("-fx-background-image: url('calendar.jpg')");
        nuc = fxmlLoader.<NewUserController>getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void changeWindow() throws IOException {
        stage = (Stage) signInButton.getScene().getWindow();
        stage.close();
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
