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
import jbank.logic.files.BankManagerSaver;

public class BankManagerSaverTest {

    Jbank jbank;

    @Test
    public void NoFileTest() {
        BankManagerSaver bankManagerSaver = new BankManagerSaver();
        assertDoesNotThrow(() -> {
            jbank = Jbank.getInstance();
            jbank.getAccountObject().addPerson(
                new Person("56565656", JBankHelp.stringToDate("010100"), "Ola", "Nordmann", "1234"));
            bankManagerSaver.readObject("56565656010100", Jbank.getInstance());
        });
        File noFile = new File("56565656010100_bankaccounts.txt");
        noFile.delete();
    }

    @Test
    public void testWriter() throws IOException {
        File fileWrite = new File("56565656010100_bankaccounts.txt");
        jbank = Jbank.getInstance();
        jbank.getBankManagerSaver().writeObject("56565656010100", jbank);
        Scanner scanner = new Scanner(fileWrite);
        while (scanner.hasNextLine()) {
            String[] element = scanner.nextLine().split(";");
            assertEquals("test", element[0]);
            assertEquals("123", element[1]);
        }
        scanner.close();
        fileWrite.delete();
        
    }
    
    @Test
    public void testReader() throws IOException {
        File fileRead = new File("testfile_read_bankaccounts.txt");
        FileWriter writer = new FileWriter(fileRead);
        writer.write("Bankkonto;321");
        writer.close();
        jbank = Jbank.getInstance();
        jbank.getBankManagerSaver().readObject("testfile_read", jbank);
        BankAccount bank = new BankAccount("Bankkonto", 321);
        assertEquals(bank.getName(), jbank.getBankManager().getAllBankAccounts().get("testfile_read").get(0).getName());
        assertEquals(bank.getValue(), jbank.getBankManager().getAllBankAccounts().get("testfile_read").get(0).getValue());
        fileRead.delete();

    }
}
