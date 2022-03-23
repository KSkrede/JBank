package skredebank;

import java.time.LocalDate;

public class Person {

    private String surName;
    private String givenName;
    private String phoneNumber;
    private String bankIDPin;
    private LocalDate birthDate;
    private int userId;

    private static int count = 0;

    //bruke map eller liste til å inneholde alle persons som igjen brukes til å logge inn

    public Person(String givenName, String surName, String phoneNumber, LocalDate birthDate, String bankIDPin) {
        setName(givenName, surName);
        setPhoneNumber(phoneNumber);
        setBirthday(birthDate);
        setBankIDPin(bankIDPin);
        this.userId = Person.count;
        count++;
    }

    public void setName(String givenName, String surName) {
        if (this.isAllLetters(givenName) && this.isAllLetters(surName)) {
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
        if (phoneNumber.length() == 8 && this.isAllDigit(phoneNumber)) {
            this.phoneNumber = phoneNumber;
        } else {
            throw new IllegalArgumentException("Illegal phonenumber " + phoneNumber);
        }
    }

    public void setBankIDPin(String bankIDPin) {
        if (this.isAllDigit(phoneNumber)) {
            this.bankIDPin = bankIDPin;
        } else {
            throw new IllegalArgumentException("Illegal phonenumber " + phoneNumber);
        }
    }

    public boolean isAllDigit(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    public boolean isAllLetters(String str) {
        for (char c : str.toCharArray()) {
            if(!Character.isLetter(c)) {
                return false;
            }
        }
    
        return true;
    }

    

    @Override
    public String toString() {
        return "Person [bankIDPin=" + bankIDPin + ", birthDate=" + birthDate + ", givenName=" + givenName
                + ", phoneNumber=" + phoneNumber + ", surName=" + surName + "]";
    }

    public static void main(String[] args) {
        Person Kristian = new Person("Kristian", "Skrede", "40612594", LocalDate.of(2000, 1, 23), "1234");
        System.out.println(Kristian);
    }

}
