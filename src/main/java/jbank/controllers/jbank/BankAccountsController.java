package jbank.controllers.jbank;

import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
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
    private Button test;
    @FXML
    private ListView<BankAccount> bankList;
    @FXML
    private ListView<String> bankInfo;
    @FXML
    private Button transferButton;
    private ArrayList<BankAccount> loggedInPersonBankAccounts;
    private BankAccount selectedBankAccount;
    private BankAccount chosenBankAccount;

    @FXML
    public void initialize() {
        jbank = Jbank.getInstance();
        loggedInPerson = jbank.getAccountObject().getLoggedInPerson();
        updateListView();

    }

    public void test() {
        System.out.println(selectedBankAccount);
        bankInfo.getItems().add("test");
        
    }

    public void viewBankAccount() {
        // https://stackoverflow.com/questions/9722418/how-to-handle-listview-item-clicked-action?rq=1
        bankList.setOnMouseClicked(me -> {
            selectedBankAccount = bankList.getSelectionModel().getSelectedItem();
            viewBankAccount();
        });
        if (selectedBankAccount == null) {
            bankInfo.getItems().add("");
        } else {
            bankInfo.getItems().clear();
            bankInfo.getItems().add(selectedBankAccount.toString());
            bankInfo.getItems().add("Her skal det komme kontoutskrift");
        }
    }

    public void updateListView() {
        bankList.getItems().clear();
        viewBankAccount();
        try {
            loggedInPersonBankAccounts = jbank.getBankAccounts().getBankAccounts(loggedInPerson);
        }

        catch (IllegalStateException e) {
            Help.showInformation(e.getMessage(), "Venligst lag en under fanen Bankkonto");
            loggedInPersonBankAccounts = new ArrayList<>();
        }
        System.out.println(loggedInPersonBankAccounts);
        bankList.getItems().addAll(loggedInPersonBankAccounts);
    }

    public void createBank(ActionEvent event) throws IOException {

        try {
            if (bankName.getText() == null || bankAmount.getText() == null) {
                throw new IllegalArgumentException("Du må fylle ut alle feltene");
            }

            if (loggedInPersonBankAccounts.stream().anyMatch(BankAccount -> bankName.getText().equals(BankAccount.getName()))) {
            throw new IllegalArgumentException("Denne kontoen eksisterer allerede");

            }

            else {

                BankAccount bankAccount = new BankAccount(bankName.getText(), Integer.parseInt(bankAmount.getText()));
                jbank.getBankAccounts().addAccounts(loggedInPerson.getUserId(), bankAccount);
                // jbank.getAccountSaver().writeFile(jbank.getAccountObject());
                Help.showInformation("Ny bankkonto lagd", bankAccount.toString());
            }

            updateListView();
        } catch (IllegalArgumentException e) {
            Help.showErrorMessage(e.getMessage());
        }

    }

    public void chooseBankAccount(){
        chosenBankAccount = Help.choseBankAccount(selectedBankAccount, loggedInPersonBankAccounts, "Overføring mellom kontoer", "Velg kontoen du ønsker å overføre penger fra", "Bankkonto: ");
        System.out.println(chosenBankAccount);
    }
}
