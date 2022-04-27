package jbank.controllers.jbank;

import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import jbank.Jbank;
import jbank.data.BankAccount;
import jbank.data.Person;
import jbank.logic.JBankHelp;

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
    @FXML
    private Button deleteButton;
    private ArrayList<BankAccount> loggedInPersonBankAccounts;
    private BankAccount selectedBankAccount;

    @FXML
    public void initialize() {
        jbank = Jbank.getInstance();
        loggedInPerson = jbank.getAccountObject().getLoggedInPerson();
        updateListView();

    }

    public void test() {

        System.out.println(jbank.getBankManager().getBankAccounts(loggedInPerson));

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
            bankInfo.getItems().add("Beløp: " + selectedBankAccount.getValue() + " kr");
            bankInfo.getItems().add("Her skal det komme kontoutskrift");
        }
    }

    public void updateListView() {
        bankList.getItems().clear();
        viewBankAccount();
        try {
            loggedInPersonBankAccounts = jbank.getBankManager().getBankAccounts(loggedInPerson);
        }

        catch (IllegalStateException e) {
            JBankHelp.showInformation(e.getMessage(), "Venligst lag en under fanen Bankkonto");
            loggedInPersonBankAccounts = new ArrayList<>();
        }
        bankList.getItems().addAll(loggedInPersonBankAccounts);
    }

    public void createBank(ActionEvent event) throws IOException {

        try {
            if (bankName.getText() == "" || bankAmount.getText() == "") {
                throw new IllegalArgumentException("Du må fylle ut alle feltene");
            }

            if (!JBankHelp.isAllLetters(bankName.getText())) {
                throw new IllegalArgumentException("Venligst bare bruk bokstaver i kontonavn");
            }

            if (!JBankHelp.isAllDigit(bankAmount.getText())) {
                throw new IllegalArgumentException("Venligst bare bruk tall i beløpet");
            }

            if (loggedInPersonBankAccounts.stream()
                    .anyMatch(BankAccount -> bankName.getText().equals(BankAccount.getName()))) {
                throw new IllegalArgumentException("Denne kontoen eksisterer allerede");
            }

            else {

                BankAccount bankAccount = new BankAccount(bankName.getText(), Integer.parseInt(bankAmount.getText()));
                jbank.getBankManager().addPerson(loggedInPerson.getUserId(), bankAccount);
                JBankHelp.showInformation("Ny bankkonto opprettet", bankAccount.toString());
            }

            updateListView();
        } catch (IllegalArgumentException e) {
            JBankHelp.showErrorMessage(e.getMessage());
        }

    }

    public void bankTransfer() {
        try {

            if (loggedInPersonBankAccounts.size() < 2) {
                throw new IllegalArgumentException("Du må ha minimum to kontoer for å overføre penger");
            }

            BankAccount source = JBankHelp.choseBankAccount(selectedBankAccount, loggedInPersonBankAccounts, "Overføring mellom kontoer", "Velg kontoen du ønsker å overføre penger fra", "Bankkonto: ");

            ArrayList<BankAccount> bankAccountsreduced = new ArrayList<BankAccount>(loggedInPersonBankAccounts);
            bankAccountsreduced.remove(source);
            BankAccount destination = JBankHelp.choseBankAccount(bankAccountsreduced.get(0), bankAccountsreduced, "Overføring mellom kontoer", "Velg kontoen du ønsker å overføre penger til", "Bankkonto: ");;
            int amount = JBankHelp.amount();

            jbank.getBankManager().movefunds(source, destination, amount);
        } catch (IllegalArgumentException e) {
            JBankHelp.showErrorMessage(e.getMessage());
        } catch (NoSuchElementException e) {
            JBankHelp.showErrorMessage("Du tokk ikke et valg");
        }
        updateListView();
    }

    public void deleteBankAccount() {
        try {
            if (loggedInPersonBankAccounts.size() < 1) {
                throw new IllegalArgumentException("Du kan ikke slette en konto før du har lagd en");
            }

            BankAccount bank = JBankHelp.choseBankAccount(selectedBankAccount, loggedInPersonBankAccounts,
                    "Sletting av konto", "Velg kontoen du ønsker å slette", "Bankkonto: ");
            Boolean choice = JBankHelp.confirm("Er du sikker på at du vil slette denne kontoen?");

            if (choice) {
                try {
                    if (selectedBankAccount == bank) {
                        selectedBankAccount = null;
                        bankInfo.getItems().clear();
                    }
                    jbank.getBankManager().deleteBankAccount(loggedInPerson, bank);
                } catch (IllegalArgumentException e) {
                    JBankHelp.showErrorMessage(e.getMessage());
                }
            }
        } catch (IllegalArgumentException e) {
            JBankHelp.showErrorMessage(e.getMessage());
        } catch (NoSuchElementException e) {
            JBankHelp.showErrorMessage("Du tokk ikke et valg");
        }

        updateListView();

    }
}
