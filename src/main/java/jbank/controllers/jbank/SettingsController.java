package jbank.controllers.jbank;

import java.io.IOException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import jbank.Jbank;
import jbank.data.BankAccount;
import jbank.data.Person;
import jbank.logic.Help;

public class SettingsController {

    Jbank jbank;
    Person loggedInPerson;

    @FXML
    private Label givenName;
    @FXML
    private Label surName;
    @FXML
    private Label phoneNumber;
    @FXML
    private Label birthDate;




    @FXML
    public void initialize() {
        jbank = Jbank.getInstance();
        loggedInPerson = jbank.getAccountObject().getLoggedInPerson();
        //surName.setText(loggedInPerson.getSurName());
        givenName.setText(loggedInPerson.getGivenName());
    }




}
