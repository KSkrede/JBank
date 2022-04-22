package jbank.data;

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

    public void addPerson(String s, Person person) throws FileNotFoundException {
        accounts.put(s, person);
    }


    public Person getLoggedInPerson() {
        return loggedInPerson;
    }

    public void setLoggedInPerson(Person loggedInPerson) {
        this.loggedInPerson = loggedInPerson;
    }

    public void removePerson(){
        //TODO Validate that account exist
        accounts.remove(loggedInPerson.getUserId());
        this.loggedInPerson = null;
    }

    public void changeGivenName(String newName){
        //TODO Validate that account exist
        accounts.get(loggedInPerson.getUserId()).setGivenName(newName);
        this.loggedInPerson = null;
    }

    public void changeSurName(String newName){
        //TODO Validate that account exist
        accounts.get(loggedInPerson.getUserId()).setSurName(newName);
        this.loggedInPerson = null;
    }

    public void changePin(String newPin){
        //TODO Validate that account exist
        accounts.get(loggedInPerson.getUserId()).setBankIDPin(newPin);
        this.loggedInPerson = null;
    }

    @Override
    public String toString() {
        return accounts + "";
    }



    



}
