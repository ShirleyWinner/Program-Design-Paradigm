/**
 * Represents an individual cooperative member.
 */
public abstract class IndividualMember extends CoOpMember {

    /**
     * Constructs an individual member.
     */
    public IndividualMember(
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

    /**
     * Calculates individual dues using a 70% reserve and health fund deduction.
     * An assessable revenue of exactly $55,000 uses the higher rate (19%).
     *
     * @return the dues amount
     */
    @Override
    public Double calculateDues() {
        double reserveAndHealthDeduction =
                (getCooperativeReserveFundContributions()
                        + getMemberHealthFundContributions()) * 0.7;
        double assessableRevenue = calculateAssessableRevenue(reserveAndHealthDeduction);
        double rate = assessableRevenue < 55000.0 ? 0.15 : 0.19;
        return assessableRevenue * rate;
    }
}
