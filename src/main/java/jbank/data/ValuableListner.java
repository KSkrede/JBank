package jbank.data;
//inspirert av min løsning av Øving 6

public interface ValuableListner {
    public void valuableChanged(String ticker, int oldValue, int newValue);
}
