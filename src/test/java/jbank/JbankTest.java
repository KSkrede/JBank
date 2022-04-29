package jbank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import jbank.data.BankAccount;
import jbank.data.Person;
import jbank.logic.JBankHelp;

public class JbankTest {

    // Most of Jbanks is delegated to other objects that have their own tests

    Jbank jbank = Jbank.getInstance();
    Person person = new Person("12345678", JBankHelp.stringToDate("010100"), "Ola", "Nordmann", "1234");

    @Test
    public void userLoginTest() {
        assertEquals(false, jbank.userLogin("12345678010100"));
        jbank.getAccountObject().addPerson(person);
        assertEquals(true, jbank.userLogin("12345678010100"));
    }

    @Test
    public void testBuyStocks() {
        assertThrows(IllegalArgumentException.class, () -> {
            jbank.buyStocks("AAPL", 1, new jbank.data.BankAccount("test", 100), person.getUserId());
        }, "Du skal ikke ha råd til å kjøpe denne aksjen");
    }

    @Test
    public void testSellStocks() {
        assertThrows(IllegalArgumentException.class, () -> {
            jbank.sellStocks("AAPL", 1, new jbank.data.BankAccount("test2", 100), person.getUserId());
        }, "Du skal ikke ha lov til å selge aksjer du ikke eier");
    }

    @Test
    public void testSumBank() {
        assertThrows(IllegalArgumentException.class, () -> {
            jbank.sumBankAccounts();
        }, "Du skal ikke kunne summere kontoer når du ikke har noen");
        jbank.getAccountObject().setLoggedInPerson(person);
        assertEquals(0, jbank.sumBankAccounts());
        jbank.getBankManager().addBank(person.getUserId(),new BankAccount("bank", 100));
        assertEquals(100, jbank.sumBankAccounts());
    }

    @Test
    public void testSumStock() {
        assertThrows(IllegalArgumentException.class, () -> {
            jbank.sumStocks();
        }, "Du skal ikke kunne summere akjser når du ikke har noen");
        jbank.getAccountObject().setLoggedInPerson(person);
        assertEquals(0, jbank.sumStocks());
        jbank.getStockMarket().buy(person.getUserId(), "AAPL", 1);
        assertEquals(300, jbank.sumStocks());
    }

    @Test
    public void testSort() {
        assertThrows(IllegalArgumentException.class, () -> {
            jbank.getSort("IllegalSort");
        }, "Du skal ikke få ut en sortering utenom de som er innebygd");        
    }

}
