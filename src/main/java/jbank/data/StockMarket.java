package jbank.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class StockMarket implements IValuable, Runnable {


    private Map<String, Integer> stocks = new HashMap<>();
    List<StockListener> listeners = new ArrayList<StockListener>();
    private Map<String, Map<String, Integer>> ownedStocks;
    private ArrayList<String> ownedStocksList;
    Random random = new Random();

    public StockMarket() {
        stocks = new HashMap<>();
        update("Aksje1", 500);
        update("Aksje2", 250);
        update("Aksje3", 12);
        ownedStocks = new HashMap<>();
    }

    public Integer getValue(String name) {
        return this.stocks.get(name);
    }

    public ArrayList<String> getTickers() {
        ArrayList<String> tickersList = new ArrayList<>(stocks.keySet());
        return tickersList;

    }

    public void addStockListener(StockListener observator) {
        listeners.add(observator);
    }

    public void removeStockListener(StockListener observator) {
        listeners.remove(observator);
    }

    public void update(String name, int value) {
        this.stocks.put(name, stocks.getOrDefault(name, 0) + value);
    }


    public void nextDay() {
        for (StockListener listeners : listeners) {
            for (String stock : getTickers()) {
            int oldValue = getValue(stock);
            run();
            int newValue = getValue(stock);
            listeners.stockPriceChanged(stock, oldValue ,newValue );    
            }
        }
    }

    public Map<String, Integer> getStocks() {
        Map<String, Integer> stocksCopy = new HashMap<>();
        stocksCopy.putAll(this.stocks);
        return stocksCopy;
    }

    public void buy(String userID, String ticker, int amount) {
        // Eiter puts amount as value, or previous amount + new amount
        // https://stackoverflow.com/questions/81346/most-efficient-way-to-increment-a-map-value-in-java
        ownedStocks.get(userID).merge(ticker, amount, (a, b) -> a + b);
    }

    public void sell(String userID, String ticker, int amount) {

        if (!ownedStocks.get(userID).containsKey(ticker)) {
            throw new IllegalArgumentException("Du kan ikke selge en aksje du ikke eier");
        }
        if (ownedStocks.get(userID).get(ticker) < amount) {
            throw new IllegalArgumentException("Du kan ikke selge flere aksjer enn de du eier");
        } else {
            ownedStocks.get(userID).merge(ticker, amount, (a, b) -> a - b);
            if (ownedStocks.get(userID).get(ticker) <= 0){
                ownedStocks.get(userID).remove(ticker);
            }
        }
    }

    public ArrayList<String> listOwnedStocks(String userID) {
        if (ownedStocks.get(userID) == null) {
            ownedStocks.put(userID, new HashMap<>());
        }
        ownedStocksList = new ArrayList<String>(ownedStocks.get(userID).keySet());
        return ownedStocksList;
    }

    public int numberOwnedStocks(String userID, String stock) {
        int numberOwnedStocks = 0;
        try{
            numberOwnedStocks = ownedStocks.get(userID).get(stock);
        }
        catch(NullPointerException e){
            return 0;
        }
        return numberOwnedStocks;
    }

    // hentet fra Stock eksempelete fra forelesning
    // https://gitlab.stud.idi.ntnu.no/tdt4100/v2022/students/-/tree/main/foreksempel/src/main/java/uke12/listener/stocks
    @Override
    public void run() {

        for (int i = 0; i < 50; i++) {

            try { 
                Thread.sleep(1); 
                String[] keys = stocks.keySet().toArray(new String[stocks.size()]);
                String randomKey = keys[random.nextInt(keys.length)];
                update(randomKey, random.nextInt(22) - 10);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

}
