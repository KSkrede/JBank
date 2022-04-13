package jbank.controllers;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import jbank.Jbank;
import jbank.data.Person;
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

    Jbank skredebank;

    public void initialize() throws FileNotFoundException {
        skredebank = Jbank.getInstance();
        skredebank.getAccountSaver().readFile();
    }


    public void userLogIn(ActionEvent event) throws IOException {
        checkLogin();
    }

    public void newUser(ActionEvent event) throws IOException {
        skredebank.getApp().changeScene("newUser.fxml");
    }

    private void checkLogin() throws IOException {

        String userID = phoneNumber.getText().toString()+birthDate.getText().toString();
        Map<String, Person> accounts = skredebank.getAccountMap();

       Optional<String> account =  accounts.keySet().stream().filter(key -> userID.equals(key)).findFirst();

        if(account.isPresent()) {
            skredebank.getAccountObject().setLoggedInPerson(accounts.get(account.get()));
            wrongLogIn.setText("Suksessfull login!");
            skredebank.getApp().changeScene("bankID.fxml");
        }

        if(phoneNumber.getText().isEmpty() && birthDate.getText().isEmpty()) {
            wrongLogIn.setText("Venligst fyll inn mobil og fødselsnummer");
        }

        else {
            wrongLogIn.setText("Det finnes ingen bruker med dette mobil og fødselsnummeret ");
        }
    }


}