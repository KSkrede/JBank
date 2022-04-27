package jbank.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BankManager {
    private Map<String, ArrayList<BankAccount>> BankManager;

    public BankManager() {
        BankManager = new HashMap<>();
    }

    public Map<String, ArrayList<BankAccount>> getAllBankAccounts() {
        return BankManager;
    }

    public ArrayList<BankAccount> getBankAccounts(Person user) {
        if (BankManager.get(user.getUserId()) == null) {
            throw new IllegalStateException("Ingen bankkontoer eksisterer foreløpig");
        } else
            return new ArrayList<>(BankManager.get(user.getUserId()));
    }

    public void deleteBankAccount(Person user, BankAccount bankAccount){
        if (!BankManager.get(user.getUserId()).contains(bankAccount)){
            throw new IllegalArgumentException("Du kan ikke slette en konto som ikke finnes");
        }
        else {
            BankManager.get(user.getUserId()).remove(bankAccount);
        }
    }

    public void addPerson(String userid, BankAccount bankAccount) {

        if (BankManager.get(userid) == null) {
            BankManager.put(userid, new ArrayList<BankAccount>());
        }
        BankManager.get(userid).add(bankAccount);
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
        return "" + BankManager;
    }

}
