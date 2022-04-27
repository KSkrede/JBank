package jbank.logic;

import java.util.HashMap;
import java.util.Map;

public class StockTracker {
    private Map<Integer, Map<String, Integer>> stocklogs;
    private Map<Integer, Integer> indexlogs;

    public void log(Map<String, Integer> stocks, int days, int indexAvg) {
        if (stocklogs == null) {
            stocklogs = new HashMap<>();
            indexlogs = new HashMap<>();
        }
        stocklogs.put(days, stocks);
        indexlogs.put(days, indexAvg);
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

    
    public Map<Integer, Integer> getIndexlogs() {
        if (indexlogs == null) {
            return new HashMap<>();
        }
        return indexlogs;
    }

}
