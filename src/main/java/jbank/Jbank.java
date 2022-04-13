package jbank;

import java.util.Map;

import jbank.data.Accounts;
import jbank.data.BankAccounts;
import jbank.data.Person;
import jbank.logic.files.AccountSaver;

public class Jbank {
    // Singleton
    private static Jbank single_instance = null;

    public JbankApp app = new JbankApp();
    public Accounts accounts = new Accounts();
    public AccountSaver accountSaver = new AccountSaver(accounts);
    public Map<String, Person> accountMap;
    public BankAccounts bankAccounts;

    private Jbank() {
        this.app = new JbankApp();
        this.accounts = new Accounts();
        this.accountSaver = new AccountSaver(accounts);
        this.bankAccounts = new BankAccounts();
    }

    // Static method
    // Static method to create instance of Singleton class
    public static Jbank getInstance() {
        if (single_instance == null)
            single_instance = new Jbank();

        return single_instance;
    }

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

    public BankAccounts getBankAccounts() {
        return this.bankAccounts;
    }


    
    

}
