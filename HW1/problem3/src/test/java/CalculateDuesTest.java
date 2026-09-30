import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculateDuesTest {
    private static final double DELTA = 0.000001;

    private ContactInfo contact() {
        return new ContactInfo(new Name("Test", "Member"),
                "Farm Road", "555-0100", "test@example.com");
    }

    private CoOpMember individual(double revenue, double paid, double interest,
                                  double tax, double reserve, double health) {
        return new FarmerMember("I1", contact(), revenue, paid, interest, tax,
                1000.0, reserve, health, 2000.0);
    }

    private CoOpMember household(String type, double revenue, double paid,
                                 double interest, double tax, double reserve,
                                 double health, double childcare) {
        switch (type) {
            case "joint":
                return new JointHouseholdMembership("H1", contact(), revenue, paid,
                        interest, tax, 1000.0, reserve, health, 2000.0,
                        2, 1, childcare, 9000.0);
            case "separate":
                return new SeparateHouseholdMembership("H1", contact(), revenue, paid,
                        interest, tax, 1000.0, reserve, health, 2000.0,
                        2, 1, childcare, 9000.0);
            case "single":
                return new SingleHeadHouseholdMembership("H1", contact(), revenue, paid,
                        interest, tax, 1000.0, reserve, health, 2000.0,
                        2, 1, childcare, 9000.0);
            default:
                throw new IllegalArgumentException("Unknown membership type");
        }
    }

    @ParameterizedTest
    @CsvSource({
            "0, 0", "54999, 8249.85", "55000, 10450", "55001, 10450.19"
    })
    void individualRateBoundaries(double revenue, double expected) {
        assertEquals(expected, individual(revenue, 0, 0, 0, 0, 0).calculateDues(), DELTA);
    }

    @ParameterizedTest
    @CsvSource({
            "0, 0", "89999, 13049.855", "90000, 16650", "90001, 16650.185"
    })
    void householdRateBoundaries(double revenue, double expected) {
        assertEquals(expected, household("joint", revenue, 0, 0, 0, 0, 0, 0)
                .calculateDues(), DELTA);
    }

    @Test
    void individualCombinesFundsAndPaidDuesBeforeSelectingRate() {
        // 60000 - 2000 - (3000 + 2000) * .7 - 2500 = 52000.
        CoOpMember member = individual(60000, 2000, 7000, 6000, 3000, 2000);
        assertEquals(7800.0, member.calculateDues(), DELTA);
        assertEquals(7800.0, member.calculateDues(), DELTA);
        assertEquals(60000.0, member.getLastYearFarmRevenue(), DELTA);
        assertEquals(2000.0, member.getTotalDuesPaid(), DELTA);
    }

    @Test
    void individualFundDeductionHasNoHouseholdCap() {
        assertEquals(13680.0, individual(100000, 0, 0, 0, 20000, 20000)
                .calculateDues(), DELTA);
    }

    @ParameterizedTest
    @CsvSource({
            "249999, 7000, 5501, 47024.81",
            "250000, 7000, 5501, 47500",
            "250001, 7000, 5501, 47500.19",
            "100000, 7000, 5499, 19000",
            "100000, 7000, 5500, 19000",
            "100000, 7000, 5501, 18525"
    })
    void equipmentDeductionUsesStrictOriginalRevenueAndCombinedExpenses(
            double revenue, double interest, double tax, double expected) {
        assertEquals(expected, individual(revenue, 0, interest, tax, 0, 0)
                .calculateDues(), DELTA);
    }

    @Test
    void equipmentEligibilityDoesNotUseRevenueAfterDeductions() {
        assertEquals(45600.0, individual(250000, 10000, 13000, 0, 0, 0)
                .calculateDues(), DELTA);
    }

    @ParameterizedTest
    @CsvSource({
            "10000, 10000, 12615", // 13000 deduction: below cap.
            "20000, 20000, 11962.5" // 26000 deduction capped at 17500.
    })
    void householdCombinesFundsAndCapsDeduction(double reserve, double health,
                                               double expected) {
        assertEquals(expected, household("joint", 100000, 0, 0, 0, reserve, health, 0)
                .calculateDues(), DELTA);
    }

    @ParameterizedTest
    @ValueSource(strings = {"joint", "separate", "single"})
    void allHouseholdTypesApplyAllDeductionsInOrder(String type) {
        // 100000 - 2000 - 13000 - 2500 - 1250 = 81250.
        CoOpMember member = household(type, 100000, 2000, 7000, 6000,
                10000, 10000, 6000);
        assertEquals(11781.25, member.calculateDues(), DELTA);
        assertEquals(11781.25, member.calculateDues(), DELTA);
        assertEquals(2000.0, member.getTotalDuesPaid(), DELTA);
    }

    @ParameterizedTest
    @CsvSource({
            "199999, 5001, 36768.565",
            "200000, 5001, 37000",
            "200001, 5001, 37000.185",
            "100000, 4999, 18500",
            "100000, 5000, 18500",
            "100000, 5001, 18268.75"
    })
    void childcareDeductionUsesStrictBoundaries(double revenue, double childcare,
                                               double expected) {
        assertEquals(expected, household("joint", revenue, 0, 0, 0, 0, 0, childcare)
                .calculateDues(), DELTA);
    }

    @Test
    void childcareEligibilityUsesOriginalRevenue() {
        assertEquals(35150.0, household("joint", 200000, 10000, 0, 0, 0, 0, 6000)
                .calculateDues(), DELTA);
    }

    @Test
    void reserveDeductionFloorsAssessableRevenueAtZero() {
        assertEquals(0.0, individual(1000, 500, 0, 0, 1000, 1000)
                .calculateDues(), DELTA);
        assertEquals(0.0, household("joint", 1000, 500, 0, 0, 1000, 1000, 0)
                .calculateDues(), DELTA);
        assertEquals(0.0, individual(700, 0, 0, 0, 1000, 0)
                .calculateDues(), DELTA);
        assertEquals(0.0, household("joint", 650, 0, 0, 0, 1000, 0, 0)
                .calculateDues(), DELTA);
    }

    @Test
    void subsequentDeductionsAreSubtractedAfterTheSpecifiedZeroFloor() {
        assertEquals(-375.0, individual(1000, 0, 13000, 0, 2000, 0)
                .calculateDues(), DELTA);
        assertEquals(-543.75, household("joint", 1000, 0, 13000, 0, 2000, 0, 6000)
                .calculateDues(), DELTA);
    }
}
