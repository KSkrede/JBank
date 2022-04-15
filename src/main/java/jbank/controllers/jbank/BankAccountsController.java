package jbank.controllers.jbank;

import java.io.IOException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import jbank.Jbank;
import jbank.data.BankAccount;
import jbank.data.BankAccounts;
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
    private ListView<BankAccount> bankList;

    ArrayList loggedInPersonBankAccounts;



    @FXML
    public void initialize() {
        jbank = Jbank.getInstance();
        loggedInPerson = jbank.getAccountObject().getLoggedInPerson();
        loggedInPersonBankAccounts = new ArrayList<>();
        //loggedInPersonBankAccounts = jbank.getBankAccounts().getBankAccounts(loggedInPerson);
        loggedInPersonBankAccounts.add(new BankAccount("test", 100));
    }

    public void updateListView(){
        ObservableList<BankAccount> observableBanks = FXCollections.observableArrayList(loggedInPersonBankAccounts);
        bankList.setItems(observableBanks);
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

            updateListView();
        }

        

        catch (IllegalArgumentException e) {
            Help.showErrorMessage(e.getMessage());
        }

    }

}
