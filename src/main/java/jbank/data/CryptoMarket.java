package jbank.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class CryptoMarket implements IValuable {

    // Inspiret fra Crypto eksempelete fra forelesning
    // https://gitlab.stud.idi.ntnu.no/tdt4100/v2022/students/-/tree/main/foreksempel/src/main/java/uke12/listener/cryptos

    private Map<String, Crypto> cryptos;
    private Map<String, Map<Crypto, Integer>> ownedCryptos;
    private ArrayList<String> ownedCryptosList;
    Random random = new Random();

    public CryptoMarket() {
        cryptos = new HashMap<>();
        update("Aksje1", 500);
        update("Aksje2", 250);
        update("Aksje3", 12);
        ownedCryptos = new HashMap<>();
    }

    public Integer getValue(String name) {
        return this.cryptos.get(name).value();
    }

    public ArrayList<String> getTickers() {
        ArrayList<String> tickersList = new ArrayList<>(cryptos.keySet());
        return tickersList;

    }

    public void update(Crypto crypto, int value) {
        this.cryptos.put(crypto.ticker(), cryptos.getOrDefault(crypto, crypto.value()) + value);
    }

    public void nextDay() {
        for (int i = 0; i < 100; i++) {
            try {
                String[] keys = cryptos.keySet().toArray(new String[cryptos.size()]);
                String randomKey = keys[random.nextInt(keys.length)];
                update(randomKey, random.nextInt(20) - 5);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public Map<String, Integer> getCryptos(){
        Map<String, Integer> cryptosCopy = new HashMap<>();
        cryptosCopy.putAll(this.cryptos);
        return cryptosCopy;
    }

    public void buy(String userID, String ticker, int amount) {
        // Eiter puts amount as value, or previous amount + new amount
        // https://stackoverflow.com/questions/81346/most-efficient-way-to-increment-a-map-value-in-java
        ownedCryptos.get(userID).merge(ticker, amount, (a, b) -> a + b);
    }

    public void sell(String userID, String ticker, int amount) {

        if (!ownedCryptos.get(userID).containsKey(ticker)) {
            throw new IllegalArgumentException("Du kan ikke selge en aksje du ikke eier");

        }

        if (ownedCryptos.get(userID).get(ticker) < amount) {
            throw new IllegalArgumentException("Du kan ikke selge flere aksjer enn de du eier");
        }

        else {
            ownedCryptos.get(userID).merge(ticker, amount, (a, b) -> a - b);
        }
    }

    public ArrayList<String> listOwnedCryptos(String userID){
        if(ownedCryptos.get(userID) == null){
            ownedCryptos.put(userID, new HashMap<>());
        }
        ownedCryptosList = new ArrayList<String>(ownedCryptos.get(userID).keySet());
        return ownedCryptosList;
    }

    public int numberOwnedCryptos(String userID, String crypto){
        return ownedCryptos.get(userID).get(crypto);
    }

}
