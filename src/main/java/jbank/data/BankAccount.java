package jbank.data;

import java.util.HashMap;
import java.util.Map;

public class BankAccount {

    String name;
    double value;
    private Map<String, Integer> bankAccount;

    
    public BankAccount(String name, int value) {
        bankAccount = new HashMap<>();
        bankAccount.put(name, value);
        this.name = name;
        this.value = value;
    }


    public Map<String, Integer> getBankAccount() {
        return bankAccount;
    }


    @Override
    public String toString() {
        return "Navn: " + name + ", Beløp: " + value ;
    }

    
    



    
}
