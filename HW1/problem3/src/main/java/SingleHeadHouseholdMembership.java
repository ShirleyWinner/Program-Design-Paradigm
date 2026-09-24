/**
 * Represents a single-head household membership.
 */
public class SingleHeadHouseholdMembership extends HouseholdMember {

    /**
     * Constructs a single-head household membership.
     */
    public SingleHeadHouseholdMembership(
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
