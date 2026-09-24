import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JointHouseholdMembershipTest {

    @Test
    public void testConstructorAndGetters() {

        Name name =
                new Name("Alice", "Brown");

        ContactInfo contactInfo =
                new ContactInfo(
                        name,
                        "200 Country Road",
                        "206-555-5678",
                        "alice@example.com"
                );

        JointHouseholdMembership member =
                new JointHouseholdMembership(
                        "H001",
                        contactInfo,
                        150000.0,
                        3000.0,
                        700.0,
                        4500.0,
                        500.0,
                        1200.0,
                        800.0,
                        300.0,

                        // Household-specific data
                        3,
                        2,
                        5000.0,
                        2500.0
                );

        // CoOpMember fields

        assertEquals(
                "H001",
                member.getMemberId()
        );

        assertEquals(
                contactInfo,
                member.getContactInfo()
        );

        assertEquals(
                150000.0,
                member.getLastYearFarmRevenue()
        );

        // HouseholdMember fields

        assertEquals(
                3,
                member.getNumberOfDependents()
        );

        assertEquals(
                2,
                member.getNumberOfMinorChildren()
        );

        assertEquals(
                5000.0,
                member.getChildcareExpenses()
        );

        assertEquals(
                2500.0,
                member.getDependentCareExpenses()
        );
    }
}