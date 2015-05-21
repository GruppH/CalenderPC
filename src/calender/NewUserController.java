/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calender;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author calleskanshed
 */
public class NewUserController extends MainWindowController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private String username;
    private Connect connect;
    
    @FXML
    private TextField usernameField;
    
    @FXML
    private TextField passwordFieldOne;
    
    @FXML
    private Button createButton;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        connect = new Connect();
    }    
    
    public void create() {
        connect.newUser(usernameField.getText(), passwordFieldOne.getText());
        Stage stage = (Stage) createButton.getScene().getWindow();
        stage.close();
    }
    
}