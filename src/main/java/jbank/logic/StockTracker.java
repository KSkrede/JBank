package jbank.logic;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class StockTracker {
    private Map<Integer, Map<String, Integer>> stocklogs;
    private int days;

    public void log(Map<String, Integer> stocks) {
        if (stocklogs == null) {
            stocklogs = new HashMap<>();
        }

        //If you add a new stock after clicking next day
        if (stocklogs.get(days) == null){ 
        System.out.println("null");   
        stocklogs.put(days, stocks);
        }
        else if (stocks.keySet().size() > stocklogs.get(days).keySet().size()){
            System.out.println(">");
            Set<String> stocksCopy = stocks.keySet();
            Set<String> stocklogsCopy = stocklogs.get(days).keySet();
            stocksCopy.removeAll(stocklogsCopy);
            for (String stock : stocklogsCopy) {
                for (int i = 0; i <= days; i++) {
                    Map<String, Integer> stockToAdd = new HashMap<>();
                    stockToAdd.put(stock, 0);
                    stocklogs.put(i, new HashMap<>());
                }
            }
        }
        else if (stocks.keySet().size() == stocklogs.get(days).keySet().size()){
            System.out.println("=");
            stocklogs.put(days, stocks);
        }
        days++;
    }


    public void logNew(String stock) {
        if (stocklogs.get(days) == null){ 
            System.out.println("null");  
            }
                for (int i = 0; i <= days; i++) {
                    Map<String, Integer> stockToAdd = new HashMap<>();
                    stockToAdd.put(stock, 0);
                    stocklogs.put(i, new HashMap<>());
                }
            }

    public int getStockprice(int day, String ticker) {
        if (this.stocklogs == null) {
            return -1;
        } else {
            return stocklogs.get(day).get(ticker);
        }
    }

    public int getDay() {
        return this.days;
    }

    public Map<Integer, Map<String, Integer>> getStocklogs() {
        if (stocklogs == null) {
            return new HashMap<>();
        }
        return stocklogs;
    }

}
