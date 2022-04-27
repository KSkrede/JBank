package jbank.data;

public class BankAccount {

    private String name;
    private Integer value;

    
    public BankAccount(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public void addValue(int funds){
        value = value + funds;
    }

    public void removeValue(int funds){
        value = value - funds;
    }




    @Override
    public String toString() {
        return name;
    }

    
    



    
}
