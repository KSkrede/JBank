package jbank.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BankAccounts {
    private Map<String, ArrayList<BankAccount>> bankAccounts;

    public BankAccounts() {
        bankAccounts = new HashMap<>();
    }

    public Map<String, ArrayList<BankAccount>> getAllBankAccounts() {
        return bankAccounts;
    }

    public ArrayList<BankAccount> getBankAccounts(Person user) {
        if (bankAccounts.get(user.getUserId()) == null) {
            throw new IllegalStateException("Ingen bankkontoer eksisterer foreløpig");
        } else
            return new ArrayList<>(bankAccounts.get(user.getUserId()));
    }

    public void addAccounts(String userid, BankAccount bankAccount) {

        if (bankAccounts.get(userid) == null) {
            bankAccounts.put(userid, new ArrayList<BankAccount>());
        }
        bankAccounts.get(userid).add(bankAccount);
    }

    public Boolean hasFunds(String accountName, double value){
    //jbank.accounts.getLoggedInPerson.

    return source.getBankAccount().get(accountName) >= value;
    }

    public void addFunds(String accountName, double change){
    bankAccounts.get(user).get(accountName).
    }

    public void updateFunds(String name, double change) {
    this.bankAccounts.put(name, bankAccounts.getOrDefault(name, 0.0) + change);
    }

    public void movefunds(String userid, BankAccount source, BankAccount recciver) {
        // if hasFunds

    }

    @Override
    public String toString() {
        return "" + bankAccounts;
    }

}
