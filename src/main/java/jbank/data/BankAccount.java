package jbank.data;

public class BankAccount extends AbstractBank {

    public BankAccount(String name, Integer value) {
        super(name, value);
    }

    @Override
    public void removeValue(int funds) {
        if (funds > value) {
            throw new IllegalArgumentException("Kan ikke ta ut flere penger enn du har på konto");
        }
        value = value - funds;
    }

}
