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
    private BankAccount b4;

    private ValueSort nameSort;
    private NameSort valueSort;

    @BeforeEach
	public void setup() {
		nameSort = new ValueSort();
        valueSort = new NameSort();
		b1 = new BankAccount("Bank1", 10);
        b2 = new BankAccount("Bank1", 20);
        b3 = new BankAccount("aa", 10);
        b3 = new BankAccount("bb", 20);
	}

    @Test
    public void testIdentical() {
        assertEquals(0, nameSort.compare(b1, b2));
        assertEquals(0, valueSort.compare(b1, b3));
    }

    public void testName() {
        assertEquals(0, nameSort.compare(b1, b1));
        assertEquals(0, valueSort.compare(b1, b2));
    }



    
}
