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
    // public Accounts readFile() throws FileNotFoundException {
    // try (Scanner scanner = new Scanner(accountsTXT)) {
    // while (scanner.hasNextLine()) {
    // String[] element = scanner.nextLine().split(";");
    // this.accounts.addPerson(element[0],
    // new Person(element[1], JBankHelp.stringToDate(element[2]), element[3],
    // element[4], element[5]));
    // }
    // }

    // catch (Exception ex) {
    // JBankHelp.showErrorMessage(ex.getMessage());
    // }
    // return accounts;
    // }

    // public void writeFile(Accounts accounts) throws IOException {

    // try (FileWriter writer = new FileWriter(accountsTXT)) {
    // for (Map.Entry<String, Person> entry : accounts.getAccounts().entrySet()) {
    // writer.write(entry.getKey() + ";");
    // writer.write(entry.getValue().toString());
    // writer.write("\n");
    // }
    // }
    // }




    @Override
    public Accounts readAccounts(String fileName, Accounts accounts) throws IOException, IllegalArgumentException {
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

    // @Override
    // public Path getFilePath(String filename) {
    //     return Path.of(AccountSaver.class.getResource(filename + ".txt").getFile());
    // }

    // public File getAccountsFile() {
    //     System.out.println("heyhey");
    //     return new File(ClassLoader.class.getResource("accounts.txt").getPath());
    // }

}
