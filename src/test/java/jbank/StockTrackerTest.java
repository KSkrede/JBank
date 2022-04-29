package jbank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jbank.data.StockIndex;
import jbank.data.StockMarket;
import jbank.data.StockTracker;

public class StockTrackerTest {

	private StockMarket stockMarket;
	private StockIndex stockIndex;
	private StockTracker stockTracker;

	@BeforeEach
	public void setup() {
		this.stockMarket = new StockMarket();
		this.stockIndex = new StockIndex("indeks", stockMarket);
		this.stockTracker = new StockTracker();
	}

	@Test
	public void testLog() {
		assertEquals(0, stockTracker.getStockprice(1, "AAPL"));
		stockTracker.log(stockMarket.getStocks(), 1, 400);
		assertEquals(300, stockTracker.getStockprice(1, "AAPL"));

		assertThrows(IllegalArgumentException.class, () -> {
			stockTracker.log(stockMarket.getStocks(), -2, 400);
			stockTracker.getStockprice(-2, "AAPL");
		}, "Skal ikke kunne logge negative dager");

	}

	@Test
	public void testGetStocklogs() {
		assertEquals(new HashMap<>(), stockTracker.getStocklogs());
		stockTracker.log(stockMarket.getStocks(), 5, 400);
		Map<Integer, Map<String, Integer>> stocklogs = new HashMap<>();
		stocklogs.put(5, stockMarket.getStocks());
		assertEquals(stocklogs, stockTracker.getStocklogs());
	}

	@Test
	public void testIndexlogs() {
		assertEquals(new HashMap<>(), stockTracker.getIndexlogs());
		stockTracker.log(stockMarket.getStocks(), 5, 400);
		Map<Integer, Integer> indexLogs = new HashMap<>();
		indexLogs.put(5, 400);
		assertEquals(indexLogs, stockTracker.getIndexlogs());
	}

}