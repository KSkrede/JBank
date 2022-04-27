package jbank.data;
//inspirert av min løsning av Øving 6

public interface StockListener {
    public void stockPriceChanged(String ticker, int oldValue, int newValue);
}
