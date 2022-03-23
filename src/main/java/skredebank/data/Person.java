package skredebank.data;

import java.time.LocalDate;

//Implements help methods
import skredebank.logic.Help;

public class Person {

    private String surName;
    private String givenName;
    private String phoneNumber;
    private String bankIDPin;
    private LocalDate birthDate;
    private String userId;

    public Person(String givenName, String surName, String phoneNumber, LocalDate birthDate, String bankIDPin) {
        setName(givenName, surName);
        setPhoneNumber(phoneNumber);
        setBirthday(birthDate);
        setBankIDPin(bankIDPin);
        this.userId = phoneNumber + Help.dateToString(birthDate) ;
        Accounts.addAccounts(this.userId, this);
    }

    public void setName(String givenName, String surName) {
        if (Help.isAllLetters(givenName) && Help.isAllLetters(surName)) {
            this.givenName = givenName;
            this.surName = surName;
        }

        else {
            throw new IllegalArgumentException("Illegal char in " + givenName + " or " + surName);
        }
    }

    public LocalDate getBirthday() {
        return birthDate;
    }

    public void setBirthday(LocalDate birthDate) {
        LocalDate today = LocalDate.now();
        if (birthDate.isAfter(today)) {
            throw new IllegalArgumentException("Birthday is in the future");
        } else {
            this.birthDate = birthDate;
        }
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber.length() == 8 && Help.isAllDigit(phoneNumber)) {
            this.phoneNumber = phoneNumber;
        } else {
            throw new IllegalArgumentException("Illegal phonenumber " + phoneNumber);
        }
    }

    public void setBankIDPin(String bankIDPin) {
        if (Help.isAllDigit(bankIDPin)) {
            this.bankIDPin = bankIDPin;
        } else {
            throw new IllegalArgumentException("Illegal BankID Pin " + bankIDPin);
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



    @Override
    public String toString() {
        return "UserID: "+ userId + " Name: " + givenName + " "+ surName;
    }

    public static void main(String[] args) {
        Person Kristian = new Person("Kristian", "Skrede", "40612594", LocalDate.of(2000, 1, 23), "1234");
        System.out.println(Kristian);
    }

}
