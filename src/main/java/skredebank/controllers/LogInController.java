package skredebank.controllers;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import skredebank.SkredebankApp;
import skredebank.data.Accounts;
import skredebank.data.Person;
import skredebank.logic.files.AccountSaver;
import javafx.event.ActionEvent;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
public class LogInController {

    public LogInController() {

    }

    @FXML
    private Button nextButton;
    @FXML
    private Button newUserButton;
    @FXML
    private Label wrongLogIn;
    @FXML
    private TextField phoneNumber;
    @FXML
    private TextField birthDate;

    SkredebankApp m = new SkredebankApp();
    Accounts a = new Accounts();
    AccountSaver accountSaver = new AccountSaver(a);
    Person loggedInUser;
    Map accounts;

    public void initialize() throws FileNotFoundException {
        accountSaver.readFile();
    }


    public void userLogIn(ActionEvent event) throws IOException {
        checkLogin();
    }

    public void newUser(ActionEvent event) throws IOException {
        m.changeScene("newUser.fxml");
    }

    private void checkLogin() throws IOException {

        //     wrongLogIn.setText("Suksessfull login!");

        String userID = phoneNumber.getText().toString()+birthDate.getText().toString();
        

        if(a.getAccounts().keySet().stream().anyMatch(key -> userID.equals(key))) {
          //loggedInUser = a.getAccounts().keySet().stream().findFirst(key -> userID.equals(key));

          for (String entry : a.getAccounts().keySet()) {
              if (entry.equals(userID)){
                  loggedInUser = a.getAccounts().get(entry);
              }
         }
            m.changeScene("bankID.fxml");
        }

        if(phoneNumber.getText().isEmpty() && birthDate.getText().isEmpty()) {
            wrongLogIn.setText("Venligst fyll inn mobil og fødselsnummer");
        }

        else {
            wrongLogIn.setText("Det finnes ingen bruker med dette mobil og fødselsnummeret ");
        }
    }


}