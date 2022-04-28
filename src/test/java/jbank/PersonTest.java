package jbank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import jbank.data.Person;
import jbank.logic.JBankHelp;

public class PersonTest {
    
    JBankHelp jbank;

    @Test
    public void testName(){
        assertThrows(IllegalArgumentException.class, () -> {
            Person p1 = new Person("12345678", LocalDate.now().minusYears(19), "0123456789","123456789101112", "1234");
        //},"Du har valgt et veldig langt navn, venligst kort det ned");

        assertThrows(IllegalArgumentException.class, () -> {
            Person p1 = new Person("12345678", LocalDate.now().minusYears(19), "34t","Nordmann", "1234");
        },"Det er et ulovlig tegn i 34t");

        Person p2 = new Person("12345678", LocalDate.now().minusYears(19), "Ola","Nordmann", "1234");
        assertEquals("Ola",p2.getGivenName());
        assertEquals("Nordmann",p2.getGivenName());
        assertEquals("Ola Nordmann",p2.getFullName());
        p2.setName("Jens", "Stoltenberg");
        assertEquals("Jens Stoltenberg", p2.getFullName());




    }}


}
