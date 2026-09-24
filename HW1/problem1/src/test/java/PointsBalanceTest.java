import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PointsBalanceTest {

    @Test
    void testInitialBalance() {
        PointsBalance balance =
                new PointsBalance(5000, 3000, 2000);

        assertEquals(5000, balance.getTotalPoints());
        assertEquals(3000, balance.getPointsEarnedThisYear());
        assertEquals(2000, balance.getPointsExpiringThisYear());
    }

    @Test
    void testAddTransferredPoints() {
        PointsBalance balance =
                new PointsBalance(5000, 3000, 2000);

        balance.addTransferredPoints(1000);

        assertEquals(6000, balance.getTotalPoints());
        assertEquals(4000, balance.getPointsEarnedThisYear());
        assertEquals(3000, balance.getPointsExpiringThisYear());
    }

    @Test
    void testSubtractPoints() {
        PointsBalance balance =
                new PointsBalance(5000, 3000, 2000);

        balance.subtractPoints(2000);

        assertEquals(3000, balance.getTotalPoints());
    }

    @Test
    void testInsufficientPoints() {
        PointsBalance balance =
                new PointsBalance(1000, 1000, 1000);

        assertThrows(
                IllegalArgumentException.class,
                () -> balance.subtractPoints(2000)
        );
    }
}