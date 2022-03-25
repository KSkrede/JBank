package skredebank.data;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BankAccounts {
    private Map<String, BankAccount> bankAccounts;


    public BankAccounts() {
        bankAccounts = new HashMap<>();
    }

    public Map<String, BankAccount> getAllBankAccounts() {
        return bankAccounts;
    }

    public void addAccounts(String userid, BankAccount bankAccount) {
        bankAccounts.put(userid, bankAccount);
    }

    public Boolean hasFunds(BankAccount source, String accountName, double value){
        return source.getBankAccount().get(accountName) >= value;
    }

    public void movefunds(String userid, BankAccount source, BankAccount recciver) {
        //if hasFunds, move
    }

    @Override
    public String toString() {
        return "" + bankAccounts;
    }

    public static void main(String[] args) {
        BankAccounts test = new BankAccounts();
        BankAccount konto = new BankAccount("Kontonavn", 20);
        test.addAccounts("Kristian", konto );
        System.out.println(test);

    }



    



}
