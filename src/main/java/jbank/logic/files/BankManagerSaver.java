package jbank.logic.files;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import jbank.Jbank;
import jbank.data.BankAccount;

public class BankManagerSaver implements IFileHandler {
    @Override
    public void readObject(String fileName, Jbank jbank) throws IOException {
        try {
            File file = new File(fileName + "_bankaccounts.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String[] element = scanner.nextLine().split(";");
                jbank.getBankManager().addBank(fileName, new BankAccount(element[0], Integer.parseInt(element[1])));
            }
            scanner.close();
        } catch (IOException e) {
            writeObject(fileName, jbank);
        }
    }

    @Override
    public void writeObject(String fileName, Jbank jbank) throws IOException {
        File file = new File(fileName + "_bankaccounts.txt");
        FileWriter writer = new FileWriter(file);
        for (BankAccount bank : jbank.getBankManager().getBankAccounts(jbank.getAccountMap().get(fileName))) {
            writer.write(bank.getName() + ";");
            // "" needed to not save int as ascii character
            writer.write("" + bank.getValue());
            writer.write("\n");
        }
        writer.close();
    }

}
