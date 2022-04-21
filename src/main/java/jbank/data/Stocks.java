package jbank.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Stocks {

    private Person loggedInPerson;
    private Map<String, int> stocks = new HashMap<>();
    Random random = new Random();

    public Stocks() {
        updateStock("Aksje1", 500.0);
        updateStock("Aksje2", 250.0);
        updateStock("Aksje3", 12.50);
    }

    public int getValue(String name) {
        return this.stocks.get(name);
    }

    public void updateStock(String name, int value) {
        this.stocks.put(name, stocks.getOrDefault(name, 0.0) + value);
    }

    public void simulateStocks() {

        for (int i = 0; i < 100; i++) {

            try { 
                String[] keys = stocks.keySet().toArray(new String[stocks.size()]);
                String randomKey = keys[random.nextInt(keys.length)];
                updateStock(randomKey, random.nextint(20)-10);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    

    @Override
    public String toString() {
        return stocks.toString();
    }

    public static void main(String[] args) {
        Stocks stocks = new Stocks();
        System.out.println(stocks);
        stocks.simulateStocks();
        System.out.println(stocks);

        
    }

    
}
