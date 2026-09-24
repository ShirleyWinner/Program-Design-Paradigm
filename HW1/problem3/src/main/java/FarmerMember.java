/**
 * Represents a farmer member of the cooperative.
 */
public class FarmerMember extends IndividualMember {

    /**
     * Constructs a farmer member.
     */
    public FarmerMember(
            String memberId,
            ContactInfo contactInfo,
            Double lastYearFarmRevenue,
            Double totalDuesPaid,
            Double equipmentLoanInterestPaid,
            Double landTaxesPaid,
            Double trainingCertificationFeesPaid,
            Double cooperativeReserveFundContributions,
            Double memberHealthFundContributions,
            Double communityDonations) {

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
                communityDonations
        );
    }
}
