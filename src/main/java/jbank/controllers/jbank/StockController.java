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
import jbank.data.StockMarket;
import jbank.logic.JBankHelp;

public class StockController {

    Jbank jbank;
    Person loggedInPerson;

    @FXML
    private TextField ticker;
    @FXML
    private TextField value;
    @FXML
    private Button importStock;
    @FXML
    private Button test;
    @FXML
    private ListView<String> stockList;
    @FXML
    private ListView<String> stockInfo;
    @FXML
    private ListView<String> stockOwned;
    @FXML
    private Button transferButton;
    @FXML
    private Button buyStock;
    @FXML
    private Button deleteButton;
    private ArrayList<String> loggedInPersonStocks;
    private String selectedStock;
    private ArrayList<String> allStocks;
    private ArrayList<String> ownedStocks;
    private StockMarket stockmarket;

    @FXML
    public void initialize() {
        jbank = Jbank.getInstance();
        loggedInPerson = jbank.getAccountObject().getLoggedInPerson();
        stockmarket = jbank.stockMarket;
        allStocks = stockmarket.getTickers();
        updateViews();

    }

    public void test() {
        System.out.println(stockmarket.listOwnedStocks(loggedInPerson.getUserId()));

    }

    public void updateViews() {
        updateStockView();
        updateStockInfo();
        updateStockOwnedView();

    }

    public void updateStockInfo() {
        // https://stackoverflow.com/questions/9722418/how-to-handle-listview-item-clicked-action?rq=1
        stockList.setOnMouseClicked(me -> {
            selectedStock = stockList.getSelectionModel().getSelectedItem();
            updateStockInfo();
        });

        stockOwned.setOnMouseClicked(me -> {
            selectedStock = stockOwned.getSelectionModel().getSelectedItem();
            updateStockInfo();
        });

        if (selectedStock == null) {
            stockInfo.getItems().add("");
        } else {
            if (selectedStock == stockList.getSelectionModel().getSelectedItem() ){
                stockInfo.getItems().clear();
                stockInfo.getItems().add(selectedStock);
                stockInfo.getItems().add("Verdi: " + stockmarket.getValue(selectedStock).toString() + "kr");
                stockInfo.getItems().add("Public stocks");
            }

            else if(selectedStock == stockOwned.getSelectionModel().getSelectedItem()) {
                int value = stockmarket.getValue(selectedStock);
                int number = stockmarket.numberOwnedStocks(loggedInPerson.getUserId(), selectedStock);

                stockInfo.getItems().clear();
                stockInfo.getItems().add(selectedStock);
                stockInfo.getItems().add("Verdi: "+ value + "kr");
                stockInfo.getItems().add("Antall du eier: " + number);
                stockInfo.getItems().add("Total verdi: " + number*value + "kr");
            }
        }
    }

    public void updateStockView() {
        stockList.getItems().clear();
        try {
            allStocks = jbank.getStockMarket().getTickers();
        }

        catch (IllegalStateException e) {
            JBankHelp.showInformation(e.getMessage(), "Venligst lag en under fanen Aksjer");
            loggedInPersonStocks = new ArrayList<>();
        }
        stockList.getItems().addAll(allStocks);
    }

    public void updateStockOwnedView() {
        stockOwned.getItems().clear();
        try {
            ownedStocks = jbank.getStockMarket().listOwnedStocks(loggedInPerson.getUserId());
        }
        catch (IllegalStateException e) {
            ownedStocks = new ArrayList<>();
        }
        stockOwned.getItems().addAll(ownedStocks);
    }

    public void importStock() throws IOException {
        try {
            if (ticker.getText() == null || value.getText() == null) {
                throw new IllegalArgumentException("Du må fylle ut alle feltene");
            }

            if (allStocks.stream()
                    .anyMatch(Stock -> ticker.getText().equals(Stock))) {
                throw new IllegalArgumentException("Denne akjsen eksisterer allerede");
            } else {
                stockmarket.update(ticker.getText(), Integer.parseInt(value.getText()));
                JBankHelp.showInformation("Ny aksje impotert", ticker.getText());
            }

            updateStockView();
        } catch (IllegalArgumentException e) {
            JBankHelp.showErrorMessage(e.getMessage());
        }
        updateViews();
    }

    public void buyStock() {
        System.out.println("nå kjøpes det aksjer");
        String stockToBuy = JBankHelp.choseStock(selectedStock, allStocks);
        int number = JBankHelp.number();
        ArrayList<BankAccount> loggedInPersonBankAccounts = jbank.bankAccounts.getBankAccounts(loggedInPerson);
        BankAccount source = JBankHelp.choseBankAccount(loggedInPersonBankAccounts.get(0), loggedInPersonBankAccounts,
                "Overføring mellom kontoer", "Velg kontoen du ønsker å kjøpe " + stockToBuy + " fra", "Bankkonto: ");
        if (!jbank.bankAccounts.hasFunds(source, number * stockmarket.getValue(stockToBuy))) {
            JBankHelp.showErrorMessage("Du har ikke råd til å kjøpe disse aksjene");
        }

        else {
            jbank.stockMarket.buy(loggedInPerson.getUserId(), stockToBuy, number);
            System.out.println("Aksjer er kjøpt");
        }
        updateViews();
    }

}
