package jbank.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

// public class CryptoMarket implements IValuable {

//     private Person loggedInPerson;
//     private Map<String, Integer> stocks = new HashMap<>();
//     Random random = new Random();

//     public CryptoMarket() {
//         updateStock("Aksje1", 500);
//         updateStock("Aksje2", 250);
//         updateStock("Aksje3", 12);
//     }

//     public int getValue(String name) {
//         return this.stocks.get(name);
//     }

//     @Override
//     public int value(String ticker) {
//         return this.stocks.get(ticker);
//     }

//     public void updateStock(String name, int value) {
//         this.stocks.put(name, stocks.getOrDefault(name, 0.0) + value);
//     }

//     public void simulateStocks() {

//         for (int i = 0; i < 100; i++) {

//             try { 
//                 String[] keys = stocks.keySet().toArray(new String[stocks.size()]);
//                 String randomKey = keys[random.nextInt(keys.length)];
//                 updateStock(randomKey, random.nextInt(20)-10);
//             } catch (Exception e) {
//                 System.out.println(e);
//             }
//         }
//     }

    

//     @Override
//     public String toString() {
//         return stocks.toString();
//     }

//     public static void main(String[] args) {
//         StockMarket stocks = new StockMarket();
//         System.out.println(stocks);
//         stocks.simulateStocks();
//         System.out.println(stocks);

        
//     }

//     @Override
//     public String ticker() {
//         // TODO Auto-generated method stub
//         return null;
//     }

//     @Override
//     public void nextDay() {
//         // TODO Auto-generated method stub
        
//     }


    
// }
