package jbank;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jbank.data.Accounts;
import jbank.data.Person;

public class AccountsTest {

    Accounts accounts;
    Person person;

    @BeforeEach
    public void setup() {
        this.person = new Person("12345678", LocalDate.now().minusYears(19), "Ola", "Nordmann", "1234");
        this.accounts = new Accounts();
    }

    @Test
    public void testRemovePerson() {

        assertThrows(IllegalArgumentException.class, () -> {
            accounts.removePerson();
        }, "Skal ikke kunne fjerne bruker som ikke er logget inn");

    }

    @Test
    public void testChangeName() {

        assertThrows(IllegalArgumentException.class, () -> {
            accounts.changeGivenName("newName");
        }, "Skal ikke kunne endre navn uten at du er logget inn");

        assertThrows(IllegalArgumentException.class, () -> {
            accounts.changeSurName("newName");
        }, "Skal ikke kunne endre navn uten at du er logget inn");

    }

    @Test
    public void testChangePin() {
        assertThrows(IllegalArgumentException.class, () -> {
            accounts.changePin("1234");
        }, "Skal ikke kunne endre navn uten at du er logget inn");

    }

}
