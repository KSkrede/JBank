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
		stockMarket.addValuableListner(new StockIndex("newListner", new StockMarket()));

		assertThrows(IllegalArgumentException.class, () -> {
			stockMarket.addValuableListner(listner);
        }, "Skal kaste feilmeldingen når observatør allerede er i listen");

		stockMarket.removeValuableListener(listner);

		assertDoesNotThrow(()->{
            stockMarket.addValuableListner(listner);
        });
	}

	@Test
	public void testUpdate() {
		stockMarket.update("test", 123);
		assertEquals(123, stockMarket.getValue("test"));
		stockMarket.update("AAPL", 100);
		assertEquals(400, stockMarket.getValue("AAPL"));

	}

// 	@Test
// 	public void testValuableChanged() {
// 		assertEquals(245, index.getAvg());
// 		index.valuableChanged("AAPL", 300, 100);
// 		assertEquals(195, index.getAvg());
// 	}
 }