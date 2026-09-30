/**
 * Represents a household cooperative member.
 */
public abstract class HouseholdMember extends CoOpMember {

    private Integer numberOfDependents;
    private Integer numberOfMinorChildren;
    private Double childcareExpenses;
    private Double dependentCareExpenses;

    /**
     * Constructs a household member.
     */
    public HouseholdMember(
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
                communityDonations
        );

        this.numberOfDependents = numberOfDependents;
        this.numberOfMinorChildren = numberOfMinorChildren;
        this.childcareExpenses = childcareExpenses;
        this.dependentCareExpenses = dependentCareExpenses;
    }

    public Integer getNumberOfDependents() {
        return numberOfDependents;
    }

    public Integer getNumberOfMinorChildren() {
        return numberOfMinorChildren;
    }

    public Double getChildcareExpenses() {
        return childcareExpenses;
    }

    public Double getDependentCareExpenses() {
        return dependentCareExpenses;
    }

    /**
     * Calculates household dues with the capped fund and childcare deductions.
     * An assessable revenue of exactly $90,000 uses the higher rate (18.5%).
     *
     * @return the dues amount
     */
    @Override
    public Double calculateDues() {
        double reserveAndHealthDeduction = Math.min(17500.0,
                (getCooperativeReserveFundContributions()
                        + getMemberHealthFundContributions()) * 0.65);
        double assessableRevenue = calculateAssessableRevenue(reserveAndHealthDeduction);

        if (getLastYearFarmRevenue() < 200000.0 && childcareExpenses > 5000.0) {
            assessableRevenue -= 1250.0;
        }
        double rate = assessableRevenue < 90000.0 ? 0.145 : 0.185;
        return assessableRevenue * rate;
    }
}
