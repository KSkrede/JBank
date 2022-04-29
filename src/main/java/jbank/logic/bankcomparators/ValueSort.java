package jbank.logic.bankcomparators;

import java.util.Comparator;

import jbank.data.BankAccount;

public class ValueSort implements Comparator<BankAccount> {

    @Override
    public int compare(BankAccount o1, BankAccount o2) {
        return o1.getValue() - o2.getValue();
    }
}
