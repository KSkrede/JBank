package jbank.data;

public class CreditAccount extends AbstractAccount {

    private int creditLimit;

    public CreditAccount(String name, Integer value, Integer creditLimit) {
        this.name = name;
        this.value = value;
        this.creditLimit = creditLimit;
    }

    @Override
    public void removeValue(int funds){
        if(value - funds > creditLimit){
            throw new IllegalStateException("Kredittgrensen er nådd");
        }
        else{
            value = value - funds;
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
