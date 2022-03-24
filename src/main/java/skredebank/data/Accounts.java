package skredebank.data;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class Accounts {
    private Map<String, Person> accounts;
    private Person loggedInPerson;

    public Accounts() {
        accounts = new HashMap<>();
    }

    public Map<String, Person> getAccounts() {
        return accounts;
    }

    public void addAccounts(String s, Person person) throws FileNotFoundException {
        accounts.put(s, person);
    }


    public Person getLoggedInPerson() {
        return loggedInPerson;
    }

    public void setLoggedInPerson(Person loggedInPerson) {
        this.loggedInPerson = loggedInPerson;
    }

    @Override
    public String toString() {
        return "Account: " + accounts;
    }



    



}
