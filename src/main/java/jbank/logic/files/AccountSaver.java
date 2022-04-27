package jbank.logic.files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

import jbank.data.Accounts;
import jbank.data.Person;
import jbank.logic.JBankHelp;

public class AccountSaver implements IFileHandler {
    @Override
    public Accounts readAccounts(String fileName, Accounts accounts) throws IOException, IllegalArgumentException, FileNotFoundException {
        File file = new File(fileName + ".txt");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String[] element = scanner.nextLine().split(";");
            accounts.addPerson(element[0],
                    new Person(element[1], JBankHelp.stringToDate(element[2]), element[3], element[4], element[5]));
        }
        scanner.close();
        return accounts;
    }

    @Override
    public void writeAccounts(String fileName, Accounts accounts) throws IOException {
        File file = new File(fileName + ".txt");
        FileWriter writer = new FileWriter(file);
        for (Map.Entry<String, Person> entry : accounts.getAccounts().entrySet()) {
            writer.write(entry.getKey() + ";");
            writer.write(entry.getValue().toString());
            writer.write("\n");
        }
        writer.close();
    }

}
