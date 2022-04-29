package jbank.data;

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
        if(days < 0){
            throw new IllegalArgumentException("Kan ikke logge negativ dag");
        }
        stocklogs.put(days, stocks);
        indexlogs.put(days, indexAvg);
    }


    public int getStockprice(int day, String ticker) {
        if (this.stocklogs == null) {
            return 0;
        } else {
            return stocklogs.get(day).get(ticker);
        }
    }


    public Map<Integer, Map<String, Integer>> getStocklogs() {
        if (stocklogs == null) {
            return new HashMap<>();
        }
        Map<Integer, Map<String, Integer>> temp = this.stocklogs;
        return temp;
    }

    
    public Map<Integer, Integer> getIndexlogs() {
        if (indexlogs == null) {
            return new HashMap<>();
        }
        Map<Integer, Integer> temp = this.indexlogs;
        return temp;
    }

}
