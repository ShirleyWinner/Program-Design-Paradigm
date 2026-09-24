import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FarmerMemberTest {

    @Test
    public void testConstructorAndGetters() {

        Name name =
                new Name("John", "Smith");

        ContactInfo contactInfo =
                new ContactInfo(
                        name,
                        "100 Farm Road",
                        "206-555-1234",
                        "john@example.com"
                );

        FarmerMember member =
                new FarmerMember(
                        "M001",
                        contactInfo,
                        100000.0,
                        2000.0,
                        500.0,
                        3000.0,
                        400.0,
                        1000.0,
                        600.0,
                        200.0
                );

        assertEquals("M001", member.getMemberId());
        assertEquals(contactInfo, member.getContactInfo());

        assertEquals(
                100000.0,
                member.getLastYearFarmRevenue()
        );

        assertEquals(
                2000.0,
                member.getTotalDuesPaid()
        );

        assertEquals(
                500.0,
                member.getEquipmentLoanInterestPaid()
        );

        assertEquals(
                3000.0,
                member.getLandTaxesPaid()
        );

        assertEquals(
                400.0,
                member.getTrainingCertificationFeesPaid()
        );

        assertEquals(
                1000.0,
                member.getCooperativeReserveFundContributions()
        );

        assertEquals(
                600.0,
                member.getMemberHealthFundContributions()
        );

        assertEquals(
                200.0,
                member.getCommunityDonations()
        );
    }
}