package skredebank;
import java.time.LocalDate;

public class Person {
    
    private String surName;
    private String givenName;
    private String fullName;
    private int phoneNumber;
    private LocalDate birthDate;

    
    public Person(String fullName, int phoneNumber, LocalDate birthDate) {
        //use setname function
        this.fullName = fullName;
        //write check for phonenumber
        this.phoneNumber = phoneNumber;
        //setbirthday test
        this.birthDate = birthDate;
    }

    public String getName() {
        return this.givenName + " " + this.surName;
    }

    public void setName(String fullName) {
        if (fullName.matches("[A-Za-z]{2,} [A-Za-z]{2,}")) {
            int pos = fullName.indexOf(' ');
            this.givenName = fullName.substring(0, pos);
            this.surName = fullName.substring(pos + 1);
            this.fullName = fullName;
          } else {
            throw new IllegalArgumentException("Illegal name");
          }
        } 

    public LocalDate getBirthday() {
        return birthDate;
    }

    public void setBirthday(LocalDate birthDate) {
        LocalDate today = LocalDate.now();
        if(birthDate.isAfter(today)) { 
            throw new IllegalArgumentException("Birthday is in the future");
         } 
         else {this.birthDate = birthDate;
         }
        }

    


    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public static void main(String[] args) {
        Person Kristian = new Person("Kristian Skrede", 40612594, LocalDate.of(2000, 1, 23));
        System.out.println(Kristian.getBirthday());
    }

}
