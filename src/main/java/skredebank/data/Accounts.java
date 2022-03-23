package skredebank.data;

import java.util.HashMap;
import java.util.Map;

public class Accounts {
    public Map<String, Person> accounts;

    public Accounts() {
        accounts = new HashMap<>();
    }

    public Map<String, Person> getAccounts() {
        return accounts;
    }

    public void addAccounts(String s, Person person) {
        accounts.put(s, person);
    }



}
