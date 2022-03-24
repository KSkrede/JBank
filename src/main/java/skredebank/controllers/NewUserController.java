package skredebank.controllers;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import skredebank.data.Person;
import skredebank.logic.Help;
import skredebank.logic.files.AccountSaver;
import skredebank.data.Accounts;
import skredebank.SkredebankApp;


import java.io.IOException;
import java.time.LocalDate;

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
    Accounts a = new Accounts();
    private AccountSaver saver = new AccountSaver(a);

    public void createUser(ActionEvent event) throws IOException {
        try{
            Person p1 = new Person(phoneNumber.getText(), birthDate.getValue(), givenName.getText(), surName.getText(), bankIDPin.getText());
            //ID = new Person(givenName.getText(), surName.getText(), phoneNumber.getText(), birthDate.getValue(), bankIDPin.getText());
            a.addAccounts(p1.getUserId(), p1);
            saver.writeFile(a);

            //Save to today.txt
            //saver.writeFile(Help.todayToString(), a);
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
