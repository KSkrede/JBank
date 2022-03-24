package skredebank;

import java.util.Map;

import skredebank.data.Accounts;
import skredebank.data.Person;
import skredebank.logic.files.AccountSaver;

public class Skredebank {
    // Singleton
    private static Skredebank single_instance = null;

    public SkredebankApp app = new SkredebankApp();
    public Accounts accounts = new Accounts();
    public AccountSaver accountSaver = new AccountSaver(accounts);
    public Map<String, Person> accountMap;

    private Skredebank() {
        this.app = new SkredebankApp();
        this.accounts = new Accounts();
        this.accountSaver = new AccountSaver(accounts);
    }

    // Static method
    // Static method to create instance of Singleton class
    public static Skredebank getInstance() {
        if (single_instance == null)
            single_instance = new Skredebank();

        return single_instance;
    }

    public SkredebankApp getApp() {
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


    
    

}
