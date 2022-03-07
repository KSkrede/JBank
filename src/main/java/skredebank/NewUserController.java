package skredebank;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import javafx.event.ActionEvent;

public class NewUserController {
    public NewUserController() {

    }

    @FXML
    private Button backButton;
    @FXML
    private Button createUserButton;
    @FXML
    private Label error;
    @FXML
    private TextField givenName;
    @FXML
    private TextField surName;
    @FXML
    private TextField phoneNumber;
    @FXML
    private DatePicker birthDate;
    

    SkredebankApp m = new SkredebankApp();

    public void createUser(ActionEvent event) throws IOException {
        System.out.println(givenName.getText());
        System.out.println(surName.getText());
        System.out.println(phoneNumber.getText());
        System.out.println(birthDate.getValue());
    }

    public void newUser(ActionEvent event) throws IOException {
        m.changeScene("newUser.fxml");
    }

}
