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

    Jbank jbank;

    public void initialize() throws FileNotFoundException {
        jbank = Jbank.getInstance();
        jbank.getAccountSaver().readFile();
        if (jbank.getAccountMap().isEmpty()){
            wrongLogIn.setText("Det er foreløpig ingen kontoer lagret");
        }
    }


    public void userLogIn(ActionEvent event) throws IOException {
        checkLogin();
    }

    public void newUser(ActionEvent event) throws IOException {
        jbank.getApp().changeScene("newUser.fxml");
    }

    private void checkLogin() throws IOException {

        String userID = phoneNumber.getText().toString()+birthDate.getText().toString();
        Map<String, Person> accounts = jbank.getAccountMap();

       Optional<String> account =  accounts.keySet().stream().filter(key -> userID.equals(key)).findFirst();

        if(account.isPresent()) {
            login(accounts, account);
            wrongLogIn.setText("Suksessfull login!");
            jbank.getApp().changeScene("bankID.fxml");
        }

        if(phoneNumber.getText().isEmpty() && birthDate.getText().isEmpty()) {
            wrongLogIn.setText("Venligst fyll inn mobil og fødselsdato");
        }

        else {
            wrongLogIn.setText("Det finnes ingen bruker med dette mobilnummeret og fødselsdato ");
        }
    }


    private void login(Map<String, Person> accounts, Optional<String> account) {
        jbank.getAccountObject().setLoggedInPerson(accounts.get(account.get()));
    }


}