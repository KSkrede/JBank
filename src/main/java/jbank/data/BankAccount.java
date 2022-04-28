package jbank.data;

public class BankAccount extends AbstractAccount{

    public BankAccount(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public void removeValue(int funds){
        if(funds > value){
            throw new IllegalArgumentException("Kan ikke ta ut flere penger enn du har på konto");
        }
        value = value - funds;
    }

    
    



    
}
