package jbank.data;

import java.util.HashMap;
import java.util.Map;

public class Accounts {
    private Map<String, Person> accounts;
    private Person loggedInPerson;

    public Accounts() {
        accounts = new HashMap<>();
    }

    public Map<String, Person> getAccounts() {
        Map<String, Person> temp = accounts;
        return temp;
    }

    public void addPerson(Person person) {
        String userID = person.getUserId();
        if (accounts.containsKey(userID)){
            throw new IllegalArgumentException("Denne brukeren eksisterer allerede");
        }
        else{
        accounts.put(userID, person);}
    }


    public Person getLoggedInPerson() {
        return loggedInPerson;
    }

    public void setLoggedInPerson(Person loggedInPerson) {
        this.loggedInPerson = loggedInPerson;
    }


    public void removePerson() throws IllegalAccessException{
        verifyUser();
        accounts.remove(loggedInPerson.getUserId());
        this.loggedInPerson = null;
    }

    public void changeGivenName(String newName) throws IllegalAccessException{
        verifyUser();
        accounts.get(loggedInPerson.getUserId()).setGivenName(newName);
        this.loggedInPerson = null;
    }

    public void changeSurName(String newName) throws IllegalAccessException{
        verifyUser();
        accounts.get(loggedInPerson.getUserId()).setSurName(newName);
        this.loggedInPerson = null;
    }

    public void changePin(String newPin) throws IllegalAccessException{
        verifyUser();
        accounts.get(loggedInPerson.getUserId()).setPin(newPin);
        this.loggedInPerson = null;
    }

    private void verifyUser() throws IllegalAccessException{
        if( getLoggedInPerson() == null || !accounts.containsKey(loggedInPerson.getUserId())){
            throw new IllegalArgumentException("Konto finnes ikke");
        }
    }

    @Override
    public String toString() {
        return accounts + "";
    }



    



}
