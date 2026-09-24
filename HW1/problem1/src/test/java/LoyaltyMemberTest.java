import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LoyaltyMemberTest {

    private LoyaltyMember alice;
    private LoyaltyMember bob;

    @BeforeEach
    void setUp() {

        LoyaltyMember.clearMembers();

        alice = new LoyaltyMember(
                "111111111111",
                new Name("Alice", "Marie", "Smith"),
                "alice@example.com",
                new PointsBalance(10000, 5000, 3000)
        );

        bob = new LoyaltyMember(
                "222222222222",
                new Name("Bob", "James", "Brown"),
                "bob@example.com",
                new PointsBalance(2000, 1000, 500)
        );
    }

    @Test
    void testMemberCreation() {
        assertEquals("111111111111", alice.getMembershipId());
        assertEquals("alice@example.com", alice.getEmail());
        assertEquals(
                new Name("Alice", "Marie", "Smith"),
                alice.getName()
        );
    }

    @Test
    void testInvalidMembershipId() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new LoyaltyMember(
                        "123",
                        new Name("John", "A", "Smith"),
                        "john@example.com",
                        new PointsBalance(1000, 1000, 1000)
                )
        );
    }

    @Test
    void testTransferPoints() {

        Deposit deposit = new Deposit(
                3000,
                "222222222222",
                new Name("Bob", "James", "Brown")
        );

        alice.transferPoints(deposit);

        // Sender
        assertEquals(
                7000,
                alice.getPointsBalance().getTotalPoints()
        );

        // Recipient
        assertEquals(
                5000,
                bob.getPointsBalance().getTotalPoints()
        );

        assertEquals(
                4000,
                bob.getPointsBalance().getPointsEarnedThisYear()
        );

        assertEquals(
                3500,
                bob.getPointsBalance().getPointsExpiringThisYear()
        );
    }

    @Test
    void testWrongRecipientName() {

        Deposit deposit = new Deposit(
                1000,
                "222222222222",
                new Name("Wrong", "Name", "Person")
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> alice.transferPoints(deposit)
        );

        // Transfer must not modify either account.
        assertEquals(
                10000,
                alice.getPointsBalance().getTotalPoints()
        );

        assertEquals(
                2000,
                bob.getPointsBalance().getTotalPoints()
        );
    }

    @Test
    void testRecipientDoesNotExist() {

        Deposit deposit = new Deposit(
                1000,
                "999999999999",
                new Name("Unknown", "A", "Person")
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> alice.transferPoints(deposit)
        );
    }

    @Test
    void testInsufficientPoints() {

        Deposit deposit = new Deposit(
                10000,
                "222222222222",
                new Name("Bob", "James", "Brown")
        );

        LoyaltyMember poorMember =
                new LoyaltyMember(
                        "333333333333",
                        new Name("Tom", "A", "Jones"),
                        "tom@example.com",
                        new PointsBalance(2000, 1000, 500)
                );

        assertThrows(
                IllegalArgumentException.class,
                () -> poorMember.transferPoints(deposit)
        );

        assertEquals(
                2000,
                poorMember.getPointsBalance().getTotalPoints()
        );
    }

    @Test
    void testTransferToSelf() {

        Deposit deposit = new Deposit(
                1000,
                "111111111111",
                new Name("Alice", "Marie", "Smith")
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> alice.transferPoints(deposit)
        );
    }
}