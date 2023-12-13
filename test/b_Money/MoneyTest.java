package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MoneyTest {
	Currency SEK, DKK, NOK, EUR;
	Money SEK100, EUR10, SEK200, EUR20, SEK0, EUR0, SEKn100;
	
	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
		SEK100 = new Money(10000, SEK);
		EUR10 = new Money(1000, EUR);
		SEK200 = new Money(20000, SEK);
		EUR20 = new Money(2000, EUR);
		SEK0 = new Money(0, SEK);
		EUR0 = new Money(0, EUR);
		SEKn100 = new Money(-10000, SEK);
	}

	@Test
	public void testGetAmount() {
		assertEquals(Integer.valueOf(10000), SEK100.getAmount());
        assertEquals(Integer.valueOf(1000), EUR10.getAmount());
	}

	@Test
	public void testGetCurrency() {
		assertEquals(SEK, SEK100.getCurrency());
        assertEquals(EUR, EUR10.getCurrency());
	}

	@Test
	public void testToString() {
		assertEquals("100.0 SEK", SEK100.toString());
        assertEquals("10.0 EUR", EUR10.toString());
	}

	@Test
	public void testGlobalValue() {
		assertEquals(Integer.valueOf(1500), EUR10.universalValue());
        assertEquals(Integer.valueOf(3000), SEK200.universalValue());
    }

	@Test
	public void testEqualsMoney() {
		assertTrue(SEK100.equals(SEK100));
        assertFalse(SEK100.equals(EUR10));
	}

	@Test
	public void testAdd() {
		Money sumSEK = SEK100.add(SEK200);
        assertEquals(Integer.valueOf(30000), sumSEK.getAmount());
        assertEquals(SEK, sumSEK.getCurrency());

        Money sumEUR = EUR10.add(EUR20);
        assertEquals(Integer.valueOf(3000), sumEUR.getAmount());
        assertEquals(EUR, sumEUR.getCurrency());
	}

	@Test
	public void testSub() {
		Money diffSEK = SEK200.sub(SEK100);
        assertEquals(Integer.valueOf(10000), diffSEK.getAmount());
        assertEquals(SEK, diffSEK.getCurrency());

        Money diffEUR = EUR20.sub(EUR10);
        assertEquals(Integer.valueOf(1000), diffEUR.getAmount());
        assertEquals(EUR, diffEUR.getCurrency());
	}

	@Test
	public void testIsZero() {
		assertFalse(SEK100.isZero());
        assertTrue(SEK0.isZero());
	}

	@Test
	public void testNegate() {
		Money negatedSEK = SEK100.negate();
        assertEquals(Integer.valueOf(-10000), negatedSEK.getAmount());
        assertEquals(SEK, negatedSEK.getCurrency());
	}

	@Test
	public void testCompareTo() {
		assertTrue(SEK100.compareTo(SEK100) == 0);
        assertTrue(SEK100.compareTo(SEK200) < 0);
        assertTrue(SEK200.compareTo(SEK100) > 0);
	}
}
