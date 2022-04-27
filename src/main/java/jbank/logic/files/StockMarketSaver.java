package jbank.logic.files;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import jbank.Jbank;
import jbank.data.StockMarket;

public class StockMarketSaver implements IFileHandler {
    @Override
    public void readObject(String fileName, Jbank jbank) throws IOException {
        StockMarket stockMarket = jbank.getStockMarket();
        File file = new File(fileName + "_stocks.txt");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String[] element = scanner.nextLine().split(";");
            stockMarket.buy(fileName, element[0], Integer.parseInt(element[1]));
        }
        scanner.close();
    }

    @Override
    public void writeObject(String fileName, Jbank jbank) throws IOException {
        StockMarket stockMarket = jbank.getStockMarket();
        File file = new File(fileName + "_stocks.txt");
        FileWriter writer = new FileWriter(file);
        for (String stock : jbank.getStockMarket().listOwnedStocks(fileName)) {
            writer.write(stock + ";");
            writer.write("" + stockMarket.numberOwnedStocks(fileName, stock));
            writer.write("\n");
        }
        writer.close();
    }

}
