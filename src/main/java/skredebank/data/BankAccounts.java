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

    public Boolean hasFunds(String accountName, double value){
        //skredebank.accounts.getLoggedInPerson.

        return source.getBankAccount().get(accountName) >= value;
    }

    public void addFunds(String accountName, double change){
        bankAccounts.get(user).get(accountName).
    }

    public void updateFunds(String name, double change) {
        this.bankAccounts.put(name, bankAccounts.getOrDefault(name, 0.0) + change);
    }

    public void movefunds(String userid, BankAccount source, BankAccount recciver) {
        //if hasFunds


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
