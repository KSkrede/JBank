package jbank;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jbank.data.StockIndex;
import jbank.data.StockMarket;

public class StockMarketTest {

	private StockMarket stockMarket;

	@BeforeEach
	public void setup() {
		this.stockMarket = new StockMarket();
	}

	@Test
	public void TestgetValue() {
		assertEquals(300, stockMarket.getValue("AAPL"));
		assertThrows(IllegalArgumentException.class, () -> {
			stockMarket.getValue("RandomStock");
		}, "Skal kaste feilmeldingen når aksjen ikke eksisterer");

		assertEquals(300, stockMarket.getValue("AAPL"));

	}

	@Test
	public void testValuableListners() {
		StockIndex listner = new StockIndex("index", stockMarket);

		assertThrows(IllegalArgumentException.class, () -> {
			stockMarket.addValuableListner(listner);
		}, "Skal kaste feilmeldingen når observatør allerede er i listen");

		stockMarket.removeValuableListener(listner);

		assertDoesNotThrow(() -> {
			stockMarket.addValuableListner(listner);
		});
	}

	@Test
	public void testUpdate() {
		stockMarket.update("AAPL", 100);
		assertEquals(400, stockMarket.getValue("AAPL"));
		stockMarket.update("AAPL", -100);

	}

	@Test
	public void testNextDay() {
		// Below test is unstable as index sometimes does not change. But can still be
		// usefull for testing
		// StockIndex listner = new StockIndex("index", stockMarket);
		// int before = listner.getAvg();
		// stockMarket.nextDay();
		// int after = listner.getAvg();
		// assertNotEquals(before, after, "Indeksen burde være endret");
	}

	@Test
	public void testBuySell() {
		// Also tests numberOwnedStocks
		stockMarket.buy("1234", "AAPL", 5);
		assertEquals(5, stockMarket.numberOwnedStocks("1234", "AAPL"));
		stockMarket.sell("1234", "AAPL", 2);
		assertEquals(3, stockMarket.numberOwnedStocks("1234", "AAPL"));

		assertThrows(IllegalArgumentException.class, () -> {
			stockMarket.sell("1234", "RandomStock", 2);
		}, "Skal ikke kunne selge en aksje du ikke eier");

		assertThrows(IllegalArgumentException.class, () -> {
			stockMarket.sell("1234", "RandomStock", 10);
		}, "Skal ikke kunne selge flere aksjer enn du eier");

	}
}