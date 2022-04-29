package jbank.logic.bankcomparators;

import java.util.Comparator;

import jbank.data.BankAccount;

public class NameSort implements Comparator<BankAccount> {
//note that this compears lower and uppercase names to be diffrent
    @Override
    public int compare(BankAccount o1, BankAccount o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
