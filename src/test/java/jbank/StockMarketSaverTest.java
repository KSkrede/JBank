package jbank;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import jbank.data.BankAccount;
import jbank.data.Person;
import jbank.logic.JBankHelp;
import jbank.logic.files.StockMarketSaver;

public class StockMarketSaverTest {

    Jbank jbank;

    @Test
    public void NoFileTest() {
        StockMarketSaver stockMarketSaver = new StockMarketSaver();
        assertDoesNotThrow(() -> {
            jbank = Jbank.getInstance();
            jbank.getAccountObject().addPerson("12345678010100",
                new Person("12345678", JBankHelp.stringToDate("010100"), "Ola", "Nordmann", "1234"));
            stockMarketSaver.readObject("12345678010100", Jbank.getInstance());
        });
        File noFile = new File("12345678010100_stocks.txt");
        noFile.delete();
    }

    @Test
    public void testWriter() throws IOException {
        File fileWrite = new File("12345678010100_stocks.txt");
        jbank = Jbank.getInstance();
        jbank.getAccountObject().addPerson("12345678010100",
                new Person("12345678", JBankHelp.stringToDate("010100"), "Ola", "Nordmann", "1234"));
        jbank.getStockMarket().addStock("aksje", 100);
        jbank.getStockMarketSaver().writeObject("12345678010100", jbank);
        Scanner scanner = new Scanner(fileWrite);
        while (scanner.hasNextLine()) {
            String[] element = scanner.nextLine().split(";");
            assertEquals("aksje", element[0]);
            assertEquals("100", element[1]);
        }
        scanner.close();
        fileWrite.delete();
        
    }
    
    @Test
    public void testReader() throws IOException {
        File fileRead = new File("testfile_read_stocks.txt");
        FileWriter writer = new FileWriter(fileRead);
        writer.write("NyAksje;123");
        writer.close();
        jbank = Jbank.getInstance();
        jbank.getStockMarketSaver().readObject("testfile_read", jbank);
        assertEquals("NyAksje", jbank.getStockMarket().listOwnedStocks("testfile_read").get(0));
        assertEquals(123, jbank.getStockMarket().numberOwnedStocks("testfile_read", "NyAksje"));
        fileRead.delete();

    }
}
