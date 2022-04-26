package jbank;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import jbank.data.Accounts;
import jbank.data.BankManager;
import jbank.data.Person;
import jbank.data.StockMarket;
import jbank.logic.JBankHelp;
import jbank.logic.StockTracker;
import jbank.logic.files.AccountSaver;

public class Jbank {
    // Singleton
    private static Jbank single_instance = null;

    private JbankApp app;
    private Accounts accounts; 
    private AccountSaver accountSaver;
    private BankManager BankManager;
    private StockMarket stockMarket;
    private StockTracker stockTracker;

    private Jbank() {
        this.app = new JbankApp();
        this.accounts = new Accounts();
        this.accountSaver = new AccountSaver(accounts);
        this.BankManager = new BankManager();
        this.stockMarket = new StockMarket();
        this.stockTracker = new StockTracker();


    }

    // Static method
    // Static method to create instance of Singleton class
    public static Jbank getInstance() {
        if (single_instance == null)
            single_instance = new Jbank();

        return single_instance;
    }

    //getters for all objects

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
        return stockMarket;
    }

    public StockTracker getStockTracker() {
        return stockTracker;
    }

    //Login

    public void jBankLogin() throws IllegalArgumentException, IOException {
        this.getAccountSaver().readAccounts("accounts", accounts);
        // if (this.getAccountMap().isEmpty()){
        //     wrongLogIn.setText("Det er foreløpig ingen kontoer lagret");
        // }



    }

    


    


    
    

}
