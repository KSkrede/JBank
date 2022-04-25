package jbank.logic.files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

import jbank.Jbank;
import jbank.data.Accounts;
import jbank.data.Person;
import jbank.logic.JBankHelp;

public class AccountSaver {
    File accountsTXT = new File("src\\main\\java\\jbank\\storage\\accounts.txt");
    Accounts test;
    private Accounts accounts;
    Jbank jbank;

    public void initialize() {
        jbank = Jbank.getInstance();
    }

    public AccountSaver(Accounts accounts) {
        this.accounts = accounts;
    }

    public Accounts readFile() throws FileNotFoundException {
        try (Scanner scanner = new Scanner(accountsTXT)) {
            while (scanner.hasNextLine()) {
                String[] element = scanner.nextLine().split(";");
                this.accounts.addPerson(element[0],
                        new Person(element[1], JBankHelp.stringToDate(element[2]), element[3], element[4], element[5]));
            }
        }

        catch (Exception ex) {
            JBankHelp.showErrorMessage(ex.getMessage());
        }
        return accounts;
    }

    public void writeFile(Accounts accounts) throws IOException {

        try (FileWriter writer = new FileWriter(accountsTXT)) {
            for (Map.Entry<String, Person> entry : accounts.getAccounts().entrySet()) {
                writer.write(entry.getKey() + ";");
                writer.write(entry.getValue().toString());
                writer.write("\n");
            }
        }
    }

}
