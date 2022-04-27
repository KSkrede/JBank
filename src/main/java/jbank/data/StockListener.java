package jbank.data;

public interface StockListener {
    public void stockPriceChanged(String ticker, int oldValue, int newValue);
}
