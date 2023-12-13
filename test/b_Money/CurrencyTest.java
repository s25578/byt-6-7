package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CurrencyTest {
	Currency SEK, DKK, NOK, EUR;
	
	@Before
	public void setUp() throws Exception {
		/* Setup currencies with exchange rates */
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
	}

	@Test
	public void testGetName() {
		assertEquals("SEK", SEK.getName());
        assertEquals("DKK", DKK.getName());
        assertEquals("EUR", EUR.getName());
	}
	
	@Test
	public void testGetRate() {
		assertEquals(Double.valueOf(0.15), SEK.getRate());
        assertEquals(Double.valueOf(0.20), DKK.getRate());
        assertEquals(Double.valueOf(1.5), EUR.getRate());
	}
	
	@Test
	public void testSetRate() {
		SEK.setRate(0.18);
        assertEquals(Double.valueOf(0.18), SEK.getRate());
    }
	
	@Test
	public void testGlobalValue() {
		assertEquals(Integer.valueOf(750), SEK.universalValue(5000));
        assertEquals(Integer.valueOf(1200), DKK.universalValue(6000));
        assertEquals(Integer.valueOf(15000), EUR.universalValue(10000));
    }
	
	@Test
	public void testValueInThisCurrency() {
        assertEquals(Integer.valueOf(120000), SEK.valueInThisCurrency(120, EUR));
        assertEquals(Integer.valueOf(33333), EUR.valueInThisCurrency(2500, DKK));
    }

}
