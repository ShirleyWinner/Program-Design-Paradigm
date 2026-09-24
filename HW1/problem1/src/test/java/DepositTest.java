import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DepositTest {

    private final Name bob =
            new Name("Bob", "James", "Brown");

    @Test
    void testValidDeposit() {
        Deposit deposit =
                new Deposit(5000, "123456789012", bob);

        assertEquals(5000, deposit.getAmount());
        assertEquals("123456789012", deposit.getRecipientId());
        assertEquals(bob, deposit.getRecipientName());
    }

    @Test
    void testMinimumDeposit() {
        assertDoesNotThrow(() ->
                new Deposit(1000, "123456789012", bob)
        );
    }

    @Test
    void testMaximumDeposit() {
        assertDoesNotThrow(() ->
                new Deposit(10000, "123456789012", bob)
        );
    }

    @Test
    void testBelowMinimum() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Deposit(
                        999,
                        "123456789012",
                        bob
                )
        );
    }

    @Test
    void testAboveMaximum() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Deposit(
                        10001,
                        "123456789012",
                        bob
                )
        );
    }
}