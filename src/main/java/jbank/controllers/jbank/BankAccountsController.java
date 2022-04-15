package jbank.controllers.jbank;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import jbank.Jbank;
import jbank.data.BankAccount;
import jbank.data.Person;
import jbank.logic.Help;

public class BankAccountsController {

    Jbank jbank;
    Person loggedInPerson;

    @FXML
    private TextField bankName;
    @FXML
    private TextField bankAmount;
    @FXML
    private Button createBank;

    @FXML
    public void initialize() {
        jbank = Jbank.getInstance();
        loggedInPerson = jbank.getAccountObject().getLoggedInPerson();
    }

    public void createBank(ActionEvent event) throws IOException {

        try {
            if (bankName.getText() == null || bankAmount.getText() == null) {
                throw new IllegalArgumentException("Du må fylle ut alle feltene");
            }

            // if (jbank.accounts.getLoggedInPerson().contains bankaccount) {
            // throw new IllegalArgumentException("Denne kontoen eksisterer allerede");

            // }

            else {

                BankAccount bankAccount = new BankAccount(bankName.getText(), Integer.parseInt(bankAmount.getText()));
                jbank.getBankAccounts().addAccounts(loggedInPerson.getUserId(), bankAccount);
                // jbank.getAccountSaver().writeFile(jbank.getAccountObject());
                Help.showInformation("Ny bankkonto lagd", bankAccount.toString());
            }
        }

        catch (IllegalArgumentException e) {
            Help.showErrorMessage(e.getMessage());
        }

    }

}
