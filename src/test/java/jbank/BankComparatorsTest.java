package jbank;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jbank.data.BankAccount;
import jbank.logic.bankcomparators.ValueSort;
import jbank.logic.bankcomparators.NameSort;

public class BankComparatorsTest {

    private BankAccount b1;
    private BankAccount b2;
    private BankAccount b3;

    private ValueSort valueSort;
    private NameSort nameSort;

    @BeforeEach
    public void setup() {
        valueSort = new ValueSort();
        nameSort = new NameSort();
        b1 = new BankAccount("Bank1", 10);
        b2 = new BankAccount("Bank1", 20);
        b3 = new BankAccount("aa", 10);
    }

    @Test
    public void testIdentical() {
        assertEquals(0, nameSort.compare(b1, b2));
        assertEquals(0, valueSort.compare(b1, b3));
    }

    @Test
    public void testName() {
        assertEquals(-31, nameSort.compare(b1, b3));
        assertEquals(31, nameSort.compare(b3, b1));
    }

    public void testValue() {
        assertEquals(-10, valueSort.compare(b1, b2));
        assertEquals(10, valueSort.compare(b2, b1));
    }

}
