package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BankTest {
	Currency SEK, DKK;
	Bank SweBank, Nordea, DanskeBank;
	
	@Before
	public void setUp() throws Exception {
		DKK = new Currency("DKK", 0.20);
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		Nordea = new Bank("Nordea", SEK);
		DanskeBank = new Bank("DanskeBank", DKK);
		SweBank.openAccount("Ulrika");
		SweBank.openAccount("Bob");
		Nordea.openAccount("Bob");
		DanskeBank.openAccount("Gertrud");
	}

	@Test
	public void testGetName() {
		assertEquals("SweBank", SweBank.getName());
	}

	@Test
	public void testGetCurrency() {
		assertEquals(SEK, SweBank.getCurrency());
	}

	@Test
	public void testOpenAccount() throws AccountExistsException, AccountDoesNotExistException {
		SweBank.openAccount("Alice");
	}

	@Test
	public void testDeposit() throws AccountDoesNotExistException {
		SweBank.deposit("Ulrika", new Money(500, SEK));
        assertEquals(500, SweBank.getBalance("Ulrika").intValue());
	}

	@Test
	public void testWithdraw() throws AccountDoesNotExistException {
		SweBank.deposit("Ulrika", new Money(500, SEK));
        SweBank.withdraw("Ulrika", new Money(200, SEK));
        assertEquals(300, SweBank.getBalance("Ulrika").intValue());
	}
	
	@Test
	public void testGetBalance() throws AccountDoesNotExistException {
		SweBank.deposit("Bob", new Money(1000, SEK));
        assertEquals(1000, SweBank.getBalance("Bob").intValue());
	}
	
	@Test
	public void testTransfer() throws AccountDoesNotExistException {
		SweBank.deposit("Ulrika", new Money(1000, SEK));
        SweBank.transfer("Ulrika", "Bob", new Money(500, SEK));
        assertEquals(500, SweBank.getBalance("Ulrika").intValue());
        assertEquals(500, SweBank.getBalance("Bob").intValue());
	}
	
	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
		SweBank.deposit("Bob", new Money(1000, SEK));
		SweBank.deposit("Ulrika", new Money(500, SEK));
        SweBank.addTimedPayment("Ulrika", "paymentID", 5, 0, new Money(100, SEK), SweBank, "Bob");
        SweBank.tick();
        assertEquals(1100, SweBank.getBalance("Bob").intValue());
        assertEquals(400, SweBank.getBalance("Ulrika").intValue());
	}
}
