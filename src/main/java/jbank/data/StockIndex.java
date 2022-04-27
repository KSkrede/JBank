package jbank.data;

import java.util.ArrayList;
import java.util.List;

public class StockIndex implements StockListener {

	private String name;
	private int index;
	List<String> stocksList = new ArrayList<>();

	public StockIndex(String name, StockMarket stockMarket) {
		this.name = name;
		stockMarket.addStockListener(this);
		for (String stock : stockMarket.getStocks().keySet() ) {
			stocksList.add(stock);
			this.index += stockMarket.getValue(stock);
		}
	}

	@Override
	public void stockPriceChanged(String stock, int oldValue, int newValue) {
		int diff = newValue - oldValue;
		this.index = this.index + diff;
	}

	public int getIndex() {
		return index;
	}

	public int getAvg(){
		System.out.println(index);
		System.out.println(stocksList.size());
		return index / stocksList.size();
	}
}
