package b_Money;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AccountTest {
	Currency SEK, DKK;
	Bank Nordea;
	Bank DanskeBank;
	Bank SweBank;
	Account testAccount;
	
	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		SweBank.openAccount("Alice");
		testAccount = new Account("Hans", SEK);
		testAccount.deposit(new Money(10000000, SEK));

		SweBank.deposit("Alice", new Money(1000000, SEK));
	}
	
	@Test
	public void testAddRemoveTimedPayment() {
		assertFalse(testAccount.timedPaymentExists("paymentID"));
        testAccount.addTimedPayment("paymentID", 10, 5, new Money(100, SEK), SweBank, "Alice");
        assertTrue(testAccount.timedPaymentExists("paymentID"));
        testAccount.removeTimedPayment("paymentID");
        assertFalse(testAccount.timedPaymentExists("paymentID"));
	}
	
	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
		
		testAccount.addTimedPayment("paymentID", 0, 0, new Money(1000000, SEK), SweBank, "Alice");
		for (int i = 0; i < 5; i++) {
            testAccount.tick();
        }
        assertEquals(6000000, SweBank.getBalance("Alice").intValue());
	}

	@Test
	public void testAddWithdraw() {
        testAccount.withdraw(new Money(9000000, SEK));
        assertEquals(1000000, testAccount.getBalance().getAmount().intValue());
	}
	
	@Test
	public void testGetBalance() {
		assertEquals(10000000, testAccount.getBalance().getAmount().intValue());
	}
}
