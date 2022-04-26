package jbank.logic;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class StockTracker {
    private Map<Integer, Map<String, Integer>> stocklogs;

    public void log(Map<String, Integer> stocks, int days) {
        if (stocklogs == null) {
            stocklogs = new HashMap<>();
        }
        stocklogs.put(days, stocks);
    }


    public int getStockprice(int day, String ticker) {
        if (this.stocklogs == null) {
            return -1;
        } else {
            return stocklogs.get(day).get(ticker);
        }
    }


    public Map<Integer, Map<String, Integer>> getStocklogs() {
        if (stocklogs == null) {
            return new HashMap<>();
        }
        return stocklogs;
    }

}
