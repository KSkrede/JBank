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
import skredebank.logic.files.AccountSaver;
import javafx.event.ActionEvent;

import java.io.FileNotFoundException;
import java.io.IOException;
public class LogInController {

    public LogInController() {

    }

    AccountSaver test2;


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

    public void initialize() throws FileNotFoundException {
        //test.readFile(a);
        //nameLabel.setText(loggedInUser.getGivenName()); 
    }


    public void userLogIn(ActionEvent event) throws IOException {
        //checkLogin();
        test2.printFile(a);
    }

    public void newUser(ActionEvent event) throws IOException {
        m.changeScene("newUser.fxml");
    }

    private void checkLogin() throws IOException {
        //Creates new main
        if(phoneNumber.getText().toString().equals("40612594") && birthDate.getText().toString().equals("230100")) {
            wrongLogIn.setText("Suksessfull login!");

            m.changeScene("bankID.fxml");
        }

        else if(phoneNumber.getText().isEmpty() && birthDate.getText().isEmpty()) {
            wrongLogIn.setText("Venligst fyll inn mobil og fødselsnummer");
        }


        else {
            wrongLogIn.setText("Det finnes ingen bruker med dette mobil og fødselsnummeret ");
        }
    }


}