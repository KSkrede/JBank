package jbank.data;

public class BankAccount extends AbstractAccount{

    public BankAccount(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return name;
    }

    
    



    
}
