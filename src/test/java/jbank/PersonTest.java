package jbank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jbank.data.Person;
import jbank.logic.JBankHelp;

public class PersonTest {

    Person person;

    @BeforeEach
    public void setup() {
        this.person = new Person("12345678", LocalDate.now().minusYears(19), "Ola", "Nordmann", "1234");
    }

    @Test
    public void testName() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Person("12345678", LocalDate.now().minusYears(19), "abcdefghijklmnop", "abcdefghijklmnop", "1234");
        }, "Bør få en feil ved veldig langt navn");

        assertThrows(IllegalArgumentException.class, () -> {
            new Person("12345678", LocalDate.now().minusYears(19), "34t", "Nordmann", "1234");
        }, "Det er et ulovlig tegn i 34t");

        assertEquals("Ola", person.getGivenName());
        assertEquals("Nordmann", person.getSurName());
        assertEquals("Ola Nordmann", person.getFullName());
        person.setName("Jens", "Stoltenberg");
        assertEquals("Jens Stoltenberg", person.getFullName());
    }

    @Test
    public void testBirthday() {

        assertThrows(IllegalArgumentException.class, () -> {
            new Person("12345678", null, "Ola", "Nordmann", "1234");
        }, "Du må velge en korrekt fødselsdato");

        assertThrows(IllegalArgumentException.class, () -> {
            new Person("12345678", LocalDate.now().minusYears(15), "Ola", "Nordmann", "1234");
        }, "Du må minst være 18 år for å lage konto her");

        assertThrows(IllegalArgumentException.class, () -> {
            new Person("12345678", LocalDate.now().plusDays(5), "Ola", "Nordmann", "1234");
        }, "Du kan ikke ha fødselsdato i fremtiden");

        assertEquals(JBankHelp.dateToString(LocalDate.now().minusYears(19)), person.getBirthday());

    }

    @Test
    public void testPhone() {

        assertThrows(IllegalArgumentException.class, () -> {
            new Person("1234567", LocalDate.now().minusYears(19), "Ola", "Nordmann", "1234");
        }, "Ulovelig telefonnummer 1234567. Du må ha 8 siffer");

        assertThrows(IllegalArgumentException.class, () -> {
            new Person("1234567a", LocalDate.now().minusYears(19), "Ola", "Nordmann", "1234");
        }, "Ulovelig telefonnummer 1234567a. Du må ha 8 siffer");

        assertEquals("12345678", person.getPhoneNumber());
    }

    @Test
    public void testPin() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Person("12345678", LocalDate.now().minusYears(19), "Ola", "Nordmann", "");
        }, "Ulovelig BankIDpin");

        assertThrows(IllegalArgumentException.class, () -> {
            new Person("12345678", LocalDate.now().minusYears(19), "Ola", "Nordmann", "abc");
        }, "Ulovelig BankIDpin");

        assertEquals("1234", person.getPin());
    }

    @Test
    public void testUserID() {
        assertEquals("12345678" + (person.getBirthday()), person.getUserId());
    }

    @Test
    public void testToString() {
        assertEquals("12345678;" + JBankHelp.dateToString(LocalDate.now().minusYears(19)) + ";Ola;Nordmann",
                person.toString());
    }

}
