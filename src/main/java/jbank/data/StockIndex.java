package jbank.data;

import java.util.ArrayList;
import java.util.List;

public class StockIndex implements ValuableListner {

	// inspirert av min øving 6

	private String name;
	private int index;
	List<String> stocksList = new ArrayList<>();
	StockMarket stockMarket;

	public StockIndex(String name, StockMarket stockMarket) {
		this.name = name;
		this.stockMarket = stockMarket;
		stockMarket.addValuableListner(this);
		for (String stock : stockMarket.getStocks().keySet()) {
			stocksList.add(stock);
			this.index += stockMarket.getValue(stock);
		}
	}

	@Override
	public void valuableChanged(String stock, int oldValue, int newValue) {
		if (!stocksList.contains(stock)) {
			addStock(stock);
		}
		int diff = newValue - oldValue;
		this.index = this.index + diff;
	}

	public String getName() {
		return this.name;
	}

	public void addStock(String stock) {
		if (stocksList.contains(stock)) {
			throw new IllegalArgumentException("Denne aksjen er allerede i indeksen");
		} else if (!stockMarket.getTickers().contains(stock)) {
			throw new IllegalArgumentException("Denne aksjen er ikke på aksjemarkedet");
		} else {
			stocksList.add(stock);
			this.index += stockMarket.getValue(stock);
		}
	}

	// Not needed as you cant remove stock from GUI
	public void removeStock(String stock) {
		if (!stocksList.contains(stock)) {
			throw new IllegalArgumentException("Kan ikke fjerne aksje som ikke er i indeksen");
		} else {
			this.index -= stockMarket.getValue(stock);
			stocksList.remove(stock);
		}

	}

	public int getAvg() {
		if (stocksList.size() == 0) {
			return 0;
		}
		return index / stocksList.size();
	}
}
