package skredebank.logic.files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

import skredebank.data.Accounts;
import skredebank.data.Person;
import skredebank.logic.Help;

public class AccountSaver {
    File accountsTXT = new File("src\\main\\java\\skredebank\\accounts\\accounts.txt");
    Accounts test;
    private Accounts accounts;


    public AccountSaver(Accounts accounts) {
        this.accounts = accounts;
    }

    public Accounts readFile() throws FileNotFoundException {
        try (Scanner scanner = new Scanner(accountsTXT)) {
            //#TODO
             //Implementer sjekk på at det er valide objekter som scannes inn.

             while (scanner.hasNextLine()) {
                String[] element = scanner.nextLine().split(";");
                this.accounts.addAccounts(element[0], new Person(element[1], Help.stringToDate(element[2]), element[3], element[4], element[5]));
                System.out.println(accounts);
            }
        }

        catch (Exception ex) {
            System.out.println("Det finnes ingen lagrede kontoer");
            System.out.println(ex.getMessage());
        }
        return accounts;
    }


    public void writeFile(Accounts accounts) throws IOException {

        try (FileWriter writer = new FileWriter(accountsTXT, true)) {
            for (Map.Entry<String, Person> entry : accounts.getAccounts().entrySet()) {
                writer.write(entry.getKey()+";");
                writer.write(entry.getValue().toString());
                writer.write("\n");
            }
            }
            }

}

