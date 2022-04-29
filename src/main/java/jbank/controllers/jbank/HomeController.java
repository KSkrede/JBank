package jbank.controllers.jbank;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import jbank.Jbank;
import jbank.data.BankAccount;
import jbank.data.BankManager;
import jbank.data.Person;
import jbank.data.StockMarket;
import jbank.logic.JBankHelp;

public class HomeController {

    Jbank jbank;
    Person loggedInPerson;

    @FXML
    private ListView<BankAccount> bankList;
    @FXML
    private ListView<String> info;
    @FXML
    private ListView<String> stockOwned;
    @FXML
    private Label sumStocks;
    @FXML
    private Label sumBank;
    @FXML
    private Label totalSum;

    private String selectedItem;
    private ArrayList<String> ownedStocks;
    private StockMarket stockmarket;
    private BankManager bankManager;
    private ArrayList<BankAccount> loggedInPersonBankAccounts;

    @FXML
    public void initialize() {
        jbank = Jbank.getInstance();
        loggedInPerson = jbank.getAccountObject().getLoggedInPerson();
        stockmarket = jbank.getStockMarket();
        bankManager = jbank.getBankManager();
        try {
            jbank.getBankManagerSaver().readObject(loggedInPerson.getUserId(), jbank);
            jbank.getStockMarketSaver().readObject(loggedInPerson.getUserId(), jbank);
        } catch (IllegalArgumentException e) {
            JBankHelp.showErrorMessage(e.getMessage());
        }

        // https://stackoverflow.com/questions/9722418/how-to-handle-listview-item-clicked-action?rq=1
        bankList.setOnMouseClicked(me -> {
            if (bankList.getSelectionModel().getSelectedItem() == null) {
                selectedItem = null;
            } else {
                selectedItem = bankList.getSelectionModel().getSelectedItem().toString();
                updateInfo();
            }
        });

        stockOwned.setOnMouseClicked(me -> {
            if (stockOwned.getSelectionModel().getSelectedItem() == null) {
                selectedItem = null;
            } else {
                selectedItem = stockOwned.getSelectionModel().getSelectedItem();
                updateInfo();
            }
        });
        updateViews();

    }

    public void updateViews() {
        updateBankList();
        updateInfo();
        updateStockOwnedView();
        updateSum();

    }

    public void updateInfo() {
        try { 
            if(selectedItem == null){
                //Do nothing
            }

            else if (selectedItem == stockOwned.getSelectionModel().getSelectedItem()) {
                int value = stockmarket.getValue(selectedItem);
                int number = stockmarket.numberOwnedStocks(loggedInPerson.getUserId(), selectedItem);

                info.getItems().clear();
                info.getItems().add(selectedItem);
                info.getItems().add("Verdi: " + value + "kr");
                info.getItems().add("Antall du eier: " + number);
                info.getItems().add("Total verdi: " + number * value + "kr");
            }

            else if (selectedItem == bankList.getSelectionModel().getSelectedItem().toString()) {
                info.getItems().clear();
                info.getItems().add(selectedItem);
                info.getItems().add("Verdi: " + bankManager.getValue(loggedInPerson, selectedItem));
            }

        } catch (NullPointerException e) {
            //Do nothing when no item is selected
        }
    }

    public void updateBankList() {
        try {
            loggedInPersonBankAccounts = jbank.getBankManager().getBankAccounts(loggedInPerson);
        }

        catch (IllegalStateException e) {
            loggedInPersonBankAccounts = new ArrayList<>();
        }
        bankList.getItems().clear();
        bankList.getItems().addAll(loggedInPersonBankAccounts);
    }

    public void updateStockOwnedView() {
        stockOwned.getItems().clear();
        try {
            ownedStocks = jbank.getStockMarket().listOwnedStocks(loggedInPerson.getUserId());
        } catch (IllegalStateException e) {
            ownedStocks = new ArrayList<>();
        }
        stockOwned.getItems().addAll(ownedStocks);
    }

    public void updateSum() {
        int sum = jbank.sumBankAccounts() + jbank.sumStocks();
        sumBank.setText("Sum valuta: " + jbank.sumBankAccounts() + "kr");
        sumStocks.setText("Sum aksjer: " + jbank.sumStocks() + "kr");
        totalSum.setText("Sum totalt: " + sum + "kr");

    }
}
