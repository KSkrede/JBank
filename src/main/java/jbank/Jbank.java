package jbank;

import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import jbank.data.Accounts;
import jbank.data.BankAccount;
import jbank.data.BankManager;
import jbank.data.Person;
import jbank.data.StockIndex;
import jbank.data.StockMarket;
import jbank.data.StockTracker;
import jbank.logic.files.AccountSaver;
import jbank.logic.files.BankManagerSaver;
import jbank.logic.files.StockMarketSaver;
import jbank.logic.bankcomparators.*;

public class Jbank {
    // Singleton
    private static Jbank single_instance = null;

    private JbankApp app;
    private Accounts accounts;
    private AccountSaver accountSaver;
    private BankManagerSaver bankManagerSaver;
    private StockMarketSaver stockMarketSaver;
    private BankManager BankManager;
    private StockMarket stockMarket;
    private StockTracker stockTracker;
    private StockIndex stockIndex;
    private Person loggedInPerson;
    private int days;
    private Map<String, Comparator<BankAccount>> sortMap;
    private Comparator<BankAccount> nameSort = new NameSort();
    private Comparator<BankAccount> valueSort = new ValueSort();
    

    private Jbank() {
        this.app = new JbankApp();
        this.accounts = new Accounts();
        this.accountSaver = new AccountSaver();
        this.BankManager = new BankManager();
        this.stockMarket = new StockMarket();
        this.stockTracker = new StockTracker();
        this.stockIndex =  new StockIndex("indeks", stockMarket);
        this.loggedInPerson = getAccountObject().getLoggedInPerson();
        this.sortMap = new HashMap<>();
        this.bankManagerSaver = new BankManagerSaver();
        this.stockMarketSaver = new StockMarketSaver();
    }


    // Static method
    // Static method to create instance of Singleton class
    public static Jbank getInstance() {
        if (single_instance == null)
            single_instance = new Jbank();

        return single_instance;
    }

    // getters for all objects

    public JbankApp getApp() {
        return app;
    }

    public Accounts getAccountObject() {
        return accounts;
    }

    public AccountSaver getAccountSaver() {
        return accountSaver;
    }

    public Map<String, Person> getAccountMap() {
        return this.getAccountObject().getAccounts();
    }

    public BankManager getBankManager() {
        return this.BankManager;
    }

    public StockMarket getStockMarket() {
        return this.stockMarket;
    }

    public StockTracker getStockTracker() {
        return this.stockTracker;
    }

    public BankManagerSaver getBankManagerSaver(){
        return this.bankManagerSaver;
    }

    public StockMarketSaver getStockMarketSaver(){
        return this.stockMarketSaver;
    }

    // Login

    public void jBankLogin() throws IllegalArgumentException, IOException {
        try{

        this.getAccountSaver().readObject("accounts", this);
    }
    catch (IOException e){
        throw new IllegalStateException("Databasen med kontoer ble ikke funnet, venlist lag kontoer på nytt");
    }
    }

    public Boolean userLogin(String userID) {
        Optional<String> account = this.getAccountMap().keySet().stream().filter(key -> userID.equals(key)).findFirst();
        if (account.isPresent()) {
            getAccountObject().setLoggedInPerson(getAccountMap().get(userID));
            return true;
        } else {
            return false;
        }
    }

    // stocks

    public void buyStocks(String stockToBuy, int number, BankAccount source, String userID) {
        int value = stockMarket.getValue(stockToBuy) * number;

        if (!getBankManager().hasFunds(source, value)) {
            throw new IllegalArgumentException(
                    "Du har ikke nok penger til å kjøpe " + number + " " + stockToBuy + "-aksjer");
        } else {
            getStockMarket().buy(userID, stockToBuy, number);
            getBankManager().removeFunds(source, value);

        }

    }

    public void sellStocks(String stockToSell, int number, BankAccount destination, String userID) {
        int value = stockMarket.getValue(stockToSell) * number;

        if (stockMarket.getValue(stockToSell) < 0) {
            throw new IllegalArgumentException("Du har ikke lov til å selge aksjer uten verdi");
        } else if (!stockMarket.listOwnedStocks(userID).contains(stockToSell)) {
            throw new IllegalArgumentException("Du har ikke lov til å selge aksjer du ikke har");
        } else if (stockMarket.numberOwnedStocks(userID, stockToSell) < number) {
            throw new IllegalArgumentException("Du har ikke så mange aksjer i" + stockToSell);
        } else {
            getStockMarket().sell(userID, stockToSell, number);
            getBankManager().addFunds(destination, value);

        }
    }

    public int sumBankAccounts() {
        loggedInPerson = getAccountObject().getLoggedInPerson();
        Integer sum = 0;
        try {
            if (BankManager.getBankAccounts(loggedInPerson).isEmpty()) {
                return 0;
            }
            for (BankAccount bankAccount : BankManager.getBankAccounts(loggedInPerson)) {
                sum = sum + bankAccount.getValue();
            }
            return sum;
        } catch (IllegalStateException e) {
            return 0;
        }
    }

    public int sumStocks() {
        loggedInPerson = getAccountObject().getLoggedInPerson();
        Integer sum = 0;
        if (stockMarket.listOwnedStocks(loggedInPerson.getUserId()).isEmpty()) {
            return 0;
        }
        for (String stock : stockMarket.listOwnedStocks(loggedInPerson.getUserId())) {
            sum = sum + stockMarket.getValue(stock)*stockMarket.numberOwnedStocks(loggedInPerson.getUserId(), stock);
        }
        return sum;
    }

    public int getDays() {
        return days;
    }

    public void daysIncrease() {
        this.days++;
    }

    public StockIndex getStockIndex() {
        return stockIndex;
    }

    public Comparator<BankAccount> getSort(String sort){
        sortMap.put("Navn økende", nameSort);
        sortMap.put("Navn synkende", nameSort.reversed());
        sortMap.put("Verdi økende", valueSort);
        sortMap.put("Verdi synkende", valueSort.reversed());
        return sortMap.get(sort);
    }


}
