package jbank.controllers.jbank;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import jbank.Jbank;
import jbank.data.BankAccount;
import jbank.data.Person;
import jbank.data.StockMarket;
import jbank.logic.JBankHelp;
import jbank.logic.StockTracker;

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
    private LineChart<Integer, Integer> totalValueChart;
    @FXML
    private Button test;

    private String selectedItem;
    private ArrayList<String> allStocks;
    private ArrayList<String> ownedStocks;
    private StockMarket stockmarket;
    private StockTracker stockTracker;
    private ArrayList<BankAccount> loggedInPersonBankAccounts;

    @FXML
    public void initialize() {
        jbank = Jbank.getInstance();
        loggedInPerson = jbank.getAccountObject().getLoggedInPerson();
        stockmarket = jbank.getStockMarket();
        allStocks = stockmarket.getTickers();
        stockTracker = jbank.getStockTracker();
        updateViews();

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

    }

    public void test() {
        updateViews();

    }

    public void updateViews() {
        updateBankList();
        updateInfo();
        updateStockOwnedView();
        updateStockChart();

    }

    public void updateInfo() {
        if (selectedItem == null) {
            info.getItems().add("");
        } else {
            if (bankList.getSelectionModel().getSelectedItem() == null) {
                // do nothing
            } else if (selectedItem == bankList.getSelectionModel().getSelectedItem().toString()) {
                info.getItems().clear();
                info.getItems().add(selectedItem);
                info.getItems().add("Bank");
            }

            else if (selectedItem == stockOwned.getSelectionModel().getSelectedItem() && selectedItem != null) {
                // int value = stockmarket.getValue(selectedItem);
                // int number = stockmarket.numberOwnedStocks(loggedInPerson.getUserId(),
                // selectedItem);

                info.getItems().clear();
                info.getItems().add(selectedItem);
                info.getItems().add("Stock");
                // info.getItems().add("Verdi: " + value + "kr");
                // info.getItems().add("Antall du eier: " + number);
                // info.getItems().add("Total verdi: " + number * value + "kr");
            }
        }
    }

    public void updateBankList() {
        try {
            loggedInPersonBankAccounts = jbank.getBankManager().getBankAccounts(loggedInPerson);
            System.out.println(loggedInPersonBankAccounts);
        }

        catch (IllegalStateException e) {
            JBankHelp.showInformation(e.getMessage(), "Venligst lag en under fanen Bankkonto");
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

    public void updateStockChart() {
        totalValueChart.getData().clear();

        for (String stock : allStocks) {
            XYChart.Series<Integer, Integer> stockData = new XYChart.Series<>();
            stockData.setName(stock);
            stockTracker.getStocklogs()
                    .forEach((day, stocks) -> stockData.getData().add(new XYChart.Data<>(day, stocks.get(stock))));
            totalValueChart.getData().add(stockData);
        }

    }
}
