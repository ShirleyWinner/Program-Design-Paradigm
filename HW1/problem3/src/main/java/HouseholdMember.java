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
}
