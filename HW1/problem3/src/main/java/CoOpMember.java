/**
 * Represents a general cooperative member.
 */
public abstract class CoOpMember {

    private String memberId;
    private ContactInfo contactInfo;

    private Double lastYearFarmRevenue;
    private Double totalDuesPaid;
    private Double equipmentLoanInterestPaid;
    private Double landTaxesPaid;
    private Double trainingCertificationFeesPaid;
    private Double cooperativeReserveFundContributions;
    private Double memberHealthFundContributions;
    private Double communityDonations;

    /**
     * Constructs a cooperative member.
     *
     * @param memberId the unique member ID
     * @param contactInfo the member's contact information
     * @param lastYearFarmRevenue last year's farm revenue
     * @param totalDuesPaid total dues already paid
     * @param equipmentLoanInterestPaid equipment loan interest paid
     * @param landTaxesPaid land taxes paid
     * @param trainingCertificationFeesPaid training and certification fees paid
     * @param cooperativeReserveFundContributions contributions to the cooperative reserve fund
     * @param memberHealthFundContributions contributions to the member health fund
     * @param communityDonations community donations and contributions
     */
    public CoOpMember(
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

        this.memberId = memberId;
        this.contactInfo = contactInfo;
        this.lastYearFarmRevenue = lastYearFarmRevenue;
        this.totalDuesPaid = totalDuesPaid;
        this.equipmentLoanInterestPaid = equipmentLoanInterestPaid;
        this.landTaxesPaid = landTaxesPaid;
        this.trainingCertificationFeesPaid =
                trainingCertificationFeesPaid;
        this.cooperativeReserveFundContributions =
                cooperativeReserveFundContributions;
        this.memberHealthFundContributions =
                memberHealthFundContributions;
        this.communityDonations = communityDonations;
    }

    public String getMemberId() {
        return memberId;
    }

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public Double getLastYearFarmRevenue() {
        return lastYearFarmRevenue;
    }

    public Double getTotalDuesPaid() {
        return totalDuesPaid;
    }

    public Double getEquipmentLoanInterestPaid() {
        return equipmentLoanInterestPaid;
    }

    public Double getLandTaxesPaid() {
        return landTaxesPaid;
    }

    public Double getTrainingCertificationFeesPaid() {
        return trainingCertificationFeesPaid;
    }

    public Double getCooperativeReserveFundContributions() {
        return cooperativeReserveFundContributions;
    }

    public Double getMemberHealthFundContributions() {
        return memberHealthFundContributions;
    }

    public Double getCommunityDonations() {
        return communityDonations;
    }

    /**
     * Calculates this member's dues after the applicable deductions.
     *
     * @return the dues amount
     */
    public abstract Double calculateDues();

    /**
     * Applies the deductions shared by individual and household members.
     * Only the reserve and health fund deduction is explicitly floored at zero
     * by the specification; subsequent deductions are subtracted as stated.
     *
     * @param reserveAndHealthDeduction the deduction for this membership type
     * @return assessable revenue after the shared deductions
     */
    protected double calculateAssessableRevenue(double reserveAndHealthDeduction) {
        double assessableRevenue = Math.max(0.0,
                lastYearFarmRevenue - totalDuesPaid - reserveAndHealthDeduction);

        if (lastYearFarmRevenue < 250000.0
                && equipmentLoanInterestPaid + landTaxesPaid > 12500.0) {
            assessableRevenue -= 2500.0;
        }
        return assessableRevenue;
    }
}
