package jbank.data;

import java.util.ArrayList;
import java.util.List;

public class StockIndex implements ValuableListner {

	private String name;
	private int index;
	List<String> stocksList = new ArrayList<>();

	public StockIndex(String name, StockMarket stockMarket) {
		this.name = name;
		stockMarket.addValuableListner(this);
		for (String stock : stockMarket.getStocks().keySet() ) {
			stocksList.add(stock);
			this.index += stockMarket.getValue(stock);
		}
	}

	@Override
	public void valuableChanged(String stock, int oldValue, int newValue) {
		int diff = newValue - oldValue;
		this.index = this.index + diff;
	}

	public int getIndex() {
		return this.index;
	}

	public String getName(){
		return this.name;
	}

	public int getAvg(){
		return index / stocksList.size();
	}
}
