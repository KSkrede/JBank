package jbank.data;

public abstract class AbstractAccount {

    //Inspirert av Øving 7;
 
    protected String name;
    protected Integer value;

    public AbstractAccount(String name, Integer value) {
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
