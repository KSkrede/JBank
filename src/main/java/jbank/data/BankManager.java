package jbank.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BankManager {
    private Map<String, ArrayList<BankAccount>> bankManager;

    public BankManager() {
        bankManager = new HashMap<>();
    }

    public Map<String, ArrayList<BankAccount>> getAllBankAccounts() {
        return bankManager;
    }

    public ArrayList<BankAccount> getBankAccounts(Person user) {
        if (bankManager.get(user.getUserId()) == null) {
            return new ArrayList<>();
        } else
            return new ArrayList<>(bankManager.get(user.getUserId()));
    }

    public void deleteBankAccount(Person user, BankAccount bankAccount){
        if(bankManager.get(user.getUserId()) == null){
            throw new IllegalArgumentException("Du kan ikke slette kontoer før du har noen");
        }
        if (!bankManager.get(user.getUserId()).contains(bankAccount)){
            throw new IllegalArgumentException("Du kan ikke slette en konto som du ikke har");
        }
        else {
            bankManager.get(user.getUserId()).remove(bankAccount);
        }
    }

    public void addBank(String userid, BankAccount bankAccount) {

        if (bankManager.get(userid) == null) {
            bankManager.put(userid, new ArrayList<BankAccount>());
        }
        bankManager.get(userid).add(bankAccount);
    }

    public Boolean hasFunds(BankAccount bank, int value){
    return bank.getValue() >= value; }


    public void addFunds(BankAccount bank, int funds){
        if(funds < 0){
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
            bank.removeValue(funds);
        }
    }

    public void movefunds(BankAccount source, BankAccount destination, int amount) {
        if(source.getValue() < amount){
            throw new IllegalArgumentException(source + " har ikke dekning");
        }
        else{
            source.removeValue(amount);
            destination.addValue(amount);
        }

    }

    public int getValue(Person user, String bank){
        for (BankAccount bankAccount : getBankAccounts(user)) {
            if(bankAccount.getName().equals(bank)){
                return bankAccount.getValue();
            }
            
        }
        return 0;
    }

    @Override
    public String toString() {
        return "" + bankManager;
    }

}
