package jbank.controllers.jbank;

import java.io.IOException;
import java.util.NoSuchElementException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import jbank.Jbank;
import jbank.data.Person;
import jbank.logic.JBankHelp;

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
        birthDate.setText("Fødselsdato: " + loggedInPerson.getBirthday());
        phoneNumber.setText("Mobilnummer: " + loggedInPerson.getPhoneNumber());
    }

    public void removePerson() throws IOException {
        if (JBankHelp.confirm("Er du sikker på at du vil slette kontoen din?")) {
            jbank.getAccountObject().removePerson();
            jbank.getAccountSaver().writeAccounts("accounts", jbank.getAccountObject());
            jbank.getApp().changeScene("login.fxml");
        }
    }

    public void changeGivenName() throws IOException {
        if (JBankHelp.confirm("Er du sikker på at du vil endre fornavnet ditt?")) {
            String newName = JBankHelp.name();
            if (!JBankHelp.isAllLettersOrBlank(newName) || newName.equals("")) {
                JBankHelp.showErrorMessage("Ulovlig navn " + newName);
            } else {
                jbank.getAccountObject().changeGivenName(newName);
                jbank.getAccountSaver().writeAccounts("accounts", jbank.getAccountObject());
                JBankHelp.showInformation("Navnebytte",
                        "Navnebyttet var vellyket, velkommen tilbake " + newName + "\n Venligst logg inn på nytt");
                jbank.getApp().changeScene("login.fxml");
            }
        }
    }

    public void changeSurName() throws IOException {
        if (JBankHelp.confirm("Er du sikker på at du vil endre etternavnet ditt?")) {
            String newName = JBankHelp.name();
            if (!JBankHelp.isAllLetters(newName) || newName.equals("")) {
                JBankHelp.showErrorMessage("Ulovlig navn " + newName);
            } else {
                jbank.getAccountObject().changeSurName(newName);
                jbank.getAccountSaver().writeAccounts("accounts", jbank.getAccountObject());
                JBankHelp.showInformation("Navnebytte", "Navnebyttet var vellyket, ditt nye etternavn er nå " + newName
                        + "\n Venligst logg inn på nytt");
                jbank.getApp().changeScene("login.fxml");
            }
        }
    }

    public void changePin() throws IOException {
        try {
            String currentPin = JBankHelp.pin("Skriv inn din nåværene pin: ");
            if (currentPin.equals(loggedInPerson.getPin())) {
                String newPin = JBankHelp.pin("Skriv inn ny pin: ");
                if (!JBankHelp.isAllDigit(newPin) || newPin.equals("")) {
                    JBankHelp.showErrorMessage("Ulovlig pin");
                } else {
                    jbank.getAccountObject().changePin(newPin);
                    jbank.getAccountSaver().writeAccounts("accounts", jbank.getAccountObject());
                    JBankHelp.showInformation("Pin bytte",
                            "Pin bytte var vellyket, dinn nye pin er nå " + newPin + "\n Venligst logg inn på nytt");
                    jbank.getApp().changeScene("login.fxml");
                }
            }
            else{
                JBankHelp.showErrorMessage(currentPin + "Er ikke din pin");
            }
        } catch (IllegalArgumentException e) {
            JBankHelp.showErrorMessage(e.getMessage());
        } catch (NoSuchElementException e) {
            JBankHelp.showErrorMessage("Du skrev ikke inn noe pin");
        }
    }

    public void back() throws IOException {
        jbank.getApp().changeScene("jbank/jBank.fxml");
    }

}
