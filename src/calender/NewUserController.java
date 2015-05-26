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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
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
    private PasswordField passwordFieldOne;

    @FXML
    private PasswordField passwordFieldTwo;

    @FXML
    private Button createButton;

    @FXML
    private Label noMatchLabel;

    @FXML
    private Label userNameExist;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        noMatchLabel.setVisible(false);
        userNameExist.setVisible(false);
        connect = new Connect();

    }

    public void create() {
        System.out.println("usernametextifled: " + usernameField.getText());
        System.out.println("the test: " + connect.checkIfUserExist("hassel"));
        if (connect.checkIfUserExist(usernameField.getText())) {
            if (passwordFieldOne.getText().equals(passwordFieldTwo.getText())) {
                connect.newUser(usernameField.getText(), passwordFieldOne.getText());
                Stage stage = (Stage) createButton.getScene().getWindow();
                stage.close();
            } else {
                //PASSWORDS DOES NOT MATCH
                noMatchLabel.setVisible(true);
                System.out.println("PASSWORDS DOES NOT MATCH");
            }
        } else {
            userNameExist.setVisible(true);
        }

    }
}
