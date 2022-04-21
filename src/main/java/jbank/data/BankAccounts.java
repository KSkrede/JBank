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

    public Boolean hasFunds(BankAccount bank, int value){
    return bank.getValue() >= value; }


    public void addFunds(BankAccount bank, int funds){
        if(funds > 0){
            throw new IllegalArgumentException("Kan ikke legge til et negativt beløp");
        }
        else {
            bank.addValue(funds);
        }
    }

    public void removeFunds(BankAccount bank, int funds){
        if(funds < 0){
            throw new IllegalArgumentException("Kan ikke trekke fra et negativt beløp");
        }
        else {
            bank.addValue(funds);
        }
    }

    public void movefunds(String userid, BankAccount source, BankAccount recciver) {
        // if hasFunds

    }

    @Override
    public String toString() {
        return "" + bankAccounts;
    }

}
