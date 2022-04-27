package jbank.data;

import java.util.HashMap;
import java.util.Map;

public class BankAccount {

    private String name;
    private Integer value;
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


    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public void addValue(int funds){
        value = value + funds;
    }

    public void removeValue(int funds){
        value = value - funds;
    }




    @Override
    public String toString() {
        return name;
    }

    
    



    
}
