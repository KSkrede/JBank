package skredebank.data;

import java.util.HashMap;
import java.util.Map;

public class BankAccount {

    String name;
    double value;
    private Map<String, Double> bankAccount = new HashMap<>();

    
    public BankAccount(String name, double value) {
        bankAccount.put(name, value);
    }


    public Map<String, Double> getBankAccount() {
        return bankAccount;
    }


    @Override
    public String toString() {
        return "" + bankAccount;
    }

    
    



    
}
