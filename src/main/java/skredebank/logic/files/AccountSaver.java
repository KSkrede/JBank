package skredebank.logic.files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
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
        return accounts;
    }

    public void printFile(Accounts accounts) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(accountsTXT)) {
            System.out.println(scanner.hasNextLine());
             while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine().split(";"));
                //accounts.addAccounts(element[0], new Person(element[1], Help.stringToDate(element[2]), element[3], element[4], element[5]));
            }
        }
    }


    public void writeFile(Accounts accounts) throws FileNotFoundException {

        try (PrintWriter writer = new PrintWriter(accountsTXT)) {
            for (Map.Entry<String, Person> entry : accounts.getAccounts().entrySet()) {
                writer.print(entry.getKey()+";");
                writer.print(entry.getValue());
            }
            }
            }

}

