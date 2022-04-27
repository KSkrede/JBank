package jbank.controllers.jbank;

import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import jbank.Jbank;
import jbank.data.BankAccount;
import jbank.data.Person;
import jbank.data.StockMarket;
import jbank.data.StockTracker;
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
    private Button updateButton;
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
    private Button sellStock;
    @FXML
    private Button deleteButton;
    @FXML
    private LineChart<Integer, Integer> indexChart;
    @FXML
    private AnchorPane importStockPane;

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
            if (selectedStock == stockList.getSelectionModel().getSelectedItem()) {
                stockInfo.getItems().clear();
                stockInfo.getItems().add(selectedStock);
                stockInfo.getItems().add("Verdi: " + stockmarket.getValue(selectedStock).toString() + "kr");
                stockInfo.getItems().add("Public stocks");
            }

            else if (selectedStock == stockOwned.getSelectionModel().getSelectedItem()) {
                int value = stockmarket.getValue(selectedStock);
                int number = stockmarket.numberOwnedStocks(loggedInPerson.getUserId(), selectedStock);

                stockInfo.getItems().clear();
                stockInfo.getItems().add(selectedStock);
                stockInfo.getItems().add("Verdi: " + value + "kr");
                stockInfo.getItems().add("Antall du eier: " + number);
                stockInfo.getItems().add("Total verdi: " + number * value + "kr");
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
            allStocks = new ArrayList<>();
        }
        stockList.getItems().addAll(allStocks);
    }

    public void updateStockOwnedView() {
        try {
            ownedStocks = jbank.getStockMarket().listOwnedStocks(loggedInPerson.getUserId());
        } catch (IllegalStateException e) {
            ownedStocks = new ArrayList<>();
        }
        stockOwned.getItems().clear();
        stockOwned.getItems().addAll(ownedStocks);
    }

    public void updateStockChart() {
        indexChart.getData().clear();

        for (String stock : allStocks) {
            XYChart.Series<Integer, Integer> stockData = new XYChart.Series<>();
            stockData.setName(stock);
            stockTracker.getStocklogs()
                    .forEach((day, stocks) -> stockData.getData().add(new XYChart.Data<>(day, stocks.get(stock))));
            indexChart.getData().add(stockData);
        }
        XYChart.Series<Integer, Integer> index = new XYChart.Series<>();
        index.setName("Indeks snitt");
        stockTracker.getIndexlogs().forEach((day, avgIndex) -> index.getData().add(new XYChart.Data<>(day, avgIndex)));
        indexChart.getData().add(index);

    }

    public void importStock() throws IOException {
        try {
            if (ticker.getText() == null || value.getText() == null || !JBankHelp.isAllLetters(ticker.getText())) {
                throw new IllegalArgumentException("Du må fylle ut alle feltene");
            }
            if (value.getText() == null || !JBankHelp.isAllDigit(value.getText())) {
                throw new IllegalArgumentException("Ugyidlge tegn i verdien");
            }


            if (allStocks.stream()
                    .anyMatch(Stock -> ticker.getText().equals(Stock))) {
                throw new IllegalArgumentException("Denne akjsen eksisterer allerede");
            } else {
                stockmarket.update(ticker.getText(), Integer.parseInt(value.getText()));
                JBankHelp.showInformation("Ny aksje impotert", ticker.getText());
            }
        } catch (IllegalArgumentException e) {
            JBankHelp.showErrorMessage(e.getMessage());
        }
        updateViews();
    }

    public void buyStock() {

            try {
                jbank.getBankManager().getBankAccounts(loggedInPerson);
                String stockToBuy = JBankHelp.choseStock(selectedStock, allStocks);
                int number = JBankHelp.number();
                ArrayList<BankAccount> loggedInPersonBankAccounts = jbank.getBankManager()
                        .getBankAccounts(loggedInPerson);
                BankAccount source = JBankHelp.choseBankAccount(loggedInPersonBankAccounts.get(0),
                        loggedInPersonBankAccounts,
                        "Overføring mellom kontoer", "Velg kontoen du ønsker å kjøpe " + stockToBuy + " fra",
                        "Bankkonto: ");

                jbank.buyStocks(stockToBuy, number, source, loggedInPerson.getUserId());
                jbank.getStockMarketSaver().writeObject(loggedInPerson.getUserId(), jbank);
            } catch (IOException e) {
                JBankHelp.showErrorMessage("Det har skjedd noe feil under lagring, data kan ha gått tapt.");
            } catch (IllegalArgumentException | IllegalStateException e) {
                JBankHelp.showErrorMessage(e.getMessage());
            } catch (IndexOutOfBoundsException e) {
                JBankHelp.showErrorMessage("Du kan ikke kjøpe aksjer uten en bankkonto");
            }
             catch (NoSuchElementException e) {
            JBankHelp.showErrorMessage("Du må ta et valg");
        }
            updateViews();
        }

    public void sellStock() {

        if (ownedStocks.isEmpty()) {
            JBankHelp.showErrorMessage("Du kan ikke selge aksjer når du ikke eier noen");
        } else {

            try {
            String stockToSell = JBankHelp.choseStock(selectedStock, ownedStocks);
            int number = JBankHelp.number();

                ArrayList<BankAccount> loggedInPersonBankAccounts = jbank.getBankManager()
                        .getBankAccounts(loggedInPerson);
                BankAccount destination = JBankHelp.choseBankAccount(loggedInPersonBankAccounts.get(0),
                        loggedInPersonBankAccounts,
                        "Overføring mellom kontoer", "Velg kontoen du å plassere gevinst fra " + stockToSell + " fra",
                        "Bankkonto: ");

                jbank.sellStocks(stockToSell, number, destination, loggedInPerson.getUserId());
                jbank.getStockMarketSaver().writeObject(loggedInPerson.getUserId(), jbank);
            } catch (IOException e) {
                JBankHelp.showErrorMessage("Det har skjedd noe feil under lagring, data kan ha gått tapt.");
            } catch (IllegalArgumentException | IllegalStateException e) {
                JBankHelp.showErrorMessage(e.getMessage());
            } catch (IndexOutOfBoundsException e) {
                JBankHelp.showErrorMessage("Du kan ikke kjøpe aksjer uten en bankkonto");
            }
            catch (NoSuchElementException e) {
                JBankHelp.showErrorMessage("Du må ta et valg");
            }
        }
        updateViews();
    }

    public void nextDay() {
        importStockPane.setVisible(false);
        updateStockInfo();
        updateStockChart();
    }
}
