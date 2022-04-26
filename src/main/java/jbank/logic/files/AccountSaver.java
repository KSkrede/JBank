package jbank.logic.files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;
import java.util.Scanner;

import jbank.Jbank;
import jbank.data.Accounts;
import jbank.data.Person;
import jbank.logic.JBankHelp;

public class AccountSaver implements IFileHandler {
    Accounts test;
    private Accounts accounts;
    Jbank jbank;

    public void initialize() {
        jbank = Jbank.getInstance();
    }

    public AccountSaver(Accounts accounts) {
        this.accounts = accounts;
    }

    // public Accounts readFile() throws FileNotFoundException {
    //     try (Scanner scanner = new Scanner(accountsTXT)) {
    //         while (scanner.hasNextLine()) {
    //             String[] element = scanner.nextLine().split(";");
    //             this.accounts.addPerson(element[0],
    //                     new Person(element[1], JBankHelp.stringToDate(element[2]), element[3], element[4], element[5]));
    //         }
    //     }

    //     catch (Exception ex) {
    //         JBankHelp.showErrorMessage(ex.getMessage());
    //     }
    //     return accounts;
    // }

    // public void writeFile(Accounts accounts) throws IOException {

    //     try (FileWriter writer = new FileWriter(accountsTXT)) {
    //         for (Map.Entry<String, Person> entry : accounts.getAccounts().entrySet()) {
    //             writer.write(entry.getKey() + ";");
    //             writer.write(entry.getValue().toString());
    //             writer.write("\n");
    //         }
    //     }
    // }

    @Override
    public Accounts readAccounts(String fileName, Accounts accounts) throws IOException, IllegalArgumentException {
        try (Scanner scanner = new Scanner(getFilePath("accounts").toFile())) {
            while (scanner.hasNextLine()) {
                System.out.println("scanner has next line");
                String[] element = scanner.nextLine().split(";");
                this.accounts.addPerson(element[0],
                        new Person(element[1], JBankHelp.stringToDate(element[2]), element[3], element[4], element[5]));
            }
        }
        catch (NullPointerException ex) {
            throw new IllegalArgumentException("Ingen kontoer eksisterer, ny fil lagd");
        }
        return accounts;
    }

    @Override
    public void writeAccounts(String fileName, Accounts accounts) throws IOException {
        try (FileWriter writer = new FileWriter(getAccountsFile())) {
            for (Map.Entry<String, Person> entry : accounts.getAccounts().entrySet()) {
                writer.write(entry.getKey() + ";");
                writer.write(entry.getValue().toString());
                writer.write("\n");
            }
        }
        
    }

    @Override
    public Path getFilePath(String filename) {
        return Path.of(AccountSaver.class.getResource(filename + ".txt").getFile());
    }

    public File getAccountsFile() {
        System.out.println("heyhey");
        return new File(ClassLoader.class.getResource("accounts.txt").getPath());
    }

}
