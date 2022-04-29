package jbank;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import jbank.data.Person;
import jbank.logic.JBankHelp;
import jbank.logic.files.AccountSaver;

public class AccountSaverTest {

    Jbank jbank = Jbank.getInstance();

    @Test
    public void NoFileTest() {
        AccountSaver accountSaver = new AccountSaver();
        assertDoesNotThrow(() -> {
            accountSaver.readObject("noFile", Jbank.getInstance());
        });
        File noFile = new File("noFile.txt");
        noFile.delete();
    }

    @Test
    public void testWriter() throws IOException, IllegalAccessException {
        jbank.getAccountObject().addPerson(
                new Person("12345678", JBankHelp.stringToDate("010100"), "Ola", "Nordmann", "1234"));
        jbank.getAccountObject().removePerson("56565656010100");
        File fileWrite = new File("testfile_write.txt");
        jbank.getAccountSaver().writeObject("testfile_write", jbank);
        Scanner scanner = new Scanner(fileWrite);
        while (scanner.hasNextLine()) {
            String[] element = scanner.nextLine().split(";");
            assertEquals("12345678010100", element[0]);
            assertEquals("12345678", element[1]);
            assertEquals("010100", element[2]);
            assertEquals("Ola", element[3]);
            assertEquals("Nordmann", element[4]);
            assertEquals("16042", element[5]);
        }
        scanner.close();
        fileWrite.delete();

    }

    public void testReader() throws IOException {
        File fileRead = new File("testfile_read.txt");
        FileWriter writer = new FileWriter(fileRead);
        writer.write("12345678010100;12345678;010100;Ola;Nordmann;16042");
        writer.close();
        jbank = Jbank.getInstance();
        jbank.getAccountSaver().readObject("testfile_read", jbank);
        Person ola = new Person("12345678", JBankHelp.stringToDate("010100"), "Ola", "Nordmann", "1234");
        assertEquals(ola, jbank.getAccountMap().get("12345678010100"));
        File accounts = new File("accounts.txt");
        fileRead.delete();
        accounts.delete();

    }
}
