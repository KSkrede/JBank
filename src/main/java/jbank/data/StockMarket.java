package jbank.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import jbank.logic.JBankHelp;

public class StockMarket implements IValuable {

    // Inspirert av Stock eksempelet
    // https://gitlab.stud.idi.ntnu.no/tdt4100/v2022/students/-/tree/main/foreksempel/src/main/java/uke12/listener/stocks

    private Map<String, Integer> stocks = new HashMap<>();
    List<ValuableListner> listeners = new ArrayList<ValuableListner>();
    private Map<String, Map<String, Integer>> ownedStocks;
    private ArrayList<String> ownedStocksList;
    private ArrayList<String> defaultStocks;
    Random random = new Random();

    public StockMarket() {
        stocks = new HashMap<>();
        this.defaultStocks = new ArrayList<String>(Arrays.asList("AAPL", "ORCL", "TSLA", "TWTR"));
        addStock("AAPL", 300);
        addStock("ORCL", 250);
        addStock("TSLA", 420);
        addStock("TWTR", 12);
        ownedStocks = new HashMap<>();
    }

    public Integer getValue(String name) {
        if (!stocks.containsKey(name)) {
            throw new IllegalArgumentException("Denne aksjen finnes ikke på børsen " + name);
        }
        return this.stocks.get(name);
    }

    public ArrayList<String> getTickers() {
        ArrayList<String> tickersList = new ArrayList<>(stocks.keySet());
        return tickersList;

    }

    public void addValuableListner(ValuableListner observator) {
        if (listeners.contains(observator)) {
            throw new IllegalArgumentException("Observatør er allerede i listen");
        }
        listeners.add(observator);
    }

    public void removeValuableListener(ValuableListner observator) {
        if (!listeners.contains(observator)) {
            throw new IllegalArgumentException("Observator er ikke i listen");
        }
        listeners.remove(observator);
    }

    public void update(String name, int value) {
        // Eiter puts amount as value, or previous amount + new amount
        // https://stackoverflow.com/questions/81346/most-efficient-way-to-increment-a-map-value-in-java
        if (name == null || !JBankHelp.isAllLetters(name) || name.equals("")) {
            throw new IllegalArgumentException("Ugyldig navn på aksje");
            // if (value.getText() == null || !JBankHelp.isAllDigit(value)) {
            // throw new IllegalArgumentException("Ugyidlge tegn i verdien");
        }
        if (value == 0) {
            throw new IllegalArgumentException("Aksjen kan ikke ha 0 som verdi");
        }
        if (getValue(name) - value < 0) {
            throw new IllegalArgumentException("Kan ikke ha 0 eller negativ aksjeverdi");
        } else {
            stocks.merge(name, value, (a, b) -> a + b);
        }
    }

    public void addStock(String name, int value) {
        if (name == null || !JBankHelp.isAllLetters(name)) {
            throw new IllegalArgumentException("Ugyldig navn på aksje");
        }
        if (value <= 0) {
            throw new IllegalArgumentException("Aksjen kan ikke ha 0 eller negativ verdi");
        }
        if (stocks.keySet().stream().filter(stock->!defaultStocks.contains(stock)).anyMatch(stock -> name.equals(stock))) {
                 throw new IllegalArgumentException("Denne akjsen eksisterer allerede");
        } 
        else {
            stocks.merge(name, value, (a, b) -> a + b);
        }}

    public void nextDay() {
        for (String stock : getTickers()) {
            int oldValue = getValue(stock);
            simulate(stock);
            int newValue = getValue(stock);
            for (ValuableListner valuableListner : listeners) {
                valuableListner.valuableChanged(stock, oldValue, newValue);
            }
        }
    }

    public Map<String, Integer> getStocks() {
        Map<String, Integer> stocksCopy = new HashMap<>();
        stocksCopy.putAll(this.stocks);
        return stocksCopy;
    }

    public void buy(String userID, String ticker, int amount) {
        if (ownedStocks.get(userID) == null) {
            ownedStocks.put(userID, new HashMap<>());
        }
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
            if (ownedStocks.get(userID).get(ticker) <= 0) {
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
        try {
            numberOwnedStocks = ownedStocks.get(userID).get(stock);
        } catch (NullPointerException e) {
            return 0;
        }
        return numberOwnedStocks;
    }

    // Inspirert av Stock eksempelet fra forelesning
    // https://gitlab.stud.idi.ntnu.no/tdt4100/v2022/students/-/tree/main/foreksempel/src/main/java/uke12/listener/stocks

    public void simulate(String stock) {

        for (int i = 0; i < 50; i++) {
            try {
                update(stock, random.nextInt(22) - 10);
            } catch (IllegalArgumentException e) {
                // dont update if Stock is going to be 0 or negative price
            }
        }
    }
}
