package skredebank.controllers;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import skredebank.SkredebankApp;
import skredebank.data.Accounts;
import skredebank.data.Person;
import skredebank.logic.files.AccountSaver;

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
    Accounts a = new Accounts();
    private AccountSaver saver = new AccountSaver(a);

    public void createUser(ActionEvent event) throws IOException {
        try{
            Person p1 = new Person(phoneNumber.getText(), birthDate.getValue(), givenName.getText(), surName.getText(), bankIDPin.getText());
            //ID = new Person(givenName.getText(), surName.getText(), phoneNumber.getText(), birthDate.getValue(), bankIDPin.getText());
            a.addAccounts(p1.getUserId(), p1);
            saver.writeFile(a);
        }
        
        catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        System.out.println(a.getAccounts());
        System.out.println("");

        a.getAccounts().entrySet().forEach(entry -> {
            System.out.println(entry.getKey() + " " + entry.getValue().getGivenName());
        });


    }

    public void newUser(ActionEvent event) throws IOException {
        m.changeScene("newUser.fxml");
    }

    
    public void back(ActionEvent event) throws IOException {
        m.changeScene("login.fxml");
    }

}
