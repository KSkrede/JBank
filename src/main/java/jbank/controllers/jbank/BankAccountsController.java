package jbank.controllers.jbank;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import jbank.Jbank;
import jbank.data.Person;

public class BankAccountsController {
    public BankAccountsController() {

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

    Jbank jbank;

    public void initialize() {
        jbank = Jbank.getInstance();
    }

    public void createUser(ActionEvent event) throws IOException {
        try{
            Person p1 = new Person(phoneNumber.getText(), birthDate.getValue(), givenName.getText(), surName.getText(), bankIDPin.getText());
            //ID = new Person(givenName.getText(), surName.getText(), phoneNumber.getText(), birthDate.getValue(), bankIDPin.getText());
            jbank.getAccountObject().addAccounts(p1.getUserId(), p1);
            jbank.getAccountSaver().writeFile(jbank.getAccountObject());
        }
        
        catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }



    }

    public void newUser(ActionEvent event) throws IOException {
        jbank.getApp().changeScene("newUser.fxml");
    }

    
    public void back(ActionEvent event) throws IOException {
        jbank.getApp().changeScene("login.fxml");
    }

}