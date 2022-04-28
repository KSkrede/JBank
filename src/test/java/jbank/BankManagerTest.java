package jbank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jbank.data.BankAccount;
import jbank.data.BankManager;
import jbank.data.Person;

public class BankManagerTest {

    BankManager bankManager;
    BankAccount bank;
    BankAccount otherBank;
    Person person;
    Person otherPerson;

    @BeforeEach
    public void setup() {
        this.bankManager = new BankManager();
        this.bank = new BankAccount("Test", 100);
        this.otherBank = new BankAccount("other", 123);
        this.person = new Person("12345678", LocalDate.now().minusYears(19), "Ola", "Nordmann", "1234");
        bankManager.addBank(person.getUserId(), bank);
        this.otherPerson = new Person("87654321", LocalDate.now().minusYears(19), "Erna", "Solberg", "1234");
    }

    @Test
    public void testGetBankAccounts() {

        //When no accounnts, shoud get null
        BankManager b1 = new BankManager();
        b1.getBankAccounts(person);
        assertEquals(null, b1.getAllBankAccounts().get(person.getUserId()));
        
        assertEquals(new ArrayList<>(Arrays.asList(bank)), bankManager.getAllBankAccounts().get(person.getUserId()));
        assertEquals(new ArrayList<>(Arrays.asList(bank)), bankManager.getBankAccounts(person));
    }

    @Test
    public void testDeleteBankAccount() {
        assertThrows(IllegalArgumentException.class, () -> {
            BankManager b2 = new BankManager();
            b2.deleteBankAccount(otherPerson, bank);
        }, "Du skal ikke kunne slette en konto som du ikke har");

        bankManager.deleteBankAccount(person, bank);
        bankManager.addBank(person.getUserId(), otherBank);
        assertEquals(new ArrayList<>(Arrays.asList(otherBank)), bankManager.getBankAccounts(person));
    }

    @Test
    public void testFunds() {

        assertEquals(true, bankManager.hasFunds(bank, 100));
        assertEquals(false, bankManager.hasFunds(bank, 101));

        assertThrows(IllegalArgumentException.class, () -> {
            bankManager.addFunds(bank, -1);
        }, "Du skal ikke kunne legge til et negativt beløp");

        assertThrows(IllegalArgumentException.class, () -> {
            bankManager.removeFunds(bank, -1);
        }, "Du skal ikke kunne fjerne et negativt beløp");

        assertThrows(IllegalArgumentException.class, () -> {
            bankManager.movefunds(bank, otherBank, 200);
        }, "Du skal ikke kunne overføre mer enn du har");

        bankManager.movefunds(bank, otherBank, 100);
        assertEquals(0, bank.getValue());
        assertEquals(223, otherBank.getValue());

    }

    @Test
    public void testGetValue() {
        assertEquals(100, bankManager.getValue(person, bank.getName()));

    }
}
