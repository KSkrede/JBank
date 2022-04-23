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
import jbank.logic.Help;

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
    private Button transferButton;
    @FXML
    private Button deleteButton;
    private ArrayList<String> loggedInPersonStocks;
    private String selecteStock;
    private ArrayList<String> allStocks;
    private StockMarket stockmarket;

    @FXML
    public void initialize() {
        jbank = Jbank.getInstance();
        loggedInPerson = jbank.getAccountObject().getLoggedInPerson();
        stockmarket = jbank.stockMarket;
        allStocks = stockmarket.getTickers();
        updateListView();

    }

    public void test() {

        System.out.println(allStocks);

    }

    public void viewStockInfo() {
        // https://stackoverflow.com/questions/9722418/how-to-handle-listview-item-clicked-action?rq=1
        stockList.setOnMouseClicked(me -> {
            selecteStock = stockList.getSelectionModel().getSelectedItem();
        });
        if (selecteStock == null) {
            stockInfo.getItems().add("");
        } else {
            stockInfo.getItems().clear();
            stockInfo.getItems().add(selecteStock);
            stockInfo.getItems().add("Beløp: "  + " kr");
            stockInfo.getItems().add("Her skal det komme kontoutskrift");
        }
    }

    public void updateListView() {
        stockList.getItems().clear();
        viewStockInfo();
        try {
            allStocks = jbank.getStockMarket().getTickers();
        }

        catch (IllegalStateException e) {
            Help.showInformation(e.getMessage(), "Venligst lag en under fanen Aksjer");
            loggedInPersonStocks = new ArrayList<>();
        }
        stockList.getItems().addAll(allStocks);
    }

    public void importStock() throws IOException {

        try {
            if (ticker.getText() == null || value.getText() == null) {
                throw new IllegalArgumentException("Du må fylle ut alle feltene");
            }

            if (allStocks.stream()
                    .anyMatch(Stock -> ticker.getText().equals(Stock))) {
                throw new IllegalArgumentException("Denne akjsen eksisterer allerede");

            }

            else {
                stockmarket.update(ticker.getText(), Integer.parseInt(value.getText()));
                Help.showInformation("Ny aksje impotert", ticker.getText());
            }

            updateListView();
        } catch (IllegalArgumentException e) {
            Help.showErrorMessage(e.getMessage());
        }

    }
}
