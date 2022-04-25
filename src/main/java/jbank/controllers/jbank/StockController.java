package jbank.controllers.jbank;

import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import jbank.Jbank;
import jbank.data.BankAccount;
import jbank.data.Person;
import jbank.data.StockMarket;
import jbank.logic.JBankHelp;
import jbank.logic.StockTracker;

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
    @FXML
    private LineChart<Integer, Integer> indexChart;

    private ArrayList<String> loggedInPersonStocks;
    private String selectedStock;
    private ArrayList<String> allStocks;
    private ArrayList<String> ownedStocks;
    private StockMarket stockmarket;
    private StockTracker stockTracker;

    @FXML
    public void initialize() {
        jbank = Jbank.getInstance();
        loggedInPerson = jbank.getAccountObject().getLoggedInPerson();
        stockmarket = jbank.getStockMarket();
        allStocks = stockmarket.getTickers();
        stockTracker = jbank.getStockTracker();
        updateViews();

        // https://stackoverflow.com/questions/9722418/how-to-handle-listview-item-clicked-action?rq=1
        stockList.setOnMouseClicked(me -> {
            selectedStock = stockList.getSelectionModel().getSelectedItem();
            updateStockInfo();
        });

        stockOwned.setOnMouseClicked(me -> {
            selectedStock = stockOwned.getSelectionModel().getSelectedItem();
            updateStockInfo();
        });

    }

    public void test() {
        System.out.println(stockTracker.getStocklogs());

    }

    public void updateViews() {
        updateStockView();
        updateStockInfo();
        updateStockOwnedView();
        updateStockChart();

    }

    public void updateStockInfo() {
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


    public void updateStockChart(){
        indexChart.getData().clear();
        int latestDay = stockTracker.getDay();

        for (String stock : allStocks) {
            XYChart.Series<Integer, Integer> stockData = new XYChart.Series<>();
            stockData.setName(stock);
            stockTracker.getStocklogs().forEach((day, stocks)->stockData.getData().add(new XYChart.Data<>(day, stocks.get(stock))));
            indexChart.getData().add(stockData);
        }

        //stockTracker.getStocklogs().forEach((day, stocks)->stockdata.getData().add(new XYChart.Data<>(day, stocks.get("Aksje1"))));
        // System.out.println(stockdata);

        // for (int day = 0; day == totalDays; day++) {
        //     stockdata.getData().add(new XYChart.Data<>(day, stockTracker.getStockprice(day, "Aksje1")));
        //     System.out.println(day + stockTracker.getStockprice(day, "Aksje1") );
        // }


       

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
                stockTracker.logNew(ticker.getText());
                JBankHelp.showInformation("Ny aksje impotert", ticker.getText());
            }
        } catch (IllegalArgumentException e) {
            JBankHelp.showErrorMessage(e.getMessage());
        }
        updateViews();
    }

    public void buyStock() {
        System.out.println("nå kjøpes det aksjer");
        String stockToBuy = JBankHelp.choseStock(selectedStock, allStocks);
        int number = JBankHelp.number();
        ArrayList<BankAccount> loggedInPersonBankManager = jbank.getBankManager().getBankAccount(loggedInPerson);
        BankAccount source = JBankHelp.choseBankAccount(loggedInPersonBankManager.get(0), loggedInPersonBankManager,
                "Overføring mellom kontoer", "Velg kontoen du ønsker å kjøpe " + stockToBuy + " fra", "Bankkonto: ");
        if (!jbank.getBankManager().hasFunds(source, number * stockmarket.getValue(stockToBuy))) {
            JBankHelp.showErrorMessage("Du har ikke råd til å kjøpe disse aksjene");
        }

        else {
            jbank.getStockMarket().buy(loggedInPerson.getUserId(), stockToBuy, number);
            System.out.println("Aksjer er kjøpt");
        }
        updateViews();
    }

}
