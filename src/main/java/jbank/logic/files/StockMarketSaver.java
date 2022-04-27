package jbank.logic.files;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import jbank.Jbank;
import jbank.data.BankAccount;

public class StockMarketSaver implements IFileHandler {
@Override
public void readObject(String fileName, Jbank jbank) throws IOException {
    File file = new File(fileName + "_stocks.txt");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String[] element = scanner.nextLine().split(";");
            jbank.getBankManager().addBank(fileName, new BankAccount(element[0], Integer.parseInt(element[1])));}
        scanner.close();
}

@Override
public void writeObject(String fileName, Jbank jbank) throws IOException {
    File file = new File(fileName + "_stocks.txt");
    FileWriter writer = new FileWriter(file);
    for (String stock : jbank.getStockMarket().listOwnedStocks(fileName)) {
        writer.write(bank.getName() + ";");
        writer.write(""+bank.getValue());
        writer.write("\n");
    }
    writer.close();   
}

}
