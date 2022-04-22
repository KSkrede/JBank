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
import jbank.data.Accounts;
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
    private Button back;
    @FXML
    private Button removePersonButton;
    @FXML
    private Button changeGivenName;
    @FXML
    private Button changeSurName;
    @FXML
    private Button changePin;

    @FXML
    public void initialize() {
        jbank = Jbank.getInstance();
        loggedInPerson = jbank.getAccountObject().getLoggedInPerson();
        givenName.setText("Fornavn: " + loggedInPerson.getGivenName());
        surName.setText("Etternavn: " + loggedInPerson.getSurName());
        birthDate.setText("Fødselsdato : " + loggedInPerson.getBirthday());
        phoneNumber.setText("Mobilnummer : " + loggedInPerson.getPhoneNumber());
    }

    public void removePerson() throws IOException {
        if (Help.confirm("Er du sikker på at du vil slette kontoen din?")) {
            jbank.getAccountObject().removePerson();
            jbank.getAccountSaver().writeFile(jbank.getAccountObject());
            jbank.getApp().changeScene("login.fxml");
        }
    }

    public void changeGivenName() throws IOException {
        if (Help.confirm("Er du sikker på at du vil endre fornavnet ditt?")) {
            String newName = Help.name();
            jbank.getAccountObject().changeGivenName(newName);
            jbank.getAccountSaver().writeFile(jbank.getAccountObject());
            Help.showInformation("Navnebytte", "Navnebyttet var vellyket, velkommen tilbake " + newName + "\n Venligst logg inn på nytt");
            jbank.getApp().changeScene("login.fxml");
        }
    }

    public void changeSurName() throws IOException {
        if (Help.confirm("Er du sikker på at du vil endre etternavnet ditt?")) {
            String newName = Help.name();
            jbank.getAccountObject().changeSurName(newName);
            jbank.getAccountSaver().writeFile(jbank.getAccountObject());
            Help.showInformation("Navnebytte", "Navnebyttet var vellyket, ditt nye etternavn er nå " + newName + "\n Venligst logg inn på nytt");
            jbank.getApp().changeScene("login.fxml");
        }
    }

    public void changePin() throws IOException {
        String currentPin = Help.pin("Skriv inn din nåværene pin: ");
        if (currentPin.equals(loggedInPerson.getBankIDPin())){
            String newPin = Help.pin("Skriv inn ny pin: ");
            jbank.getAccountObject().changePin(newPin);
            jbank.getAccountSaver().writeFile(jbank.getAccountObject());
            Help.showInformation("Pin bytte", "Pin bytte var vellyket, dinn nye pin er nå " + newPin + "\n Venligst logg inn på nytt");
            jbank.getApp().changeScene("login.fxml");
        }
    }

    public void back() throws IOException {
        jbank.getApp().changeScene("jbank/jBank.fxml");
    }

}
