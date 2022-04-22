package jbank.controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import jbank.Jbank;
import jbank.data.Person;
import jbank.logic.Help;

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

    Jbank jbank;

    public void initialize() {
        jbank = Jbank.getInstance();
    }

    public void createUser(ActionEvent event) throws IOException {

        try {
            if (phoneNumber.getText() == null || birthDate.getValue() == null) {
                throw new IllegalArgumentException("Du må fylle ut alle feltene");
            }

            String userID = phoneNumber.getText() + Help.dateToString(birthDate.getValue());
            
            if (jbank.getAccountMap().containsKey(userID)) {
                throw new IllegalArgumentException("Denne kontoen eksisterer allerede");

            }

            else if (!bankIDPin.getText().equals(confirmBankIDPin.getText())) {
                throw new IllegalArgumentException("BankID pin er ulik");

            } else {

                Person person = new Person(phoneNumber.getText(), birthDate.getValue(), givenName.getText(),
                        surName.getText(), bankIDPin.getText());
                jbank.getAccountObject().addPerson(person.getUserId(), person);
                jbank.getAccountSaver().writeFile(jbank.getAccountObject());
                Help.showInformation("Ny bruker laget", person.prettyString());
            }
        }

        catch (IllegalArgumentException e) {
            Help.showErrorMessage(e.getMessage());
        }

    }

    // public void newUser(ActionEvent event) throws IOException {
    //     jbank.getApp().changeScene("newUser.fxml");
    // }

    public void back(ActionEvent event) throws IOException {
        jbank.getApp().changeScene("login.fxml");
    }

}
