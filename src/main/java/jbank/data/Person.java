package jbank.data;

import java.io.FileNotFoundException;
import java.time.LocalDate;

import jbank.logic.JBankHelp;

public class Person {

    private String surName;
    private String givenName;
    private String phoneNumber;
    private String bankIDPin;
    private String birthDate;
    private String userId;
    public Accounts a;

    public Person(String phoneNumber, LocalDate birthDate, String givenName, String surName, String bankIDPin) throws FileNotFoundException {
        setName(givenName, surName);
        setPhoneNumber(phoneNumber);
        setBirthday(birthDate);
        setBankIDPin(bankIDPin);
        this.userId = phoneNumber + JBankHelp.dateToString(birthDate);
        Accounts a = new Accounts();
        a.addPerson(this.userId, this);
    }

    public void setName(String givenName, String surName) {
        if((givenName+surName).length() > 25 ){
            throw new  IllegalArgumentException("Du har valgt et veldig langt navn, venligst kort det ned");
        }
        if (JBankHelp.isAllLettersOrBlank(givenName)) {
            this.givenName = givenName;
        }
        
        else {
            throw new IllegalArgumentException("Det er et ulovlig tegn i " + givenName);
        }

        if(JBankHelp.isAllLetters(surName)){
            this.surName = surName;
        }

        else {
            throw new IllegalArgumentException("Det er et ulovlig tegn i " + surName);
        }

    }

    public String getBirthday() {
        return birthDate;
    }

    public void setBirthday(LocalDate birthDate) {
        LocalDate today = LocalDate.now();
        if(birthDate == null){
            throw new IllegalArgumentException("Du må velge fødselsdato");
        }
        else if (birthDate.isAfter(today)) {
            throw new IllegalArgumentException("Du kan ikke ha fødselsdato i fremtiden");
        }
        else if (birthDate.isAfter(today.minusYears(18))) {
            throw new IllegalArgumentException("Du må minst være 18 år for å lage konto her");
        }

        else {
            this.birthDate = JBankHelp.dateToString(birthDate);
        }
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber.length() == 8 && JBankHelp.isAllDigit(phoneNumber)) {
            this.phoneNumber = phoneNumber;
        } else {
            throw new IllegalArgumentException("Ulovelig telefonnummer " + phoneNumber);
        }
    }

    public void setBankIDPin(String bankIDPin) {
        if (JBankHelp.isAllDigit(bankIDPin)) {
            this.bankIDPin = bankIDPin;
        } else {
            throw new IllegalArgumentException("Ulovelig BankIDpin " + bankIDPin);
        }
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }


    public String getUserId() {
        return userId;
    }


    public String getBankIDPin() {
        return bankIDPin;
    }

    @Override
    public String toString() {
        return String.format("%s;%s;%s;%s;%s", phoneNumber, birthDate, givenName, surName, bankIDPin);
    }

    public String prettyString(){
        return givenName + " " + surName;
    }

    public String getFullName(){
        return givenName + " " + surName;
    }


}
