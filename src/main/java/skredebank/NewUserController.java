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
    @FXML
    private TextField bankIDPin;
    @FXML
    private TextField confirmBankIDPin;
    

    SkredebankApp m = new SkredebankApp();

    public void createUser(ActionEvent event) throws IOException {
        String BankID = phoneNumber+"x";
        Person BankID2 = null;
        try{
            BankID2 = new Person(givenName.getText(), surName.getText(), phoneNumber.getText(), birthDate.getValue(), bankIDPin.getText());
            System.out.println("Hey");
        }
        
        catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        System.out.println(BankID2);

    }

    public void newUser(ActionEvent event) throws IOException {
        m.changeScene("newUser.fxml");
    }

}
