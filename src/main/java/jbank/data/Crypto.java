package jbank.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Crypto {

    private Person loggedInPerson;
    private Map<String, Double> crypto = new HashMap<>();
    Random random = new Random();

    public Crypto() {
        updateCrypto("Crypto1", 100.0);
        updateCrypto("Crypto2", 250.0);
        updateCrypto("Crypto3", 500.0);
    }

    public double getValue(String name) {
        return this.crypto.get(name);
    }

    public void updateCrypto(String name, double value) {
        this.crypto.put(name, crypto.getOrDefault(name, 0.0) + value);
    }

    public void simulateCrypto() {

        for (int i = 0; i < 100; i++) {

            try { 
                String[] keys = crypto.keySet().toArray(new String[crypto.size()]);
                String randomKey = keys[random.nextInt(keys.length)];
                updateCrypto(randomKey, random.nextDouble(20)-10);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    

    @Override
    public String toString() {
        return crypto.toString();
    }

    public static void main(String[] args) {
        Crypto crypto = new Crypto();
        System.out.println(crypto);
        crypto.simulateCrypto();
        System.out.println(crypto);

        
    }

    
}
