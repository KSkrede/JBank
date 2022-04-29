package jbank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import jbank.data.StockIndex;
import jbank.data.StockMarket;

public class StockIndexTest {

	private StockIndex index;
	private StockMarket stockMarket;

	@BeforeEach
	public void setup() {
		this.stockMarket = new StockMarket();
		this.index = new StockIndex("index", stockMarket);
	}

	@Test
	public void testAddStock() {

		// Looks weird because there are default stocks initiated
		assertEquals(245, index.getAvg());
		stockMarket.addStock("NewStock", 100);
		index.addStock("NewStock");
		assertEquals(216, index.getAvg());

		assertThrows(IllegalArgumentException.class, () -> {
			index.addStock("RandomStock");
		}, "Du skal ikke kunne legge til en aksje ikke er på aksjemarkedet");

	}

	@Test
	public void testAddDuplicateStock() {
		assertThrows(IllegalArgumentException.class, () -> {
			index.addStock("AAPL");
		}, "Du skal ikke kunne legge til en aksje som allerede");

	}

	@Test
	public void testRemoveStock() {
		assertEquals(245, index.getAvg());
		this.stockMarket.addStock("NewStock", 100);
		index.addStock("NewStock");
		assertEquals(216, index.getAvg());
		index.removeStock("NewStock");
		assertEquals(245, index.getAvg());
		assertThrows(IllegalArgumentException.class, () -> {
			index.addStock("RandomStock");
		}, "Du skal ikke kunne fjerne aksje som ikke er i indeksen");

	}

	@Test
	public void testValuableChanged() {
		assertEquals(245, index.getAvg());
		index.valuableChanged("AAPL", 300, 100);
		assertEquals(195, index.getAvg());
	}
}