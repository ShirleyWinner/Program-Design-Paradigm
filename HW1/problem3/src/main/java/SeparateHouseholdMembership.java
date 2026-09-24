/**
 * Represents a separate household membership.
 */
public class SeparateHouseholdMembership extends HouseholdMember {

    /**
     * Constructs a separate household membership.
     */
    public SeparateHouseholdMembership(
            String memberId,
            ContactInfo contactInfo,
            Double lastYearFarmRevenue,
            Double totalDuesPaid,
            Double equipmentLoanInterestPaid,
            Double landTaxesPaid,
            Double trainingCertificationFeesPaid,
            Double cooperativeReserveFundContributions,
            Double memberHealthFundContributions,
            Double communityDonations,
            Integer numberOfDependents,
            Integer numberOfMinorChildren,
            Double childcareExpenses,
            Double dependentCareExpenses) {

        super(
                memberId,
                contactInfo,
                lastYearFarmRevenue,
                totalDuesPaid,
                equipmentLoanInterestPaid,
                landTaxesPaid,
                trainingCertificationFeesPaid,
                cooperativeReserveFundContributions,
                memberHealthFundContributions,
                communityDonations,
                numberOfDependents,
                numberOfMinorChildren,
                childcareExpenses,
                dependentCareExpenses
        );
    }
}
