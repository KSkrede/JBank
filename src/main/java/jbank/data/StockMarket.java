package jbank.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class StockMarket implements IValuable {

    // Inspiret fra Stock eksempelete fra forelesning
    // https://gitlab.stud.idi.ntnu.no/tdt4100/v2022/students/-/tree/main/foreksempel/src/main/java/uke12/listener/stocks

    private Map<String, Double> stocks = new HashMap<>();
    private Map<String, Map<String, Integer>> ownedStocks;
    Random random = new Random();

    public StockMarket() {
        update("Aksje1", 500);
        update("Aksje2", 250);
        update("Aksje3", 12);
    }

    public Double getValue(String name) {
        return this.stocks.get(name);
    }

    public ArrayList<String> getTickers() {
        ArrayList<String> tickersList = new ArrayList<>(stocks.keySet());
        return tickersList;

    }

    public void update(String name, int value) {
        this.stocks.put(name, stocks.getOrDefault(name, 0.0) + value);
    }

    public void nextDay() {
        for (int i = 0; i < 100; i++) {

            try {
                String[] keys = stocks.keySet().toArray(new String[stocks.size()]);
                String randomKey = keys[random.nextInt(keys.length)];
                update(randomKey, random.nextInt(20) - 10);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
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
        }

        else {
            ownedStocks.get(userID).merge(ticker, amount, (a, b) -> a - b);
        }
    }
}
